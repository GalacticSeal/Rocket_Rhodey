package Level;

import Enemies.Rocket;
import Engine.Key;
import Engine.KeyLocker;
import Engine.Keybinds;
import Engine.Keyboard;
import Engine.MouseControls;
import Engine.Sound;
import GameObject.GameObject;
import GameObject.SpriteSheet;
import Utils.AirGroundState;
import Utils.Direction;
import Utils.Point;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

public abstract class Player extends GameObject {
    // new temp variables (this is going to be ugly)

    protected float terminalFactorY = 0; //acceleration factor after reaching terminal velocity on the y-axis
    protected float terminalFactorX = 0; //acceleration factor after reaching terminal velocity on the y-axis

    
    protected float groundDegrade = 0;
    protected float degradeFactor = 0;
    protected float accelFactor = 0; //acceleration multiplier
    protected float airSpeed = 0;
    protected long stunTime = 0;
    protected boolean isStunned = false;
    protected boolean isPushed = false;
    protected boolean isRocketJump = false;
    protected long fireFrame = 0;

    public static final float DEFAULT_KNOCKBACK = 10f;
    public static final float LEVEL_BOUNDS_X = 800;
    public static final long FIRE_RATE = 750;
    public static final long STUN_TIME = 1500;
    public static final float HURT_MOMENTUM_X = 0.60f;
    public static final float HURT_MOMENTUM_Y = 0.20f;

    // values that affect player movement
    // these should be set in a subclass
    protected float walkSpeed = 0;
    protected float gravity = 0;
    protected float jumpPower = 0;
    protected float velocityX = 0;
    protected float velocityY = 0;

    // values used to handle player movement
    protected float momentumY = 0;
    protected float moveAmountX, moveAmountY;
    protected float lastAmountMovedX, lastAmountMovedY;

    // values used to keep track of player's current state
    protected PlayerState playerState;
    protected PlayerState previousPlayerState;
    protected Direction facingDirection;
    protected AirGroundState airGroundState;
    protected AirGroundState previousAirGroundState;
    protected LevelState levelState;

    // classes that listen to player events can be added to this list
    protected ArrayList<PlayerListener> listeners = new ArrayList<>();

    // define keys
    protected KeyLocker keyLocker = new KeyLocker();
    protected Key JUMP_KEY = Keybinds.getJumpKey();//Key.UP;
    protected Key JUMP2_KEY = Keybinds.getJump2Key();//Key.SPACE;
    protected Key MOVE_LEFT_KEY = Keybinds.getMoveLeftKey();//Key.LEFT;
    protected Key MOVE_RIGHT_KEY = Keybinds.getMoveRightKey();//Key.RIGHT;
    protected Key CROUCH_KEY = Keybinds.getCrouchKey();//Key.DOWN;

    // flags
    protected boolean isInvincible = false; // if true, player cannot be hurt by enemies (good for testing)

    public Player(SpriteSheet spriteSheet, float x, float y, String startingAnimationName) {
        super(spriteSheet, x, y, startingAnimationName);
        facingDirection = Direction.RIGHT;
        airGroundState = AirGroundState.AIR;
        previousAirGroundState = airGroundState;
        playerState = PlayerState.STANDING;
        previousPlayerState = playerState;
        levelState = LevelState.RUNNING;
    }

    // bootleg application of momentum
    protected void applyMomentum() {
        moveAmountX += velocityX;
        moveAmountY += velocityY;
        if(!isPushed) {
            if (airGroundState == AirGroundState.AIR) {
            velocityY += gravity;
            } else {
                velocityY = 0f;
                moveAmountY = 0.5f;
                isRocketJump = false;
            }
        } else {
            isPushed = false;
        }
    }

    public void applyKnockback(MapEntity mapEntity, float power, boolean applyStun) {
        //turning mouse position from rocket spawn position into right triangle
        Point source = new Point(mapEntity.getX()-mapEntity.getWidth()/2f, mapEntity.getY()+mapEntity.getHeight()/2f);
        float distanceX = source.x-getX();
        float distanceY = source.y-getY();

        //hypotenuse of mouse position to location - used for determining movement ratios
        double distanceH = Math.sqrt(Math.pow(distanceX, 2)+Math.pow(distanceY, 2));
        double powRatio = -power/distanceH; //explosion knockback calculation

        //Apply momentum to player from explosion
        velocityX += (float) (distanceX*powRatio);
        velocityY += (float) (distanceY*powRatio);

        if(applyStun) {
            if (!isStunned)
                stunTime = System.currentTimeMillis();
            isStunned = true;
            isRocketJump = false;
        } else {
            isRocketJump = true;
        }
        isPushed = true;
        airGroundState = AirGroundState.AIR;
        playerState = PlayerState.JUMPING;
    }

    protected void applyMovementX() {
        //ground movement
        if (airGroundState == AirGroundState.GROUND) {
            if (!isStunned && Keyboard.isKeyDown(MOVE_LEFT_KEY)) {
                velocityX -= walkSpeed/accelFactor;
            } else if (!isStunned && Keyboard.isKeyDown(MOVE_RIGHT_KEY)) {
                velocityX += walkSpeed/accelFactor;
            } else {
                if(velocityX != 0f && Math.abs(velocityX) > degradeFactor) velocityX -= degradeFactor * walkSpeed * Math.abs(velocityX)/velocityX;
                else velocityX = 0f;
            }

            //fixes ground movement to maximum walk speed
            if(velocityX > walkSpeed) {
                velocityX = walkSpeed; //cannot walk faster than normal walk speed
            } else if(velocityX < -walkSpeed) {
                velocityX = -walkSpeed; //cannot walk faster than normal walk speed
            }

        } else {
            //air movement - player can only slow themselves while above max air speed, determined by walk speed 
            //or airSpeed when rocket jumping
            float moveVar;
            if(isRocketJump) moveVar = airSpeed;
            else moveVar = walkSpeed;
            if (!isStunned && Keyboard.isKeyDown(MOVE_LEFT_KEY) && velocityX > -moveVar) {
                if(velocityX >= -moveVar) {
                    velocityX -= moveVar/accelFactor;
                } else {
                    velocityX = -moveVar/accelFactor;
                }
            } else if (!isStunned && Keyboard.isKeyDown(MOVE_RIGHT_KEY) && velocityX < moveVar) {
                if(velocityX <= moveVar) {
                    velocityX += moveVar/accelFactor;
                } else {
                    velocityX = moveVar/accelFactor;
                }
            }
        }
    }

    public void update() {
        moveAmountX = 0;
        moveAmountY = 0;

        // if player is currently playing through level (has not won or lost)
        if (levelState == LevelState.RUNNING) {
            // update player's state and current actions, which includes things like determining how much it should move each frame and if its walking or jumping
            do {
                previousPlayerState = playerState;
                handlePlayerState();
            } while (previousPlayerState != playerState);

            previousAirGroundState = airGroundState;
            if (isStunned) {
                if ((System.currentTimeMillis() - stunTime) > STUN_TIME) {
                    isStunned = false;
                }
            }

            applyMovementX();
            applyMomentum();

            // move player with respect to map collisions based on how much player needs to move this frame
            lastAmountMovedX = super.moveXHandleCollision(moveAmountX);
            lastAmountMovedY = super.moveYHandleCollision(moveAmountY);
            if(System.currentTimeMillis() - fireFrame > FIRE_RATE) {
                if(MouseControls.isMousePressed() && !isStunned) {
                    // original position
                    int rocketX = Math.round(getX() + getWidth()/2.0f);
                    int rocketY = Math.round(getY() + getHeight()/2.0f);
                    // ending position
                    float rocketEndX = MouseControls.getMouseX() - map.getCamera().getX();
                    float rocketEndY = map.getCamera().getY() + MouseControls.getMouseY();
                    int rocketSpeed = 10;
                    int lifeTime = 120; 
                    // Aiming the rocket
                    Rocket rocket = new Rocket(new Point(rocketX, rocketY),  // original position
                        new Point(rocketEndX, rocketEndY), rocketSpeed, lifeTime
                        ); // ^ where we want the mouse to point
                    map.addEnemy(rocket);
                    fireFrame = System.currentTimeMillis();
                }
            }
            
            handlePlayerAnimation();

            updateLockedKeys();

            // update player's animation
            super.update();

            if(getX() < 0) {
                setX(0);
            } else if(getX() > LEVEL_BOUNDS_X-getWidth()) {
                setX(LEVEL_BOUNDS_X-getWidth());
            }
        }

        // if player has beaten level
        else if (levelState == LevelState.LEVEL_COMPLETED) {
            updateLevelCompleted();
        }

        // if player has lost level
        else if (levelState == LevelState.PLAYER_DEAD) {
            //updatePlayerDead();
        }
    }

    // based on player's current state, call appropriate player state handling method
    protected void handlePlayerState() {
        switch (playerState) {
            case STANDING:
                playerStanding();
                break;
            case WALKING:
                playerWalking();
                break;
            case CROUCHING:
                playerCrouching();
                break;
            case JUMPING:
                playerJumping();
                break;
        }
    }

    // player STANDING state logic
    protected void playerStanding() {
        // if walk left or walk right key is pressed, player enters WALKING state
        if (!isStunned && (Keyboard.isKeyDown(MOVE_LEFT_KEY) || Keyboard.isKeyDown(MOVE_RIGHT_KEY))) {
            playerState = PlayerState.WALKING;
        }

        // if jump key is pressed, player enters JUMPING state
        else if (!isStunned && Keyboard.isKeyDown(JUMP_KEY) && !keyLocker.isKeyLocked(JUMP_KEY)) {
            keyLocker.lockKey(JUMP_KEY);
            playerState = PlayerState.JUMPING;
        }
        else if (!isStunned && Keyboard.isKeyDown(JUMP2_KEY) && !keyLocker.isKeyLocked(JUMP2_KEY)) {
            keyLocker.lockKey(JUMP2_KEY);
            playerState = PlayerState.JUMPING;
        }
        // if crouch key is pressed, player enters CROUCHING state
        else if (!isStunned && Keyboard.isKeyDown(CROUCH_KEY)) {
            playerState = PlayerState.CROUCHING;
        }
    }
    //checkpoint stuff
    private Point respawnPoint;

    public void respawn(){
        if (respawnPoint != null) {
            this.setLocation(respawnPoint.x, respawnPoint.y);
        } else {
            this.setLocation(4,95);
        }
    }

    public void setRespawnPoint(Point respawnPoint) {
        this.respawnPoint = respawnPoint;
    }

    // player WALKING state logic
    protected void playerWalking() {
        // if walk left key is pressed, move player to the left
        
        if (!isStunned && Keyboard.isKeyDown(MOVE_LEFT_KEY)) {
            facingDirection = Direction.LEFT;
        }

        // if walk right key is pressed, move player to the right
        else if (!isStunned && Keyboard.isKeyDown(MOVE_RIGHT_KEY)) {
            facingDirection = Direction.RIGHT;
        } else if (!isStunned && Keyboard.isKeyUp(MOVE_LEFT_KEY) && Keyboard.isKeyUp(MOVE_RIGHT_KEY)) {
            playerState = PlayerState.STANDING;
        }

        // if jump key is pressed, player enters JUMPING state
        if (!isStunned && Keyboard.isKeyDown(JUMP_KEY) && !keyLocker.isKeyLocked(JUMP_KEY)) {
            keyLocker.lockKey(JUMP_KEY);
            playerState = PlayerState.JUMPING;
        }
        else if (!isStunned && Keyboard.isKeyDown(JUMP2_KEY) && !keyLocker.isKeyLocked(JUMP2_KEY)) {
            keyLocker.lockKey(JUMP2_KEY);
            playerState = PlayerState.JUMPING;
        }

        // if crouch key is pressed,
        else if (!isStunned && Keyboard.isKeyDown(CROUCH_KEY)) {
            playerState = PlayerState.CROUCHING;
        }
    }

    // player CROUCHING state logic
    protected void playerCrouching() {
        // if crouch key is released, player enters STANDING state
        if (!isStunned && Keyboard.isKeyUp(CROUCH_KEY)) {
            playerState = PlayerState.STANDING;
        }

        // if jump key is pressed, player enters JUMPING state
        if (!isStunned && Keyboard.isKeyDown(JUMP_KEY) && !keyLocker.isKeyLocked(JUMP_KEY)) {
            keyLocker.lockKey(JUMP_KEY);
            playerState = PlayerState.JUMPING;
        }
        else if (!isStunned && Keyboard.isKeyDown(JUMP2_KEY) && !keyLocker.isKeyLocked(JUMP2_KEY)) {
            keyLocker.lockKey(JUMP2_KEY);
            playerState = PlayerState.JUMPING;
        }
    }

    // player JUMPING state logic
    protected void playerJumping() {
        // if last frame player was on ground and this frame player is still on ground, the jump needs to be setup
        if (previousAirGroundState == AirGroundState.GROUND && airGroundState == AirGroundState.GROUND) {
            Sound.playSFX(Sound.JUMP_SOUND);
            // sets animation to a JUMP animation based on which way player is facing
            currentAnimationName = facingDirection == Direction.RIGHT ? "JUMP_RIGHT" : "JUMP_LEFT";

            // player is set to be in air and then player is sent into the air
            airGroundState = AirGroundState.AIR;
            velocityY -= jumpPower;
        }

        // if player last frame was in air and this frame is now on ground, player enters STANDING state
        else if (previousAirGroundState == AirGroundState.AIR && airGroundState == AirGroundState.GROUND) {
            playerState = PlayerState.STANDING;
        }
    }

    protected void updateLockedKeys() {
        if (Keyboard.isKeyUp(JUMP_KEY)) {
            keyLocker.unlockKey(JUMP_KEY);
        }
        if (Keyboard.isKeyUp(JUMP2_KEY)) {
            keyLocker.unlockKey(JUMP2_KEY);
        }
    }

    // anything extra the player should do based on interactions can be handled here
    protected void handlePlayerAnimation() {
        if (playerState == PlayerState.STANDING) {
            // sets animation to a STAND animation based on which way player is facing
            this.currentAnimationName = facingDirection == Direction.RIGHT ? "STAND_RIGHT" : "STAND_LEFT";

            // handles putting goggles on when standing in water
            // checks if the center of the player is currently touching a water tile
            int centerX = Math.round(getBounds().getX1()) + Math.round(getBounds().getWidth() / 2f);
            int centerY = Math.round(getBounds().getY1()) + Math.round(getBounds().getHeight() / 2f);
            MapTile currentMapTile = map.getTileByPosition(centerX, centerY);
            if (currentMapTile != null && currentMapTile.getTileType() == TileType.WATER) {
                this.currentAnimationName = facingDirection == Direction.RIGHT ? "SWIM_STAND_RIGHT" : "SWIM_STAND_LEFT";
            }
        }
        else if (playerState == PlayerState.WALKING) {
            // sets animation to a WALK animation based on which way player is facing
            this.currentAnimationName = facingDirection == Direction.RIGHT ? "WALK_RIGHT" : "WALK_LEFT";
        }
        else if (playerState == PlayerState.CROUCHING) {
            // sets animation to a CROUCH animation based on which way player is facing
            this.currentAnimationName = facingDirection == Direction.RIGHT ? "CROUCH_RIGHT" : "CROUCH_LEFT";
        }
        else if (playerState == PlayerState.JUMPING) {
            // if player is moving upwards, set player's animation to jump. if player moving downwards, set player's animation to fall
            if (lastAmountMovedY <= 0) {
                this.currentAnimationName = facingDirection == Direction.RIGHT ? "JUMP_RIGHT" : "JUMP_LEFT";
            } else {
                this.currentAnimationName = facingDirection == Direction.RIGHT ? "FALL_RIGHT" : "FALL_LEFT";
            }
        }
    }

    @Override
    public void onEndCollisionCheckX(boolean hasCollided, Direction direction, MapEntity entityCollidedWith) { }

    @Override
    public void onEndCollisionCheckY(boolean hasCollided, Direction direction, MapEntity entityCollidedWith) {
        // if player collides with a map tile below it, it is now on the ground
        // if player does not collide with a map tile below, it is in air
        if (direction == Direction.DOWN) {
            if (hasCollided) {
                momentumY = 0;
                airGroundState = AirGroundState.GROUND;
            } else {
                playerState = PlayerState.JUMPING;
                airGroundState = AirGroundState.AIR;
            }
        }

        // if player collides with map tile upwards, it means it was jumping and then hit into a ceiling -- immediately stop upwards jump velocity
        else if (direction == Direction.UP) {
            if (hasCollided) {
                velocityY = 0;
            }
        }

        if (airGroundState == AirGroundState.GROUND) {
            if (entityCollidedWith instanceof SlipperyPlatform) {
                setM(0.01);
            }
            else {
                setM(0.1);
            }
        }

        if (airGroundState == AirGroundState.GROUND) {
            if (entityCollidedWith instanceof DissapearingPlatform) {

                // Set a delay (e.g., 2 seconds) for the initial removal
        int initialRemovalDelay = 2000; // delay in milliseconds (2 seconds)
        new Timer().schedule(new TimerTask() {
            @Override
            public void run() {
                // Set platform to INACTIVE after delay
                entityCollidedWith.setMapEntityStatus(MapEntityStatus.DISSAPEARING);

                // Set a second delay (e.g., 3 seconds) to make the platform reappear
                int reappearDelay = 3000;
                new Timer().schedule(new TimerTask() {
                    @Override
                    public void run() {
                        // Code to make the platform reappear
                        entityCollidedWith.setMapEntityStatus(MapEntityStatus.ACTIVE);
                    }
                }, reappearDelay);
            }
        }, initialRemovalDelay);
        }
    }
    }


    // other entities can call this method to hurt the player
    public void hurtPlayer(MapEntity mapEntity) {
        if (!isInvincible && !isStunned) {
            velocityX *= HURT_MOMENTUM_X;
            velocityY *= HURT_MOMENTUM_Y;
            applyKnockback(mapEntity, DEFAULT_KNOCKBACK, true);

            // if map entity is an enemy, kill player on touch
            // if (mapEntity instanceof Enemy) {
            //     levelState = LevelState.PLAYER_DEAD;
            // }
        }
    }

    // other entities can call this to tell the player they beat a level
    public void completeLevel() {
        levelState = LevelState.LEVEL_COMPLETED;
    }

    // if player has beaten level, this will be the update cycle
    public void updateLevelCompleted() {
        // if player is not on ground, player should fall until it touches the ground
        if (airGroundState != AirGroundState.GROUND && map.getCamera().containsDraw(this)) {
            currentAnimationName = "FALL_RIGHT";
            super.update();
            moveYHandleCollision(moveAmountY);
        }
        // move player to the right until it walks off screen
        else if (map.getCamera().containsDraw(this)) {
            currentAnimationName = "WALK_RIGHT";
            super.update();
            moveXHandleCollision(walkSpeed);
        } else {
            // tell all player listeners that the player has finished the level
            for (PlayerListener listener : listeners) {
                listener.onLevelCompleted();
            }
        }
    }

    // if player has died, this will be the update cycle
    public void updatePlayerDead() {
        // change player animation to DEATH
        if (!currentAnimationName.startsWith("DEATH")) {
            if (facingDirection == Direction.RIGHT) {
                currentAnimationName = "DEATH_RIGHT";
            } else {
                currentAnimationName = "DEATH_LEFT";
            }
            super.update();
        }
        // if death animation not on last frame yet, continue to play out death animation
        else if (currentFrameIndex != getCurrentAnimation().length - 1) {
          super.update();
        }
        // if death animation on last frame (it is set up not to loop back to start), player should continually fall until it goes off screen
        else if (currentFrameIndex == getCurrentAnimation().length - 1) {
            if (map.getCamera().containsDraw(this)) {
                moveY(3);
            } else {
                // tell all player listeners that the player has died in the level
                for (PlayerListener listener : listeners) {
                    listener.onDeath();
                }
            }
        }
    }

    public PlayerState getPlayerState() {
        return playerState;
    }

    public void setPlayerState(PlayerState playerState) {
        this.playerState = playerState;
    }

    public AirGroundState getAirGroundState() {
        return airGroundState;
    }

    public Direction getFacingDirection() {
        return facingDirection;
    }

    public void setFacingDirection(Direction facingDirection) {
        this.facingDirection = facingDirection;
    }

    public void setLevelState(LevelState levelState) {
        this.levelState = levelState;
    }

    public void addListener(PlayerListener listener) {
        listeners.add(listener);
    }
    // Uncomment this to have game draw player's bounds to make it easier to visualize
    /*
    public void draw(GraphicsHandler graphicsHandler) {
        super.draw(graphicsHandler);
        drawBounds(graphicsHandler, new Color(255, 0, 0, 100));
    }
    */


    public void setM(double slip){
            this.degradeFactor = (float) slip;
    }
}

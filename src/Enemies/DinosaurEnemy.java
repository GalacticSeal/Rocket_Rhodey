package Enemies;

import Builders.FrameBuilder;
import Engine.ImageLoader;
import Engine.Keyboard;
import GameObject.Frame;
import GameObject.ImageEffect;
import GameObject.SpriteSheet;
import Scene.Enemy;
import Scene.Map;
import Scene.Player;
import Utils.AirGroundState;
import Utils.Direction;
import Utils.Timer;

import java.awt.*;
import java.util.HashMap;

public class DinosaurEnemy extends Enemy {
    protected Point startLocation;
    protected Point endLocation;
    protected float movementSpeed = 1f;
    protected Direction facingDirection;
    protected AirGroundState airGroundState;
    protected Timer shootTimer = new Timer();
    protected DinosaurState dinosaurState;
    protected DinosaurState previousDinosaurState;

    public DinosaurEnemy(Point startLocation, Point endLocation) {
        super(startLocation.x, startLocation.y, new SpriteSheet(ImageLoader.load("DinosaurEnemy.png"), 14, 17), "WALK_RIGHT");
        this.startLocation = startLocation;
        this.endLocation = endLocation;
        initialize();
    }

    @Override
    public void initialize() {
        super.initialize();
        dinosaurState = DinosaurState.WALK;
        previousDinosaurState = dinosaurState;
        facingDirection = Direction.RIGHT;
        currentAnimationName = "WALK_RIGHT";
        airGroundState = AirGroundState.GROUND;
        shootTimer.setWaitTime(2000);
    }

    @Override
    public void update(Keyboard keyboard, Map map, Player player) {
        int startBound = map.getPointCameraAdjusted(startLocation).x;
        int endBound = map.getPointCameraAdjusted(endLocation).x;

        if (shootTimer.isTimeUp() && dinosaurState != DinosaurState.SHOOT) {
            dinosaurState = DinosaurState.SHOOT;
        }

        if (dinosaurState == DinosaurState.WALK) {
            if (facingDirection == Direction.RIGHT) {
                currentAnimationName = "WALK_RIGHT";
                moveXHandleCollision(map, movementSpeed);
            } else {
                currentAnimationName = "WALK_LEFT";
                moveXHandleCollision(map, -movementSpeed);
            }

            if (getX1() + getScaledWidth() >= endBound) {
                int difference = endBound - (getScaledX2());
                moveXHandleCollision(map, -difference);
                facingDirection = Direction.LEFT;
            } else if (getX1() <= startBound) {
                int difference = startBound - getX1();
                moveXHandleCollision(map, difference);
                facingDirection = Direction.RIGHT;
            }

        } else if (dinosaurState == DinosaurState.SHOOT) {
            if (previousDinosaurState == DinosaurState.WALK) {
                shootTimer.setWaitTime(1000);
                currentAnimationName = facingDirection == Direction.RIGHT ? "SHOOT_RIGHT" : "SHOOT_LEFT";
            } else if (shootTimer.isTimeUp()) {
                int fireballX;
                float movementSpeed;
                if (facingDirection == Direction.RIGHT) {
                    fireballX = (int)getPureXLocation(map) + getScaledWidth();
                    movementSpeed = 1.5f;
                } else {
                    fireballX = (int)getPureXLocation(map);
                    movementSpeed = -1.5f;
                }
                int fireballY = (int)getPureYLocation(map) + 4;
                Fireball fireball = new Fireball(new Point(fireballX, fireballY), movementSpeed, 1000);
                map.getEnemies().add(fireball);
                dinosaurState = DinosaurState.WALK;
                shootTimer.setWaitTime(2000);
            }
        }
        super.update(keyboard, map, player);
        previousDinosaurState = dinosaurState;
    }

    @Override
    public void onEndCollisionCheckX(boolean hasCollided, Direction direction) {
        if (hasCollided) {
            if (direction == Direction.RIGHT) {
                facingDirection = Direction.LEFT;
                currentAnimationName = "WALK_LEFT";
            } else {
                facingDirection = Direction.RIGHT;
                currentAnimationName = "WALK_RIGHT";
            }
        }
    }

    @Override
    public HashMap<String, Frame[]> getAnimations(SpriteSheet spriteSheet) {
        return new HashMap<String, Frame[]>() {{
            put("WALK_LEFT", new Frame[]{
                    new FrameBuilder(spriteSheet.getSprite(0, 0), 200)
                            .withScale(3)
                            .withBounds(4, 2, 5, 13)
                            .build(),
                    new FrameBuilder(spriteSheet.getSprite(0, 1), 200)
                            .withScale(3)
                            .withBounds(4, 2, 5, 13)
                            .build()
            });
            put("WALK_RIGHT", new Frame[]{
                    new FrameBuilder(spriteSheet.getSprite(0, 0), 200)
                            .withScale(3)
                            .withImageEffect(ImageEffect.FLIP_HORIZONTAL)
                            .withBounds(4, 2, 5, 13)
                            .build(),
                    new FrameBuilder(spriteSheet.getSprite(0, 1), 200)
                            .withScale(3)
                            .withImageEffect(ImageEffect.FLIP_HORIZONTAL)
                            .withBounds(4, 2, 5, 13)
                            .build()
            });
            put("SHOOT_LEFT", new Frame[]{
                    new FrameBuilder(spriteSheet.getSprite(1, 0), 0)
                            .withScale(3)
                            .withBounds(4, 2, 5, 13)
                            .build(),
            });
            put("SHOOT_RIGHT", new Frame[]{
                    new FrameBuilder(spriteSheet.getSprite(1, 0), 0)
                            .withScale(3)
                            .withImageEffect(ImageEffect.FLIP_HORIZONTAL)
                            .withBounds(4, 2, 5, 13)
                            .build(),
            });
        }};
    }

    public enum DinosaurState {
        WALK, SHOOT
    }
}
package Enemies;

import Builders.FrameBuilder;
import Engine.ImageLoader;
import Engine.Sound;
import GameObject.Frame;
import GameObject.SpriteSheet;
import Level.Enemy;
import Level.MapEntity;
import Level.MapEntityStatus;
import Level.Player;
import Utils.Direction;
import Utils.Point;
import java.util.HashMap;

// This class is for the fireball enemy that the DinosaurEnemy class shoots out
// it will travel in a straight line (x axis) for a set time before disappearing
// it will disappear early if it collides with a solid map tile
public class Rocket extends Enemy {
    private int existenceFrames;
    private float ratioX;
    private float ratioY;
    protected static final int ROCKET_WIDTH = 7;
    protected static final int ROCKET_HEIGHT = 7;

    public Rocket(Point location, Point mouseTarget, float movementSpeed, int existenceFrames) {
        super(location.x-ROCKET_WIDTH, location.y-ROCKET_HEIGHT, new SpriteSheet(ImageLoader.load("Fireball.png"), ROCKET_WIDTH, ROCKET_HEIGHT), "DEFAULT");

        //turning mouse position from rocket spawn position into right triangle
        float mouseXT = mouseTarget.x-(location.x-ROCKET_WIDTH/2f);
        float mouseYT = mouseTarget.y-(location.y+ROCKET_HEIGHT/2f);
        Sound.playSFX(Sound.RPG_SOUND);

        //hypotenuse of mouse position to location - used for determining movement ratios
        double mouseHyp = Math.sqrt(Math.pow(mouseXT, 2f)+Math.pow(mouseYT, 2f));
        double ratioT = movementSpeed/mouseHyp; //triangle ratio calculation
        ratioX = (float) (mouseXT*ratioT);
        ratioY = (float) (mouseYT*ratioT);

        // how long the rocket will exist for before disappearing
        this.existenceFrames = existenceFrames;

        initialize();
    }

    @Override
    public void update(Player player) {
        // if timer is up, set map entity status to REMOVED
        // the camera class will see this next frame and remove it permanently from the map
        if (existenceFrames == 0) {
            this.mapEntityStatus = MapEntityStatus.REMOVED;
        } else {
            // move rocket based on x/y ratios
            moveXHandleCollision(ratioX);
            moveYHandleCollision(ratioY);
            super.update(player);
        }
        existenceFrames--;
    }

    @Override
    public void onEndCollisionCheckX(boolean hasCollided, Direction direction, MapEntity entityCollidedWith) {
        // if fireball collides with anything solid on the x axis, it is removed
        if (hasCollided) {
            createExplosion();
            this.mapEntityStatus = MapEntityStatus.REMOVED;
        }
    }
    @Override
    public void onEndCollisionCheckY(boolean hasCollided, Direction direction, MapEntity entityCollidedWith) {
        // if fireball collides with anything solid on the y axis, it is removed
        if (hasCollided) {
            createExplosion();
            this.mapEntityStatus = MapEntityStatus.REMOVED;
        }
    }

    private void createExplosion() {
        //add code for creating explosion
        // create Fireball enemy
        Explosion explode = new Explosion(new Point(getX(), getY()), 15);

        //add fireball enemy to the map for it to spawn in the level
        map.addEnemy(explode);
    }

    @Override
    public void touchedPlayer(Player player) {
        //No interaction with player - leftover command from Enemy.java
    }

    @Override
    public HashMap<String, Frame[]> loadAnimations(SpriteSheet spriteSheet) {
        return new HashMap<String, Frame[]>() {{
            put("DEFAULT", new Frame[]{
                    new FrameBuilder(spriteSheet.getSprite(0, 0))
                            .withScale(5)
                            .withBounds(1, 1, 5, 5)
                            .build()
            });
        }};
    }
}

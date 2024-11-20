package Enemies;

import Builders.FrameBuilder;
import Engine.ImageLoader;
import GameObject.Frame;
import GameObject.SpriteSheet;
import Level.Enemy;
import Level.MapEntityStatus;
import Level.Player;
import Utils.Point;
import java.util.HashMap;

// This class is for the fireball enemy that the DinosaurEnemy class shoots out
// it will travel in a straight line (x axis) for a set time before disappearing
// it will disappear early if it collides with a solid map tile
public class Explosion extends Enemy {
    private int existenceFrames;
    private boolean pushedPlayer;
    private float knockPower = 11.20f;
    protected static final int BOOM_WIDTH = 40;
    protected static final int BOOM_HEIGHT = 40;

    public Explosion(Point location, int existenceFrames) {
        super(location.x-(BOOM_WIDTH-Rocket.ROCKET_WIDTH)/2f, location.y+(-BOOM_HEIGHT+Rocket.ROCKET_HEIGHT)/2f, new SpriteSheet(ImageLoader.load("Rocket.png"), 40, 40), "SMOKE");

        // how long the explosion will exist for before disappearing
        this.existenceFrames = existenceFrames;
        pushedPlayer = false;

        initialize();
    }

    @Override
    public void update(Player player) {
        // if timer is up, set map entity status to REMOVED
        // the camera class will see this next frame and remove it permanently from the map
        if (existenceFrames == 0) {
            this.mapEntityStatus = MapEntityStatus.REMOVED;
        } else {
            super.update(player);
        }
        existenceFrames--;
    }

    @Override
    public void touchedPlayer(Player player) {
        // if explosion touches player, it pushes the player
        if(!pushedPlayer) {
            player.applyKnockback(this, knockPower, false);
            
        }
        pushedPlayer = true;
    }

    @Override
    public HashMap<String, Frame[]> loadAnimations(SpriteSheet spriteSheet) {
        return new HashMap<String, Frame[]>() {{
            put("SMOKE", new Frame[] {
                new FrameBuilder(spriteSheet.getSprite(0, 1), 14)
                        .withScale(2)
                        .withBounds(1, 1, 20, 27)
                        .build(),
                new FrameBuilder(spriteSheet.getSprite(0, 3), 28)
                        .withScale(2)
                        .withBounds(1, 1, 20, 27)
                        .build(),
                new FrameBuilder(spriteSheet.getSprite(0, 4), 48)
                        .withScale(2)
                        .withBounds(1, 1, 20, 27)
                        .build(),
            });
        }};
    }
}

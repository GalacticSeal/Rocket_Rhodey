package Level;

import GameObject.Frame;
import Utils.Point;


// Checkpoint tile that sets the respawn point for the player
public class CheckpointTile extends EnhancedMapTile {

    public CheckpointTile(float x,float y, Frame frame) {
        super(x, y, frame, TileType.PASSABLE);
    }

    @Override
    public void update(Player Cat) {
        super.update(Cat);
        // check if the player touches checkpoint
        if (this.intersects(Cat)) {
            // set the player's respawn point to this tile's location
            Cat.setRespawnPoint(new Point(getX(), getY()));        } 
    }
}



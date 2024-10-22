package Level;

import GameObject.Frame;


// Checkpoint tile that sets the respawn point for the player
public class CheckpointTile extends EnhancedMapTile {
    private boolean isActivated = false;

    public CheckpointTile(float x,float y, Frame frame) {
        super(x, y, frame, TileType.PASSABLE);
    }

    @Override
    public void update(Player Cat) {
        // check if the player touches checkpoint
        if (Cat.getBounds().intersects(this.getBounds()) && !isActivated) {
            isActivated = true;
            // set the player's respawn point to this tile's location
            // Cat.setRespawnPoint(new Point(getX(), getY()));        } //GIT STASH APPLY -ONLY FOR JASON
    }

    // @Override
    // public void draw(GraphicsHandler graphicsHandler){
    //     if(!isActivated){
    //         graphicsHandler.drawFilledRectangle((int) getX(), (int) getY(), getWidth(), getHeight(), null);  // Visual feedback (optional)
    //     } else {
    //         graphicsHandler.drawFilledRectangle((int) getX(), (int) getY(), getWidth(), getHeight(), null);  // Visual feedback (optional)
    //     }
    //     super.draw(graphicsHandler);  // Draw the checkpoint
    // }
}
}


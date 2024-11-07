package Level;
import GameObject.Frame;
import Utils.AirGroundState;

public class SlipperyPlatform extends EnhancedMapTile {

    private int slip = 25;
    private int def = 2;

    public SlipperyPlatform(float x, float y, Frame frame){
        super(x, y, frame, TileType.NOT_PASSABLE);
        
    }

    public void update(Player cat) {

        super.update(cat);

        // if (touching(cat) && (cat.getBounds().getY2() + 1) == getBounds().getY1() && cat.getAirGroundState() == AirGroundState.GROUND) {
        //    cat.setM(slip);
        // } 
}
}
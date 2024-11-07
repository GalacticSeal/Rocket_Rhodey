package Level;
import GameObject.Frame;

public class DissapearingPlatform extends EnhancedMapTile {

    



    public DissapearingPlatform(float x, float y, Frame frame){
        super(x, y, frame, TileType.NOT_PASSABLE);
        
        
    }

    public void update(Player cat) {

        super.update(cat);

        
}


// public int getDissapearingX(int x){
//     return x;

// }

// public int getDissapearingY(int y){
//     return y;

// }


}


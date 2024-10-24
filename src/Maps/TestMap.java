package Maps;

import Enemies.BugEnemy;
import Enemies.DinosaurEnemy;
import Engine.ImageLoader;
import GameObject.Frame;
import Level.*;
import Tilesets.CommonTileset;
import Utils.Direction;
import java.util.ArrayList;


// Represents a test map to be used in a level
public class TestMap extends Map {

    public TestMap() {
        super("test_map.txt", new CommonTileset());
        this.playerStartPosition = getMapTile(4, 95).getLocation();
    }
    
    @Override
    public ArrayList<Enemy> loadEnemies() {
        ArrayList<Enemy> enemies = new ArrayList<>();

        BugEnemy bugEnemy = new BugEnemy(getMapTile(18, 99).getLocation().subtractY(25), Direction.RIGHT);
        enemies.add(bugEnemy);

        DinosaurEnemy dinosaurEnemy = new DinosaurEnemy(getMapTile(7, 97).getLocation().addY(2), getMapTile(8, 97).getLocation().addY(2), Direction.RIGHT);
        enemies.add(dinosaurEnemy);

        return enemies;
    }
    @Override
    public ArrayList<EnhancedMapTile> loadEnhancedMapTiles() { 
        ArrayList<EnhancedMapTile> enhancedMapTiles = new ArrayList<>();
        CheckpointTile checkpointTile = new CheckpointTile(getMapTile(8, 95).getLocation().x, getMapTile(5,94 ).getLocation().y, new Frame(ImageLoader.load("Checkpoint.png")));
        enhancedMapTiles.add(checkpointTile);

        return enhancedMapTiles;
    }


    // @Override
    // public ArrayList<EnhancedMapTile> loadEnhancedMapTiles() {
    //     ArrayList<EnhancedMapTile> enhancedMapTiles = new ArrayList<>();

        // HorizontalMovingPlatform hmp = new HorizontalMovingPlatform(
        //         ImageLoader.load("GreenPlatform.png"),
        //         getMapTile(4, 6).getLocation(),
        //         getMapTile(7, 6).getLocation(),
        //         TileType.JUMP_THROUGH_PLATFORM,
        //         3,
        //         new Rectangle(0, 6,10,4),
        //         Direction.RIGHT
        // );
        // enhancedMapTiles.add(hmp);

        // EndLevelBox endLevelBox = new EndLevelBox(getMapTile(2, 7).getLocation());
        // enhancedMapTiles.add(endLevelBox);

    //     return enhancedMapTiles;
    // }

    // @Override
    // public ArrayList<NPC> loadNPCs() {
    //     ArrayList<NPC> npcs = new ArrayList<>();

    //     // Walrus walrus = new Walrus(getMapTile(3, 10).getLocation().subtractY(3));
    //     // npcs.add(walrus);

    //     return npcs;
    // }
}

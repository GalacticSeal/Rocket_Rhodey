package Maps;

import Enemies.BugEnemy;
import Enemies.DinosaurEnemy;
import Enemies.FireFrog;
import Enemies.FlyEnemy;
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
        this.playerStartPosition = getMapTile(4, 245).getLocation();
    }
    
    @Override
    public ArrayList<Enemy> loadEnemies() {
        ArrayList<Enemy> enemies = new ArrayList<>();

        BugEnemy bugEnemy = new BugEnemy(getMapTile(10, 79).getLocation().subtractY(25), Direction.RIGHT);
        enemies.add(bugEnemy);

        DinosaurEnemy dinosaurEnemy = new DinosaurEnemy(getMapTile(8, 25).getLocation().addY(2), getMapTile(12, 150).getLocation().addY(2), Direction.RIGHT);
        enemies.add(dinosaurEnemy);

        FlyEnemy FlyEnemy = new FlyEnemy(getMapTile(16, 213).getLocation().addY(2), getMapTile(19, 150).getLocation().addY(2), Direction.RIGHT);
        enemies.add(FlyEnemy);

        FlyEnemy FlyEnemy2 = new FlyEnemy(getMapTile(4, 169).getLocation().addY(2), getMapTile(6, 125).getLocation().addY(2), Direction.RIGHT);
        enemies.add(FlyEnemy2);

        FireFrog FireFrog = new FireFrog(getMapTile(18, 110).getLocation().addY(2), getMapTile(18, 150).getLocation().addY(2), Direction.RIGHT);
        enemies.add(FireFrog);



        return enemies;
    }
    @Override
    public ArrayList<EnhancedMapTile> loadEnhancedMapTiles() { 
        ArrayList<EnhancedMapTile> enhancedMapTiles = new ArrayList<>();
        CheckpointTile checkpointTile = new CheckpointTile(getMapTile(8, 245).getLocation().x, getMapTile(5,244 ).getLocation().y, new Frame(ImageLoader.load("Checkpoint.png")));
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

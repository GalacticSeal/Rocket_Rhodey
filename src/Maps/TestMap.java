package Maps;

import Enemies.BugEnemy;
import Enemies.DinosaurEnemy;
import Enemies.FireFrog;
import Enemies.FlyEnemy;
import Engine.ImageLoader;
import EnhancedMapTiles.EndLevelBox;
import EnhancedMapTiles.HorizontalMovingPlatform;
import GameObject.Frame;
import GameObject.Rectangle;
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
        CheckpointTile checkpointTile = new CheckpointTile(getMapTile(8, 248).getLocation().x, getMapTile(5,247 ).getLocation().y, new Frame(ImageLoader.load("Checkpoint.png")));
        enhancedMapTiles.add(checkpointTile);

        CheckpointTile checkpointTile2 = new CheckpointTile(getMapTile(5, 114).getLocation().x, getMapTile(5,114 ).getLocation().y, new Frame(ImageLoader.load("Checkpoint.png")));
        enhancedMapTiles.add(checkpointTile2);

        CheckpointTile checkpointTile3 = new CheckpointTile(getMapTile(14, 40).getLocation().x, getMapTile(14,49 ).getLocation().y, new Frame(ImageLoader.load("Checkpoint.png")));
        enhancedMapTiles.add(checkpointTile3);

        SlipperyPlatform slipTile4 = new SlipperyPlatform(getMapTile(18, 245).getLocation().x, getMapTile(18,244 ).getLocation().y, new Frame(ImageLoader.load("iceTile.png")));
        enhancedMapTiles.add(slipTile4);

        SlipperyPlatform slipTile3 = new SlipperyPlatform(getMapTile(17, 245).getLocation().x, getMapTile(17,244 ).getLocation().y, new Frame(ImageLoader.load("iceTile.png")));
        enhancedMapTiles.add(slipTile3);

        SlipperyPlatform slipTile2 = new SlipperyPlatform(getMapTile(16, 245).getLocation().x, getMapTile(16,244 ).getLocation().y, new Frame(ImageLoader.load("iceTile.png")));
        enhancedMapTiles.add(slipTile2);

        SlipperyPlatform slipTile = new SlipperyPlatform(getMapTile(15, 245).getLocation().x, getMapTile(15,244 ).getLocation().y, new Frame(ImageLoader.load("iceTile.png")));
        enhancedMapTiles.add(slipTile);

        DissapearingPlatform dissapearingTile = new DissapearingPlatform(getMapTile(2, 245).getLocation().x, getMapTile(2,244 ).getLocation().y, new Frame(ImageLoader.load("TestTileSet.png")));
        enhancedMapTiles.add(dissapearingTile);

        DissapearingPlatform dissapearingTile2 = new DissapearingPlatform(getMapTile(4, 245).getLocation().x, getMapTile(4,244 ).getLocation().y, new Frame(ImageLoader.load("TestTileSet.png")));
        enhancedMapTiles.add(dissapearingTile2);

        EndLevelBox endLevelBox = new EndLevelBox(getMapTile(12, 0).getLocation());
        enhancedMapTiles.add(endLevelBox);

        HorizontalMovingPlatform hmp = new HorizontalMovingPlatform(
                ImageLoader.load("TestTileSet2.png"),
                getMapTile(4, 242).getLocation(),
                getMapTile(14, 242).getLocation(),
                TileType.NOT_PASSABLE,
                1,
                new Rectangle(0, 6,60,30),
                Direction.RIGHT
        );
        enhancedMapTiles.add(hmp);

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

       
    // }

    // @Override
    // public ArrayList<NPC> loadNPCs() {
    //     ArrayList<NPC> npcs = new ArrayList<>();

    //     // Walrus walrus = new Walrus(getMapTile(3, 10).getLocation().subtractY(3));
    //     // npcs.add(walrus);

    //     return npcs;
    // }
}

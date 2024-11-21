package Maps;

import Enemies.BugEnemy;
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
        this.playerStartPosition = getMapTile(12, 395).getLocation();
    }
    
    @Override
    public ArrayList<Enemy> loadEnemies() {
        ArrayList<Enemy> enemies = new ArrayList<>();

        BugEnemy bugEnemy = new BugEnemy(getMapTile(10, 395).getLocation().subtractY(25), Direction.RIGHT);
        enemies.add(bugEnemy);

        // DinosaurEnemy dinosaurEnemy = new DinosaurEnemy(getMapTile(8, 25).getLocation().addY(2), getMapTile(12, 150).getLocation().addY(2), Direction.RIGHT);
        // enemies.add(dinosaurEnemy);

        // FlyEnemy FlyEnemy = new FlyEnemy(getMapTile(16, 213).getLocation().addY(2), getMapTile(19, 150).getLocation().addY(2), Direction.RIGHT);
        // enemies.add(FlyEnemy);

        // FlyEnemy FlyEnemy2 = new FlyEnemy(getMapTile(4, 169).getLocation().addY(2), getMapTile(6, 125).getLocation().addY(2), Direction.RIGHT);
        // enemies.add(FlyEnemy2);

        // FireFrog FireFrog = new FireFrog(getMapTile(18, 110).getLocation().addY(2), getMapTile(18, 150).getLocation().addY(2), Direction.RIGHT);
        // enemies.add(FireFrog);



        return enemies;
    }
    @Override
    public ArrayList<EnhancedMapTile> loadEnhancedMapTiles() { 
        ArrayList<EnhancedMapTile> enhancedMapTiles = new ArrayList<>();
        // CheckpointTile checkpointTile = new CheckpointTile(getMapTile(8, 248).getLocation().x, getMapTile(5,247 ).getLocation().y, new Frame(ImageLoader.load("Checkpoint.png")));
        // enhancedMapTiles.add(checkpointTile);

        // CheckpointTile checkpointTile2 = new CheckpointTile(getMapTile(5, 114).getLocation().x, getMapTile(5,114 ).getLocation().y, new Frame(ImageLoader.load("Checkpoint.png")));
        // enhancedMapTiles.add(checkpointTile2);

        // CheckpointTile checkpointTile3 = new CheckpointTile(getMapTile(14, 40).getLocation().x, getMapTile(14,49 ).getLocation().y, new Frame(ImageLoader.load("Checkpoint.png")));
        // enhancedMapTiles.add(checkpointTile3);

        // SlipperyPlatform slipTile4 = new SlipperyPlatform(getMapTile(18, 245).getLocation().x, getMapTile(18,244 ).getLocation().y, new Frame(ImageLoader.load("iceTile.png")));
        // enhancedMapTiles.add(slipTile4);

        // SlipperyPlatform slipTile3 = new SlipperyPlatform(getMapTile(17, 245).getLocation().x, getMapTile(17,244 ).getLocation().y, new Frame(ImageLoader.load("iceTile.png")));
        // enhancedMapTiles.add(slipTile3);

        // SlipperyPlatform slipTile2 = new SlipperyPlatform(getMapTile(16, 245).getLocation().x, getMapTile(16,244 ).getLocation().y, new Frame(ImageLoader.load("iceTile.png")));
        // enhancedMapTiles.add(slipTile2);

        // SlipperyPlatform slipTile = new SlipperyPlatform(getMapTile(15, 245).getLocation().x, getMapTile(15,244 ).getLocation().y, new Frame(ImageLoader.load("iceTile.png")));
        // enhancedMapTiles.add(slipTile);

        DissapearingPlatform dissapearingTile = new DissapearingPlatform(getMapTile(4, 195).getLocation().x, getMapTile(4,196).getLocation().y, new Frame(ImageLoader.load("TestTileSet.png")));
        enhancedMapTiles.add(dissapearingTile);

        DissapearingPlatform dissapearingTile2 = new DissapearingPlatform(getMapTile(8, 191).getLocation().x, getMapTile(8,192).getLocation().y, new Frame(ImageLoader.load("TestTileSet.png")));
        enhancedMapTiles.add(dissapearingTile2);

        DissapearingPlatform dissapearingTile3 = new DissapearingPlatform(getMapTile(15, 193).getLocation().x, getMapTile(15,194).getLocation().y, new Frame(ImageLoader.load("TestTileSet.png")));
        enhancedMapTiles.add(dissapearingTile3);

        DissapearingPlatform dissapearingTile4 = new DissapearingPlatform(getMapTile(10, 184).getLocation().x, getMapTile(10,185).getLocation().y, new Frame(ImageLoader.load("TestTileSet.png")));
        enhancedMapTiles.add(dissapearingTile4);

        DissapearingPlatform dissapearingTile5 = new DissapearingPlatform(getMapTile(16, 184).getLocation().x, getMapTile(16,185).getLocation().y, new Frame(ImageLoader.load("TestTileSet.png")));
        enhancedMapTiles.add(dissapearingTile5);

        DissapearingPlatform dissapearingTile6 = new DissapearingPlatform(getMapTile(20, 190).getLocation().x, getMapTile(20,191).getLocation().y, new Frame(ImageLoader.load("TestTileSet.png")));
        enhancedMapTiles.add(dissapearingTile6);

        DissapearingPlatform dissapearingTile7 = new DissapearingPlatform(getMapTile(13, 170).getLocation().x, getMapTile(13,171).getLocation().y, new Frame(ImageLoader.load("TestTileSet.png")));
        enhancedMapTiles.add(dissapearingTile7);

        DissapearingPlatform dissapearingTile8 = new DissapearingPlatform(getMapTile(9, 165).getLocation().x, getMapTile(9,166).getLocation().y, new Frame(ImageLoader.load("TestTileSet.png")));
        enhancedMapTiles.add(dissapearingTile8);

        DissapearingPlatform dissapearingTile9 = new DissapearingPlatform(getMapTile(5, 160).getLocation().x, getMapTile(5,161).getLocation().y, new Frame(ImageLoader.load("TestTileSet.png")));
        enhancedMapTiles.add(dissapearingTile9);

        DissapearingPlatform dissapearingTile10 = new DissapearingPlatform(getMapTile(1, 155).getLocation().x, getMapTile(1,156).getLocation().y, new Frame(ImageLoader.load("TestTileSet.png")));
        enhancedMapTiles.add(dissapearingTile10);

        DissapearingPlatform dissapearingTile11 = new DissapearingPlatform(getMapTile(12, 146).getLocation().x, getMapTile(12,147).getLocation().y, new Frame(ImageLoader.load("TestTileSet.png")));
        enhancedMapTiles.add(dissapearingTile11);

        DissapearingPlatform dissapearingTile12 = new DissapearingPlatform(getMapTile(13, 146).getLocation().x, getMapTile(13,147).getLocation().y, new Frame(ImageLoader.load("TestTileSet.png")));
        enhancedMapTiles.add(dissapearingTile12);

        DissapearingPlatform dissapearingTile13 = new DissapearingPlatform(getMapTile(14, 146).getLocation().x, getMapTile(14,147).getLocation().y, new Frame(ImageLoader.load("TestTileSet.png")));
        enhancedMapTiles.add(dissapearingTile13);

        DissapearingPlatform dissapearingTile14 = new DissapearingPlatform(getMapTile(15, 146).getLocation().x, getMapTile(15,147).getLocation().y, new Frame(ImageLoader.load("TestTileSet.png")));
        enhancedMapTiles.add(dissapearingTile14);

        DissapearingPlatform dissapearingTile15 = new DissapearingPlatform(getMapTile(16, 146).getLocation().x, getMapTile(16,147).getLocation().y, new Frame(ImageLoader.load("TestTileSet.png")));
        enhancedMapTiles.add(dissapearingTile15);

        DissapearingPlatform dissapearingTile16 = new DissapearingPlatform(getMapTile(10, 141).getLocation().x, getMapTile(10,142).getLocation().y, new Frame(ImageLoader.load("TestTileSet.png")));
        enhancedMapTiles.add(dissapearingTile16);

        DissapearingPlatform dissapearingTile17 = new DissapearingPlatform(getMapTile(0, 137).getLocation().x, getMapTile(0,138).getLocation().y, new Frame(ImageLoader.load("TestTileSet.png")));
        enhancedMapTiles.add(dissapearingTile17);

        DissapearingPlatform dissapearingTile18 = new DissapearingPlatform(getMapTile(1, 137).getLocation().x, getMapTile(1,138).getLocation().y, new Frame(ImageLoader.load("TestTileSet.png")));
        enhancedMapTiles.add(dissapearingTile18);

        DissapearingPlatform dissapearingTile19 = new DissapearingPlatform(getMapTile(2, 137).getLocation().x, getMapTile(2,138).getLocation().y, new Frame(ImageLoader.load("TestTileSet.png")));
        enhancedMapTiles.add(dissapearingTile19);

        DissapearingPlatform dissapearingTile20 = new DissapearingPlatform(getMapTile(3, 137).getLocation().x, getMapTile(3,138).getLocation().y, new Frame(ImageLoader.load("TestTileSet.png")));
        enhancedMapTiles.add(dissapearingTile20);

        DissapearingPlatform dissapearingTile21 = new DissapearingPlatform(getMapTile(4, 137).getLocation().x, getMapTile(4,138).getLocation().y, new Frame(ImageLoader.load("TestTileSet.png")));
        enhancedMapTiles.add(dissapearingTile21);

        DissapearingPlatform dissapearingTile22 = new DissapearingPlatform(getMapTile(5, 137).getLocation().x, getMapTile(5,138).getLocation().y, new Frame(ImageLoader.load("TestTileSet.png")));
        enhancedMapTiles.add(dissapearingTile22);

        DissapearingPlatform dissapearingTile23 = new DissapearingPlatform(getMapTile(22, 140).getLocation().x, getMapTile(22,141).getLocation().y, new Frame(ImageLoader.load("TestTileSet.png")));
        enhancedMapTiles.add(dissapearingTile23);

        DissapearingPlatform dissapearingTile24 = new DissapearingPlatform(getMapTile(23, 140).getLocation().x, getMapTile(23,141).getLocation().y, new Frame(ImageLoader.load("TestTileSet.png")));
        enhancedMapTiles.add(dissapearingTile24);

        DissapearingPlatform dissapearingTile25 = new DissapearingPlatform(getMapTile(24, 140).getLocation().x, getMapTile(24,141).getLocation().y, new Frame(ImageLoader.load("TestTileSet.png")));
        enhancedMapTiles.add(dissapearingTile25);

        DissapearingPlatform dissapearingTile26 = new DissapearingPlatform(getMapTile(20, 134).getLocation().x, getMapTile(20,135).getLocation().y, new Frame(ImageLoader.load("TestTileSet.png")));
        enhancedMapTiles.add(dissapearingTile26);

        DissapearingPlatform dissapearingTile27 = new DissapearingPlatform(getMapTile(12, 130).getLocation().x, getMapTile(12,131).getLocation().y, new Frame(ImageLoader.load("TestTileSet.png")));
        enhancedMapTiles.add(dissapearingTile27);

        DissapearingPlatform dissapearingTile28 = new DissapearingPlatform(getMapTile(23, 126).getLocation().x, getMapTile(23,127).getLocation().y, new Frame(ImageLoader.load("TestTileSet.png")));
        enhancedMapTiles.add(dissapearingTile28);

        DissapearingPlatform dissapearingTile29 = new DissapearingPlatform(getMapTile(24, 126).getLocation().x, getMapTile(24,127).getLocation().y, new Frame(ImageLoader.load("TestTileSet.png")));
        enhancedMapTiles.add(dissapearingTile29);

        DissapearingPlatform dissapearingTile30 = new DissapearingPlatform(getMapTile(7, 123).getLocation().x, getMapTile(7,124).getLocation().y, new Frame(ImageLoader.load("TestTileSet.png")));
        enhancedMapTiles.add(dissapearingTile30);

        DissapearingPlatform dissapearingTile31 = new DissapearingPlatform(getMapTile(2, 116).getLocation().x, getMapTile(7,117).getLocation().y, new Frame(ImageLoader.load("TestTileSet.png")));
        enhancedMapTiles.add(dissapearingTile31);

        DissapearingPlatform dissapearingTile32 = new DissapearingPlatform(getMapTile(3, 116).getLocation().x, getMapTile(7,117).getLocation().y, new Frame(ImageLoader.load("TestTileSet.png")));
        enhancedMapTiles.add(dissapearingTile32);

        DissapearingPlatform dissapearingTile33 = new DissapearingPlatform(getMapTile(4, 116).getLocation().x, getMapTile(7,117).getLocation().y, new Frame(ImageLoader.load("TestTileSet.png")));
        enhancedMapTiles.add(dissapearingTile33);

        DissapearingPlatform dissapearingTile34 = new DissapearingPlatform(getMapTile(17, 120).getLocation().x, getMapTile(17,121).getLocation().y, new Frame(ImageLoader.load("TestTileSet.png")));
        enhancedMapTiles.add(dissapearingTile34);

        DissapearingPlatform dissapearingTile35 = new DissapearingPlatform(getMapTile(18, 120).getLocation().x, getMapTile(18,121).getLocation().y, new Frame(ImageLoader.load("TestTileSet.png")));
        enhancedMapTiles.add(dissapearingTile35);

        DissapearingPlatform dissapearingTile36 = new DissapearingPlatform(getMapTile(19, 120).getLocation().x, getMapTile(19,121).getLocation().y, new Frame(ImageLoader.load("TestTileSet.png")));
        enhancedMapTiles.add(dissapearingTile36);

        DissapearingPlatform dissapearingTile37 = new DissapearingPlatform(getMapTile(18, 114).getLocation().x, getMapTile(18,115).getLocation().y, new Frame(ImageLoader.load("TestTileSet.png")));
        enhancedMapTiles.add(dissapearingTile37);

        DissapearingPlatform dissapearingTile38 = new DissapearingPlatform(getMapTile(18, 107).getLocation().x, getMapTile(18,108).getLocation().y, new Frame(ImageLoader.load("TestTileSet.png")));
        enhancedMapTiles.add(dissapearingTile38);

        DissapearingPlatform dissapearingTile39 = new DissapearingPlatform(getMapTile(23, 103).getLocation().x, getMapTile(23,104).getLocation().y, new Frame(ImageLoader.load("TestTileSet.png")));
        enhancedMapTiles.add(dissapearingTile39);
        
        

        EndLevelBox endLevelBox = new EndLevelBox(getMapTile(12, 0).getLocation());
        enhancedMapTiles.add(endLevelBox);

        HorizontalMovingPlatform hmp = new HorizontalMovingPlatform(
                ImageLoader.load("lushHMP.png"),
                getMapTile(8, 295).getLocation(),
                getMapTile(16, 295).getLocation(),
                TileType.NOT_PASSABLE,
                1,
                new Rectangle(0, 0,96,32),
                Direction.LEFT
        );
        enhancedMapTiles.add(hmp);

        HorizontalMovingPlatform hmp2 = new HorizontalMovingPlatform(
                ImageLoader.load("lushHMP.png"),
                getMapTile(9, 270).getLocation(),
                getMapTile(16, 270).getLocation(),
                TileType.NOT_PASSABLE,
                1,
                new Rectangle(0, 0,96,32),
                Direction.RIGHT
        );
        enhancedMapTiles.add(hmp2);

        HorizontalMovingPlatform hmp3 = new HorizontalMovingPlatform(
                ImageLoader.load("lushHMP.png"),
                getMapTile(14, 262).getLocation(),
                getMapTile(24, 262).getLocation(),
                TileType.NOT_PASSABLE,
                1,
                new Rectangle(0, 0,96,32),
                Direction.RIGHT
        );
        enhancedMapTiles.add(hmp3);

        HorizontalMovingPlatform hmp4 = new HorizontalMovingPlatform(
                ImageLoader.load("lushHMP.png"),
                getMapTile(4, 228).getLocation(),
                getMapTile(12, 228).getLocation(),
                TileType.NOT_PASSABLE,
                1,
                new Rectangle(0, 0,96,32),
                Direction.LEFT
        );
        enhancedMapTiles.add(hmp4);

        HorizontalMovingPlatform hmp5 = new HorizontalMovingPlatform(
                ImageLoader.load("lushHMP.png"),
                getMapTile(16, 232).getLocation(),
                getMapTile(24, 232).getLocation(),
                TileType.NOT_PASSABLE,
                1,
                new Rectangle(0, 0,96,32),
                Direction.RIGHT
        );
        enhancedMapTiles.add(hmp5);

        HorizontalMovingPlatform hmp6 = new HorizontalMovingPlatform(
                ImageLoader.load("lushHMP.png"),
                getMapTile(5, 212).getLocation(),
                getMapTile(17, 212).getLocation(),
                TileType.NOT_PASSABLE,
                1,
                new Rectangle(0, 0,96,32),
                Direction.RIGHT
        );
        enhancedMapTiles.add(hmp6);

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

package Tilesets;

import Builders.FrameBuilder;
import Builders.MapTileBuilder;
import Engine.ImageLoader;
import GameObject.Frame;
import GameObject.ImageEffect;
import Level.TileType;
import Level.Tileset;
import Utils.SlopeTileLayoutUtils;
import java.util.ArrayList;

// This class represents a "common" tileset of standard tiles defined in the CommonTileset.png file
public class CommonTileset extends Tileset {

    public CommonTileset() {
        super(ImageLoader.load("CaveTileset.png"), 16, 16, 2);
        // super(ImageLoader.load("TestTileSet.png"), 1, 1,3);
    }

    @Override
    public ArrayList<MapTileBuilder> defineTiles() {
        ArrayList<MapTileBuilder> mapTiles = new ArrayList<>();

        // default cave tile
        Frame defaultCaveFrame = new FrameBuilder(getSubImage(0, 0))
                .withScale(tileScale)
                .build();

        MapTileBuilder defaultCaveTile = new MapTileBuilder(defaultCaveFrame)
                .withTileType(TileType.NOT_PASSABLE);

        mapTiles.add(defaultCaveTile);

        

        // default cave second 30 degree slope left 
        Frame defaultCaveFrame30P2L = new FrameBuilder(getSubImage(0, 1))
                .withScale(tileScale)
                .build();

        MapTileBuilder defaultCaveTile30P2L = new MapTileBuilder(defaultCaveFrame30P2L)
                .withTileType(TileType.PASSABLE);

        mapTiles.add(defaultCaveTile30P2L);

        // default cave 45 degree slope left
        Frame defaultCaveFrame45L = new FrameBuilder(getSubImage(0, 2))
                .withScale(tileScale)
                .build();

        MapTileBuilder defaultCaveTile45L = new MapTileBuilder(defaultCaveFrame45L)
                .withTileType(TileType.PASSABLE);

        mapTiles.add(defaultCaveTile45L);

        // sun
        // Frame[] sunFrames = new Frame[]{
        //         new FrameBuilder(getSubImage(2, 0), 50)
        //                 .withScale(tileScale)
        //                 .build(),
        //         new FrameBuilder(getSubImage(2, 1), 50)
        //                 .withScale(tileScale)
        //                 .build()
        // };

        // MapTileBuilder sunTile = new MapTileBuilder(sunFrames);

        // mapTiles.add(sunTile);

        //green crystal
        Frame greenCrystalFrame = new FrameBuilder(getSubImage(2, 0))
        .withScale(tileScale)
        .build();

        MapTileBuilder greenCrystalTile = new MapTileBuilder(greenCrystalFrame)                         
        .withTileType(TileType.PASSABLE);

        mapTiles.add(greenCrystalTile);

        // blue crystal
        Frame blueCrystalFrame = new FrameBuilder(getSubImage(2, 1))
        .withScale(tileScale)
        .build();

        MapTileBuilder blueCrystalTile = new MapTileBuilder(blueCrystalFrame)                
        .withTileType(TileType.PASSABLE);

        mapTiles.add(blueCrystalTile);


        // purple crystal
        Frame purpleCrystalFrame = new FrameBuilder(getSubImage(2, 2))
                .withScale(tileScale)
                .build();

        MapTileBuilder purpleCrystalTile = new MapTileBuilder(purpleCrystalFrame)
                .withTileType(TileType.PASSABLE);

        mapTiles.add(purpleCrystalTile);

        // lush cave 45 degree slope left
        Frame lushCaveFrame45L = new FrameBuilder(getSubImage(1, 5))
                .withScale(tileScale)
                .withBounds(0, 6, 16, 4)
                .build();

        MapTileBuilder lushCaveTile45L = new MapTileBuilder(lushCaveFrame45L)
                .withTileType(TileType.PASSABLE);

        mapTiles.add(lushCaveTile45L);

        // lush cave 45 degree slope right
        Frame lushCaveFrame45R = new FrameBuilder(getSubImage(1, 5))
                .withScale(tileScale)
                .withBounds(0, 6, 16, 4)
                .withImageEffect(ImageEffect.FLIP_HORIZONTAL)
                .build();

        MapTileBuilder lushCaveTile45R = new MapTileBuilder(lushCaveFrame45R)
                .withTileType(TileType.PASSABLE);

        mapTiles.add(lushCaveTile45R);

        //default lush cave tile
        Frame lushCaveFrame = new FrameBuilder(getSubImage(1, 0))
        .withScale(tileScale)
        .build();

        MapTileBuilder lushCaveTile = new MapTileBuilder(lushCaveFrame)
        .withTileType(TileType.NOT_PASSABLE);

        mapTiles.add(lushCaveTile);

         // lush cave second 30 degree slope right
         Frame lushCaveFrame30P2R = new FrameBuilder(getSubImage(1, 1))
         .withScale(tileScale)
         .build();
 
         MapTileBuilder lushCaveTile30P2R = new MapTileBuilder(lushCaveFrame30P2R)  
         .withTileType(TileType.PASSABLE);
 
         mapTiles.add(lushCaveTile30P2R);

        // yellow flower
        // Frame[] yellowFlowerFrames = new Frame[] {
        //         new FrameBuilder(getSubImage(1, 2), 65)
        //                 .withScale(tileScale)
        //                 .build(),
        //         new FrameBuilder(getSubImage(1, 3), 65)
        //                 .withScale(tileScale)
        //                 .build(),
        //         new FrameBuilder(getSubImage(1, 2), 65)
        //                 .withScale(tileScale)
        //                 .build(),
        //         new FrameBuilder(getSubImage(1, 4), 65)
        //                 .withScale(tileScale)
        //                 .build()
        // };

        // MapTileBuilder yellowFlowerTile = new MapTileBuilder(yellowFlowerFrames);

        // mapTiles.add(yellowFlowerTile);

        // lush cave 45 degree slope left special variant
        Frame lushCaveFrame45LP2 = new FrameBuilder(getSubImage(1, 2))
                .withScale(tileScale)
                .withBounds(0, 6, 16, 4)
                .build();

        MapTileBuilder lushCaveTile45LP2 = new MapTileBuilder(lushCaveFrame45LP2)
                .withTileType(TileType.PASSABLE);

        mapTiles.add(lushCaveTile45LP2);

        // lush cave 45 degree slope right special variant
        Frame lushCaveFrame45RP2 = new FrameBuilder(getSubImage(1, 2))
                .withScale(tileScale)
                .withBounds(0, 6, 16, 4)
                .withImageEffect(ImageEffect.FLIP_HORIZONTAL)
                .build();

        MapTileBuilder lushCaveTile45RP2 = new MapTileBuilder(lushCaveFrame45RP2)
                .withTileType(TileType.PASSABLE);

        mapTiles.add(lushCaveTile45RP2);
        
        // lush cave 30 degree slope right
        Frame lushCaveFrame30R = new FrameBuilder(getSubImage(1, 3))
        .withScale(tileScale)
        .build();

        MapTileBuilder lushCaveTile30R = new MapTileBuilder(lushCaveFrame30R)  
        .withTileType(TileType.PASSABLE);

        mapTiles.add(lushCaveTile30R);

        // lush cave second 30 degree slope left
        Frame lushCaveFrame30P2L = new FrameBuilder(getSubImage(1, 4))
        .withScale(tileScale)
        .build();

        MapTileBuilder lushCaveTile30P2L = new MapTileBuilder(lushCaveFrame30P2L)  
        .withTileType(TileType.PASSABLE);

        mapTiles.add(lushCaveTile30P2L);

        // purple flower
        // Frame[] purpleFlowerFrames = new Frame[] {
        //         new FrameBuilder(getSubImage(0, 3), 65)
        //                 .withScale(tileScale)
        //                 .build(),
        //         new FrameBuilder(getSubImage(0, 4), 65)
        //                 .withScale(tileScale)
        //                 .build(),
        //         new FrameBuilder(getSubImage(0, 3), 65)
        //                 .withScale(tileScale)
        //                 .build(),
        //         new FrameBuilder(getSubImage(0, 5), 65)
        //                 .withScale(tileScale)
        //                 .build()
        // };

        // MapTileBuilder purpleFlowerTile = new MapTileBuilder(purpleFlowerFrames);

        // mapTiles.add(purpleFlowerTile);

        // default cave 30 degree slope left 
        Frame defaultCaveFrame30L = new FrameBuilder(getSubImage(0, 3))
                .withScale(tileScale)
                .build();

        MapTileBuilder defaultCaveTile30L = new MapTileBuilder(defaultCaveFrame30L)
                .withTileType(TileType.PASSABLE);

        mapTiles.add(defaultCaveTile30L);


        // default cave 30 degree slope right
        Frame defaultCaveFrame30R = new FrameBuilder(getSubImage(0, 4))
                .withScale(tileScale)
                .build();

        MapTileBuilder defaultCaveTile30R = new MapTileBuilder(defaultCaveFrame30R)
                .withTileType(TileType.PASSABLE);

        mapTiles.add(defaultCaveTile30R);

        // default cave 45 degree slope right
        Frame defaultCaveFrame45R = new FrameBuilder(getSubImage(0, 5))
                .withScale(tileScale)
                .build();

        MapTileBuilder defaultCaveTile45R = new MapTileBuilder(defaultCaveFrame45R)
        .withTileType(TileType.SLOPE)
        .withTileLayout(SlopeTileLayoutUtils.createLeft45SlopeLayout(spriteWidth, (int) tileScale));

        mapTiles.add(defaultCaveTile45R);


        // lush cave hanging leaf
        Frame lushCaveHangingLeafFrame = new FrameBuilder(getSubImage(2, 3))
                .withScale(tileScale)
                .withBounds(0, 6, 16, 4)
                .build();

        MapTileBuilder lushCaveHangingLeafTile = new MapTileBuilder(lushCaveHangingLeafFrame)
                .withTileType(TileType.PASSABLE);

        mapTiles.add(lushCaveHangingLeafTile);

        // lush cave 30 degree slope left
        Frame lushCaveFrame30L = new FrameBuilder(getSubImage(2, 4))
        .withScale(tileScale)
        .build();

        MapTileBuilder lushCaveTile30L = new MapTileBuilder(lushCaveFrame30L)  
        .withTileType(TileType.PASSABLE);

        mapTiles.add(lushCaveTile30L);

        // default cave second 30 degree slope right
        Frame defaultCaveFrame30P2R = new FrameBuilder(getSubImage(2, 5))
                .withScale(tileScale)
                .build();

        MapTileBuilder defaultCaveTile30P2R = new MapTileBuilder(defaultCaveFrame30P2R)
                .withTileType(TileType.PASSABLE);

        mapTiles.add(defaultCaveTile30P2R);

        // empty tile
        Frame emptyFrame = new FrameBuilder(getSubImage(4, 5))
                .withScale(tileScale)
                .build();

        MapTileBuilder emptyTile = new MapTileBuilder(emptyFrame);

        mapTiles.add(emptyTile);

        // // water
        // Frame waterFrame = new FrameBuilder(getSubImage(3, 1))
        //         .withScale(tileScale)
        //         .build();

        // MapTileBuilder waterTile = new MapTileBuilder(waterFrame)
        //         .withTileType(TileType.WATER);

        // mapTiles.add(waterTile);

        // grey rock
        // Frame greyRockFrame = new FrameBuilder(getSubImage(3, 2))
        //         .withScale(tileScale)
        //         .build();

        // MapTileBuilder greyRockTile = new MapTileBuilder(greyRockFrame)
        //         .withTileType(TileType.NOT_PASSABLE);

        // mapTiles.add(greyRockTile);

        // // left 45 degree slope
        // Frame leftSlopeFrame = new FrameBuilder(getSubImage(3, 3))
        //         .withScale(tileScale)
        //         .build();

        // MapTileBuilder leftSlopeTile = new MapTileBuilder(leftSlopeFrame)
        //         .withTileType(TileType.SLOPE)
        //         .withTileLayout(SlopeTileLayoutUtils.createLeft45SlopeLayout(spriteWidth, (int) tileScale));

        // mapTiles.add(leftSlopeTile);

        // // right 45 degree slope
        // Frame rightSlopeFrame = new FrameBuilder(getSubImage(3, 4))
        //         .withScale(tileScale)
        //         .build();

        // MapTileBuilder rightSlopeTile = new MapTileBuilder(rightSlopeFrame)
        //         .withTileType(TileType.SLOPE)
        //         .withTileLayout(SlopeTileLayoutUtils.createRight45SlopeLayout(spriteWidth, (int) tileScale));

        // mapTiles.add(rightSlopeTile);

        // left 30 degree slope bottom
        // Frame leftStairsBottomFrame = new FrameBuilder(getSubImage(4, 0))
        //         .withScale(tileScale)
        //         .build();

        // MapTileBuilder leftStairsBottomTile = new MapTileBuilder(leftStairsBottomFrame)
                // .withTileType(TileType.SLOPE)
                // .withTileLayout(SlopeTileLayoutUtils.createBottomLeft30SlopeLayout(spriteWidth, (int) tileScale));

        // mapTiles.add(leftStairsBottomTile);

        // left 30 degree slope top
        // Frame leftStairsTopFrame = new FrameBuilder(getSubImage(4, 1))
        //         .withScale(tileScale)
        //         .build();

        // MapTileBuilder leftStairsTopTile = new MapTileBuilder(leftStairsTopFrame)
        //         .withTileType(TileType.SLOPE)
        //         .withTileLayout(SlopeTileLayoutUtils.createTopLeft30SlopeLayout(spriteWidth, (int) tileScale));

        // mapTiles.add(leftStairsTopTile);

        // default cave tile faced down
        Frame defaultCaveFrameD = new FrameBuilder(getSubImage(0,0))
                .withScale(tileScale)
                .withBounds(0, 6, 16, 4)
                .withImageEffect(ImageEffect.FLIP_VERTICAL)
                .build();

        MapTileBuilder defaultCaveTileD = new MapTileBuilder(defaultCaveFrameD)
                .withTileType(TileType.NOT_PASSABLE);

        mapTiles.add(defaultCaveTileD);

        // Random lava tile
        Frame lavaCaveFrame = new FrameBuilder(getSubImage(3, 0))
                .withScale(tileScale)
                .build();

        MapTileBuilder lavaCaveTile = new MapTileBuilder(lavaCaveFrame)
                .withTileType(TileType.NOT_PASSABLE);

        mapTiles.add(lavaCaveTile);

        // Default lava cave tile
        Frame defaultLavaCaveFrame = new FrameBuilder(getSubImage(3, 1))
                .withScale(tileScale)
                .build();

        MapTileBuilder defaultLavaCaveTile = new MapTileBuilder(defaultLavaCaveFrame)
                .withTileType(TileType.NOT_PASSABLE);

        mapTiles.add(defaultLavaCaveTile);

        // Lava cave 30 degree slope p1 Right
        Frame lavaCaveFrame30P1R = new FrameBuilder(getSubImage(3, 2))
                .withScale(tileScale)
                .build();

        MapTileBuilder lavaCaveTile30P1R = new MapTileBuilder(lavaCaveFrame30P1R)
                .withTileType(TileType.PASSABLE);

        mapTiles.add(lavaCaveTile30P1R);

        // Lava cave 45 degree slope right
        Frame lavaCaveFrame45R = new FrameBuilder(getSubImage(3, 3))
                .withScale(tileScale)
                .build();

        MapTileBuilder lavaCaveTile45R = new MapTileBuilder(lavaCaveFrame45R)
                .withTileType(TileType.PASSABLE);

        mapTiles.add(lavaCaveTile45R);

        // Lava cave 30 degree slope p2 right
        Frame lavaCaveFrame30P2R = new FrameBuilder(getSubImage(3, 4))
                .withScale(tileScale)
                .build();

        MapTileBuilder lavaCaveTile30P2R = new MapTileBuilder(lavaCaveFrame30P2R)
                .withTileType(TileType.PASSABLE);

        mapTiles.add(lavaCaveTile30P2R);

        // Lava cave inner tile
        Frame lavaCaveInnerFrame = new FrameBuilder(getSubImage(3, 5))
                .withScale(tileScale)
                .build();

        MapTileBuilder lavaCaveInnerTile = new MapTileBuilder(lavaCaveInnerFrame)
                .withTileType(TileType.NOT_PASSABLE);

        mapTiles.add(lavaCaveInnerTile);

        // Default cave corner tile
        Frame DefaultCaveCornerFrame = new FrameBuilder(getSubImage(4, 0))
                .withScale(tileScale)
                .build();

        MapTileBuilder DefaultCaveCornerTile = new MapTileBuilder(DefaultCaveCornerFrame)
                .withTileType(TileType.NOT_PASSABLE);

        mapTiles.add(DefaultCaveCornerTile);

        // Lava cave 30 degree slope p2 right
        Frame lavaCaveFrame30P2L = new FrameBuilder(getSubImage(4, 1))
                .withScale(tileScale)
                .build();

        MapTileBuilder lavaCaveTile30P2L = new MapTileBuilder(lavaCaveFrame30P2L)
                .withTileType(TileType.PASSABLE);

        mapTiles.add(lavaCaveTile30P2L);


        // Lava cave 30 degree slope p1 Left
        Frame lavaCaveFrame30P1L = new FrameBuilder(getSubImage(4, 2))
                .withScale(tileScale)
                .build();

        MapTileBuilder lavaCaveTile30P1L = new MapTileBuilder(lavaCaveFrame30P1L)
                .withTileType(TileType.PASSABLE);

        mapTiles.add(lavaCaveTile30P1L);

        // Lava cave 45 degree slope left
        Frame lavaCaveFrame45L = new FrameBuilder(getSubImage(4, 3))
                .withScale(tileScale)
                .build();

        MapTileBuilder lavaCaveTile45L = new MapTileBuilder(lavaCaveFrame45L)
                .withTileType(TileType.PASSABLE);

        mapTiles.add(lavaCaveTile45L);

        // Default cave tile R90
        Frame DefaultCaveFrameR90 = new FrameBuilder(getSubImage(4, 4))
                .withScale(tileScale)
                .build();

        MapTileBuilder DefaultCaveTileR90 = new MapTileBuilder(DefaultCaveFrameR90)
                .withTileType(TileType.NOT_PASSABLE);

        mapTiles.add(DefaultCaveTileR90);

        // Default cave corner tile 2
        Frame DefaultCaveCornerFrame2 = new FrameBuilder(getSubImage(4,0))
                .withScale(tileScale)
                .withBounds(0, 6, 16, 4)
                .withImageEffect(ImageEffect.FLIP_VERTICAL)
                .build();

        MapTileBuilder DefaultCaveCornerTile2 = new MapTileBuilder(DefaultCaveCornerFrame2)
                .withTileType(TileType.NOT_PASSABLE);

        mapTiles.add(DefaultCaveCornerTile2);

        // Default cave corner tile 3
        Frame DefaultCaveCornerFrame3 = new FrameBuilder(getSubImage(4,0))
                .withScale(tileScale)
                .withBounds(0, 6, 16, 4)
                .withImageEffect(ImageEffect.FLIP_HORIZONTAL)
                .build();

        MapTileBuilder DefaultCaveCornerTile3 = new MapTileBuilder(DefaultCaveCornerFrame3)
                .withTileType(TileType.NOT_PASSABLE);

        mapTiles.add(DefaultCaveCornerTile3);

         // Default cave corner tile 4
        Frame DefaultCaveCornerFrame4 = new FrameBuilder(getSubImage(5, 1))
                .withScale(tileScale)
                .build();

        MapTileBuilder DefaultCaveCornerTile4 = new MapTileBuilder(DefaultCaveCornerFrame4)
                .withTileType(TileType.NOT_PASSABLE);

        mapTiles.add(DefaultCaveCornerTile4);

        // Default cave tile R902
        Frame DefaultCaveFrameR902 = new FrameBuilder(getSubImage(4,4))
                .withScale(tileScale)
                .withBounds(0, 6, 16, 4)
                .withImageEffect(ImageEffect.FLIP_HORIZONTAL)
                .build();

        MapTileBuilder DefaultCaveTileR902 = new MapTileBuilder(DefaultCaveFrameR902)
                .withTileType(TileType.NOT_PASSABLE);

        mapTiles.add(DefaultCaveTileR902);

        // Lava cave inner tile
        Frame defaultCaveInnerFrame = new FrameBuilder(getSubImage(5, 0))
                .withScale(tileScale)
                .build();

        MapTileBuilder defaultCaveInnerTile = new MapTileBuilder(defaultCaveInnerFrame)
                .withTileType(TileType.NOT_PASSABLE);

        mapTiles.add(defaultCaveInnerTile);
        





        return mapTiles;
    }
}

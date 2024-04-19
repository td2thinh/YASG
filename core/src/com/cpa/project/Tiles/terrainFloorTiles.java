package com.cpa.project.Tiles;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

import java.util.HashMap;
import java.util.Map;

public class terrainFloorTiles {

    //

    public static final int[] GRASS = {0, 1, 2, 3, 4, 5, 6, 7, 8};
    public static final int[] SAND = { 10, 11, 12, 13, 14, 15, 16, 17, 18};
    public static final int[] ROCK = {20, 21, 22, 23, 24, 25, 26, 27, 28};
    public static final int[] VOID = {30, 31, 32, 33, 34, 35, 36, 37, 38};
    public static final int[] WATER = {40, 41, 42, 43, 44, 45, 46, 47, 48};

    // our terrain tileset is "terrain.png"
    // we load it here
    public static Texture terrainTiles ;



    // we split the terrain tileset into individual tiles
    // it's constructed in a box pattern with 9 tiles by box
    // first line of the boxes are : grass ( with center being sand )
    // then dirt( grass as center ) , then a rock row ( this one is not a box  , its just a horizontal line with 3 rock tiles )
    // then sand ( rock as center ) , ( then rock with void as center )
    // then grass with void as center then void with water as center
    // and finally grass with water as center
    // which means we have 7 boxes of 9 tiles each and 3 rock tiles

    public  TextureRegion[][] terrainTilesSplit ;

    // we load the tree tile
    public static Texture treeTexture ;
    public TextureRegion treeTile;

    public static Tile tree1Tile ;



    // -- these are from the terrain tiles
    // we define each box as a 3x3 matrix of tiles
    // we define the center tile of each box as the main tile
    // and the surrounding tiles as the surrounding tiles
    public static Tile[][] sandCenterWGrass ;
    public static Tile[][] grassCenterWSand ;
    public static Tile[][] rockCenterWSand ;
    public static Tile[][]  voidCenterWRock ;
    public static Tile[][]  waterTiles;
    public static Tile[][] flowerTiles;



    public  void init() {

        terrainTiles = new Texture(Gdx.files.internal("terrain.png"));
        terrainTilesSplit = TextureRegion.split(terrainTiles, 48, 48);
        treeTexture = new Texture(Gdx.files.internal("map/grassland_trees.png")); // pas le meme tileset
        int treeTileWidth = treeTexture.getWidth() / 8;
        int treeTileHeight = treeTexture.getHeight() / 2;
        treeTile = new TextureRegion(treeTexture, 0, 0, treeTileWidth, treeTileHeight);
        tree1Tile = new Tile(50, treeTile);
        sandCenterWGrass = new Tile[][] {
                {new Tile(SAND[0], terrainTilesSplit[0][0]), new Tile(SAND[1], terrainTilesSplit[0][1]), new Tile(SAND[2], terrainTilesSplit[0][2])},
                {new Tile(SAND[3], terrainTilesSplit[1][0]), new Tile(SAND[4], terrainTilesSplit[1][1]), new Tile(SAND[5], terrainTilesSplit[1][2])},
                {new Tile(SAND[6], terrainTilesSplit[2][0]), new Tile(SAND[7], terrainTilesSplit[2][1]), new Tile(SAND[8], terrainTilesSplit[2][2])}
        };

        grassCenterWSand = new Tile[][] {
                {new Tile(GRASS[0], terrainTilesSplit[0][3]), new Tile(GRASS[1], terrainTilesSplit[0][4]), new Tile(GRASS[2], terrainTilesSplit[0][5])},
                {new Tile(GRASS[3], terrainTilesSplit[1][3]), new Tile(GRASS[4], terrainTilesSplit[1][4]), new Tile(GRASS[5], terrainTilesSplit[1][5])},
                {new Tile(GRASS[6], terrainTilesSplit[2][3]), new Tile(GRASS[7], terrainTilesSplit[2][4]), new Tile(GRASS[8], terrainTilesSplit[2][5])}
        };

        rockCenterWSand = new Tile[][] {
                {new Tile(ROCK[0], terrainTilesSplit[0][7]), new Tile(ROCK[1], terrainTilesSplit[0][8]), new Tile(ROCK[2], terrainTilesSplit[0][9])},
                {new Tile(ROCK[3], terrainTilesSplit[1][7]), new Tile(ROCK[4], terrainTilesSplit[1][8]), new Tile(ROCK[5], terrainTilesSplit[1][9])},
                {new Tile(ROCK[6], terrainTilesSplit[2][7]), new Tile(ROCK[7], terrainTilesSplit[2][8]), new Tile(ROCK[8], terrainTilesSplit[2][9])}
        };

        voidCenterWRock = new Tile[][] {
                {new Tile(VOID[0], terrainTilesSplit[0][9]), new Tile(VOID[1], terrainTilesSplit[0][10]), new Tile(VOID[2], terrainTilesSplit[0][11])},
                {new Tile(VOID[3], terrainTilesSplit[1][9]), new Tile(VOID[4], terrainTilesSplit[1][10]), new Tile(VOID[5], terrainTilesSplit[1][11])},
                {new Tile(VOID[6], terrainTilesSplit[2][9]), new Tile(VOID[7], terrainTilesSplit[2][10]), new Tile(VOID[8], terrainTilesSplit[2][11])}
        };

        waterTiles = new Tile[][] {
                {new Tile(WATER[0], terrainTilesSplit[11][3]), new Tile(WATER[1], terrainTilesSplit[11][4]), new Tile(WATER[2], terrainTilesSplit[11][5])},
                {new Tile(WATER[3], terrainTilesSplit[11][6]), new Tile(WATER[4], terrainTilesSplit[11][7]), new Tile(WATER[5], terrainTilesSplit[11][8])},
                {new Tile(WATER[6], terrainTilesSplit[11][9]), new Tile(WATER[7], terrainTilesSplit[11][10]), new Tile(WATER[8], terrainTilesSplit[11][11])}
        };

        flowerTiles = new Tile[][] {
                {new Tile(50, terrainTilesSplit[3][0]), new Tile(50, terrainTilesSplit[3][1]), new Tile(50, terrainTilesSplit[3][2])},
                {new Tile(50, terrainTilesSplit[4][0]), new Tile(50, terrainTilesSplit[4][1]), new Tile(50, terrainTilesSplit[4][2])}
        };


    }


    public  Tile getTile(int id) {
        switch (id) {
            case 0:
                return sandCenterWGrass[1][1];
//                return waterTileCenter; // testing
            case 1:
                return sandCenterWGrass[1][0];
            case 2:
                return sandCenterWGrass[1][2];
            case 3:
                return sandCenterWGrass[0][1];
            case 4:
                return sandCenterWGrass[2][1];
            case 5:
                return sandCenterWGrass[0][0];
            case 6:
                return sandCenterWGrass[0][2];
            case 7:
                return sandCenterWGrass[2][0];
            case 8:
                return sandCenterWGrass[2][2];
            case 10:
                return grassCenterWSand[1][1];
            case 11:
                return grassCenterWSand[1][0];
            case 12:
                return grassCenterWSand[1][2];
            case 13:
                return grassCenterWSand[0][1];
            case 14:
                return grassCenterWSand[2][1];
            case 15:
                return grassCenterWSand[0][0];
            case 16:
                return grassCenterWSand[0][2];
            case 17:
                return grassCenterWSand[2][0];
            case 18:
                return grassCenterWSand[2][2];
            case 20:
                return rockCenterWSand[1][1];
            case 21:
                return rockCenterWSand[1][0];
            case 22:
                return rockCenterWSand[1][2];
            case 23:
                return rockCenterWSand[0][1];
            case 24:
                return rockCenterWSand[2][1];
            case 25:
                return rockCenterWSand[0][0];
            case 26:
                return rockCenterWSand[0][2];
            case 27:
                return rockCenterWSand[2][0];
            case 28:
                return rockCenterWSand[2][2];
            case 30:
                return voidCenterWRock[1][1];
            case 31:
                return voidCenterWRock[1][0];
            case 32:
                return voidCenterWRock[1][2];
            case 33:
                return voidCenterWRock[0][1];
            case 34:
                return voidCenterWRock[2][1];
            case 35:
                return voidCenterWRock[0][0];
            case 36:
                return voidCenterWRock[0][2];
            case 37:
                return voidCenterWRock[2][0];
            case 38:
                return voidCenterWRock[2][2];
            case 40:
                return waterTiles[1][1];
            case 41:
                return waterTiles[1][0];
            case 42:
                return waterTiles[1][2];
            case 43:
                return waterTiles[0][1];
            case 44:
                return waterTiles[2][1];
            case 45:
                return waterTiles[0][0];
            case 46:
                return waterTiles[0][2];
            case 47:
                return waterTiles[2][0];
            case 48:
                return waterTiles[2][2];
            default:
                return null;
        }
    }


    public  Tile[][] getTileBox(int id) {
        switch (id) {
            case 0, 1, 2, 3, 4, 5, 6, 7, 8:
                return sandCenterWGrass;
            case 10, 11, 12, 13, 14, 15, 16, 17, 18:
                return grassCenterWSand;
            case 20, 21, 22, 23, 24, 25, 26, 27, 28:
                return rockCenterWSand;
            case 30, 31, 32, 33, 34, 35, 36, 37, 38:
                return voidCenterWRock;
            case 40, 41, 42, 43, 44, 45, 46, 47, 48:
                return waterTiles;
                default:
                return null;
        }
    }

    public terrainFloorTiles() {
        init();
    }


    // map all the tile to a string representation for map generation
    public static final Map<String, Integer> tileMapString = new HashMap<>() {{
        put("SandUpLeft", 0); put("SandUp", 1); put("SandUpRight", 2); put("SandLeft", 3); put("SandCenter", 4); put("SandRight", 5); put("SandDownLeft", 6); put("SandDown", 7); put("SandDownRight", 8);
        put("GrassUpLeft", 10); put("GrassUp", 11); put("GrassUpRight", 12); put("GrassLeft", 13); put("GrassCenter", 14); put("GrassRight", 15); put("GrassDownLeft", 16); put("GrassDown", 17); put("GrassDownRight", 18);
        put("RockUpLeft", 20); put("RockUp", 21); put("RockUpRight", 22); put("RockLeft", 23); put("RockCenter", 24); put("RockRight", 25); put("RockDownLeft", 26); put("RockDown", 27); put("RockDownRight", 28);
        put("VoidUpLeft", 30); put("VoidUp", 31); put("VoidUpRight", 32); put("VoidLeft", 33); put("VoidCenter", 34); put("VoidRight", 35); put("VoidDownLeft", 36); put("VoidDown", 37); put("VoidDownRight", 38);
        put("WaterUpLeft", 40); put("WaterUp", 41); put("WaterUpRight", 42); put("WaterLeft", 43); put("WaterCenter", 44); put("WaterRight", 45); put("WaterDownLeft", 46); put("WaterDown", 47); put("WaterDownRight", 48);
    }};

    public  int getTileIdByString(String tile) {
        return tileMapString.get(tile);
    }



}

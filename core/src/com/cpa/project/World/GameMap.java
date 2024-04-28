package com.cpa.project.World;

import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapRenderer;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.cpa.project.Tiles.Tile;
import com.cpa.project.Tiles.terrainFloorTiles;
import com.cpa.project.World.procGen.NoiseProceduralGen;

public class GameMap {
    private final static int tileSize = 48; // Size of a tile in pixels
    private final static int chunkSize = 64; // Size of a chunk in tiles

    private TiledMap tiledMap;
    private OrthographicCamera camera;
    private SpriteBatch batch;


    private terrainFloorTiles terrainFloorTiles;
    private OrthogonalTiledMapRenderer renderer;

    BitmapFont font;

    NoiseProceduralGen noiseProceduralGen;
    Tile[][] outputNoiseMap;

    public GameMap( OrthographicCamera camera , Vector2 playerPos) {
        this.camera = camera;
        this.terrainFloorTiles = new terrainFloorTiles();
        this.tiledMap = new TiledMap();
        this.renderer = new OrthogonalTiledMapRenderer(tiledMap);
        this.font = new BitmapFont();
        this.noiseProceduralGen = new NoiseProceduralGen(5, 1448, 1448);

    }

    public void init(){
        this.noiseProceduralGen.generateMap();
        this.outputNoiseMap = noiseProceduralGen.getMap();
    }

//    // take the output of the WFC algorithm and add it to the tiled map for rendering
//    // it's a terrain so add it to the terrain layer
//    public void addWFCtoTiledMap() {
//        TiledMapTileLayer terrainLayer = new TiledMapTileLayer(wfc.getWidth(), wfc.getHeight(), tileSize, tileSize);
//        // declare a new layer for the objects (e.g. trees, rocks, etc.) and add it to the tiled map
//        TiledMapTileLayer backgroudLayer = new TiledMapTileLayer(wfc.getWidth(), wfc.getHeight(), tileSize, tileSize);
//
//
//        for (int i = 0; i < WFCoutput.size(); i++) {
//            Tile tile = WFCoutput.get(i);
//            terrainLayer.setCell((int) tile.getPosition().x / tileSize, (int) tile.getPosition().y / tileSize, new TiledMapTileLayer.Cell().setTile(tile));
//        }
//        tiledMap.getLayers().add(terrainLayer);
//        tiledMap.getLayers().add(backgroudLayer);
//    }

    // take the output of the noise procedural generation and add it to the tiled map for rendering
    // it's a terrain so add it to the terrain layer
//    public void addNoiseMapToTiledMap(Vector2 playerposition) {
//        TiledMapTileLayer terrainLayer = new TiledMapTileLayer(noiseProceduralGen.getWidth(), noiseProceduralGen.getHeight(), tileSize, tileSize);
//
//        // declare a new layer for background
//        TiledMapTileLayer bgLayer = new TiledMapTileLayer(noiseProceduralGen.getWidth(), noiseProceduralGen.getHeight(), tileSize, tileSize);
//
//
//        for (int i = 0; i < outputNoiseMap.length; i++) {
//            for (int j = 0; j < outputNoiseMap[i].length; j++) {
//                Tile tile = outputNoiseMap[i][j];
//
//                // i need the tiles to be positioned relative to the player ( player needs to spawn in the middle of the map to not encounter edges )
//                int poxX = (int) playerposition.x - (outputNoiseMap.length / 2) * tileSize;
//                int poxY = (int) playerposition.y - (outputNoiseMap[i].length / 2) * tileSize;
//                tile.setPosition(new Vector2(poxX + i * tileSize, poxY + j * tileSize));
//                terrainLayer.setCell(i, j, new TiledMapTileLayer.Cell().setTile(tile));
//            }
//        }
//        tiledMap.getLayers().add(bgLayer);
//        tiledMap.getLayers().add(terrainLayer);
//    }
    public void addNoiseMapToTiledMap(Vector2 playerposition) {
        TiledMapTileLayer terrainLayer = new TiledMapTileLayer(noiseProceduralGen.getWidth(), noiseProceduralGen.getHeight(), tileSize, tileSize);
        TiledMapTileLayer bgLayer = new TiledMapTileLayer(noiseProceduralGen.getWidth(), noiseProceduralGen.getHeight(), tileSize, tileSize);

        int centerX = outputNoiseMap.length / 2;
        int centerY = outputNoiseMap[0].length / 2;

        for (int i = 0; i < outputNoiseMap.length; i++) {
            for (int j = 0; j < outputNoiseMap[i].length; j++) {
                Tile tile = outputNoiseMap[i][j];

                // Calculate the position based on the center of the map
                int posX = (i - centerX) * tileSize;
                int posY = (j - centerY) * tileSize;

                // Offset position by the player's start position
                tile.setPosition(new Vector2(playerposition.x + posX, playerposition.y + posY));
                terrainLayer.setCell(i, j, new TiledMapTileLayer.Cell().setTile(tile));
            }
        }
        tiledMap.getLayers().add(bgLayer);
        tiledMap.getLayers().add(terrainLayer);
    }

    public Vector2 getWorldCenter(){
        return  new Vector2(
                (float) (this.getWidth() * tileSize) / 2,
                (float) (this.getHeight() * tileSize) / 2
        );
    }



    public void render( ) {
        // Render the WFC output tiles
        renderer.setView(camera);
        renderer.render();

        // access the terrain layer
//        TiledMapTileLayer terrainLayer = (TiledMapTileLayer) tiledMap.getLayers().get(1);

        // access the tile at the player's position
//        TiledMapTileLayer.Cell cell = terrainLayer.getCell((int) playerPos.x / tileSize, (int) playerPos.y / tileSize);
//        Tile tile = (Tile) cell.getTile();
//        System.out.println("Tile at player position: " + tile.isReachable());

    }


    public Tile getTileAt(Vector2 position , int playerHe) {
        TiledMapTileLayer terrainLayer = (TiledMapTileLayer) tiledMap.getLayers().get(1);

        // Adjust the Y position based on the height of the player sprite
        // we do this because we had an off by one error in the tile position retrieval
        int tileX = (int) position.x / tileSize;
        int tileY = ((int) position.y - playerHe) / tileSize;

        TiledMapTileLayer.Cell cell = terrainLayer.getCell(tileX, tileY);

        if (cell != null) {
            return (Tile) cell.getTile();
        } else {
            return null; // No tile found at this position
        }
    }





    public void update(float dt, TiledMapRenderer tiledMapRenderer) {
        this.camera = camera;
        this.batch = batch;
//        this.render();
    }

    public int getWidth() {
        return this.noiseProceduralGen.getWidth();
    }

    public int getHeight() {
        return this.noiseProceduralGen.getHeight();
    }

    public void dispose() {
        tiledMap.dispose();
        renderer.dispose();
        noiseProceduralGen.dispose();
    }
}



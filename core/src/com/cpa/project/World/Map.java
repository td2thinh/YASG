package com.cpa.project.World;

import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapRenderer;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Vector2;
import com.cpa.project.Camera.OrthographicCamera;
import com.cpa.project.Tiles.Tile;
import com.cpa.project.Tiles.terrainFloorTiles;
import com.cpa.project.World.procGen.NoiseProceduralGen;
import com.cpa.project.World.procGen.WFC;

import java.util.ArrayList;

public class Map {
    private final static int tileSize = 48; // Size of a tile in pixels
    private final static int chunkSize = 64; // Size of a chunk in tiles

    private TiledMap tiledMap;
    private OrthographicCamera camera;
    private SpriteBatch batch;

    private Tile[][] sandCenterWGrass;

    private terrainFloorTiles terrainFloorTiles;
    private OrthogonalTiledMapRenderer renderer;

    private WFC wfc;
    private ArrayList<Tile> WFCoutput;
    BitmapFont font;

    NoiseProceduralGen noiseProceduralGen;
    Tile[][] outputNoiseMap;

    public Map(SpriteBatch batch, OrthographicCamera camera , Vector2 playerPos) {
        this.batch = batch;
        this.camera = camera;
        this.terrainFloorTiles = new terrainFloorTiles();
        this.sandCenterWGrass = terrainFloorTiles.getTileBox(0); // Get sand tile box
        this.tiledMap = new TiledMap();
        this.renderer = new OrthogonalTiledMapRenderer(tiledMap);
//        this.wfc = new WFC(  48, 48);
//        this.WFCoutput = wfc.getOutputTiles();
        this.font = new BitmapFont();
        this.noiseProceduralGen = new NoiseProceduralGen(5, 1448, 1448);
        this.noiseProceduralGen.generateMap();
        this.outputNoiseMap = noiseProceduralGen.getMap();
        addNoiseMapToTiledMap(playerPos);
    }

    // take the output of the WFC algorithm and add it to the tiled map for rendering
    // it's a terrain so add it to the terrain layer
    public void addWFCtoTiledMap() {
        TiledMapTileLayer terrainLayer = new TiledMapTileLayer(wfc.getWidth(), wfc.getHeight(), tileSize, tileSize);

        // declare a new layer for the objects (e.g. trees, rocks, etc.) and add it to the tiled map
        TiledMapTileLayer objectLayer = new TiledMapTileLayer(wfc.getWidth(), wfc.getHeight(), tileSize, tileSize);
        for (int i = 0; i < WFCoutput.size(); i++) {
            Tile tile = WFCoutput.get(i);
            terrainLayer.setCell((int) tile.getPosition().x / tileSize, (int) tile.getPosition().y / tileSize, new TiledMapTileLayer.Cell().setTile(tile));
        }
        tiledMap.getLayers().add(terrainLayer);
        tiledMap.getLayers().add(objectLayer);
    }

    // take the output of the noise procedural generation and add it to the tiled map for rendering
    // it's a terrain so add it to the terrain layer
    public void addNoiseMapToTiledMap(Vector2 playerposition) {
        TiledMapTileLayer terrainLayer = new TiledMapTileLayer(noiseProceduralGen.getWidth(), noiseProceduralGen.getHeight(), tileSize, tileSize);

        // declare a new layer for the objects (e.g. trees, rocks, etc.) and add it to the tiled map
        TiledMapTileLayer objectLayer = new TiledMapTileLayer(noiseProceduralGen.getWidth(), noiseProceduralGen.getHeight(), tileSize, tileSize);
        for (int i = 0; i < outputNoiseMap.length; i++) {
            for (int j = 0; j < outputNoiseMap[i].length; j++) {
                Tile tile = outputNoiseMap[i][j];

                // i need the tiles to be positioned relative to the player ( player needs to spawn in the middle of the map to not encounter edges )
                int poxX = (int) playerposition.x - (outputNoiseMap.length / 2) * tileSize;
                int poxY = (int) playerposition.y - (outputNoiseMap[i].length / 2) * tileSize;
                tile.setPosition(new Vector2(poxX + i * tileSize, poxY + j * tileSize));
                terrainLayer.setCell(i, j, new TiledMapTileLayer.Cell().setTile(tile));
            }
        }
        tiledMap.getLayers().add(terrainLayer);
        tiledMap.getLayers().add(objectLayer);
    }



    public void render( Vector2 playerPos  ) {
        // Render the WFC output tiles
        renderer.setView(camera);
        renderer.render();

        // access the terrain layer
        TiledMapTileLayer terrainLayer = (TiledMapTileLayer) tiledMap.getLayers().get(0);

        // access the tile at the player's position
        TiledMapTileLayer.Cell cell = terrainLayer.getCell((int) playerPos.x / tileSize, (int) playerPos.y / tileSize);
        Tile tile = (Tile) cell.getTile();
        System.out.println("Tile at player position: " + tile.isReachable());

    }



    public void update(float dt, SpriteBatch batch, ShapeRenderer shapeRenderer, OrthographicCamera camera, TiledMapRenderer tiledMapRenderer) {
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
}
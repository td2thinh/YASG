package com.cpa.project.World;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.maps.tiled.TiledMapTile;
import com.badlogic.gdx.math.Vector2;
import com.cpa.project.Camera.TopDownCamera;

public class Map {

    final static int mapSize = 4   ;
    final static  int tileSize = 64;

    Color color;
    TiledMapTile[][] tiles;

    Vector2 position;
    Color [] [] colors ;


    public Map(Color color , Vector2 position) {
        this.color = color;
        this.tiles = new TiledMapTile[mapSize][mapSize];
        this.colors = new Color[mapSize][mapSize];
        // fill colors with random colors
        for (int i = 0; i < mapSize; i++) {
            for (int j = 0; j < mapSize; j++) {
                colors[i][j] = new Color((float)Math.random(), (float)Math.random(), (float)Math.random(), 1);
            }
        }
        this.position = position;
    }

    public void render(ShapeRenderer shapeRenderer , TopDownCamera camera) {
        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        shapeRenderer.setColor(color);

        // calculate the offset to position the map in world-space
        float offsetX = -(mapSize * tileSize) / 2f + position.x;
        float offsetY = -(mapSize * tileSize) / 2 + position.y;

        for (int i = 0; i < mapSize; i++) {
            for (int j = 0; j < mapSize; j++) {
//                shapeRenderer.setColor(colors[i][j]);
                // draw the tile
                shapeRenderer.rect(offsetX + i * tileSize, offsetY + j * tileSize, tileSize, tileSize);
            }
        }
        shapeRenderer.end();
    }

    public void update(float dt , ShapeRenderer shapeRenderer  , TopDownCamera camera) {
        render(shapeRenderer , camera);
    }

    public Vector2 getPosition() {
        return position;
    }

    public void setPosition(Vector2 position) {
        this.position = position;
    }

    public int getMapSize() {
        return mapSize;
    }

    public int getTileSize() {
        return tileSize;
    }

    public boolean isPlayerInMap(Vector2 playerPos) {
        float mapStartX = position.x - (mapSize * tileSize) / 2f;
        float mapEndX = position.x + (mapSize * tileSize) / 2f;

        float mapStartY = position.y - (mapSize * tileSize) / 2f;
        float mapEndY = position.y + (mapSize * tileSize) / 2f;

        return playerPos.x >= mapStartX && playerPos.x <= mapEndX
                && playerPos.y >= mapStartY && playerPos.y <= mapEndY;
    }

    public void dispose() {
        // dispose of the tiles

    }
}

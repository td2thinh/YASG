package com.cpa.project.World;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.cpa.project.Camera.OrthographicCamera;
import com.sun.tools.javac.util.Pair;

import java.util.HashMap;
import java.util.ArrayList;
import java.util.List;



public class MapManager {

    final static int[][] DIRECTIONS = {
            {-1, 1}, {0, 1}, {1, 1},
            {-1, 0},          {1, 0},
            {-1,-1}, {0,-1}, {1,-1}
    };

    int renderDistance;
    Vector2 playerMapPos;
    HashMap<Vector2, Map> maps;
    OrthographicCamera camera;
    BitmapFont font;

    Map currentMap;

    public MapManager(OrthographicCamera camera, int renderDistance) {
        this.camera = camera;
        this.renderDistance = renderDistance;
        this.playerMapPos = new Vector2(0, 0);
        this.maps = new HashMap<>();
        this.font = new BitmapFont();
        this.currentMap = new Map(new Color((float)Math.random(), (float)Math.random(), (float)Math.random(), 1),
                new Vector2(camera.position.x, camera.position.y));
        createSurroundingMaps((int) playerMapPos.x, (int) playerMapPos.y);
    }

    public void update(float dt,SpriteBatch bacth, ShapeRenderer shapeRenderer, Vector2 playerPos) {
        shapeRenderer.setProjectionMatrix(camera.combined);
        currentMap.update(dt, shapeRenderer, camera);
        for (Map map : maps.values()) {
            map.update(dt, shapeRenderer, camera);
        }

        // check if the player is in a new map and update the current map
        for (Map map : maps.values()) {
            if (map.isPlayerInMap(playerPos)) {
                currentMap = map;
            }
        }

        // draw the current map
        bacth.begin();
        font.draw(bacth, "Current Map", currentMap.getPosition().x, currentMap.getPosition().y);

        bacth.end();

        Vector2 newPlayerMapPos = getMapPosForPlayer(playerPos);
        if(!newPlayerMapPos.equals(playerMapPos)) {
            playerMapPos = newPlayerMapPos;
            updateMaps((int) playerMapPos.x, (int) playerMapPos.y);
        }
    }

    public void addMap(Vector2 position, Map map) {
        maps.put(position, map);
    }



    private Vector2 getMapPosForPlayer(Vector2 playerPos) {
        return new Vector2((int) Math.floor(playerPos.x / (currentMap.getMapSize() * currentMap.getTileSize())),
                (int) Math.floor(playerPos.y / (currentMap.getMapSize() * currentMap.getTileSize())));
    }

    private void createSurroundingMaps(int playerX, int playerY) {
        // Calculate offsets to prioritize maps around the player ----  this here is kinda approximate
        int offsetX = playerX - renderDistance;
        int offsetY = playerY - renderDistance;

        for(int x = 0; x < renderDistance * 2 + 1; x++) {
            for(int y = 0; y < renderDistance * 2 + 1; y++) {
                int newX = offsetX + x;
                int newY = offsetY + y;
                Vector2 newPosition = new Vector2(newX, newY);
                if(!maps.containsKey(newPosition)) {
                    createNewMapAt(newX, newY);
                }
            }
        }
    }


    private void createNewMapAt(int x, int y) {
        int mapSize = currentMap.getMapSize();
        int tileSize = currentMap.getTileSize();
        Vector2 mapPosition = new Vector2(x * mapSize * tileSize, y * mapSize * tileSize);
        Map newMap = new Map(new Color((float)Math.random(), (float)Math.random(), (float)Math.random(), 1), mapPosition);
        addMap(new Vector2(x, y), newMap);
    }

    private void updateMaps(int centerX, int centerY) {
        List<Vector2> positionsToRemove = new ArrayList<>();
        for(Vector2 position : maps.keySet()) {
            if(Math.abs(centerX - position.x) > renderDistance || Math.abs(centerY - position.y) > renderDistance) {
                positionsToRemove.add(position);
            }
        }

        for(Vector2 positionToRemove : positionsToRemove) {
            Map mapToRemove = maps.remove(positionToRemove);
            mapToRemove.dispose();
        }

        createSurroundingMaps(centerX, centerY);

        System.err.println("maps to remove : " + positionsToRemove.size());
        System.err.println("number of maps : " + maps.size());
    }

}
package com.cpa.project.World;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.cpa.project.Camera.OrthographicCamera;

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
    OrthographicCamera camera;
    BitmapFont font;
    TiledMapRenderer tiledMapRenderer;
    SpriteBatch batch;
    Map currentMap;

    public MapManager(OrthographicCamera camera,SpriteBatch batch, int renderDistance , Vector2 playerMapPos) {
        this.camera = camera;
        this.renderDistance = renderDistance;
        this.playerMapPos = playerMapPos;
        this.font = new BitmapFont();
        this.batch = batch;
        this.currentMap = new Map( batch, camera);
    }

    public void update(float dt, ShapeRenderer shapeRenderer, Vector2 playerPos) {
        shapeRenderer.setProjectionMatrix(camera.combined);
        currentMap.update(dt, this.batch , shapeRenderer, camera,tiledMapRenderer);


    }

}
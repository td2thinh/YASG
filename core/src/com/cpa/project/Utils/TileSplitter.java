package com.cpa.project.Utils;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class TileSplitter {

    private String tileset = "grassland_tiles.png";
    TextureRegion[][] tiles;
    private TextureRegion[][] splitTiles(Texture tileset, int tileWidth, int tileHeight) {
        TextureRegion[][] tiles = new TextureRegion[tileset.getWidth() / tileWidth][tileset.getHeight() / tileHeight];

        for (int y = 0; y < tileset.getHeight() / tileHeight; y++) {
            for (int x = 0; x < tileset.getWidth() / tileWidth; x++) {
                tiles[x][y] = new TextureRegion(tileset, x * tileWidth, y * tileHeight, tileWidth, tileHeight);
            }
        }

        return tiles;
    }

    public TextureRegion[][] getTiles(Texture tileset, int tileWidth, int tileHeight) {
        return splitTiles(tileset, tileWidth, tileHeight);
    }

    public TileSplitter() {
        tiles = getTiles(new Texture(tileset), 32, 32);
    }




}

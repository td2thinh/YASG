package com.cpa.project;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.ScreenUtils;
import com.cpa.project.Camera.OrthographicCamera;
import com.cpa.project.Entities.Actors.Mobs.Skeleton;
import com.cpa.project.Entities.Actors.Player;
import com.cpa.project.Entities.Entity;
import com.cpa.project.World.MapManager;
import com.cpa.project.World.World;

import java.util.HashSet;
import java.util.Set;

public class Survivors extends Game {
    SpriteBatch batch;
    World world;
    MapManager mapManager;
    ShapeRenderer shapeRenderer;
    OrthographicCamera camera;

    @Override
    public void create() {
        batch = new SpriteBatch();
        Player player = new Player(new Vector2(800, 240), new Sprite(new Texture("threeformsPrev.png")));
        player.setSpeed(50);
        player.setHealth(10);
        Set<Entity> entities = new HashSet<>();
        entities.add(player);
        Entity ske1 = new Skeleton(new Vector2(900, 500), new Sprite(new Texture("threeformsPrev.png")));
        entities.add(ske1);
        camera = new OrthographicCamera();
        camera.setTarget(player);
        camera.setToOrtho(false, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        mapManager = new MapManager(camera , batch,1 , new Vector2(player.getPosition().x, player.getPosition().y));
        world = new World(player, entities, camera );
        shapeRenderer = new ShapeRenderer();
    }

    @Override
    public void render() {
        ScreenUtils.clear(0, 1, 0, 0.2f);
        this.camera.update(Gdx.graphics.getDeltaTime());

        mapManager.update(Gdx.graphics.getDeltaTime(), shapeRenderer, world.getPlayer().getPosition());

        batch.setProjectionMatrix(this.world.getCamera().combined);
        batch.begin();

        world.update(Gdx.graphics.getDeltaTime());
        world.getPlayer().getSprite().draw(batch);
        for (Entity entity : world.getEntities()) {
            entity.getSprite().draw(batch);
        }
        batch.end();


    }

    @Override
    public void dispose() {
        batch.dispose();
        world.dispose();
    }
}

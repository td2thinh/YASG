package com.cpa.project;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
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
import com.cpa.project.World.Map;
import com.cpa.project.World.World;

import java.util.HashSet;
import java.util.Set;

public class Survivors extends Game {
    SpriteBatch batch;
    World world;
    ShapeRenderer shapeRenderer;
    OrthographicCamera camera;

    Player player ;

    Map map;

    @Override
    public void create() {
        batch = new SpriteBatch();
        player = new Player(new Vector2(800, 240), new Sprite(new Texture("threeformsPrev.png")));
        player.setSpeed(10);
        player.setHealth(10);
        Set<Entity> entities = new HashSet<>();
        entities.add(player);
        Entity ske1 = new Skeleton(new Vector2(900, 500), new Sprite(new Texture("threeformsPrev.png")));
        entities.add(ske1);
        camera = new OrthographicCamera();
        camera.setTarget(player);
        camera.setToOrtho(false, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        world = new World(player, entities, camera );
        shapeRenderer = new ShapeRenderer();
        map = new Map(batch, camera, player.getPosition());
        // change the player position to the center of the map
        player.setPosition(new Vector2((float) (map.getWidth() * 48) / 2, (float) (map.getHeight() * 48) / 2));
    }

    @Override
    public void render() {
        ScreenUtils.clear(0, 1, 0, 0.2f);
        this.camera.update(Gdx.graphics.getDeltaTime());
        map.render( this.player.getPosition());

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

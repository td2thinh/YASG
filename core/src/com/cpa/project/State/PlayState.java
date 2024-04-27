package com.cpa.project.State;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.cpa.project.Camera.TopDownCamera;
import com.cpa.project.Entities.Actors.Mobs.Skeleton;
import com.cpa.project.Entities.Actors.Player;
import com.cpa.project.Entities.Entity;
import com.cpa.project.Entities.Spells.SonicWave;
import com.cpa.project.Utils.CollisionDetector;
import com.cpa.project.Utils.SonicWaveProps;
import com.cpa.project.World.GameMap;


import java.util.*;
import java.util.concurrent.Future;

import static com.cpa.project.Survivors.audioHandler;

public class PlayState {
    public static TopDownCamera topDownCamera;
    public static Player player;

    public static Map<Entity, SonicWaveProps> affectedBySonicWave;

    public static Set<Entity> playerProjectiles;
    public static Set<Entity> enemyProjectiles;
    public static List<Entity> removedEntities;
    public static Set<Entity> enemies;

    public static boolean isPaused;

    public static GameMap map;




    public PlayState() {

        playerProjectiles = new HashSet<>();
        enemyProjectiles = new HashSet<>();
        removedEntities = new ArrayList<>();
        affectedBySonicWave = new HashMap<>();
        enemies = new HashSet<>();
        // FOR TESTING PURPOSES
        Sprite playerSprite = new Sprite(new Texture("wizard.png"));
        playerSprite.setScale(0.20f);
        Player player = new Player(new Vector2(800, 240), playerSprite);
        player.setSpeed(500);
        player.setHealth(10);
        player.setDamage(10);
        player.setLevel(20);
        PlayState.player = player;

        topDownCamera = new TopDownCamera();
        topDownCamera.setTarget(player);
        topDownCamera.setToOrtho(false, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        isPaused = false;
        Vector2 playerPosition = player.getPosition().cpy();
        map = new GameMap(topDownCamera, playerPosition);
        map.init();
        map.addNoiseMapToTiledMap(playerPosition);

        Vector2 worldcenter = map.getWorldCenter();
        player.setPosition(worldcenter.cpy());

        Skeleton ske1 = new Skeleton(new Vector2(player.getPosition().cpy().x + 100, player.getPosition().cpy().y + 100), new Sprite(new Texture("skeleton.png")));
        ske1.setSpeed(100);
        enemies.add(ske1);

        Skeleton ske2 = new Skeleton(new Vector2(player.getPosition().cpy().x + 200, player.getPosition().cpy().y + 200), new Sprite(new Texture("skeleton.png")));
        ske2.setSpeed(100);
        enemies.add(ske2);


    }




    public void update(float dt) {
        topDownCamera.update(dt);
        player.update(dt);
        for (Entity entity : playerProjectiles) {
            // if projectile are at a certain distance from the player , remove them
            if (entity.getPosition().dst(player.getPosition()) > 5000) {
                removedEntities.add(entity);
            }

            entity.update(dt);
        }
        for (Entity entity : enemyProjectiles) {
            entity.update(dt);
        }
        for (Entity entity : enemies) {
            ((Skeleton) entity).handleSound(player.getPosition());
            if (affectedBySonicWave.get(entity) != null) {
                SonicWaveProps props = affectedBySonicWave.get(entity);
                Vector2 playerPosition = props.getPlayerPos();
                Vector2 entityPosition = entity.getPosition();
                Vector2 direction = entityPosition.sub(playerPosition);
                if (direction.len() > SonicWave.MAX_DISTANCE_AWAY) {
                    affectedBySonicWave.remove(entity);
                    entity.setVelocity(new Vector2(0, 0));
                    entity.resetSpeed();
                } else {
                    entity.setVelocity(props.getDirection());
                    entity.setSpeed(props.getSpeed());
                }
            }
            entity.update(dt);
            if (CollisionDetector.checkCollision(player, entity)) {
                // Put attacking animation here
                player.collidesWith(entity);
            }

        }
        for (Entity entity : removedEntities) {
            playerProjectiles.remove(entity);
            enemyProjectiles.remove(entity);
            enemies.remove(entity);
            entity.dispose();
        }
        removedEntities.clear();
    }

    public void render(SpriteBatch batch) {

        batch.setProjectionMatrix(topDownCamera.combined);
        batch.begin();
        map.render(player.getPosition());
        player.getSprite().draw(batch);
        for (Entity entity : playerProjectiles) {
            entity.getSprite().draw(batch);
        }
        for (Entity entity : enemyProjectiles) {
            entity.getSprite().draw(batch);
        }
        for (Entity entity : enemies) {
            Texture healthBar = entity.getHealthBar();
            batch.draw(healthBar, entity.getPosition().x - healthBar.getWidth() / 2, entity.getPosition().y + entity.getSprite().getHeight() / 2);
            entity.getSprite().draw(batch);
        }
        // UI Elements need to be drawn at the end so that they are on top of everything

        // TODO: Figure out a way to make these UI elements using Scene2D
        Vector3 unprojectedPos = topDownCamera.unproject(new Vector3(100, 800, 0));
        Texture healthBar = player.getHealthBar();
        batch.draw(healthBar, unprojectedPos.x, unprojectedPos.y);
        String healthText = String.format("%.0f%%", ((float) player.getHealth() / player.getMaxHealth()) * 100);
        BitmapFont font = new BitmapFont();
        GlyphLayout layout = new GlyphLayout(font, healthText);
        float textX = (unprojectedPos.x + 140);
        float textY = (unprojectedPos.y + 17);
        font.draw(batch, healthText, textX, textY);

        Texture xpBar = player.getXPBar();
        batch.draw(xpBar, unprojectedPos.x, unprojectedPos.y - 30);
        // TODO: Change this to get the player's class
        String xpText = String.format("Mage Lvl.%d", player.getLevel());
        layout.setText(font, xpText);
        textX = (unprojectedPos.x + 118);
        textY = (unprojectedPos.y - 13);
        font.draw(batch, xpText, textX, textY);
        batch.end();
    }

    public void dispose() {
        player.dispose();
        for (Entity entity : playerProjectiles) {
            entity.dispose();
        }
        for (Entity entity : enemyProjectiles) {
            entity.dispose();
        }
        for (Entity entity : enemies) {
            entity.dispose();
        }
        playerProjectiles.clear();
        enemyProjectiles.clear();
        enemies.clear();
        affectedBySonicWave.clear();
        removedEntities.clear();
        map.dispose();

    }

    public void pause() {
        isPaused = true;
    }

    public void resume() {
        isPaused = false;
    }

    public void resize(int width, int height) {
        topDownCamera.setToOrtho(false, width, height);
    }

}

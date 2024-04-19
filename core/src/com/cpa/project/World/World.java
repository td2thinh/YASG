package com.cpa.project.World;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.cpa.project.Camera.OrthographicCamera;
import com.cpa.project.Entities.Actors.Player;
import com.cpa.project.Entities.Entity;
import com.cpa.project.Entities.Projectiles.Fire;
import com.cpa.project.Utils.CollisionDetector;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class World {
    protected Player player;
    protected Set<Entity> entities;
    protected OrthographicCamera camera;

    float fireRate = 0.1f;

    public World(Player player, Set<Entity> entities, OrthographicCamera camera ) {
        this.player = player;
        this.entities = entities;
        this.camera = camera;
    }

    public void update(float dt) {
//        camera.update(dt);
        // TODO: DELEGATE THIS TO THE PLAYER CLASS OR SOMETHING

        fireRate -= dt;
        if (fireRate <= 0) {
            Vector3 cursorPos3D = camera.unproject(new Vector3(Gdx.input.getX(), Gdx.input.getY(), 0));
            Vector2 cursorPos = new Vector2(cursorPos3D.x, cursorPos3D.y);
            Vector2 playerPos = this.player.getPosition();
            Vector2 bulletDir = cursorPos.sub(playerPos).nor();
            entities.add(new Fire(this.player.getPosition(), new Sprite(new Texture("firebullet.png")), bulletDir, 200, 10));
//        }
            fireRate += 0.1f;
        }
        player.update(dt);
        List<Entity> entitiesCopy = new ArrayList<>(entities);
        List<Entity> toRemove = new ArrayList<>();
        for (int i = 0; i < entitiesCopy.size(); i++) {
            Entity entity = entitiesCopy.get(i);
            entity.update(dt);
            for (int j = i + 1; j < entitiesCopy.size(); j++) {
                Entity other = entitiesCopy.get(j);
                if (CollisionDetector.checkCollision(entity, other)) {
                    entity.collidesWith(other);
                    if (entity.getHealth() <= 0) {
                        toRemove.add(entity);
                    }
                    if (other.getHealth() <= 0) {
                        toRemove.add(other);
                    }
                }
            }
            // Remove projectiles that are too far out
            if (entitiesCopy.get(i).getEntityType() == Entity.EntityType.PROJECTILE_PL) {
                if (entitiesCopy.get(i).getPosition().dst(player.getPosition()) > 5000) {
                    toRemove.add(entitiesCopy.get(i));
                }
            }
        }
        // Dispose of entities that are dead
        for (Entity entity : toRemove) {
            entity.dispose();
            entities.remove(entity);
        }
    }
    public void dispose() {
        for (Entity entity : entities) {
            entity.getSprite().getTexture().dispose();
        }
        entities.clear();
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public Set<Entity> getEntities() {
        return entities;
    }

    public void setEntities(Set<Entity> entities) {
        this.entities = entities;
    }

    public OrthographicCamera getCamera() {
        return camera;
    }

    public void setCamera(OrthographicCamera camera) {
        this.camera = camera;
    }


}

package com.cpa.project.World;

import com.cpa.project.Camera.OrthographicCamera;
import com.cpa.project.Entities.Actors.Player;
import com.cpa.project.Entities.Entity;
import com.cpa.project.Utils.CollisionDetector;

import java.util.List;

public class World {
    protected Player player;
    protected List<Entity> entities;
    protected OrthographicCamera camera;

    public World(Player player, List<Entity> entities, OrthographicCamera camera) {
        this.player = player;
        this.entities = entities;
        this.camera = camera;
        this.camera.setTarget(player);
    }

    public void update(float dt) {
        for (Entity entity : entities) {
            for (Entity other : entities) {
                if (entity != other) {
                    if (CollisionDetector.checkCollision(entity, other)) {
                        entity.collidesWith(other);
                    }
                }
            }
            entity.update(dt);
        }
        player.update(dt);
    }

    public void dispose() {
        player.dispose();
        for (Entity entity : entities) {
            entity.dispose();
        }
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public List<Entity> getEntities() {
        return entities;
    }

    public void setEntities(List<Entity> entities) {
        this.entities = entities;
    }

    public OrthographicCamera getCamera() {
        return camera;
    }

    public void setCamera(OrthographicCamera camera) {
        this.camera = camera;
    }

}

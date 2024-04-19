package com.cpa.project.Entities.Projectiles;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;
import com.cpa.project.Entities.Entity;

public class SmallFireBall extends Projectile {


    public SmallFireBall(Vector2 position, Sprite sprite, Vector2 velocity, float speed, float damage) {
        super(position, sprite, velocity, speed, damage);
        this.entityType = EntityType.PLAYER_PROJECTILE;
    }

    @Override
    public Entity clone() {
        return null;
    }
}

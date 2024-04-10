package com.cpa.project.Entities.Actors.Mobs;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;
import com.cpa.project.Entities.Entity;

public class Skeleton extends Entity {


    public Skeleton(Vector2 position, Sprite sprite, float speed, float health, float damage) {
        super(position, sprite, speed, health, damage);
        this.entityType = EntityType.ENEMY;
    }

    public Skeleton(Vector2 position, Sprite sprite) {
        super(position, sprite);
        this.speed = 20;
        this.health = 100;
        this.damage = 10;
    }

    @Override
    public void update(float dt) {

    }

    @Override
    public void collidesWith(Entity other) {
        if (other.getEntityType() == EntityType.PLAYER) {
            other.setHealth(other.getHealth() - this.getDamage());
        }
        if (other.getEntityType() == EntityType.PROJECTILE_PL) {
            this.health -= other.getDamage();
        }
    }

    @Override
    public Entity clone() {
        return new Skeleton(new Vector2(this.getSprite().getX(), this.getSprite().getY()), this.sprite, this.speed, this.health, this.damage);
    }
}

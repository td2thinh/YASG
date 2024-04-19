package com.cpa.project.Entities.Actors.Mobs;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;
import com.cpa.project.Entities.Entity;

public class Skeleton extends Entity {

    public Skeleton(Vector2 position, Sprite sprite, float speed, float damage, float health) {
        super(position, sprite, speed, damage);
        this.health = health;
        this.maxHealth = health;
        this.entityType = EntityType.ENEMY;
    }

    public Skeleton(Vector2 position, Sprite sprite) {
        super(position, sprite);
        this.speed = 20;
        this.health = 100;
        this.maxHealth = 100;
        this.damage = 10;
        this.entityType = EntityType.ENEMY;
        this.velocity = new Vector2(0, 0);
    }

    @Override
    public void update(float dt) {
        this.sprite.translate(this.velocity.x * this.speed * dt, this.velocity.y * this.speed * dt);
    }

    @Override
    public void collidesWith(Entity other) {
        if (other.getEntityType() == EntityType.PLAYER_PROJECTILE) {
            this.setHealth(this.getHealth() - other.getDamage());
        }
        if (other.getEntityType() == EntityType.PLAYER) {
           other.setHealth(other.getHealth() - this.getDamage());
        }
    }

    @Override
    public Entity clone() {
        return new Skeleton(new Vector2(this.getSprite().getX(),
                this.getSprite().getY()),
                this.sprite, this.speed, this.damage, this.health);
    }

}

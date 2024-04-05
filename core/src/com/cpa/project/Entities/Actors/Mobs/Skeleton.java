package com.cpa.project.Entities.Actors.Mobs;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;
import com.cpa.project.Entities.Entity;

public class Skeleton extends Entity {

    EntityType entityType = EntityType.ENEMY;

    public Skeleton(Vector2 position, Sprite sprite, int speed, float health, float damage) {
        super(position, sprite, speed, health, damage);
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
        this.health -= other.getDamage();
        if (this.health <= 0) {
            this.dispose();
        }
    }

    @Override
    public Entity clone() {
        return new Skeleton(this.position, this.sprite, this.speed, this.health, this.damage);
    }
}

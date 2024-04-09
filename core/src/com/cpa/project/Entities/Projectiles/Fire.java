package com.cpa.project.Entities.Projectiles;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;
import com.cpa.project.Entities.Entity;

public class Fire extends Entity {


    public Fire(Vector2 position, Sprite sprite, Vector2 direction, float speed, float damage) {
        super(position, sprite, direction, speed, damage);
        this.entityType = EntityType.PROJECTILE_PL;
        this.health = 1;
    }

    @Override
    public void update(float dt) {
        this.sprite.translate(this.direction.x * this.speed * dt, this.direction.y * this.speed * dt);
    }

    @Override
    public void collidesWith(Entity other) {
        if (other.getEntityType() == EntityType.ENEMY) {
            other.setHealth(other.getHealth() - this.getDamage());
        }
    }

    @Override
    public Entity clone() {
        return new Fire(new Vector2(this.getSprite().getX(), this.getSprite().getY()), this.sprite, this.direction, this.speed, this.damage);
    }
}

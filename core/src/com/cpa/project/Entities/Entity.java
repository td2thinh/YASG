package com.cpa.project.Entities;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;

public abstract class Entity {

    final protected Sprite sprite;
    protected Vector2 center;
    protected Vector2 direction;
    protected float health;
    protected Vector2 velocity;
    protected int speed;
    protected float damage;

    protected EntityType entityType;

    public Entity(Vector2 position, Sprite sprite) {
        this.sprite = sprite;
        this.sprite.setPosition(position.x, position.y);
        this.center = new Vector2(position.x + sprite.getWidth() / 2, position.y + sprite.getHeight() / 2);
    }

    // Constructor for Projectiles
    public Entity(Vector2 position, Sprite sprite, Vector2 direction, float speed, float damage) {
        this.sprite = sprite;
        this.sprite.setPosition(position.x, position.y);
        this.direction = direction;
        this.speed = (int) speed;
        this.damage = damage;
    }


    public Entity(Vector2 position, Sprite sprite, int speed, float health, float damage) {
        this.sprite = sprite;
        this.sprite.setPosition(position.x, position.y);
        this.speed = speed;
        this.health = health;
        this.damage = damage;
    }

    public abstract void update(float dt);

    public void dispose() {
        sprite.getTexture().dispose();
    }

    public abstract void collidesWith(Entity other);

    public Vector2 getPosition() {
        return new Vector2(sprite.getX() + sprite.getWidth() / 2, sprite.getY() + sprite.getHeight() / 2);
    }

    public void setPosition(Vector2 position) {
        this.sprite.setPosition(position.x, position.y);
    }

    public float getHealth() {
        return health;
    }

    public void setHealth(float health) {
        this.health = health;
    }

    public Vector2 getVelocity() {
        return velocity;
    }

    public void setVelocity(Vector2 velocity) {
        this.velocity = velocity;
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public float getDamage() {
        return damage;
    }

    public void setDamage(float damage) {
        this.damage = damage;
    }

    public Sprite getSprite() {
        return sprite;
    }

    public abstract Entity clone();

    public EntityType getEntityType() {
        return this.entityType;
    }

    public Vector2 getCenter() {
        return center;
    }

    public enum EntityType {
        ENEMY,
        PROJECTILE_EN,
        PROJECTILE_PL,
        PLAYER,
        ENVIRONMENT
    }
}

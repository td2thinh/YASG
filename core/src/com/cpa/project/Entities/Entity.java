package com.cpa.project.Entities;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;

public abstract class Entity {
    protected Vector2 position;
    protected Vector2 direction;
    protected Sprite sprite;
    protected float health;
    protected Vector2 velocity;
    protected int speed;
    protected float damage;

    public Entity(Vector2 position, Sprite sprite) {
        this.position = position;
        this.sprite = sprite;
    }


    public Entity(Vector2 position, Sprite sprite, int speed, float health, float damage) {
        this.position = position;
        this.sprite = sprite;
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
        return position;
    }

    public void setPosition(Vector2 position) {
        this.position = position;
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

    protected enum EntityType {
        ENEMY,
        PROJECTILE_EN,
        PROJECTILE_PL,
        PLAYER,
        ENVIRONMENT
    }
}

package com.cpa.project.Entities.Actors;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;
import com.cpa.project.Entities.Entity;

public class Player extends Entity {


    public Player(Vector2 position, Sprite sprite) {
        super(position, sprite);
        this.entityType = EntityType.PLAYER;
        this.velocity = new Vector2(0, 0);
        this.direction = new Vector2(1, 0);
    }

    public void move(float dt) {
        if (Gdx.input.isKeyPressed(Input.Keys.LEFT) || Gdx.input.isKeyPressed(Input.Keys.Q)) {
            if (Gdx.input.isKeyPressed(Input.Keys.UP) || Gdx.input.isKeyPressed(Input.Keys.Z))
                this.sprite.translate(-this.speed * dt * 0.70710678118f, this.speed * dt * 0.70710678118f);
            else if (Gdx.input.isKeyPressed(Input.Keys.DOWN) || Gdx.input.isKeyPressed(Input.Keys.S))
                this.sprite.translate(-this.speed * dt * 0.70710678118f, -this.speed * dt * 0.70710678118f);
            else
                this.sprite.translate(-this.speed * dt, 0);
        }
        if (Gdx.input.isKeyPressed(Input.Keys.RIGHT) || Gdx.input.isKeyPressed(Input.Keys.D)) {
            if (Gdx.input.isKeyPressed(Input.Keys.UP) || Gdx.input.isKeyPressed(Input.Keys.Z))
                this.sprite.translate(this.speed * dt * 0.70710678118f, this.speed * dt * 0.70710678118f);
            else if (Gdx.input.isKeyPressed(Input.Keys.DOWN) || Gdx.input.isKeyPressed(Input.Keys.S))
                this.sprite.translate(this.speed * dt * 0.70710678118f, -this.speed * dt * 0.70710678118f);
            else
                this.sprite.translate(this.speed * dt, 0);
        }
        if (Gdx.input.isKeyPressed(Input.Keys.DOWN) || Gdx.input.isKeyPressed(Input.Keys.S)) {
            if (Gdx.input.isKeyPressed(Input.Keys.LEFT) || Gdx.input.isKeyPressed(Input.Keys.Q))
                this.sprite.translate(-this.speed * dt * 0.70710678118f, -this.speed * dt * 0.70710678118f);
            else if (Gdx.input.isKeyPressed(Input.Keys.RIGHT) || Gdx.input.isKeyPressed(Input.Keys.D))
                this.sprite.translate(this.speed * dt * 0.70710678118f, -this.speed * dt * 0.70710678118f);
            else
                this.sprite.translate(0, -this.speed * dt);
        }
        if (Gdx.input.isKeyPressed(Input.Keys.UP) || Gdx.input.isKeyPressed(Input.Keys.Z)) {
            if (Gdx.input.isKeyPressed(Input.Keys.LEFT) || Gdx.input.isKeyPressed(Input.Keys.Q))
                this.sprite.translate(-this.speed * dt * 0.70710678118f, this.speed * dt * 0.70710678118f);
            else if (Gdx.input.isKeyPressed(Input.Keys.RIGHT) || Gdx.input.isKeyPressed(Input.Keys.D))
                this.sprite.translate(this.speed * dt * 0.70710678118f, this.speed * dt * 0.70710678118f);
            else
                this.sprite.translate(0, this.speed * dt);
        }
    }

    @Override
    public void update(float dt) {
        this.move(dt);
    }

    @Override
    public void collidesWith(Entity other) {
        // More repulsive force
        // MORE PHYSICS BITCHES
        if (other.getEntityType() == EntityType.ENEMY || other.getEntityType() == EntityType.PROJECTILE_EN) {
            this.health -= other.getDamage();
        }
    }

    @Override
    public Entity clone() {
        return new Player(new Vector2(this.getSprite().getX(), this.getSprite().getY()), this.sprite);
    }


    public Vector2 getDirection() {
        return direction;
    }
}

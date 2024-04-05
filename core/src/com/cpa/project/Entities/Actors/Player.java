package com.cpa.project.Entities.Actors;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;
import com.cpa.project.Entities.Entity;

public class Player extends Entity {
    EntityType entityType = EntityType.PLAYER;

    public Player(Vector2 position, Sprite sprite) {
        super(position, sprite);
    }

    public void move(float dt) {
        if (Gdx.input.isKeyPressed(Input.Keys.LEFT) || Gdx.input.isKeyPressed(Input.Keys.Q)) {
            this.direction = new Vector2(-1, 0);
            this.velocity = this.direction.scl(this.speed);
            this.position.add(this.velocity.scl(dt));
        }
        if (Gdx.input.isKeyPressed(Input.Keys.RIGHT) || Gdx.input.isKeyPressed(Input.Keys.D)) {
            this.direction = new Vector2(1, 0);
            this.velocity = this.direction.scl(this.speed);
            this.position.add(this.velocity.scl(dt));
        }
        if (Gdx.input.isKeyPressed(Input.Keys.DOWN) || Gdx.input.isKeyPressed(Input.Keys.S)) {
            this.direction = new Vector2(0, -1);
            this.velocity = this.direction.scl(this.speed);
            this.position.add(this.velocity.scl(dt));
        }
        if (Gdx.input.isKeyPressed(Input.Keys.UP) || Gdx.input.isKeyPressed(Input.Keys.Z)) {
            this.direction = new Vector2(0, 1);
            this.velocity = this.direction.scl(this.speed);
            this.position.add(this.velocity.scl(dt));
        }
        this.sprite.setPosition(this.position.x, this.position.y);
    }

    @Override
    public void update(float dt) {
        this.move(dt);
    }

    @Override
    public void collidesWith(Entity other) {
        // More repulsive force
        // MORE PHYSICS BITCHES
        this.health -= other.getDamage();
    }

    @Override
    public Entity clone() {
        return new Player(this.position, this.sprite);
    }
}

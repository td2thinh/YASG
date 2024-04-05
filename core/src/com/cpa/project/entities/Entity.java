package com.cpa.project.entities;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;

public abstract class Entity {
    protected Vector2 position;
    Sprite sprite;

    public Entity(Vector2 position, Sprite sprite) {
        this.position = position;
        this.sprite = sprite;
    }

    public abstract void update(float dt);

    public void dispose() {
        sprite.getTexture().dispose();
    }

    public abstract void collidesWith(Entity other);

    public Vector2 getPosition() {
        return position;
    }

    public Sprite getSprite() {
        return sprite;
    }

    public abstract Entity clone();
}

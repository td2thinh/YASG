package com.cpa.project.Utils;

import com.badlogic.gdx.math.Vector2;

public class SonicWaveProps {
    protected final float speed;
    protected final Vector2 playerPos;
    protected final Vector2 direction;

    public SonicWaveProps(float speed, Vector2 playerPos, Vector2 direction) {
        this.speed = speed;
        this.playerPos = playerPos;
        this.direction = direction;
    }

    public float getSpeed() {
        return speed;
    }

    public Vector2 getPlayerPos() {
        return playerPos;
    }

    public Vector2 getDirection() {
        return direction;
    }

}

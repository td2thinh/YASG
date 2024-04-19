package com.cpa.project.Camera;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.cpa.project.Entities.Entity;
import com.badlogic.gdx.graphics.OrthographicCamera;

public class TopDownCamera extends OrthographicCamera {
    Entity target;
    float lerp = 0.1f;

    Viewport viewport;

    public TopDownCamera() {
        super();
        viewport = new ScreenViewport(this);
        viewport.setWorldSize(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
    }

    public void update(float dt) {
        if (target != null) {
            this.position.lerp(new Vector3(target.getPosition().x, target.getPosition().y, 0), lerp);
        }
        viewport.update(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
    }

    public void setTarget(Entity target) {
        this.target = target;
    }

    public void setLerp(float lerp) {
        this.lerp = lerp;
    }

    public Viewport getViewport() {
        return viewport;
    }

    public void setViewport(Viewport viewport) {
        this.viewport = viewport;
    }
}
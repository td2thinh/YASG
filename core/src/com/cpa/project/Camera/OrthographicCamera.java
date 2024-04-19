package com.cpa.project.Camera;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Matrix4;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.cpa.project.Entities.Entity;

public class OrthographicCamera extends com.badlogic.gdx.graphics.OrthographicCamera {
    Entity target;
    float lerp = 0.1f;
    private float zoom = 1.0f;



    Viewport viewport;

    public OrthographicCamera() {
        super();
        viewport = new ScreenViewport(this);
        viewport.setWorldSize(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
    }

    public void update(float dt) {
        if (target != null) {
            position.x = target.getSprite().getX() + target.getSprite().getWidth() / 2;
            position.y = target.getSprite().getY() + target.getSprite().getHeight() / 2;
        }

        position.x += zoom * 0.5f;
        position.y -= zoom * 0.25f;

        position.lerp(new Vector3(position.x, position.y, 0), lerp);

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


    public void setZoom(float zoom) {
        this.zoom = zoom;
    }

    public float getZoom() {
        return zoom;
    }
}
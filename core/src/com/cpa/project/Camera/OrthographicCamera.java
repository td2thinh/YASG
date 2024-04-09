package com.cpa.project.Camera;

import com.badlogic.gdx.math.Vector3;
import com.cpa.project.Entities.Entity;

public class OrthographicCamera extends com.badlogic.gdx.graphics.OrthographicCamera {
    Entity target;
    float lerp = 0.1f;

    public OrthographicCamera() {
        super();
    }

    public void update(float dt) {
        if (target != null) {
            Vector3 pos = this.position;
            pos.x += (target.getSprite().getX() - pos.x + target.getSprite().getWidth() / 2) * lerp;
            pos.y += (target.getSprite().getY() - pos.y) * lerp;
            this.position.set(position); // Update the camera position
        }
        super.update();
    }

    public void setTarget(Entity target) {
        this.target = target;
    }


    public void setLerp(float lerp) {
        this.lerp = lerp;
    }
}

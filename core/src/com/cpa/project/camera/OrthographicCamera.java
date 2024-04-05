package com.cpa.project.camera;

import com.badlogic.gdx.math.Vector3;
import com.cpa.project.entities.Entity;

public class OrthographicCamera extends com.badlogic.gdx.graphics.OrthographicCamera {
    Entity target;
    float lerp = 0.1f;

    public OrthographicCamera() {
        super();
    }

    public void update(float dt) {
        if (target != null) {
            Vector3 position = this.position;
            position.x += (target.getPosition().x - position.x + target.getSprite().getWidth() / 2) * lerp;
            position.y += (target.getPosition().y - position.y) * lerp;
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

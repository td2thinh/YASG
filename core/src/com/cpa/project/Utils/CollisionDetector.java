package com.cpa.project.Utils;

import com.badlogic.gdx.math.Rectangle;
import com.cpa.project.Entities.Entity;

public class CollisionDetector {
    public static boolean checkCollision(Entity entity1, Entity entity2) {
        // Bounding rectangles of the two sprites
        Rectangle rect1 = entity1.getSprite().getBoundingRectangle();
        Rectangle rect2 = entity2.getSprite().getBoundingRectangle();

        // AABB collision detection
        return rect1.overlaps(rect2);
    }
}
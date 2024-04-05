package com.cpa.project.entities;

public class CollisionDetector {
    public static boolean checkCollision(Entity entity1, Entity entity2) {
        return entity1.getSprite().getBoundingRectangle().overlaps(entity2.getSprite().getBoundingRectangle());
    }
}
package com.cpa.project.Utils;

import com.cpa.project.Entities.Actors.Mobs.Skeleton;
import com.cpa.project.Entities.Actors.Mobs.bat;

public class PoolManager {
    private static final EntityPool<Skeleton> skeletonPool = new EntityPool<>(Skeleton.class);
    private static final EntityPool<bat> batPool = new EntityPool<>(bat.class);

    public static Skeleton obtainSkeleton() {
        return skeletonPool.obtain();
    }

    public static void freeSkeleton(Skeleton skeleton) {
        skeletonPool.free(skeleton);
    }

    public static bat obtainBat() {
        return batPool.obtain();
    }

    public static void freeBat(bat Bat) {
        batPool.free(Bat);
    }
}

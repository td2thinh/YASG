package com.cpa.project.Utils;

import com.badlogic.gdx.graphics.Texture;

public class AssetManager {
    private static final Texture playerTexture = new Texture("wizard.png");
    private static final Texture skeletonTexture = new Texture("skeleton.png");
    private static final Texture FireBallSmall = new Texture("FireBallSmall.png");

    public static Texture getPlayerTexture() {
        return playerTexture;
    }

    public static Texture getSkeletonTexture() {
        return skeletonTexture;
    }

    public static Texture getFireBallSmall() {
        return FireBallSmall;
    }

    public static void dispose() {
        playerTexture.dispose();
        skeletonTexture.dispose();
        FireBallSmall.dispose();
    }
}
package com.cpa.project.Utils;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;

public class AssetManager {
    private static final Texture playerTexture = new Texture("wizard.png");
    private static final Texture skeletonTexture = new Texture("skeleton.png");
    private static final Texture FireBallSmall = new Texture("FireBallSmall.png");

    private static final Texture Autofireball = new Texture("firebullet.png");

    private static final Skin skin = new Skin(Gdx.files.internal("skin/OS Eight.json"));

    private static final Texture arrowTexture = new Texture("arrow.png");

    private static final BitmapFont font = new BitmapFont();

    public static Texture getPlayerTexture() {
        return playerTexture;
    }

    public static Texture getSkeletonTexture() {
        return skeletonTexture;
    }

    public static Texture getFireBallSmall() {
        return FireBallSmall;
    }

    public static Texture getAutofireball() {
        return Autofireball;
    }

    public static Skin getSkin() {
        return skin;
    }

    public static BitmapFont getFont() {
        return font;
    }

    public static Texture getArrowTexture () {
        return arrowTexture;
    }


    public static void dispose() {
        playerTexture.dispose();
        skeletonTexture.dispose();
        FireBallSmall.dispose();
        skin.dispose();
        font.dispose();
    }
}
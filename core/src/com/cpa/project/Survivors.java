package com.cpa.project;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.cpa.project.Screens.MenuScreen;

public class Survivors extends Game {
    private SpriteBatch batch;
    private MenuScreen menuScreen;
    public void setScreen(Screen screen) {
        super.setScreen(screen);
    }

    public void create() {
        batch = new SpriteBatch();
        menuScreen = new MenuScreen(this);
        this.setScreen(menuScreen);
    }

    @Override
    public void dispose() {
        super.dispose();
        batch.dispose();
        menuScreen.dispose();
    }

}

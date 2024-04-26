package com.cpa.project;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.cpa.project.Screens.MenuScreen;
import com.cpa.project.Utils.AudioHandler;

public class Survivors extends Game {
    private SpriteBatch batch;
    private MenuScreen menuScreen;
    public static AudioHandler audioHandler;
    public void setScreen(Screen screen) {
        super.setScreen(screen);
    }

    public void create() {
        audioHandler = new AudioHandler();
        audioHandler.setMenuOST(audioHandler.loadMusic("audio/MenuOST.wav"));
        audioHandler.setGameOST(audioHandler.loadMusic("audio/forest.wav"));
        batch = new SpriteBatch();
        menuScreen = new MenuScreen(this);
        this.setScreen(menuScreen);
    }

    @Override
    public void dispose() {
        super.dispose();
        batch.dispose();
        menuScreen.dispose();
        audioHandler.dispose();
    }

}

package com.cpa.project.Screens;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;

public class GameOverScreen implements Screen {

    private final Stage gameOverStage = new Stage();
    Game game;

    public GameOverScreen(Game game) {
        Gdx.input.setInputProcessor(gameOverStage);
        this.game = game;
    }

    @Override
    public void show() {
        Skin skin = new Skin(Gdx.files.internal("skin/OS Eight.json"));
        Table gameOverTable = new Table();
        gameOverTable.setFillParent(true);
        Texture gameOverTexture = new Texture(Gdx.files.internal("GameOver.png"));
        Image gameOverImage = new Image(gameOverTexture);
        gameOverTable.add(gameOverImage).height(500f);
        gameOverTable.row();
        TextButton returnMenu = new TextButton("Return to Menu", skin);

        // Return to menu button OnClick
        returnMenu.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                game.setScreen(new MenuScreen(game));
                dispose();
            }
        });
        gameOverTable.row();
        gameOverTable.add(returnMenu).width(200f).height(50f);
        gameOverStage.addActor(gameOverTable);
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(.9f, .9f, .9f, 1);
        Gdx.gl.glClear(Gdx.gl.GL_COLOR_BUFFER_BIT);
        gameOverStage.act();
        gameOverStage.draw();
    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {
        gameOverStage.clear();
    }

    @Override
    public void dispose() {
        gameOverStage.dispose();
    }
}

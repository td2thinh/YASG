package com.cpa.project.Screens;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.cpa.project.State.PlayState;

import static com.cpa.project.Survivors.audioHandler;

public class GameScreen implements Screen {

    private Game game;
    private PlayState playState;
    SpriteBatch batch;

    private final Stage gameStage = new Stage();

    private final Music ButtonClickSound;

    public GameScreen(Game game) {
        Gdx.input.setInputProcessor(gameStage);
        ButtonClickSound = audioHandler.loadMusic("audio/click2.wav");
        this.game = game;
    }

    @Override
    public void show() {
        // Init the state, when the game is done I think this needs a loading screen
        playState = new PlayState();
        batch = new SpriteBatch();
        Skin skin = new Skin(Gdx.files.internal("skin/OS Eight.json"));

        // The div for the pause button
        Table gameTable = new Table();

        gameTable.top().right();
        // The div for the resume button
        Table resumeTable = new Table();
        gameTable.setFillParent(true);
        resumeTable.setFillParent(true);

        // Pause button
        TextButton pauseButton = new TextButton("Pause", skin);
        // Pause button OnClick
        pauseButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                Gdx.app.postRunnable(() -> audioHandler.addSoundEffect("ButtonClick" , ButtonClickSound)); // on utilise postRunnable pour éviter les problèmes de thread avec openGL
                PlayState.isPaused = true;
                gameTable.setVisible(false);
                resumeTable.setVisible(true);
            }
        });

        // Resume button
        TextButton resumeButton = new TextButton("Resume", skin);
        // Resume button OnClick
        resumeButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                Gdx.app.postRunnable(() -> audioHandler.addSoundEffect("ButtonClick" , ButtonClickSound));
                PlayState.isPaused = false;
                resumeTable.setVisible(false);
                gameTable.setVisible(true);
            }
        });

        // Add the buttons to the tables
        gameTable.add(pauseButton).padBottom(20f).width(200f).height(50f);
        resumeTable.add(resumeButton).padBottom(20f).width(200f).height(50f);

        // Add new row ie. new line
        resumeTable.row();

        // Return to menu button
        TextButton returnMenu = new TextButton("Return to Menu", skin);

        // Return to menu button OnClick
        returnMenu.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                Gdx.app.postRunnable(() -> audioHandler.addSoundEffect("ButtonClick" , ButtonClickSound));
                dispose();
                game.setScreen(new MenuScreen(game));

            }
        });
        // Add the return to menu button to the resume table
        resumeTable.add(returnMenu).padBottom(20f).width(200f).height(50f);

        // Set the resume table to invisible
        resumeTable.setVisible(false);

        Texture sonicWave = new Texture(Gdx.files.internal("icons/BTNInnerFire.jpg"));
        Image sonicWaveImage = new Image(sonicWave);
        Texture fireBall = new Texture(Gdx.files.internal("icons/BTNFireBolt.jpg"));
        Image fireBallImage = new Image(fireBall);
        Texture autoFireBall = new Texture(Gdx.files.internal("icons/BTNOrbOfFire.jpg"));
        Image autoFireBallImage = new Image(autoFireBall);
        Texture heal = new Texture(Gdx.files.internal("icons/BTNHeal.jpg"));
        Image healImage = new Image(heal);

        Table skillsTable = new Table();
        skillsTable.bottom().left().setPosition(450, 50);
        skillsTable.defaults().padRight(40f).spaceBottom(10f);
        skillsTable.add(fireBallImage).width(50f).height(50f);
        skillsTable.add(sonicWaveImage).width(50f).height(50f);
        skillsTable.add(autoFireBallImage).width(50f).height(50f);
        skillsTable.add(healImage).width(50f).height(50f);
        skillsTable.row();
        skillsTable.add(new Label("Fire Ball", skin));
        skillsTable.add(new Label("Sonic Wave", skin));
        skillsTable.add(new Label("Auto Fire Ball", skin));
        skillsTable.add(new Label("Heal", skin));
        skillsTable.row();
        skillsTable.add(new Label("LMB", skin));
        skillsTable.add(new Label("SPACE", skin));
        skillsTable.add(new Label("Autocast", skin));
        skillsTable.add(new Label("H", skin));


        gameStage.addActor(resumeTable);
        gameStage.addActor(gameTable);
        gameStage.addActor(skillsTable);

        audioHandler.playMusic("game");
    }

    @Override
    public void render(float delta) {
        if (PlayState.player.getHealth() <= 0) {
            game.setScreen(new GameOverScreen(game));
//            dispose(); // attention ce dispose() fait crash le jeu ...
        }
        if (!PlayState.isPaused) {
            Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
            playState.update(delta);
            playState.render(batch);
        }
        gameStage.act();
        gameStage.draw();
        batch.begin();
        BitmapFont font = new BitmapFont();
        String sonicWaveCD = String.format("%.2f", PlayState.player.getSpells().get("SonicWave").getTimeToReady());
        String healCD = String.format("%.2f", PlayState.player.getSpells().get("Heal").getTimeToReady());
        String autoFireBallCD = String.format("%.2f", PlayState.player.getSpells().get("AutoFireBall").getTimeToReady());
        Vector3 unprojectedPos = PlayState.topDownCamera.unproject(new Vector3(100, 800, 0));
        if (PlayState.player.getSpells().get("SonicWave").getTimeToReady() > 0.08f) {
            font.draw(batch, sonicWaveCD, unprojectedPos.x + 458, unprojectedPos.y + 32);
        }
        if (PlayState.player.getSpells().get("AutoFireBall").getTimeToReady() > 0.08f) {
            font.draw(batch, autoFireBallCD, unprojectedPos.x + 570, unprojectedPos.y + 32);
        }
        if (PlayState.player.getSpells().get("Heal").getTimeToReady() > 0.08f) {
            font.draw(batch, healCD, unprojectedPos.x + 673, unprojectedPos.y + 32);
        }
        batch.end();
    }

    @Override
    public void resize(int width, int height) {
        playState.resize(width, height);
    }

    @Override
    public void pause() {
        playState.pause();
        audioHandler.pauseMusic("game");
    }

    @Override
    public void resume() {
        playState.resume();
        audioHandler.playMusic("game");
    }

    @Override
    public void hide() {
        audioHandler.stopMusic("game");
    }

    @Override
    public void dispose() {
        playState.dispose();
        batch.dispose();
    }
}

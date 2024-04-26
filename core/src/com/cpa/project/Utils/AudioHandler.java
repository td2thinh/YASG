package com.cpa.project.Utils;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;

import java.util.HashMap;
import java.util.Map;
import java.util.Timer;

// This class is used to handle audio in the game and is used to play, pause, and stop audio.
public class AudioHandler {

    // we're going to use the libGDX Music class to handle the streaming of the audio file
    private Music gameOST;
    private Music menuOST;
    private  HashMap<String, Sound> soundEffects;
    private Timer timer;

    public AudioHandler() {
        soundEffects = new HashMap<>();
        timer = new Timer();
    }

    // This method is used to play the game's background music.
    public void playGameOST() {
        gameOST.setLooping(true);
        gameOST.play();
    }

    // This method is used to pause the game's background music.
    public void pauseGameOST() {
        gameOST.pause();
    }

    // This method is used to stop the game's background music.
    public void stopGameOST() {
        gameOST.stop();
    }

    // add a sound effect to the sound effects map
    public void addSoundEffect(String name, Sound soundEffect) {
        soundEffects.put(name, soundEffect);
        soundEffect.play();
    }

    // play a sound effect ( like a spell cast, or a hit sound) , this method will play the sound effect once and then stop
    public void playSoundEffect(String name) {
        soundEffects.get(name).play();
    }

    // stop a sound effect
    public void stopSoundEffect(String name) {
        soundEffects.get(name).stop();
    }

    // pause a sound effect
    public void pauseSoundEffect(String name) {
        soundEffects.get(name).pause();
    }


    public void dispose() {
        gameOST.dispose();
        for (Map.Entry<String, Sound> entry : soundEffects.entrySet()) {
            entry.getValue().dispose();
        }
    }

    public Music loadMusic(String path) {
        return Gdx.audio.newMusic(Gdx.files.internal(path));
    }

    public Sound loadSound(String path) {
        return Gdx.audio.newSound(Gdx.files.internal(path));
    }

    public void setGameOST(Music gameOST) {
        this.gameOST = gameOST;
    }

    public void setMenuOST(Music menuOST) {
        this.menuOST = menuOST;
    }

    public void playMenuOST() {
        menuOST.setLooping(true);
        menuOST.play();
    }

    public void pauseMenuOST() {
        menuOST.pause();
    }

    public void stopMenuOST() {
        menuOST.stop();
    }


    public Music getMenuOST() {
        return menuOST;
    }

    public Music getGameOST() {
        return gameOST;
    }


    public boolean hasSound(String soundName) {
        return soundEffects.containsKey(soundName);
    }
}

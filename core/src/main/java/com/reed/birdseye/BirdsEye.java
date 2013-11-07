package com.reed.birdseye;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;

public class BirdsEye extends Game {

    @Override
    public void create() {
        Assets.load();
        setScreen(new GameScreen(this));
        SaveAndLoad.load();
    }

    @Override
    public void dispose() {
        SaveAndLoad.save();
        super.dispose();
        Gdx.app.exit();
    }

    @Override
    public void pause() {
    }

    @Override
    public void render() {
        super.render();
    }

    @Override
    public void resize(int width, int height) {
    }

    @Override
    public void resume() {
    }
}
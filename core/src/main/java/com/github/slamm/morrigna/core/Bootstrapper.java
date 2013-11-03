package com.github.slamm.morrigna.core;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.FPSLogger;

public class Bootstrapper extends Game {

    private static final Logger LOGGER = LoggerFactory.getLogger(Bootstrapper.class);

    private FPSLogger fpsLogger;

    @Override
    public void create() {
        LOGGER.debug("Createing game on {}", Gdx.app.getType());
        fpsLogger = new FPSLogger();
        setScreen(new MainMenuScreen(this));
    }

    @Override
    public void render() {
        super.render();
        fpsLogger.log();
    }
}
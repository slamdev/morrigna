package com.github.slamm.morrigna.core;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.FPSLogger;

public class Bootstrapper extends Game {

    public static class ScreenChanger {

        private final Game game;

        public ScreenChanger(Game game) {
            this.game = game;
        }

        public void change(Screen screen) {
            game.setScreen(screen);
        }
    }

    private static final Logger LOGGER = LoggerFactory.getLogger(Bootstrapper.class);

    private FPSLogger fpsLogger;

    @Override
    public void create() {
        LOGGER.debug("Createing game on {}", Gdx.app.getType());
        fpsLogger = new FPSLogger();
        setScreen(new MainMenuScreen(new ScreenChanger(this)));
    }

    @Override
    public void render() {
        super.render();
        fpsLogger.log();
    }
}
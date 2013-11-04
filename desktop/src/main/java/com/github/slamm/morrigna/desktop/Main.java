package com.github.slamm.morrigna.desktop;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.github.slamm.morrigna.core.Bootstrapper;

public class Main {

    private static final Logger LOGGER = LoggerFactory.getLogger(Main.class);

    public static void main(String[] args) {
        Thread.setDefaultUncaughtExceptionHandler(new Thread.UncaughtExceptionHandler() {

            @Override
            public void uncaughtException(Thread t, Throwable e) {
                LOGGER.error("Uncaught Exception Handler", e);
            }
        });
        LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
        config.forceExit = false;
        config.useGL20 = true;
        @SuppressWarnings("unused")
        LwjglApplication app = new LwjglApplication(new Bootstrapper(), config) {

            {
                if (LOGGER.isDebugEnabled()) {
                    logLevel = LOG_DEBUG;
                } else if (LOGGER.isInfoEnabled()) {
                    logLevel = LOG_INFO;
                } else {
                    logLevel = LOG_ERROR;
                }
            }

            @Override
            public void debug(String tag, String message) {
                LOGGER.debug("{} : {}", tag, message);
            }

            @Override
            public void debug(String tag, String message, Throwable exception) {
                LOGGER.debug(tag + " : " + message, exception);
            }

            @Override
            public void error(String tag, String message) {
                LOGGER.error("{} : {}", tag, message);
            }

            @Override
            public void error(String tag, String message, Throwable exception) {
                LOGGER.error(tag + " : " + message, exception);
            }

            @Override
            public void log(String tag, String message) {
                LOGGER.info("{} : {}", tag, message);
            }

            @Override
            public void log(String tag, String message, Throwable exception) {
                LOGGER.info(tag + " : " + message, exception);
            }
        };
    }
}
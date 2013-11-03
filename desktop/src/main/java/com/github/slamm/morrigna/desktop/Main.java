package com.github.slamm.morrigna.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.github.slamm.morrigna.core.Bootstrapper;

public class Main {

    public static void main(String[] args) {
        LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
        config.useGL20 = true;
        @SuppressWarnings("unused")
        LwjglApplication app = new LwjglApplication(new Bootstrapper(), config);
    }
}
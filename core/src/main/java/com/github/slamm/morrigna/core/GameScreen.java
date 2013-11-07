package com.github.slamm.morrigna.core;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;

public class GameScreen extends ScreenAdapter {

    private final Stage stage;

    public GameScreen() {
        stage = new Stage();
        stage.addListener(new InputListener() {

            @Override
            public boolean keyUp(InputEvent event, int keycode) {
                if (keycode == Keys.ESCAPE) {
                    Gdx.app.exit();
                }
                return false;
            }
        });
    }

    @Override
    public void render(float delta) {
        stage.act(delta);
        Gdx.gl.glClearColor(0f, 0f, 0f, 1f);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        stage.draw();
    }

    @Override
    public void show() {
        Gdx.input.setInputProcessor(stage);
    }
}
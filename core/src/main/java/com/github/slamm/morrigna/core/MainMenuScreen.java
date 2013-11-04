package com.github.slamm.morrigna.core;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.github.slamm.morrigna.core.Bootstrapper.ScreenChanger;

public class MainMenuScreen extends ScreenAdapter {

    private final ScreenChanger screenChanger;

    private final Stage stage;

    public MainMenuScreen(ScreenChanger screenChanger) {
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
        this.screenChanger = screenChanger;
    }

    private static void addMenuItem(Table table, Skin skin, String name, ChangeListener listener) {
        TextButton button = new TextButton(name, skin, "menu-button");
        button.addListener(listener);
        table.pad(10).defaults().spaceBottom(10).space(5);
        table.add(button).colspan(3).size(300, 60).uniform().spaceBottom(10);
        table.row();
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
        FileHandle skinFile = Gdx.files.internal("uiskin.json");
        Skin skin = new Skin(skinFile);
        Table table = new Table(skin);
        table.setFillParent(true);
        stage.addActor(table);
        addMenuItem(table, skin, "New game", new ChangeListener() {

            @Override
            public void changed(ChangeEvent event, Actor actor) {
                screenChanger.change(new GameScreen());
            }
        });
        addMenuItem(table, skin, "Options", new ChangeListener() {

            @Override
            public void changed(ChangeEvent event, Actor actor) {
                screenChanger.change(new OptionsScreen(screenChanger));
            }
        });
        addMenuItem(table, skin, "Exit", new ChangeListener() {

            @Override
            public void changed(ChangeEvent event, Actor actor) {
                Gdx.app.exit();
            }
        });
    }
}
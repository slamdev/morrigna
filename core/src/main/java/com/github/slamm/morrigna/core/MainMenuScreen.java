package com.github.slamm.morrigna.core;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;

public class MainMenuScreen extends ScreenAdapter {

    private Game game;

    private Skin skin;

    private Stage stage;

    private Table table;

    public MainMenuScreen(Game game) {
        stage = new Stage();
        this.game = game;
    }

    @Override
    public void render(float delta) {
        stage.act(delta);
        Gdx.gl.glClearColor(0f, 0f, 0f, 1f);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        stage.draw();
        Table.drawDebug(stage);
    }

    @Override
    public void show() {
        Gdx.input.setInputProcessor(stage);
        FileHandle skinFile = Gdx.files.internal("uiskin.json");
        skin = new Skin(skinFile);
        table = new Table(skin);
        table.setFillParent(true);
        table.debug();
        stage.addActor(table);
        table.setWidth(stage.getWidth());
        table.setHeight(stage.getHeight());
        table.pad(10).defaults().spaceBottom(10).space(5);
        TextButton continueGameButton = new TextButton("Continue game", skin);
        table.add(continueGameButton).colspan(3).size(300, 60).uniform().spaceBottom(10);
        table.row();
        table.pad(10).defaults().spaceBottom(10);
        TextButton newGameButton = new TextButton("New game", skin);
        table.add(newGameButton).colspan(3).size(300, 60).uniform().spaceBottom(10);
        table.row();
        table.pad(10).defaults().spaceBottom(10);
        TextButton optionsButton = new TextButton("Options", skin);
        optionsButton.addListener(new ChangeListener() {

            @Override
            public void changed(ChangeEvent event, Actor actor) {
                game.setScreen(new OptionsScreen(game));
            }
        });
        table.add(optionsButton).colspan(3).size(300, 60).uniform().spaceBottom(10);
        table.row();
        table.pad(10).defaults().spaceBottom(10);
        TextButton exitButton = new TextButton("Exit", skin);
        exitButton.addListener(new ChangeListener() {

            @Override
            public void changed(ChangeEvent event, Actor actor) {
                Gdx.app.exit();
            }
        });
        table.add(exitButton).colspan(3).size(300, 60).uniform().spaceBottom(10);
        table.row();
        table.pad(10).defaults().spaceBottom(10);
    }
}
package com.github.slamm.morrigna.core;

import java.util.Locale;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.CheckBox;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Slider;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

public class OptionsScreen extends ScreenAdapter {

    private Game game;

    private Skin skin;

    private Stage stage;

    private Table table;

    public OptionsScreen(Game game) {
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
        table.defaults().spaceBottom(30);
        table.columnDefaults(0).padRight(20);
        table.add("Options").colspan(3);
        table.row();
        table.add("Sound Effects");
        final CheckBox soundEffectsCheckbox = new CheckBox("", skin);
        table.add(soundEffectsCheckbox).colspan(2).left();
        table.row();
        table.add("Music");
        final CheckBox musicCheckbox = new CheckBox("", skin);
        table.add(musicCheckbox).colspan(2).left();
        // add the volume row
        table.row();
        table.add("Volume");
        // range is [0.0,1.0]; step is 0.1f
        Slider volumeSlider = new Slider(0f, 1f, 0.1f, false, skin);
        table.add(volumeSlider);
        // create the volume label
        Label volumeValue = new Label("", skin);
        table.add(volumeValue).width(40);
        float volume = 50;
        volumeValue.setText(String.format(Locale.US, "%1.0f%%", volume));
        TextButton backButton = new TextButton("Back to Main Menu", skin);
        backButton.addListener(new ClickListener() {

            @Override
            public void clicked(InputEvent event, float x, float y) {
                game.setScreen(new MainMenuScreen(game));
            }
        });
        table.row();
        table.add(backButton).size(250, 60).colspan(3);
    }
}
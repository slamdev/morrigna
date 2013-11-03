package com.github.slamm.morrigna.core;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;

public class GameScreen extends ScreenAdapter {

    // the fixed viewport dimensions (ratio: 1.6)
    public static final int GAME_VIEWPORT_WIDTH = 400, GAME_VIEWPORT_HEIGHT = 240;

    public static final int MENU_VIEWPORT_WIDTH = 800, MENU_VIEWPORT_HEIGHT = 480;

    private OrthographicCamera camera;

    private Stage hud = new Stage();

    private Game game;

    private OrthogonalTiledMapRenderer renderer;

    private Stage stage;

    public GameScreen(Game game) {
        stage = new Stage();
        this.game = game;
    }

    @Override
    public void render(float delta) {
        stage.act(delta);
        Gdx.gl.glClearColor(0.55f, 0.55f, 0.55f, 1f);
        Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
        stage.draw();
        camera.update();
        renderer.setView(camera);
        renderer.render();
        hud.act();
        hud.draw();
    }

    @Override
    public void show() {
        Gdx.input.setInputProcessor(stage);
        float w = Gdx.graphics.getWidth();
        float h = Gdx.graphics.getHeight();
        camera = new OrthographicCamera();
        camera.setToOrtho(false, w / h * 10, 10);
        camera.update();
        TiledMap map = new TmxMapLoader().load("map-atlases/level1.tmx");
        renderer = new OrthogonalTiledMapRenderer(map, 1f / 32f);
        stage.setCamera(camera);
        FileHandle skinFile = Gdx.files.internal("uiskin.json");
        Skin skin = new Skin(skinFile);
        hud.addActor(new Label("test", skin));
        Gdx.input.setInputProcessor(new InputMultiplexer(hud));
    }
}
package com.github.slamm.morrigna.core;

import com.artemis.World;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;

public class GameScreen extends ScreenAdapter {

    private OrthographicCamera camera;

    private final Stage stage;

    private World world;

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
        Gdx.gl.glClearColor(0.55f, 0.55f, 0.55f, 1f);
        Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
        camera.update();
        world.setDelta(delta);
        world.process();
        stage.act(delta);
        stage.draw();
    }

    @Override
    public void show() {
        initCamera();
        initES();
        Gdx.input.setInputProcessor(stage);
    }

    private void initCamera() {
        camera = new OrthographicCamera();
        camera.setToOrtho(false, Gdx.graphics.getWidth() / Gdx.graphics.getHeight() * 10, 10);
        camera.update();
        stage.setCamera(camera);
    }

    private void initES() {
        world = new World();
        world.setSystem(new MapRenderingSystem());
        world.setSystem(new SpriteRenderSystem());
        world.initialize();
        EntityFactory.map(world, "map-atlases/level1.tmx", camera).addToWorld();
        EntityFactory.sprite(world, "sprite/bobargb8888-32x32.png").addToWorld();
    }
}
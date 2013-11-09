package com.github.slamm.morrigna.core;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.github.slamm.morrigna.core.hud.HudRenderSystem;
import com.github.slamm.morrigna.core.map.MapRenderSystem;
import com.reed.birdseye.CollisionDetection;
import com.reed.birdseye.Fishing;
import com.reed.birdseye.Food;
import com.reed.birdseye.House;
import com.reed.birdseye.SaveAndLoad;

public class GameScreen extends ScreenAdapter {

    /**
     * static for getting and setting position during save / load
     */
    public static OrthographicCamera mapCamera;

    private final SpriteBatch batch;

    private final OrthographicCamera camera;

    private final CollisionDetection collision;

    private final BitmapFont currentFont;

    private final Fishing fishing;

    private final Food food;

    private final House house;

    private HudRenderSystem hudSystem;

    private MapRenderSystem mapSystem;

    public GameScreen() {
        float w = Gdx.graphics.getWidth();
        float h = Gdx.graphics.getHeight();
        batch = new SpriteBatch();
        camera = new OrthographicCamera(w, h);
        mapCamera = new OrthographicCamera(w, h);
        camera.update();
        mapCamera.update();
        // translate HUD camera to make bottom left cordinate 0,0
        camera.translate(w / 2, h / 2);
        // translate camera to spawn point
        mapCamera.translate(1422 + 16, 3562 + 24);
        collision = new CollisionDetection();
        fishing = new Fishing();
        house = new House();
        currentFont = new BitmapFont();
        food = new Food();
    }

    @Override
    public void hide() {
        SaveAndLoad.save();
    }

    @Override
    public void render(float delta) {
        // update
        mapSystem.update();
        hudSystem.update();
        collision.doCollision();
        fishing.update();
        fishing.fishCaught();
        house.update();
        food.affectHealth();
        food.looseHunger();
        // draw
        Gdx.gl.glClearColor(0f, 0f, 0f, 1f);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        hudSystem.renderLevel();
        mapSystem.render(delta);
        hudSystem.render(delta);
    }

    @Override
    public void show() {
        hudSystem = new HudRenderSystem(batch, camera, currentFont);
        mapSystem = new MapRenderSystem(batch, mapCamera, currentFont);
        SaveAndLoad.load();
    }
}
package com.github.slamm.morrigna.core;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.GL20;
import com.github.slamm.morrigna.core.hud.HudRenderSystem;
import com.github.slamm.morrigna.core.map.MapRenderSystem;
import com.reed.birdseye.CollisionDetection;
import com.reed.birdseye.Fishing;
import com.reed.birdseye.Food;
import com.reed.birdseye.House;
import com.reed.birdseye.SaveAndLoad;

public class GameScreen extends ScreenAdapter {

    private final CollisionDetection collision;

    private final Fishing fishing;

    private final Food food;

    private final House house;

    private HudRenderSystem hudSystem;

    private MapRenderSystem mapSystem;

    public GameScreen() {
        collision = new CollisionDetection();
        fishing = new Fishing();
        house = new House();
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
        hudSystem = new HudRenderSystem();
        mapSystem = new MapRenderSystem();
        SaveAndLoad.load();
    }
}
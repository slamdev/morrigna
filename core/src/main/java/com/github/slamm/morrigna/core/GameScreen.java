package com.github.slamm.morrigna.core;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.GL20;
import com.github.slamm.morrigna.core.hud.HudRenderSystem;
import com.github.slamm.morrigna.core.map.MapRenderSystem;
import com.github.slamm.morrigna.core.process.ProcessUpdateSystem;
import com.reed.birdseye.SaveAndLoad;

public class GameScreen extends ScreenAdapter {

    private HudRenderSystem hudSystem;

    private MapRenderSystem mapSystem;

    private ProcessUpdateSystem processSysem;

    @Override
    public void hide() {
        SaveAndLoad.save();
    }

    @Override
    public void render(float delta) {
        // update
        mapSystem.update();
        hudSystem.update();
        processSysem.update();
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
        processSysem = new ProcessUpdateSystem();
        SaveAndLoad.load();
    }
}
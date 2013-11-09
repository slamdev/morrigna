package com.github.slamm.morrigna.core.map;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.github.slamm.morrigna.core.Assets;

public class MapRenderer {

    public static OrthogonalTiledMapRenderer renderer;

    public static float xRate = 0;

    public static float yRate = 0;

    public MapRenderer() {
        renderer = new OrthogonalTiledMapRenderer(Assets.mainTiledMap);
    }

    public void render(OrthographicCamera camera) {
        camera.translate(xRate, yRate);
        camera.update();
        renderer.setView(camera);
        renderer.render();
    }
}
package com.github.slamm.morrigna.core.map;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.reed.birdseye.SwordShopOwner;
import com.reed.birdseye.TradeShopOwner;

public class MapRenderSystem {

    private final SpriteBatch batch;

    private final OrthographicCamera camera;

    private final BitmapFont currentForn;

    private final SwordShopOwner swordShopOwner;

    private final TradeShopOwner tradeShopOwner;

    public MapRenderSystem(SpriteBatch batch, OrthographicCamera camera, BitmapFont currentFont) {
        this.batch = batch;
        this.camera = camera;
        currentForn = currentFont;
        swordShopOwner = new SwordShopOwner();
        tradeShopOwner = new TradeShopOwner();
    }

    public void render(float delta) {
        batch.begin();
        batch.setProjectionMatrix(camera.combined);
        swordShopOwner.draw(batch);
        tradeShopOwner.draw(batch);
        batch.end();
    }

    public void update() {
    }
}
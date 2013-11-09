package com.github.slamm.morrigna.core.map;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.reed.birdseye.Particles;

public class MapRenderSystem {

    /**
     * static to modify when entering different map areas.
     */
    public static MapRenderer mapRenderer;

    private final SpriteBatch batch;

    private final OrthographicCamera camera;

    private final BitmapFont currentFont;

    private final PlayerRenderer playerRenderer;

    private final Particles smokePracticle;

    private final SwordShopOwnerRenderer swordShopOwnerRenderer;

    private final TradeShopOwnerRenderer tradeShopOwnerRenderer;

    public MapRenderSystem(SpriteBatch batch, OrthographicCamera camera, BitmapFont currentFont) {
        this.batch = batch;
        this.camera = camera;
        this.currentFont = currentFont;
        swordShopOwnerRenderer = new SwordShopOwnerRenderer();
        tradeShopOwnerRenderer = new TradeShopOwnerRenderer();
        playerRenderer = new PlayerRenderer();
        mapRenderer = new MapRenderer();
        smokePracticle = new Particles();
        InputMultiplexer multiplexer;
        if (Gdx.input.getInputProcessor() instanceof InputMultiplexer) {
            multiplexer = (InputMultiplexer) Gdx.input.getInputProcessor();
        } else {
            multiplexer = new InputMultiplexer(Gdx.input.getInputProcessor());
        }
        Gdx.input.setInputProcessor(multiplexer);
    }

    public void render(float delta) {
        mapRenderer.render(camera);
        batch.begin();
        batch.setProjectionMatrix(camera.combined);
        swordShopOwnerRenderer.draw(batch);
        tradeShopOwnerRenderer.draw(batch);
        playerRenderer.draw(batch);
        smokePracticle.smokeUpdateAndDraw(batch, delta);
        batch.end();
    }

    public void update() {
        playerRenderer.update();
    }
}
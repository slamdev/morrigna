package com.github.slamm.morrigna.core.hud;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.reed.birdseye.Points;

public class HudRenderSystem {

    private final SpriteBatch batch;

    private final OrthographicCamera camera;

    private final BitmapFont currentFont;

    private final HouseFurnaceRenderer houseFurnaceRenderer;

    private final InventoryRenderer inventoryRenderer;

    private final MessagesRenderer messagesRenderer;

    private final Points points;

    private final ShapeRenderer shapeRenderer;

    private final SwordShopRenderer swordShopRenderer;

    private final ToolsListRenderer toolsListRenderer;

    private final TopMenuRenderer topMenuRenderer;

    private final TradeShopRenderer tradeShopRenderer;

    public HudRenderSystem(SpriteBatch batch, OrthographicCamera camera, Points points, BitmapFont currentFont) {
        this.batch = batch;
        this.camera = camera;
        this.points = points;
        this.currentFont = currentFont;
        messagesRenderer = new MessagesRenderer();
        inventoryRenderer = new InventoryRenderer();
        swordShopRenderer = new SwordShopRenderer();
        tradeShopRenderer = new TradeShopRenderer();
        houseFurnaceRenderer = new HouseFurnaceRenderer();
        shapeRenderer = new ShapeRenderer();
        toolsListRenderer = new ToolsListRenderer();
        topMenuRenderer = new TopMenuRenderer();
        InputMultiplexer multiplexer;
        if (Gdx.input.getInputProcessor() instanceof InputMultiplexer) {
            multiplexer = (InputMultiplexer) Gdx.input.getInputProcessor();
        } else {
            multiplexer = new InputMultiplexer(Gdx.input.getInputProcessor());
        }
        multiplexer.addProcessor(topMenuRenderer);
        multiplexer.addProcessor(inventoryRenderer);
        multiplexer.addProcessor(swordShopRenderer);
        multiplexer.addProcessor(houseFurnaceRenderer);
        multiplexer.addProcessor(tradeShopRenderer);
        Gdx.input.setInputProcessor(multiplexer);
    }

    public void render(float delta) {
        batch.begin();
        batch.setProjectionMatrix(camera.combined);
        toolsListRenderer.render(batch);
        topMenuRenderer.render(batch);
        points.draw(batch);
        messagesRenderer.render(currentFont, batch);
        inventoryRenderer.render(currentFont, batch);
        swordShopRenderer.render(currentFont, batch);
        tradeShopRenderer.render(currentFont, batch);
        houseFurnaceRenderer.render(delta, currentFont, batch);
        batch.end();
        shapeRenderer.setProjectionMatrix(camera.combined);
        points.drawBars(shapeRenderer);
    }

    public void update() {
        messagesRenderer.update();
        swordShopRenderer.update();
        houseFurnaceRenderer.update();
        tradeShopRenderer.update();
    }
}
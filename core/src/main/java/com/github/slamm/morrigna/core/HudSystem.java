package com.github.slamm.morrigna.core;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.reed.birdseye.House;
import com.reed.birdseye.Inventory;
import com.reed.birdseye.Messages;
import com.reed.birdseye.Points;
import com.reed.birdseye.SwordShop;
import com.reed.birdseye.TradeShop;
import com.reed.birdseye.Tutorial;

public class HudSystem {

    public static class TopMenu extends InputAdapter {

        /**
         * 0 is first cell, etc, 5 means null conluding that it is not drawn
         */
        public static int currentTool = 5;

        public void draw(SpriteBatch batch) {
            batch.draw(Assets.itemSelector, Gdx.graphics.getWidth() / 2 - 250, Gdx.graphics.getHeight() - 75);
            // decides where the currentTool white box is drawn - please excuse these *random* numbers
            switch (currentTool) {
            case 0:
                batch.draw(Assets.currentItem, 230, 465);
                break;
            case 1:
                batch.draw(Assets.currentItem, 230 + Assets.currentItem.getWidth() * 1 - 7 * 1, 465);
                break;
            case 2:
                batch.draw(Assets.currentItem, 230 + Assets.currentItem.getWidth() * 2 - 7 * 2, 465);
                break;
            case 3:
                batch.draw(Assets.currentItem, 230 + Assets.currentItem.getWidth() * 3 - 7 * 3, 465);
                break;
            case 4:
                batch.draw(Assets.currentItem, 230 + Assets.currentItem.getWidth() * 4 - 7 * 4, 465);
                break;
            default:
                break; // seems it 5
            }
        }

        @Override
        public boolean touchUp(int x, int y, int pointer, int button) {
            if (x > 649 && x < 723 && y > 6 && y < 68) {
                // opens invertory
                Inventory.inventoryVisible = true;
            } else if (y < 74 && y > 0 && x > 230 && x < 310) {
                currentTool = 0;
            } else if (y < 74 && y > 0 && x > 310 && x < 390) {
                currentTool = 1;
            } else if (y < 74 && y > 0 && x > 390 && x < 470) {
                currentTool = 2;
            } else if (y < 74 && y > 0 && x > 470 && x < 550) {
                currentTool = 3;
            } else if (y < 74 && y > 0 && x > 550 && x < 630) {
                currentTool = 4;
            } else {
                return false;
            }
            return true;
        }
    }

    private static class ToolsListRenderer {

        private static final int BOX_WIDTH = 80;

        /**
         * draws at appropriate x coordinate
         */
        private static final int TOOL_X = 12;

        /**
         * draws at 65 from top of screen as shown below
         */
        private static final int TOOL_Y = 65;

        private final int startOfTopBar;

        public ToolsListRenderer() {
            startOfTopBar = Gdx.graphics.getWidth() / 2 - Assets.itemSelector.getWidth() / 2;
        }

        public void render(SpriteBatch batch) {
            // draw sword
            if (Tutorial.step >= 7) {
                batch.draw(Assets.toolsMasterAtlas.findRegion("w_shortsword_0"),
                        startOfTopBar + 3 * BOX_WIDTH + TOOL_X, Gdx.graphics.getHeight() - TOOL_Y, 70, 70);
            }
            // draw pick axe
            batch.draw(Assets.toolsMasterAtlas.findRegion("pick"), startOfTopBar + 0 * BOX_WIDTH + TOOL_X,
                    Gdx.graphics.getHeight() - TOOL_Y, 50, 50);
            if (Tutorial.step >= 5) {
                // draw hatchet
                batch.draw(Assets.toolsMasterAtlas.findRegion("gear_swords"), startOfTopBar + 2 * BOX_WIDTH + TOOL_X,
                        Gdx.graphics.getHeight() - TOOL_Y, 50, 50);
            }
            if (Tutorial.step >= 2) {
                // draw fishing rod
                batch.draw(Assets.toolsMasterAtlas.findRegion("fishingrod"), startOfTopBar + 1 * BOX_WIDTH + TOOL_X,
                        Gdx.graphics.getHeight() - TOOL_Y, 80, 80);
            }
        }
    }

    private final SpriteBatch batch;

    private final OrthographicCamera camera;

    private BitmapFont currentFont;

    private House house;

    private Inventory inv;

    private Messages message;

    private Points points;

    private ShapeRenderer shapeRenderer;

    private SwordShop swordShop;

    private ToolsListRenderer toolsListRenderer;

    private TopMenu topMenu;

    private TradeShop tradeShop;

    public HudSystem(SpriteBatch batch, OrthographicCamera camera, Points points, Messages message, Inventory inv,
            SwordShop swordShop, TradeShop trade, House house, BitmapFont currentFont) {
        this.batch = batch;
        this.camera = camera;
        this.points = points;
        this.message = message;
        this.inv = inv;
        this.swordShop = swordShop;
        tradeShop = trade;
        this.house = house;
        this.currentFont = currentFont;
        shapeRenderer = new ShapeRenderer();
        toolsListRenderer = new ToolsListRenderer();
        topMenu = new TopMenu();
        InputMultiplexer multiplexer;
        if (Gdx.input.getInputProcessor() instanceof InputMultiplexer) {
            multiplexer = (InputMultiplexer) Gdx.input.getInputProcessor();
        } else {
            multiplexer = new InputMultiplexer(Gdx.input.getInputProcessor());
        }
        multiplexer.addProcessor(topMenu);
        Gdx.input.setInputProcessor(multiplexer);
    }

    public void render(float delta) {
        batch.begin();
        batch.setProjectionMatrix(camera.combined);
        toolsListRenderer.render(batch);
        topMenu.draw(batch);
        points.draw(batch);
        message.drawText(currentFont, batch);
        inv.draw(batch, currentFont);
        swordShop.drawInputText(batch, currentFont);
        tradeShop.drawInputText(batch, currentFont);
        house.furnaceGUIdraw(batch, delta, currentFont);
        batch.end();
        shapeRenderer.setProjectionMatrix(camera.combined);
        points.drawBars(shapeRenderer);
    }

    public void update() {
    }
}
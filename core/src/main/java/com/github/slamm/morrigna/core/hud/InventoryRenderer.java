package com.github.slamm.morrigna.core.hud;

import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.github.slamm.morrigna.core.Assets;
import com.github.slamm.morrigna.core.map.PlayerRenderer;
import com.github.slamm.morrigna.core.process.FishingUpdater;
import com.github.slamm.morrigna.core.process.FoodUpdater;
import com.reed.birdseye.Coal;
import com.reed.birdseye.Tree;

public class InventoryRenderer extends InputAdapter {

    static boolean inventoryVisible;

    private static final int ROW_1_Y = 420;

    private static final int ROW_2_Y = 800;

    @Override
    public boolean keyUp(int keycode) {
        if (Keys.ESCAPE == keycode) {
            PlayerRenderer.ableToMove = true;
            PlayerRenderer.drawCharacter = true;
            inventoryVisible = false;
            return true;
        }
        return false;
    }

    public void render(BitmapFont font, SpriteBatch batch) {
        if (inventoryVisible) {
            batch.draw(Assets.inventory, 0, 0);
            // move somewhere else
            PlayerRenderer.ableToMove = false;
            PlayerRenderer.drawCharacter = false;
            // draw amounts
            font.draw(batch, Tree.amountOfWoodString, ROW_1_Y, 388);
            font.draw(batch, Coal.amountOfCoalString, ROW_1_Y, 356);
            font.draw(batch, String.valueOf(FishingUpdater.count), ROW_1_Y, 330);
            font.draw(batch, String.valueOf(FoodUpdater.count), ROW_1_Y, 302);
            // row 2
            font.draw(batch, TradeShopRenderer.cashString, ROW_2_Y, 135);
            font.draw(batch, String.valueOf(FoodUpdater.foodLevel) + "%", ROW_2_Y, 105);
            font.draw(batch, String.valueOf(PointsRenderer.currentLevel), ROW_2_Y, 76);
        }
    }
}
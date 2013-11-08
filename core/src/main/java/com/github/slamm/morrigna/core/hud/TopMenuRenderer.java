package com.github.slamm.morrigna.core.hud;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.github.slamm.morrigna.core.Assets;

public class TopMenuRenderer extends InputAdapter {

    /**
     * 0 is first cell, etc, 5 means null conluding that it is not drawn
     */
    public static int currentTool = 5;

    public void render(SpriteBatch batch) {
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
            InventoryRenderer.inventoryVisible = true;
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
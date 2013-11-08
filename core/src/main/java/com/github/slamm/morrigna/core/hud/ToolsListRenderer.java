package com.github.slamm.morrigna.core.hud;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.github.slamm.morrigna.core.Assets;
import com.reed.birdseye.Tutorial;

class ToolsListRenderer {

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
            batch.draw(Assets.toolsMasterAtlas.findRegion("w_shortsword_0"), startOfTopBar + 3 * BOX_WIDTH + TOOL_X,
                    Gdx.graphics.getHeight() - TOOL_Y, 70, 70);
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
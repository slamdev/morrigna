package com.github.slamm.morrigna.core.hud;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.github.slamm.morrigna.core.Assets;
import com.github.slamm.morrigna.core.map.MapRenderer;

public class LevelRenderer {

    /**
     * current map (uses integers for saving);
     * 0 is Main map, 1 is river house
     */
    public static int currentMap = 0;

    public static int grassX = -64; // position for bottom left grass

    public static int grassY = -64; // ^

    public static float middleX = Gdx.graphics.getWidth() / 2 - 16;

    public static float middleY = Gdx.graphics.getHeight() / 2 - 24;

    private static void setMap() {
        switch (currentMap) {
        case 0: {
            MapRenderer.renderer.setMap(Assets.mainTiledMap);
            break;
        }
        case 1: {
            MapRenderer.renderer.setMap(Assets.riverHouse);
            break;
        }
        default:
            throw new RuntimeException("Should not get here");
        }
    }

    public void draw(SpriteBatch batch) {
        // draws grass image (64 , 64) everywhere
        for (int i = 0; i < Gdx.graphics.getWidth() + 128; i += 64) {
            for (int j = 0; j < Gdx.graphics.getHeight() + 128; j += 64) {
                batch.draw(Assets.grass, grassX + i, grassY + j);
            }
        }
    }

    public void update() {
        // sets map back to original position before white background becomes
        // visible
        if (grassY <= -128 || grassY >= 0) {
            grassY = -64;
        }
        if (grassX <= -128 || grassX >= 0) {
            grassX = -64;
        }
        setMap();
    }
}
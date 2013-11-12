package com.reed.birdseye;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.github.slamm.morrigna.core.Assets;
import com.github.slamm.morrigna.core.hud.LevelRenderer;
import com.github.slamm.morrigna.core.hud.PointsRenderer;
import com.github.slamm.morrigna.core.hud.TopMenuRenderer;
import com.github.slamm.morrigna.core.map.PlayerRenderer;

@SuppressWarnings("unused")
public class Resource {

    public static int amountOfStone = 0;

    private static String amountOfStoneString;

    private final int distanceFromMaterial = 100;

    private boolean drawResource = true;

    private int height = 58;

    private final float miningRate = 1f;

    private float resourceTimer = 0;

    private int width = 64;

    private final int x;

    private final int y;

    public Resource(int x, int y) {
        this.x = x;
        this.y = y;
    }

    private boolean closeEnough() {
        return Math.sqrt((x - LevelRenderer.middleX) * (x - LevelRenderer.middleX) + (y - LevelRenderer.middleY)
                * (y - LevelRenderer.middleY)) < distanceFromMaterial;
    }

    private void collect() {
        amountOfStoneString = Integer.toString(amountOfStone);// update string
        // starts making the image of the "resource" smaller as b is
        // held down
        if (closeEnough() && drawResource) {
            if (Gdx.input.isKeyPressed(Keys.B) && TopMenuRenderer.currentTool == 1) {
                resourceTimer += Gdx.graphics.getDeltaTime() * miningRate;
                if (resourceTimer > 1 && resourceTimer < 2) {
                    width = (int) (64 * .8);
                    height = (int) (58 * .8);
                }
                if (resourceTimer > 2 && resourceTimer < 3) {
                    width = (int) (64 * .6);
                    height = (int) (58 * .6);
                }
                if (resourceTimer > 3 && resourceTimer < 4) {
                    width = (int) (64 * .4);
                    height = (int) (58 * .4);
                }
                if (resourceTimer > 4 && resourceTimer < 5) {
                    width = (int) (64 * .2);
                    height = (int) (58 * .2);
                }
                if (resourceTimer > 5) {
                    amountOfStone += 1;
                    resourceTimer = 0;
                    PointsRenderer.xp += 1;
                    drawResource = false;
                }
            } else {
                resourceTimer = 0;
                width = 64;
                height = 58;
            }
        }
    }

    private void draw(SpriteBatch batch, BitmapFont font) {
        if (drawResource) {
            batch.draw(Assets.material, x, y, width, height);
            if (closeEnough() && PlayerRenderer.ableToMove) {
                font.draw(batch, "Hold B to Pick up the Stone", 50, 50);
            }
        }
    }
}
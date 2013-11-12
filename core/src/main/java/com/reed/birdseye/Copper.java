package com.reed.birdseye;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.github.slamm.morrigna.core.Assets;
import com.github.slamm.morrigna.core.hud.LevelRenderer;
import com.github.slamm.morrigna.core.map.PlayerRenderer;

@SuppressWarnings("unused")
public class Copper {

    private static int amountOfCopper = 0;

    private static String amountOfCopperString;

    private float copperTimer = 0;

    private final int distanceFromMaterial = 100;

    private boolean drawResource = true;

    private int height = 58;

    private final float miningRate = 1f;

    private int width = 64;

    private final int x = 100;

    private final int y = 300;

    public void collect() {
        amountOfCopperString = Integer.toString(amountOfCopper);// update string
        // starts making the image of the "resource" smaller as b is
        // held down
        if (closeEnough()) {
            if (Gdx.input.isKeyPressed(Keys.B)) {
                copperTimer += Gdx.graphics.getDeltaTime() * miningRate;
                if (copperTimer > 1 && copperTimer < 2) {
                    width = (int) (64 * .8);
                    height = (int) (58 * .8);
                }
                if (copperTimer > 2 && copperTimer < 3) {
                    width = (int) (64 * .6);
                    height = (int) (58 * .6);
                }
                if (copperTimer > 3 && copperTimer < 4) {
                    width = (int) (64 * .4);
                    height = (int) (58 * .4);
                }
                if (copperTimer > 4 && copperTimer < 5) {
                    width = (int) (64 * .2);
                    height = (int) (58 * .2);
                }
                if (copperTimer > 5) {
                    amountOfCopper += 1;
                    copperTimer = 0;
                    drawResource = false;
                }
            } else {
                copperTimer = 0;
                width = 64;
                height = 58;
            }
        }
    }

    public void draw(SpriteBatch batch, BitmapFont font) {
        if (drawResource) {
            batch.draw(Assets.copperOre, x, y, width, height);
            if (closeEnough() && PlayerRenderer.ableToMove) {
                font.draw(batch, "Hold B to Pick up the Copper", 50, 50);
            }
        }
    }

    private boolean closeEnough() {
        return Math.sqrt(LevelRenderer.middleX * (x - LevelRenderer.middleX) + (y - LevelRenderer.middleY)
                * (y - LevelRenderer.middleY)) < distanceFromMaterial;
    }
}
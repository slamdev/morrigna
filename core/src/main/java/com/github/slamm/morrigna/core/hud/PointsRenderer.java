package com.github.slamm.morrigna.core.hud;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.github.slamm.morrigna.core.Assets;

public class PointsRenderer {

    public static int currentLevel = 0;

    /**
     * health points
     */
    public static int hp = 100;

    /**
     * experience points
     */
    public static int xp = 0;

    private static final int BAR_HEIGHT = 13;

    private static final int BAR_WIDTH = 126;

    /**
     * define positions to draw bars for each of these
     */
    private static final float HP_X = Gdx.graphics.getWidth() * .80f + 4;

    private static final float HP_Y = Gdx.graphics.getHeight() - Gdx.graphics.getHeight() * .1011f + 25;

    /**
     * The amount of xp points required to obtain that level. 2^(level)
     */
    private static final int LEVELS[] = { 2, 4, 6, 8, 16, 32, 64, 128, 256, 512, 1024, 2048 };

    private static final ShapeRenderer shapeRenderer = new ShapeRenderer();

    private static final float XP_X = Gdx.graphics.getWidth() * .80f + 4;

    private static final float XP_Y = Gdx.graphics.getHeight() - Gdx.graphics.getHeight() * .1011f + 5;

    private float hpWidth;

    private float xpWidth;

    public static void gainExperience(int amount) {
        xp += amount;
    }

    public static void gainHealth(int amount) {
        if (hp <= 100) {
            hp += amount;
        }
    }

    public static void looseHealth(int amount) {
        if (hp >= 0) {
            hp -= amount;
        }
    }

    public void render(OrthographicCamera camera, SpriteBatch batch) {
        // draws at a percentage to account for different screen sizes
        batch.draw(Assets.pointsBar, Gdx.graphics.getWidth() * .80f,
                Gdx.graphics.getHeight() - Gdx.graphics.getHeight() * .1011f);
        // done with shapeRender - it makes things easier this way
        batch.end();
        shapeRenderer.setProjectionMatrix(camera.combined);
        // update these booleans
        hpWidth = hp * BAR_WIDTH / 100;
        xpWidth = xp * BAR_WIDTH / LEVELS[currentLevel];
        shapeRenderer.begin(ShapeType.Filled);
        // draw hp in orange
        shapeRenderer.setColor(255, 179, 0, .02f);
        shapeRenderer.rect(HP_X, HP_Y, hpWidth, BAR_HEIGHT);
        // draw xp in blue
        shapeRenderer.setColor(0, 65, 162, 0);
        shapeRenderer.rect(XP_X, XP_Y, xpWidth, BAR_HEIGHT);
        shapeRenderer.end();
        batch.begin();
    }

    public void update() {
        // changes level if xp exceeds current level
        if (xp > LEVELS[currentLevel]) {
            currentLevel++;
            // sets xp back to 1 for next level
            xp = 1;
        }
    }
}
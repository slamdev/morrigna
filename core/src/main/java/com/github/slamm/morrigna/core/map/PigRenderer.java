package com.github.slamm.morrigna.core.map;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.utils.Array;
import com.reed.birdseye.ArrayListsz;
import com.reed.birdseye.Pig;

public class PigRenderer {

    public static final int SIZE = 4;

    private final Array<Pig> pigs = new Array<>();

    public PigRenderer() {
        for (int i = 0; i < SIZE; i++) {
            pigs.add(new Pig());
        }
    }

    public void render(OrthographicCamera camera, SpriteBatch batch) {
        for (int i = 0; i < pigs.size; i++) {
            pigs.get(i).drops(batch);
            pigs.get(i).pig.draw(batch, pigs.get(i).theRealPig);
            pigs.get(i).update();
            pigs.get(i).regeneration();
            for (int j = 0; j < ArrayListsz.coalArray.size; j++) {
                ArrayListsz.coalArray.get(j).mobCollision(pigs.get(i).pig);
            }
        }
        batch.end();
        pigHealthBars(camera);
        batch.begin();
    }

    private void pigHealthBars(OrthographicCamera camera) {
        ShapeRenderer shapeRenderer = new ShapeRenderer();
        shapeRenderer.setProjectionMatrix(camera.combined);
        for (int i = 0; i < pigs.size; i++) {
            pigs.get(i).pig.healthBar(shapeRenderer, pigs.get(i).theRealPig);
        }
    }
}
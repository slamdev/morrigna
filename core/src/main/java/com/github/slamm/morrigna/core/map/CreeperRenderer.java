package com.github.slamm.morrigna.core.map;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.utils.Array;
import com.github.slamm.morrigna.core.Assets;
import com.reed.birdseye.Mob;

public class CreeperRenderer {

    private static final int SIZE = 2;

    private final Array<Mob> creepers = new Array<>();

    public CreeperRenderer() {
        for (int i = 0; i < SIZE; i++) {
            creepers.add(new Mob(1562, 1264, 1000, 918));
        }
    }

    public void render(OrthographicCamera camera, SpriteBatch batch) {
        for (int i = 0; i < creepers.size; i++) {
            creepers.get(i).draw(batch, creepers.get(i).realMob);
        }
        batch.end();
        mobHealthBars(camera);
        batch.begin();
    }

    public void update() {
        for (int i = 0; i < creepers.size; i++) {
            creepers.get(i).realMob = creepers.get(i).setSprites(Assets.upCreeper_STILL, Assets.upCreeper_LEFT,
                    Assets.upCreeper_RIGHT, Assets.downCreeper_STILL, Assets.downCreeper_LEFT,
                    Assets.downCreeper_RIGHT, Assets.leftCreeper_STILL, Assets.leftCreeper_LEFT,
                    Assets.leftCreeper_RIGHT, Assets.rightCreeper_STILL, Assets.rightCreeper_LEFT,
                    Assets.rightCreeper_RIGHT);
            creepers.get(i).movement();
            creepers.get(i).attack();
            creepers.get(i).looseHealth();
            creepers.get(i).boundingArea();
            creepers.get(i).follow();
            creepers.get(i).detectIfUnderAttack();
            creepers.get(i).regeneration();
            for (int j = 0; j < CoalRenderer.coalArray.size; j++) {
                // if not under attack
                if (!creepers.get(i).underAttack) {
                    CoalRenderer.coalArray.get(j).mobCollision(creepers.get(i));
                } else { // assume that it is under attack then
                    CoalRenderer.coalArray.get(j).mobUnderAttackCollision(creepers.get(i));
                }
            }
        }
    }

    private void mobHealthBars(OrthographicCamera camera) {
        ShapeRenderer shapeRenderer = new ShapeRenderer();
        shapeRenderer.setProjectionMatrix(camera.combined);
        for (int i = 0; i < creepers.size; i++) {
            creepers.get(i).healthBar(shapeRenderer, creepers.get(i).realMob);
        }
    }
}
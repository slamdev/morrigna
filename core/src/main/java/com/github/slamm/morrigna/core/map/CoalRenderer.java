package com.github.slamm.morrigna.core.map;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Array;
import com.reed.birdseye.Coal;

public class CoalRenderer {

    public static Array<Coal> coalArray = new Array<>();

    public CoalRenderer() {
        coalArray.add(new Coal(2290, 1798));
        coalArray.add(new Coal(1618, 2022));
        coalArray.add(new Coal(1844, 1652));
    }

    public void render(SpriteBatch batch) {
        for (int i = 0; i < coalArray.size; i++) {
            coalArray.get(i).draw(batch);
        }
    }

    public void update() {
        for (int i = 0; i < coalArray.size; i++) {
            coalArray.get(i).collect();
            coalArray.get(i).regeneration();
            coalArray.get(i).playerCollision();
        }
    }
}
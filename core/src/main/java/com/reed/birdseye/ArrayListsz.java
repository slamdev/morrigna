package com.reed.birdseye;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Array;

public class ArrayListsz {

    public static Array<Coal> coalArray = new Array<>();

    public void drawCoal(SpriteBatch batch) {
        for (int i = 0; i < coalArray.size; i++) {
            coalArray.get(i).draw(batch);
        }
    }

    public void updateCoal() {
        for (int i = 0; i < coalArray.size; i++) {
            coalArray.get(i).collect();
            coalArray.get(i).regeneration();
            coalArray.get(i).playerCollision();
        }
    }

    void coalArrayEstablisher() {
        coalArray.add(new Coal(2290, 1798));
        coalArray.add(new Coal(1618, 2022));
        coalArray.add(new Coal(1844, 1652));
    }
}

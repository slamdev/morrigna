package com.github.slamm.morrigna.core.map;

import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Array;
import com.reed.birdseye.Tree;

public class TreeRenderer {

    private final int size = 2;

    private final Array<Tree> trees = new Array<>();

    public TreeRenderer() {
        for (int i = 0; i < size; i++) {
            trees.add(new Tree());
        }
        trees.get(0).x = 1258;
        trees.get(0).y = 2236;
        trees.get(1).x = 1158;
        trees.get(1).y = 2016;
    }

    public void render(BitmapFont font, SpriteBatch batch) {
        for (int i = 0; i < trees.size; i++) {
            trees.get(i).drawTrunk(batch);
            trees.get(i).draw(batch, font);
            trees.get(i).closeEnough();
            trees.get(i).collectingTree();
            trees.get(i).leavesFall();
            trees.get(i).collision();
        }
    }
}
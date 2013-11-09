package com.github.slamm.morrigna.core.map;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.github.slamm.morrigna.core.Assets;

public class SwordShopOwnerRenderer {

    public static final int X = 2473;

    public static final int Y = 2392;

    public void draw(SpriteBatch batch) {
        batch.draw(Assets.shopOwner, X, Y);
    }
}
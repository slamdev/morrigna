package com.reed.birdseye;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.github.slamm.morrigna.core.Assets;

public class TradeShopOwner {

    public static final int X = 1838;

    public static final int Y = 2906;

    public void draw(SpriteBatch batch) {
        batch.draw(Assets.tradePerson, X, Y);
    }
}
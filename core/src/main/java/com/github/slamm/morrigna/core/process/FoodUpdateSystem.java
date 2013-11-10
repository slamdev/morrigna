package com.github.slamm.morrigna.core.process;

import com.badlogic.gdx.Gdx;
import com.github.slamm.morrigna.core.hud.PointsRenderer;

public class FoodUpdateSystem {

    public static int count = 0;

    public static int foodLevel = 100;

    private float timer = 0;

    private float timer2 = 0;

    public void update() {
        timer += Gdx.graphics.getDeltaTime();
        if (foodLevel == 0 && timer > 2) {
            PointsRenderer.looseHealth(1);
            timer = 0;
        }
        if (foodLevel >= 80 && timer > 2 && PointsRenderer.hp < 100) {
            PointsRenderer.gainHealth(5);
            timer = 0;
        }
        looseHunger();
    }

    private void looseHunger() {
        timer2 += Gdx.graphics.getDeltaTime();
        if (timer2 > 2 && foodLevel > 0) {
            foodLevel -= 1;
            timer2 = 0;
        }
    }
}
package com.reed.birdseye;

import com.badlogic.gdx.Gdx;
import com.github.slamm.morrigna.core.hud.PointsRenderer;

public class Food {

    // make food level
    // make health go down if low on food, and display message (need food now!)
    // contain integer for amount of food
    public static int amountOfFood = 0;

    public static String amountOfFoodString;

    public static int foodLevel = 100;

    float timer = 0;

    float timer2 = 0;

    public void affectHealth() {
        amountOfFoodString = Integer.toString(amountOfFood);
        timer += Gdx.graphics.getDeltaTime();
        if (foodLevel == 0 && timer > 2) {
            PointsRenderer.looseHealth(1);
            timer = 0;
        }
        if (foodLevel >= 80 && timer > 2 && PointsRenderer.hp < 100) {
            PointsRenderer.gainHealth(5);
            timer = 0;
        }
    }

    public void looseHunger() {
        timer2 += Gdx.graphics.getDeltaTime();
        if (timer2 > 2 && foodLevel > 0) {
            foodLevel -= 1;
            timer2 = 0;
        }
    }
}

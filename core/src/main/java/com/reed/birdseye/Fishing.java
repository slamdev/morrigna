package com.reed.birdseye;

import java.util.Random;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.github.slamm.morrigna.core.hud.MessagesRenderer;
import com.github.slamm.morrigna.core.hud.PointsRenderer;
import com.github.slamm.morrigna.core.hud.TopMenuRenderer;
import com.github.slamm.morrigna.core.map.PlayerRenderer;

public class Fishing {

    public static int amountOfFish = 0;

    public static String amountOfFishString;

    String caught = "You have caught a fish!";

    boolean fishCaught = false;

    Random r = new Random();

    float timer;

    public void fishCaught() {
        if (fishingPossible() && Gdx.input.isKeyPressed(Keys.B) && timer > 1) {
            if (r.nextInt(4) == 2) {
                fishCaught = true;
            } else {
                timer = 0;
            }
        }
        if (fishCaught) {
            MessagesRenderer.add(caught);
            amountOfFish += 1;
            PointsRenderer.xp += 1;
            timer = 0;
            fishCaught = false;
        }
    }

    public void update() {
        // update fish string
        amountOfFishString = Integer.toString(amountOfFish);
        if (Gdx.input.isKeyPressed(Keys.B)) {
            timer += Gdx.graphics.getDeltaTime();
        }
    }

    // detects if you have the ability to fish
    boolean fishingPossible() {
        return PlayerRenderer.x > 700 && PlayerRenderer.x < 758 && PlayerRenderer.y > 1896 && PlayerRenderer.y < 2036
                && TopMenuRenderer.currentTool == 1;
    }
}

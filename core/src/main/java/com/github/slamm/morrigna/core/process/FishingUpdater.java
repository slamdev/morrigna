package com.github.slamm.morrigna.core.process;

import java.util.Random;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.github.slamm.morrigna.core.hud.MessagesRenderer;
import com.github.slamm.morrigna.core.hud.PointsRenderer;
import com.github.slamm.morrigna.core.hud.TopMenuRenderer;
import com.github.slamm.morrigna.core.map.PlayerRenderer;

public class FishingUpdater {

    public static int count = 0;

    private static final String CAUGHT = "You have caught a fish!";

    private static final Random RANDOM = new Random();

    private boolean fishCaught = false;

    private float timer;

    /**
     * detects if you have the ability to fish
     */
    private static boolean fishingPossible() {
        return PlayerRenderer.x > 700 && PlayerRenderer.x < 758 && PlayerRenderer.y > 1896 && PlayerRenderer.y < 2036
                && TopMenuRenderer.currentTool == 1;
    }

    public void update() {
        if (Gdx.input.isKeyPressed(Keys.B)) {
            timer += Gdx.graphics.getDeltaTime();
        }
        fishCaught();
    }

    private void fishCaught() {
        if (fishingPossible() && Gdx.input.isKeyPressed(Keys.B) && timer > 1) {
            if (RANDOM.nextInt(4) == 2) {
                fishCaught = true;
            } else {
                timer = 0;
            }
        }
        if (fishCaught) {
            MessagesRenderer.add(CAUGHT);
            count += 1;
            PointsRenderer.xp += 1;
            timer = 0;
            fishCaught = false;
        }
    }
}
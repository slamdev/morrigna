package com.github.slamm.morrigna.core.process;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.math.Vector2;
import com.github.slamm.morrigna.core.Assets;
import com.github.slamm.morrigna.core.hud.LevelRenderer;
import com.github.slamm.morrigna.core.hud.MessagesRenderer;
import com.github.slamm.morrigna.core.map.MapRenderSystem;
import com.github.slamm.morrigna.core.map.PlayerRenderer;
import com.reed.birdseye.Time;

public class HouseUpdater {

    public static float preAmbientLight;

    public static Vector2 preCameraPos = new Vector2();

    public static Vector2 prePlayerPos = new Vector2();

    private static float ambientLight = 1;

    private static boolean inRiverHouse = false;

    /**
     * booleans for a run once code strip
     */
    private static boolean justEntered = false;

    private static boolean justExited = false;

    private static Vector2 preSleepPlayerPos = new Vector2();

    /**
     * Current step in sleep <br>
     * <u><b>What each step means:</b></u> <br>
     * <b>1</b> means just got in bed <br>
     * <b>2</b> means lights are diming <br>
     * <b>3</b> means lights are brightening <br>
     * <b>4</b> means just got out of bed <br>
     * <b>0</b> means doing nothing (deafult)
     */
    private static int sleepStep = 0;

    private boolean canSendDayMessage = true;

    private boolean canSendNightMessage = true;

    private final int distanceFromBed = 50;

    private boolean justGotNearBed = false;

    private final float lightCycleSpeed = .005f;

    /**
     * bed cordinates
     */
    private final int x = 730;

    /**
     * bed cordinates
     */
    private final int y = 386;

    public static void save() {
        if (sleepStep > 0) {
            PlayerRenderer.x = preSleepPlayerPos.x;
            PlayerRenderer.y = preSleepPlayerPos.y;
            Time.setAmbientLight(1);
        }
    }

    public static void setInRiverHouse(boolean isInRiverHouse) {
        HouseUpdater.inRiverHouse = isInRiverHouse;
    }

    public static void setJustEntered(boolean justEntered) {
        HouseUpdater.justEntered = justEntered;
    }

    public static void setJustExited(boolean justExited) {
        HouseUpdater.justExited = justExited;
    }

    public void update() {
        // TODO: SEPPERATE INTO INDIVIDUAL METHODS
        // variable becomes true through the collision class
        if (inRiverHouse) {
            if (justEntered) {
                prePlayerPos.x = PlayerRenderer.x;
                prePlayerPos.y = PlayerRenderer.y;
                preCameraPos.x = MapRenderSystem.camera.position.x;
                preCameraPos.y = MapRenderSystem.camera.position.y;
                preAmbientLight = Time.getAmbientLight();
                Time.setOutdoors(false);
                Time.setAmbientLight(ambientLight);
                CollisionUpdater.collisionType = 1;
                LevelRenderer.currentMap = 1;
                // set cordinates
                MapRenderSystem.camera.position.x = Gdx.graphics.getWidth() / 2 + 10;
                MapRenderSystem.camera.position.y = Gdx.graphics.getHeight() / 2 - 140;
                // draw player in correct spot
                PlayerRenderer.x = LevelRenderer.middleX + 10;
                PlayerRenderer.y = LevelRenderer.middleY - 140;
                // get rid of grass (set to what? blackness)
                // get rid of darkness. (how to set back to normal levels when
                // you exit *looks around franticly* -- set up currentAmbient
                // light and outdoor ambient light variables.
                // finally set to false
                justEntered = false;
            }
            // check for collision (will be done in gamescreen class thanks to a
            // switch statment)
            // check for bed (prob add its own method) must be close enough (add
            // close enough method)
            // stove / oven method stuff
            // allow for exit of house ability set justExited to true
        } else {
            if (justExited) {
                LevelRenderer.currentMap = 0;
                PlayerRenderer.x = prePlayerPos.x;
                PlayerRenderer.y = prePlayerPos.y;
                MapRenderSystem.camera.position.x = preCameraPos.x;
                MapRenderSystem.camera.position.y = preCameraPos.y;
                CollisionUpdater.collisionType = 0;
                Time.setAmbientLight(preAmbientLight);
                Time.setOutdoors(true);
                justExited = false;
            }
        }
        nearBed();
        remCycles();
        // Time.setTimeOfDay(300);
    }

    /**
     * Returns boolean if you are close enough from the designated variable
     * 
     * @variable distanceFromBed
     */
    private boolean closeEnoughToBed() {
        return Math.sqrt((x - PlayerRenderer.x) * (x - PlayerRenderer.x) + (y - PlayerRenderer.y)
                * (y - PlayerRenderer.y)) < distanceFromBed;
    }

    private void nearBed() {
        if (closeEnoughToBed() && !justGotNearBed) {
            MessagesRenderer.add("Press B to sleep");
            justGotNearBed = true;
        }
        if (!closeEnoughToBed()) {
            justGotNearBed = false;
            canSendNightMessage = true;
        }
        if (justGotNearBed && Gdx.input.isKeyPressed(Keys.B)) {
            if (preAmbientLight <= .2f && canSendDayMessage) {
                canSendDayMessage = false;
                sleepStep = 1;
                canSendNightMessage = false;
            } else if (canSendNightMessage) {
                MessagesRenderer.add("You can only sleep at night!");
                canSendNightMessage = false;
            }
        }
    }

    /**
     * sleep cycles
     */
    private void remCycles() {
        if (sleepStep == 1) {
            preSleepPlayerPos.x = PlayerRenderer.x;
            preSleepPlayerPos.y = PlayerRenderer.y;
            PlayerRenderer.x = 735;
            PlayerRenderer.y = 380;
            PlayerRenderer.ableToMove = false;
            Assets.mainChar = Assets.downChar_STILL;
            // set sprite to look down (should be a static sprite)
            sleepStep = 2;
        }
        if (ambientLight >= 0 && sleepStep == 2) {
            Time.setAmbientLight(ambientLight);
            ambientLight -= lightCycleSpeed;
            if (ambientLight <= 0) {
                sleepStep = 3;
            }
        }
        if (ambientLight <= 1 && sleepStep == 3) {
            Time.setAmbientLight(ambientLight);
            ambientLight += lightCycleSpeed;
            if (ambientLight >= 1) {
                sleepStep = 4;
            }
        }
        if (sleepStep == 4) {
            PlayerRenderer.ableToMove = true;
            canSendDayMessage = true;
            PlayerRenderer.x = preSleepPlayerPos.x;
            PlayerRenderer.y = preSleepPlayerPos.y;
            Time.colorAlpha = 0;
            preAmbientLight = 1;
            Time.setTimeOfDay(0);
            sleepStep = 0;
        }
    }
}
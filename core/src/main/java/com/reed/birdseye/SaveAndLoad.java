package com.reed.birdseye;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.github.slamm.morrigna.core.GameScreen;
import com.github.slamm.morrigna.core.hud.LevelRenderer;
import com.github.slamm.morrigna.core.hud.PointsRenderer;
import com.github.slamm.morrigna.core.hud.TradeShopRenderer;
import com.github.slamm.morrigna.core.map.MapRenderSystem;
import com.github.slamm.morrigna.core.map.PlayerRenderer;

public class SaveAndLoad {

    static String currentVersion = "1.1.3";

    static Preferences prefs;

    static String version;

    public static void load() {
        prefs = Gdx.app.getPreferences("preferences.lodescape");
        version = prefs.getString("version");
        // checks if up to date also checks if game has been played before :)
        if (version.equals(currentVersion)) {
            GameScreen.mapCamera.position.x = prefs.getFloat("camera x");
            GameScreen.mapCamera.position.y = prefs.getFloat("camera y");
            PlayerRenderer.x = prefs.getFloat("player x");
            PlayerRenderer.y = prefs.getFloat("player y");
            Tutorial.step = prefs.getInteger("Tutorial Level");
            Fishing.amountOfFish = prefs.getInteger("Fish");
            Resource.amountOfStone = prefs.getInteger("Stone");
            Tree.amountOfWood = prefs.getInteger("Wood");
            TradeShopRenderer.cash = prefs.getInteger("cash");
            PointsRenderer.hp = prefs.getInteger("hp");
            PointsRenderer.xp = prefs.getInteger("xp");
            Time.colorAlpha = prefs.getFloat("color alpha");
            Time.setTimeOfDay(prefs.getFloat("time"));
            Time.setAmbientLight(prefs.getFloat("ambient light"));
            House.preCameraPos.x = prefs.getFloat("pre camera x");
            House.preCameraPos.y = prefs.getFloat("pre camera y");
            House.prePlayerPos.x = prefs.getFloat("pre player x");
            House.prePlayerPos.y = prefs.getFloat("pre player y");
            CollisionDetection.setCollisionType(prefs.getInteger("collision type"));
            LevelRenderer.currentMap = prefs.getInteger("current map");
            Time.setOutdoors(prefs.getBoolean("is outside"));
            House.preAmbientLight = prefs.getFloat("pre ambient light");
            Food.amountOfFood = prefs.getInteger("foodAmount");
            PointsRenderer.currentLevel = prefs.getInteger("level");
            Time.createLights(MapRenderSystem.rayHandlerRenderer);
        } else {
            // still gotta load lights even if the versions dont match up
            Time.createLights(MapRenderSystem.rayHandlerRenderer);
            prefs.clear();
        }
    }

    public static void save() {
        House.exitGame();
        prefs.putInteger("Tutorial Level", Tutorial.step);
        prefs.putInteger("Fish", Fishing.amountOfFish);
        prefs.putInteger("Stone", Resource.amountOfStone);
        prefs.putInteger("Wood", Tree.amountOfWood);
        prefs.putInteger("Cash", TradeShopRenderer.cash);
        prefs.putInteger("hp", PointsRenderer.hp);
        prefs.putInteger("xp", PointsRenderer.xp);
        prefs.putFloat("camera x", GameScreen.mapCamera.position.x);
        prefs.putFloat("camera y", GameScreen.mapCamera.position.y);
        prefs.putFloat("player x", PlayerRenderer.x);
        prefs.putFloat("player y", PlayerRenderer.y);
        prefs.putFloat("color alpha", Time.colorAlpha);
        prefs.putFloat("time", Time.getTimeOfDay());
        prefs.putFloat("ambient light", Time.getAmbientLight());
        prefs.putFloat("pre camera x", House.preCameraPos.x);
        prefs.putFloat("pre camera y", House.preCameraPos.y);
        prefs.putFloat("pre player x", House.prePlayerPos.x);
        prefs.putFloat("pre player y", House.prePlayerPos.y);
        prefs.putInteger("collision type", CollisionDetection.getCollisionType());
        prefs.putInteger("current map", LevelRenderer.currentMap);
        prefs.putBoolean("is outside", Time.isOutdoors());
        prefs.putFloat("pre ambient light", House.preAmbientLight);
        prefs.putInteger("foodAmount", Food.amountOfFood);
        prefs.putInteger("level", PointsRenderer.currentLevel);
        // set to current version before saving
        prefs.putString("version", currentVersion);
        prefs.flush();
    }
}

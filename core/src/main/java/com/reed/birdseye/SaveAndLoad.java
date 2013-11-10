package com.reed.birdseye;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.github.slamm.morrigna.core.hud.LevelRenderer;
import com.github.slamm.morrigna.core.hud.PointsRenderer;
import com.github.slamm.morrigna.core.hud.TradeShopRenderer;
import com.github.slamm.morrigna.core.map.MapRenderSystem;
import com.github.slamm.morrigna.core.map.PlayerRenderer;
import com.github.slamm.morrigna.core.process.CollisionUpdater;
import com.github.slamm.morrigna.core.process.FishingUpdater;
import com.github.slamm.morrigna.core.process.FoodUpdater;
import com.github.slamm.morrigna.core.process.HouseUpdater;

public class SaveAndLoad {

    static String currentVersion = "1.1.3";

    static Preferences prefs;

    static String version;

    public static void load() {
        prefs = Gdx.app.getPreferences("preferences.lodescape");
        version = prefs.getString("version");
        // checks if up to date also checks if game has been played before :)
        if (version.equals(currentVersion)) {
            MapRenderSystem.camera.position.x = prefs.getFloat("camera x");
            MapRenderSystem.camera.position.y = prefs.getFloat("camera y");
            PlayerRenderer.x = prefs.getFloat("player x");
            PlayerRenderer.y = prefs.getFloat("player y");
            Tutorial.step = prefs.getInteger("Tutorial Level");
            FishingUpdater.count = prefs.getInteger("Fish");
            Resource.amountOfStone = prefs.getInteger("Stone");
            Tree.amountOfWood = prefs.getInteger("Wood");
            TradeShopRenderer.cash = prefs.getInteger("cash");
            PointsRenderer.hp = prefs.getInteger("hp");
            PointsRenderer.xp = prefs.getInteger("xp");
            Time.colorAlpha = prefs.getFloat("color alpha");
            Time.setTimeOfDay(prefs.getFloat("time"));
            Time.setAmbientLight(prefs.getFloat("ambient light"));
            HouseUpdater.preCameraPos.x = prefs.getFloat("pre camera x");
            HouseUpdater.preCameraPos.y = prefs.getFloat("pre camera y");
            HouseUpdater.prePlayerPos.x = prefs.getFloat("pre player x");
            HouseUpdater.prePlayerPos.y = prefs.getFloat("pre player y");
            CollisionUpdater.collisionType = prefs.getInteger("collision type");
            LevelRenderer.currentMap = prefs.getInteger("current map");
            Time.setOutdoors(prefs.getBoolean("is outside"));
            HouseUpdater.preAmbientLight = prefs.getFloat("pre ambient light");
            FoodUpdater.count = prefs.getInteger("foodAmount");
            PointsRenderer.currentLevel = prefs.getInteger("level");
            Time.createLights(MapRenderSystem.rayHandlerRenderer);
        } else {
            // still gotta load lights even if the versions dont match up
            Time.createLights(MapRenderSystem.rayHandlerRenderer);
            prefs.clear();
        }
    }

    public static void save() {
        HouseUpdater.save();
        prefs.putInteger("Tutorial Level", Tutorial.step);
        prefs.putInteger("Fish", FishingUpdater.count);
        prefs.putInteger("Stone", Resource.amountOfStone);
        prefs.putInteger("Wood", Tree.amountOfWood);
        prefs.putInteger("Cash", TradeShopRenderer.cash);
        prefs.putInteger("hp", PointsRenderer.hp);
        prefs.putInteger("xp", PointsRenderer.xp);
        prefs.putFloat("camera x", MapRenderSystem.camera.position.x);
        prefs.putFloat("camera y", MapRenderSystem.camera.position.y);
        prefs.putFloat("player x", PlayerRenderer.x);
        prefs.putFloat("player y", PlayerRenderer.y);
        prefs.putFloat("color alpha", Time.colorAlpha);
        prefs.putFloat("time", Time.getTimeOfDay());
        prefs.putFloat("ambient light", Time.getAmbientLight());
        prefs.putFloat("pre camera x", HouseUpdater.preCameraPos.x);
        prefs.putFloat("pre camera y", HouseUpdater.preCameraPos.y);
        prefs.putFloat("pre player x", HouseUpdater.prePlayerPos.x);
        prefs.putFloat("pre player y", HouseUpdater.prePlayerPos.y);
        prefs.putInteger("collision type", CollisionUpdater.collisionType);
        prefs.putInteger("current map", LevelRenderer.currentMap);
        prefs.putBoolean("is outside", Time.isOutdoors());
        prefs.putFloat("pre ambient light", HouseUpdater.preAmbientLight);
        prefs.putInteger("foodAmount", FoodUpdater.count);
        prefs.putInteger("level", PointsRenderer.currentLevel);
        // set to current version before saving
        prefs.putString("version", currentVersion);
        prefs.flush();
    }
}

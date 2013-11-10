package com.reed.birdseye;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.github.slamm.morrigna.core.hud.LevelRenderer;
import com.github.slamm.morrigna.core.hud.PointsRenderer;
import com.github.slamm.morrigna.core.hud.TradeShopRenderer;
import com.github.slamm.morrigna.core.map.MapRenderSystem;
import com.github.slamm.morrigna.core.map.PlayerRenderer;
import com.github.slamm.morrigna.core.process.CollisionProcessSystem;
import com.github.slamm.morrigna.core.process.FishingProcessSystem;
import com.github.slamm.morrigna.core.process.HouseUpdateSystem;

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
            FishingProcessSystem.count = prefs.getInteger("Fish");
            Resource.amountOfStone = prefs.getInteger("Stone");
            Tree.amountOfWood = prefs.getInteger("Wood");
            TradeShopRenderer.cash = prefs.getInteger("cash");
            PointsRenderer.hp = prefs.getInteger("hp");
            PointsRenderer.xp = prefs.getInteger("xp");
            Time.colorAlpha = prefs.getFloat("color alpha");
            Time.setTimeOfDay(prefs.getFloat("time"));
            Time.setAmbientLight(prefs.getFloat("ambient light"));
            HouseUpdateSystem.preCameraPos.x = prefs.getFloat("pre camera x");
            HouseUpdateSystem.preCameraPos.y = prefs.getFloat("pre camera y");
            HouseUpdateSystem.prePlayerPos.x = prefs.getFloat("pre player x");
            HouseUpdateSystem.prePlayerPos.y = prefs.getFloat("pre player y");
            CollisionProcessSystem.collisionType = prefs.getInteger("collision type");
            LevelRenderer.currentMap = prefs.getInteger("current map");
            Time.setOutdoors(prefs.getBoolean("is outside"));
            HouseUpdateSystem.preAmbientLight = prefs.getFloat("pre ambient light");
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
        HouseUpdateSystem.save();
        prefs.putInteger("Tutorial Level", Tutorial.step);
        prefs.putInteger("Fish", FishingProcessSystem.count);
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
        prefs.putFloat("pre camera x", HouseUpdateSystem.preCameraPos.x);
        prefs.putFloat("pre camera y", HouseUpdateSystem.preCameraPos.y);
        prefs.putFloat("pre player x", HouseUpdateSystem.prePlayerPos.x);
        prefs.putFloat("pre player y", HouseUpdateSystem.prePlayerPos.y);
        prefs.putInteger("collision type", CollisionProcessSystem.collisionType);
        prefs.putInteger("current map", LevelRenderer.currentMap);
        prefs.putBoolean("is outside", Time.isOutdoors());
        prefs.putFloat("pre ambient light", HouseUpdateSystem.preAmbientLight);
        prefs.putInteger("foodAmount", Food.amountOfFood);
        prefs.putInteger("level", PointsRenderer.currentLevel);
        // set to current version before saving
        prefs.putString("version", currentVersion);
        prefs.flush();
    }
}

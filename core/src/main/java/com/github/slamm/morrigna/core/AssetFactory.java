package com.github.slamm.morrigna.core;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;

public final class AssetFactory {

    private static final AssetManager MANAGER = new AssetManager();
    static {
        MANAGER.setLoader(TiledMap.class, new TmxMapLoader());
    }

    private AssetFactory() {
    }

    public static TiledMap map(String path) {
        MANAGER.load(path, TiledMap.class);
        MANAGER.finishLoading();
        return MANAGER.get(path);
    }
}
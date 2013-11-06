package com.github.slamm.morrigna.core;

import com.artemis.Entity;
import com.artemis.World;
import com.badlogic.gdx.graphics.OrthographicCamera;

public final class EntityFactory {

    private EntityFactory() {
    }

    public static Entity map(World world, String path, OrthographicCamera camera) {
        Entity e = world.createEntity();
        e.addComponent(new MapComponent(AssetFactory.map(path)));
        e.addComponent(new CameraComponent(camera));
        return e;
    }
}
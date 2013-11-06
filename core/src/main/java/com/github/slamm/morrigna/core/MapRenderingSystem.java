package com.github.slamm.morrigna.core;

import com.artemis.Aspect;
import com.artemis.ComponentMapper;
import com.artemis.Entity;
import com.artemis.annotations.Mapper;
import com.artemis.systems.EntityProcessingSystem;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;

public class MapRenderingSystem extends EntityProcessingSystem {

    @Mapper
    ComponentMapper<CameraComponent> cameraMapper;

    @Mapper
    ComponentMapper<MapComponent> mapMapper;

    private OrthogonalTiledMapRenderer renderer;

    @SuppressWarnings("unchecked")
    public MapRenderingSystem() {
        super(Aspect.getAspectForAll(MapComponent.class, CameraComponent.class));
    }

    @Override
    protected void inserted(Entity e) {
        MapComponent c = mapMapper.get(e);
        renderer = new OrthogonalTiledMapRenderer(c.map, 1f / 32f);
    }

    @Override
    protected void process(Entity e) {
        OrthographicCamera camera = cameraMapper.get(e).camera;
        renderer.setView(camera);
        renderer.render();
    }

    @Override
    protected void removed(Entity e) {
        super.removed(e);
    }
}
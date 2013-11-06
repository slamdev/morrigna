package com.github.slamm.morrigna.core;

import com.artemis.Aspect;
import com.artemis.ComponentMapper;
import com.artemis.Entity;
import com.artemis.annotations.Mapper;
import com.artemis.systems.EntityProcessingSystem;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class SpriteRenderSystem extends EntityProcessingSystem {

    @Mapper
    ComponentMapper<PositionComponent> positionMapper;

    @Mapper
    ComponentMapper<SpriteComponent> spriteMapper;

    private SpriteBatch spriteBatch;

    @SuppressWarnings("unchecked")
    public SpriteRenderSystem() {
        super(Aspect.getAspectForAll(PositionComponent.class, SpriteComponent.class));
    }

    @Override
    protected void begin() {
        if (spriteBatch == null) {
            spriteBatch = new SpriteBatch();
        }
        spriteBatch.begin();
    }

    @Override
    protected void end() {
        spriteBatch.end();
    }

    @Override
    protected void process(Entity e) {
        PositionComponent position = positionMapper.get(e);
        SpriteComponent sprite = spriteMapper.get(e);
        float posX = position.x - sprite.texture.getWidth() / 2;
        float posY = position.y - sprite.texture.getHeight() / 2;
        spriteBatch.draw(sprite.texture, posX, posY);
    }
}
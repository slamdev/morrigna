package com.github.slamm.morrigna.core;

import com.artemis.Component;
import com.badlogic.gdx.graphics.Texture;

public class SpriteComponent extends Component {

    public final Texture texture;

    public SpriteComponent(Texture texture) {
        this.texture = texture;
    }
}
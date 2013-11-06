package com.github.slamm.morrigna.core;

import com.artemis.Component;
import com.badlogic.gdx.graphics.OrthographicCamera;

public class CameraComponent extends Component {

    public final OrthographicCamera camera;

    public CameraComponent(OrthographicCamera camera) {
        this.camera = camera;
    }
}

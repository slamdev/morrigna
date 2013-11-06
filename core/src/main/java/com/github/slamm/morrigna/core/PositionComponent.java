package com.github.slamm.morrigna.core;

import com.artemis.Component;

public class PositionComponent extends Component {

    public float x;

    public float y;

    public PositionComponent(float x, float y) {
        this.x = x;
        this.y = y;
    }
}
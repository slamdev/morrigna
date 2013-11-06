package com.github.slamm.morrigna.core;

import com.artemis.Component;
import com.badlogic.gdx.maps.tiled.TiledMap;

public class MapComponent extends Component {

    public TiledMap map;

    public MapComponent(TiledMap map) {
        this.map = map;
    }
}
package com.github.slamm.morrigna.core.process;

import com.reed.birdseye.Fishing;
import com.reed.birdseye.Food;
import com.reed.birdseye.House;

public class ProcessUpdateSystem {

    private final CollisionProcessSystem collision;

    private final Fishing fishing;

    private final Food food;

    private final House house;

    public ProcessUpdateSystem() {
        collision = new CollisionProcessSystem();
        fishing = new Fishing();
        house = new House();
        food = new Food();
    }

    public void update() {
        collision.update();
        fishing.update();
        fishing.fishCaught();
        house.update();
        food.affectHealth();
        food.looseHunger();
    }
}
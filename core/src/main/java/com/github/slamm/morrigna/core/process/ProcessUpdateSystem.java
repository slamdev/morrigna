package com.github.slamm.morrigna.core.process;

import com.reed.birdseye.Food;
import com.reed.birdseye.House;

public class ProcessUpdateSystem {

    private final CollisionProcessSystem collisionSystem;

    private final FishingProcessSystem fishingSystem;

    private final Food food;

    private final House house;

    public ProcessUpdateSystem() {
        collisionSystem = new CollisionProcessSystem();
        fishingSystem = new FishingProcessSystem();
        house = new House();
        food = new Food();
    }

    public void update() {
        collisionSystem.update();
        fishingSystem.update();
        house.update();
        food.affectHealth();
        food.looseHunger();
    }
}
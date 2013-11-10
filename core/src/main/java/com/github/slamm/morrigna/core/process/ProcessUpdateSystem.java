package com.github.slamm.morrigna.core.process;

import com.reed.birdseye.Food;

public class ProcessUpdateSystem {

    private final CollisionProcessSystem collisionSystem;

    private final FishingProcessSystem fishingSystem;

    private final Food food;

    private final HouseUpdateSystem houseSystem;

    public ProcessUpdateSystem() {
        collisionSystem = new CollisionProcessSystem();
        fishingSystem = new FishingProcessSystem();
        houseSystem = new HouseUpdateSystem();
        food = new Food();
    }

    public void update() {
        collisionSystem.update();
        fishingSystem.update();
        houseSystem.update();
        food.affectHealth();
        food.looseHunger();
    }
}
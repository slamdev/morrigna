package com.github.slamm.morrigna.core.process;

public class ProcessUpdateSystem {

    private final CollisionProcessSystem collisionSystem;

    private final FishingProcessSystem fishingSystem;

    private final FoodUpdateSystem foodSystem;

    private final HouseUpdateSystem houseSystem;

    public ProcessUpdateSystem() {
        collisionSystem = new CollisionProcessSystem();
        fishingSystem = new FishingProcessSystem();
        houseSystem = new HouseUpdateSystem();
        foodSystem = new FoodUpdateSystem();
    }

    public void update() {
        collisionSystem.update();
        fishingSystem.update();
        houseSystem.update();
        foodSystem.update();
    }
}
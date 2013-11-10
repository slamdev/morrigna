package com.github.slamm.morrigna.core.process;

public class ProcessUpdateSystem {

    private final CollisionUpdater collisionUpdater;

    private final FishingUpdater fishingUpdater;

    private final FoodUpdater foodUpdater;

    private final HouseUpdater houseUpdater;

    public ProcessUpdateSystem() {
        collisionUpdater = new CollisionUpdater();
        fishingUpdater = new FishingUpdater();
        houseUpdater = new HouseUpdater();
        foodUpdater = new FoodUpdater();
    }

    public void update() {
        collisionUpdater.update();
        fishingUpdater.update();
        houseUpdater.update();
        foodUpdater.update();
    }
}
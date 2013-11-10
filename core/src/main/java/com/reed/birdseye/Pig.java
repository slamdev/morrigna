package com.reed.birdseye;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.github.slamm.morrigna.core.Assets;
import com.github.slamm.morrigna.core.hud.PointsRenderer;
import com.github.slamm.morrigna.core.process.FoodUpdateSystem;

public class Pig {

    // area under sword shop
    public Mob pig = new Mob(1562, 1264, 1000, 918);

    // creates new image for each pig (new Pig(params))
    public TextureRegion theRealPig = Assets.mainPig;

    boolean pickedUpFood = false;

    float timer;

    // when pig.health = -15 that means that pig is dead and the food has been
    // picked up. Simplify the saving process.
    public void drops(SpriteBatch batch) {
        if (pig.health <= 0 && !pickedUpFood && !(pig.health <= -100)) {
            batch.draw(Assets.bacon, pig.x, pig.y);
        }
    }

    // respawn pigs during day
    public void regeneration() {
        if (pig.health <= 0 && Time.isDay() && pig.distanceBetweenMobAndPlayer() > 544) {
            pickedUpFood = false;
            pig.underAttack = false;
            pig.health = 100;
            timer = 0;
            pig.attackedHealth = 100;
        }
    }

    public void update() {
        avadeAttack();
        pickupDrops();
        pig.movement();
        pig.looseHealth();
        pig.boundingArea();
        pig.detectIfUnderAttack();
        theRealPig = pig.setSprites(Assets.upPig_STILL, Assets.upPig_LEFT, Assets.upPig_RIGHT, Assets.downPig_STILL,
                Assets.downPig_LEFT, Assets.downPig_RIGHT, Assets.leftPig_STILL, Assets.leftPig_LEFT,
                Assets.leftPig_RIGHT, Assets.rightPig_STILL, Assets.rightPig_LEFT, Assets.rightPig_RIGHT);
    }

    void avadeAttack() {
        // once under attack move in random directions quickly
        if (pig.underAttack && pig.isAlive() && !pickedUpFood) {
            pig.mobTime -= Gdx.graphics.getDeltaTime();
            if (pig.direction == 0 && pig.mobTime > 0) {
                pig.y += pig.speed * 2;
            } else if (pig.direction == 1 && pig.mobTime > 0) {
                pig.y -= pig.speed * 2;
            } else if (pig.direction == 2 && pig.mobTime > 0) {
                pig.x -= pig.speed * 2;
            } else if (pig.direction == 3 && pig.mobTime > 0) {
                pig.x += pig.speed * 2;
            } else {
                pig.mobTime = pig.r.nextInt(3) + 1;
                pig.direction = pig.r.nextInt(4) + 1;
            }
            // bounding stuff
            if (pig.x > pig.boundX + pig.boundWidth) {
                pig.direction = 2;
            }
            if (pig.x < pig.boundX) {
                pig.direction = 3;
            }
            if (pig.y > pig.boundY + pig.boundHeight) {
                pig.direction = 1;
            }
            if (pig.y < pig.boundY) {
                pig.direction = 0;
            }
        }
    }

    void pickupDrops() {
        if (timer < 1 && pig.health <= 0 && !(pig.health <= -100)) {
            timer += Gdx.graphics.getDeltaTime();
        }
        if (pig.health <= 0 && pig.distanceBetweenMobAndPlayer() < 25 && !pickedUpFood && timer >= 1
                && !(pig.health <= -100)) {
            FoodUpdateSystem.count += 1;
            pickedUpFood = true;
            pig.health = -100;
            PointsRenderer.gainExperience(1);
        }
    }
}

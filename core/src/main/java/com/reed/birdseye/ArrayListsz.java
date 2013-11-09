package com.reed.birdseye;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.utils.Array;
import com.github.slamm.morrigna.core.Assets;

public class ArrayListsz {

    static int amountOfMobs = 2;

    static int amountOfPigs = 4;

    static Array<Coal> coalArray = new Array<>();

    // all creeper stuff
    static Array<Mob> creeperArray = new Array<>();

    // All the pig stuff
    static Array<Pig> pigArray = new Array<>();

    public void drawCoal(SpriteBatch batch) {
        for (int i = 0; i < coalArray.size; i++) {
            coalArray.get(i).draw(batch);
        }
    }

    public void mobDraw(SpriteBatch batch) {
        for (int i = 0; i < creeperArray.size; i++) {
            creeperArray.get(i).draw(batch, creeperArray.get(i).realMob);
        }
    }

    public void mobHealthBars(ShapeRenderer shapeRenderer) {
        for (int i = 0; i < creeperArray.size; i++) {
            creeperArray.get(i).healthBar(shapeRenderer, creeperArray.get(i).realMob);
        }
    }

    public void mobUpdate() {
        for (int i = 0; i < creeperArray.size; i++) {
            creeperArray.get(i).realMob = creeperArray.get(i).setSprites(Assets.upCreeper_STILL, Assets.upCreeper_LEFT,
                    Assets.upCreeper_RIGHT, Assets.downCreeper_STILL, Assets.downCreeper_LEFT,
                    Assets.downCreeper_RIGHT, Assets.leftCreeper_STILL, Assets.leftCreeper_LEFT,
                    Assets.leftCreeper_RIGHT, Assets.rightCreeper_STILL, Assets.rightCreeper_LEFT,
                    Assets.rightCreeper_RIGHT);
            creeperArray.get(i).movement();
            creeperArray.get(i).attack();
            creeperArray.get(i).looseHealth();
            creeperArray.get(i).boundingArea();
            creeperArray.get(i).follow();
            creeperArray.get(i).detectIfUnderAttack();
            creeperArray.get(i).regeneration();
            for (int j = 0; j < coalArray.size; j++) {
                // if not under attack
                if (!creeperArray.get(i).underAttack) {
                    coalArray.get(j).mobCollision(creeperArray.get(i));
                } else { // assume that it is under attack then
                    coalArray.get(j).mobUnderAttackCollision(creeperArray.get(i));
                }
            }
        }
    }

    public void pigHealthBars(ShapeRenderer shapeRenderer) {
        for (int i = 0; i < pigArray.size; i++) {
            pigArray.get(i).pig.healthBar(shapeRenderer, pigArray.get(i).theRealPig);
        }
    }

    public void pigUpdateAndDraw(SpriteBatch batch) {
        for (int i = 0; i < pigArray.size; i++) {
            pigArray.get(i).drops(batch);
            pigArray.get(i).pig.draw(batch, pigArray.get(i).theRealPig);
            pigArray.get(i).update();
            pigArray.get(i).regeneration();
            for (int j = 0; j < coalArray.size; j++) {
                coalArray.get(j).mobCollision(pigArray.get(i).pig);
            }
        }
    }

    public void updateCoal() {
        for (int i = 0; i < coalArray.size; i++) {
            coalArray.get(i).collect();
            coalArray.get(i).regeneration();
            coalArray.get(i).playerCollision();
        }
    }

    void coalArrayEstablisher() {
        coalArray.add(new Coal(2290, 1798));
        coalArray.add(new Coal(1618, 2022));
        coalArray.add(new Coal(1844, 1652));
    }

    void mobArrayEstablisher() {
        for (int i = 0; i < amountOfMobs; i++) {
            creeperArray.add(new Mob(1562, 1264, 1000, 918));
        }
    }

    void pigArrayEstablisher() {
        for (int i = 0; i < amountOfPigs; i++) {
            pigArray.add(new Pig());
        }
    }
}

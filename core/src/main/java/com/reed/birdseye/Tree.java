package com.reed.birdseye;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.esotericsoftware.spine.Bone;
import com.esotericsoftware.spine.Skeleton;
import com.esotericsoftware.spine.SkeletonRenderer;
import com.github.slamm.morrigna.core.Assets;
import com.github.slamm.morrigna.core.hud.PointsRenderer;
import com.github.slamm.morrigna.core.hud.TopMenuRenderer;
import com.github.slamm.morrigna.core.map.PlayerRenderer;

public class Tree {

    public static String amountOfWoodString;

    static int amountOfWood = 0;

    public Skeleton leavesSkel = new Skeleton(Assets.leaveSkeletonData);

    public Skeleton treeSkel = new Skeleton(Assets.treeSkeletonData);

    public int x, y, width = 64, height = 58;

    float chopTimer;

    int distanceFromMaterial = 100;

    Bone leaveRoot = leavesSkel.getRootBone();

    SkeletonRenderer renderer = new SkeletonRenderer();

    Bone root = treeSkel.getRootBone();

    boolean treeDone = false;

    boolean treeFall = false;

    float treeFallTime, leavesTime;

    public boolean closeEnough() {
        return Math.sqrt((x - PlayerRenderer.x) * (x - PlayerRenderer.x) + (y - PlayerRenderer.y)
                * (y - PlayerRenderer.y)) < distanceFromMaterial;
    }

    public void collectingTree() {
        amountOfWoodString = Integer.toString(amountOfWood);// update string
        if (closeEnough() && !treeFall) {
            if (Gdx.input.isKeyPressed(Keys.B) && TopMenuRenderer.currentTool == 2 && Tutorial.step >= 5) {
                chopTimer += Gdx.graphics.getDeltaTime();
                if (chopTimer > 2) {
                    PointsRenderer.xp += 1;
                    amountOfWood += 4;
                    treeFall = true;
                }
            } else {
                chopTimer = 0;
            }
        }
        if (treeFall && !treeDone) {
            float delta = Gdx.graphics.getDeltaTime(); // 3;
            treeFallTime += delta;
            Assets.treeAnim.apply(treeSkel, treeFallTime, treeFallTime, false, null);
            if (treeFallTime > .9) {
                treeDone = true;
            }
        }
        if (Tutorial.step == 5 && amountOfWood >= 8) {
            Tutorial.step = 6;
        }
    }

    public void collision() {
        if (PlayerRenderer.x > x - 45 && PlayerRenderer.x < x - 20 && PlayerRenderer.y < y + 40
                && PlayerRenderer.y > y - 20) {
            PlayerRenderer.isAbleToMoveRight = false;
        }
        if (PlayerRenderer.x > x + 10 && PlayerRenderer.x < x + 30 && PlayerRenderer.y < y + 40
                && PlayerRenderer.y > y - 20) {
            PlayerRenderer.isAbleToMoveLeft = false;
        }
        if (PlayerRenderer.x > x - 41 && PlayerRenderer.x < x + 26 && PlayerRenderer.y < y + 40
                && PlayerRenderer.y > y + 20) {
            PlayerRenderer.isAbleToMoveDown = false;
        }
        if (PlayerRenderer.x > x - 41 && PlayerRenderer.x < x + 26 && PlayerRenderer.y < y && PlayerRenderer.y > y - 24) {
            PlayerRenderer.isAbleToMoveUp = false;
        }
    }

    public void draw(SpriteBatch batch, BitmapFont font) {
        root.setX(x);
        root.setY(y);
        leaveRoot.setX(x);
        leaveRoot.setY(y);
        renderer.draw(batch, leavesSkel);
        renderer.draw(batch, treeSkel);
        // batch.draw(Assets.tree, x, y);
        if (closeEnough() && PlayerRenderer.ableToMove && !treeDone) {
            font.draw(batch, "Press B to Pick up the Tree", 50, 50);
        }
        treeSkel.updateWorldTransform();
        leavesSkel.updateWorldTransform();
    }

    public void drawTrunk(SpriteBatch batch) {
        batch.draw(Assets.treeAtlas.findRegion("treeBottom"), x - 50, y);
    }

    public void leavesFall() {
        if (closeEnough() && !treeFall) {
            leavesTime += Gdx.graphics.getDeltaTime();
            Assets.leaveAnim.apply(leavesSkel, leavesTime, leavesTime, false, null);
        }
    }
}

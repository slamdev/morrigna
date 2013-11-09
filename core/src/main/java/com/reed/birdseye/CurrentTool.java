package com.reed.birdseye;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.esotericsoftware.spine.Bone;
import com.esotericsoftware.spine.Skeleton;
import com.esotericsoftware.spine.SkeletonRenderer;
import com.github.slamm.morrigna.core.Assets;
import com.github.slamm.morrigna.core.hud.LevelRenderer;
import com.github.slamm.morrigna.core.hud.TopMenuRenderer;

public class CurrentTool {

    public static boolean isTooling = false; // great name :)

    private final SkeletonRenderer renderer = new SkeletonRenderer();

    private final Bone root;

    private float timer;

    /**
     * pick stuff
     */
    private final Skeleton toolSkel;

    public CurrentTool() {
        toolSkel = new Skeleton(Assets.toolsMasterData);
        root = toolSkel.getRootBone();
    }

    public void render(SpriteBatch batch) {
        if ((Player.currentDirection == 2 || Player.currentDirection == 3) && !(TopMenuRenderer.currentTool == 5)
                && !(TopMenuRenderer.currentTool == 4)) {
            if (Gdx.input.isKeyPressed(Keys.B)) {
                isTooling = true;
                renderer.draw(batch, toolSkel);
            } else {
                isTooling = false;
            }
        }
    }

    public void update() {
        timer += Gdx.graphics.getDeltaTime() / 2;
        toolSkel.setSkin("sword");
        Assets.toolsMasterAnim.apply(toolSkel, timer, timer, true, null);
        toolSkel.updateWorldTransform();
        changeTool();
        direction();
    }

    /**
     * FIX INVENTORY BOX clicking coordinates
     */
    private void changeTool() {
        if (TopMenuRenderer.currentTool == 0) {
            toolSkel.setSkin("pick");
            toolSkel.setSlotsToSetupPose();
            toolSkel.updateWorldTransform();
        }
        if (TopMenuRenderer.currentTool == 1 && Tutorial.step >= 2) {
            toolSkel.setSkin("fishingRod");
            toolSkel.setSlotsToSetupPose();
            toolSkel.updateWorldTransform();
        }
        if (TopMenuRenderer.currentTool == 2 && Tutorial.step >= 5) {
            toolSkel.setSkin("hatchet");
            toolSkel.setSlotsToSetupPose();
            toolSkel.updateWorldTransform();
        }
        if (TopMenuRenderer.currentTool == 3 && Tutorial.step >= 7) {
            toolSkel.setSkin("sword");
            toolSkel.setSlotsToSetupPose();
            toolSkel.updateWorldTransform();
        }
    }

    private void direction() {
        if (Player.right) {
            root.setX(LevelRenderer.middleX + 11);
            root.setY(LevelRenderer.middleY - 11);
            toolSkel.setFlipX(false);
        }
        if (Player.left) {
            root.setX(LevelRenderer.middleX + 20);
            root.setY(LevelRenderer.middleY - 11);
            toolSkel.setFlipX(true);
        }
    }
}
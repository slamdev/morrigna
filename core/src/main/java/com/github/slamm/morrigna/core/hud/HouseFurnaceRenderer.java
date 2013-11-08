package com.github.slamm.morrigna.core.hud;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.github.slamm.morrigna.core.Assets;
import com.reed.birdseye.Coal;
import com.reed.birdseye.Food;
import com.reed.birdseye.Particles;
import com.reed.birdseye.Player;

public class HouseFurnaceRenderer extends InputAdapter {

    private static final Particles FIRE = new Particles();

    private static final int X = 512;

    private static final int Y = 418;

    private boolean canSendFurnaceMessage = true;

    private int coalInFurnace;

    private int cookedFoodInFurnace;

    private final int distanceFromFurnace = 50;

    private boolean furnaceOpen;

    private int rawFoodInFurnace;

    public void render(float deltaTime, BitmapFont font, SpriteBatch batch) {
        if (furnaceOpen) {
            Player.ableToMove = false;
            Player.drawCharacter = false;
            batch.draw(Assets.furnaceGUI, 0, 0);
            FIRE.fireUpdateAndDraw(batch, deltaTime);
            // draw fonts
            font.draw(batch, String.valueOf(coalInFurnace), 420, 234);
            font.draw(batch, String.valueOf(rawFoodInFurnace), 420, 376);
            font.draw(batch, String.valueOf(cookedFoodInFurnace), 760, 376);
            if (Gdx.input.isKeyPressed(Keys.ESCAPE)) {
                Player.ableToMove = true;
                Player.drawCharacter = true;
                furnaceOpen = false;
            }
        }
    }

    @Override
    public boolean touchUp(int x, int y, int pointer, int button) {
        if (x > 73 && x < 374 && y > 380 && y < 430 && Food.amountOfFood > 0) {
            rawFoodInFurnace += 1;
            Food.amountOfFood -= 1;
        } else if (x > 73 && x < 374 && y > 430 && y < 475 && Coal.amountOfCoal > 0) {
            coalInFurnace += 1;
            Coal.amountOfCoal -= 1;
        } else {
            return false;
        }
        return true;
    }

    public void update() {
        if (nearFurnace() && canSendFurnaceMessage) {
            MessagesRenderer.add("Press B to open Furnace");
            canSendFurnaceMessage = false;
        }
        if (!nearFurnace()) {
            canSendFurnaceMessage = true;
        }
        if (nearFurnace() && Gdx.input.isKeyPressed(Keys.B)) {
            System.out.println("furnace");
            furnaceOpen = true;
        }
    }

    /**
     * started but never finished need to add ores for fuel first furnace / stove stuff
     */
    private boolean nearFurnace() {
        return Math.sqrt((X - Player.x) * (X - Player.x) + (Y - Player.y) * (Y - Player.y)) < distanceFromFurnace;
    }
}
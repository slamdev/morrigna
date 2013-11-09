package com.reed.birdseye;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.github.slamm.morrigna.core.Assets;
import com.github.slamm.morrigna.core.GameScreen;
import com.github.slamm.morrigna.core.hud.LevelRenderer;

public class Player {

    public static boolean ableToMove = true;

    public static int currentDirection = 0; // 0 is down, 1 is up, 2 is left, 3 is

    public static boolean drawCharacter = true;

    public static boolean left = false;

    public static boolean right = false;

    public static float x = 1422;

    public static float y = 3562;

    // right
    static boolean isAbleToMoveLeft = true, isAbleToMoveRight = true, isAbleToMoveDown = true, isAbleToMoveUp = true;

    static final int playerHeight = 48;

    static final int playerWidth = 32;

    static boolean up = false, down = false;

    int playerSpeed = 2;

    float timer = 0;

    public void draw(SpriteBatch batch) {
        // font.draw(batch, "X:  " + Level.levelX, 850, 1030);
        // font.draw(batch, "Y:  " + Level.levelY, 850, 1000);
        // if (currentDirection == 3 && Resource.mining)
        // pickSkel.draw(batch);
        if (drawCharacter) {
            batch.draw(Assets.mainChar, x, y);
        }
    }

    public void input() {
        // x = 1422;
        // y = 3562;
        // System.out.println("X: " + x + " Y: " + y);
        if (ableToMove) {
            if (Gdx.input.isKeyPressed(Keys.W) && isAbleToMoveUp) {
                up = true;
                currentDirection = 1;
            } else {
                up = false;
            }
            if (Gdx.input.isKeyPressed(Keys.A) && isAbleToMoveLeft) {
                left = true;
                currentDirection = 2;
            } else {
                left = false;
            }
            if (Gdx.input.isKeyPressed(Keys.S) && isAbleToMoveDown) {
                currentDirection = 0;
                down = true;
            } else {
                down = false;
            }
            if (Gdx.input.isKeyPressed(Keys.D) && isAbleToMoveRight) {
                right = true;
                currentDirection = 3;
            } else {
                right = false;
            }
        }
        // if (Gdx.input.isButtonPressed(Keys.SHIFT_LEFT)) {
        // playerSpeed = 8;
        // } else
        // playerSpeed = 2;
    }

    public void move() {
        // System.out.println("X: " + x + " Y: " + y);
        if (ableToMove) {
            // handle y movement
            if (up) {
                GameScreen.yRate = playerSpeed;
                y += playerSpeed;
                // Level.levelY -= playerSpeed;
                LevelRenderer.grassY -= playerSpeed;
            } else if (down) {
                GameScreen.yRate = -playerSpeed;
                y -= playerSpeed;
                // Level.levelY += playerSpeed;
                LevelRenderer.grassY += playerSpeed;
            } else {
                GameScreen.yRate = 0;
            }
            // handle x movement
            if (left) {
                GameScreen.xRate = -playerSpeed;
                x -= playerSpeed;
                // Level.levelX += playerSpeed;
                LevelRenderer.grassX += playerSpeed;
            } else if (right) {
                GameScreen.xRate = playerSpeed;
                x += playerSpeed;
                // Level.levelX -= playerSpeed;
                LevelRenderer.grassX -= playerSpeed;
            } else {
                GameScreen.xRate = 0;
            }
        } else {
            GameScreen.yRate = 0;
            GameScreen.xRate = 0;
        }
    }

    public void setSprites() {
        if (timer < 4) {
            timer += .1f;
        } else {
            timer = 0;
        }
        // walking right animation
        if (right) {
            if (timer < 1) {
                Assets.mainChar = Assets.rightChar_STILL;
            } else if (timer > 1 && timer < 2) {
                Assets.mainChar = Assets.rightChar_LEFT;
            } else if (timer > 2 && timer < 3) {
                Assets.mainChar = Assets.rightChar_STILL;
            } else if (timer > 3) {
                Assets.mainChar = Assets.rightChar_RIGHT;
            }
        }
        // walking left animation
        if (left) {
            if (timer < 1) {
                Assets.mainChar = Assets.leftChar_STILL;
            } else if (timer > 1 && timer < 2) {
                Assets.mainChar = Assets.leftChar_LEFT;
            } else if (timer > 2 && timer < 3) {
                Assets.mainChar = Assets.leftChar_STILL;
            } else if (timer > 3) {
                Assets.mainChar = Assets.leftChar_RIGHT;
            }
        }
        // walking up animation
        if (up) {
            if (timer < 1) {
                Assets.mainChar = Assets.upChar_STILL;
            } else if (timer > 1 && timer < 2) {
                Assets.mainChar = Assets.upChar_LEFT;
            } else if (timer > 2 && timer < 3) {
                Assets.mainChar = Assets.upChar_STILL;
            } else if (timer > 3) {
                Assets.mainChar = Assets.upChar_RIGHT;
            }
        }
        // walking down animation
        if (down) {
            if (timer < 1) {
                Assets.mainChar = Assets.downChar_STILL;
            } else if (timer > 1 && timer < 2) {
                Assets.mainChar = Assets.downChar_LEFT;
            } else if (timer > 2 && timer < 3) {
                Assets.mainChar = Assets.downChar_STILL;
            } else if (timer > 3) {
                Assets.mainChar = Assets.downChar_RIGHT;
            }
        }
    }
}

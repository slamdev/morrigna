package com.github.slamm.morrigna.core.map;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.github.slamm.morrigna.core.Assets;
import com.github.slamm.morrigna.core.GameScreen;
import com.github.slamm.morrigna.core.hud.LevelRenderer;

public class PlayerRenderer {

    public static boolean ableToMove = true;

    public static int currentDirection; // 0 is down, 1 is up, 2 is left, 3 is

    public static boolean drawCharacter = true;

    public static boolean isAbleToMoveDown = true;

    public static boolean isAbleToMoveLeft = true;

    public static boolean isAbleToMoveRight = true;

    public static boolean isAbleToMoveUp = true;

    public static boolean left;

    public static boolean right;

    public static float x = 1422;

    public static float y = 3562;

    private static final int PLAYER_SPEED = 2;

    private boolean down;

    private float timer = 0;

    private boolean up;

    public void draw(SpriteBatch batch) {
        if (drawCharacter) {
            batch.draw(Assets.mainChar, x, y);
        }
    }

    public void update() {
        setSprites();
        move();
        input();
    }

    private void input() {
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
    }

    private void move() {
        if (ableToMove) {
            // handle y movement
            if (up) {
                GameScreen.yRate = PLAYER_SPEED;
                y += PLAYER_SPEED;
                LevelRenderer.grassY -= PLAYER_SPEED;
            } else if (down) {
                GameScreen.yRate = -PLAYER_SPEED;
                y -= PLAYER_SPEED;
                LevelRenderer.grassY += PLAYER_SPEED;
            } else {
                GameScreen.yRate = 0;
            }
            // handle x movement
            if (left) {
                GameScreen.xRate = -PLAYER_SPEED;
                x -= PLAYER_SPEED;
                LevelRenderer.grassX += PLAYER_SPEED;
            } else if (right) {
                GameScreen.xRate = PLAYER_SPEED;
                x += PLAYER_SPEED;
                LevelRenderer.grassX -= PLAYER_SPEED;
            } else {
                GameScreen.xRate = 0;
            }
        } else {
            GameScreen.yRate = 0;
            GameScreen.xRate = 0;
        }
    }

    private void setSprites() {
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
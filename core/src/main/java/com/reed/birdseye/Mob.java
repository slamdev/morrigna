package com.reed.birdseye;

import java.util.Random;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.github.slamm.morrigna.core.Assets;
import com.github.slamm.morrigna.core.hud.PointsRenderer;
import com.github.slamm.morrigna.core.hud.TopMenuRenderer;
import com.github.slamm.morrigna.core.map.PlayerRenderer;

public class Mob {

    static int food = 0;

    // health etc
    public float health = 100;

    public Random r = new Random();

    public TextureRegion realMob = Assets.mainCreeper;

    public boolean underAttack;

    float attackedHealth = health;

    float attackTimer;

    int boundX, boundY, boundWidth, boundHeight;

    /**
     * 0 = up <br>
     * 1 = down <br>
     * 2 = Left <br>
     * 3 = right <br>
     * 4 = no movement
     */
    int direction = r.nextInt(5) + 1;

    // randomly moving "mob" (animal, monster, etc)
    final int distanceFromMob = 100;

    float dyingTimer;

    boolean isAbleToMoveUp = true, isAbleToMoveDown = true, isAbleToMoveRight = true, isAbleToMoveLeft = true;

    float mobTime = r.nextInt(3) + 1;

    // animal drops
    boolean onGround = false;

    float speed = 1;

    float timer = 0;

    int x, y;

    public Mob(int boundX, int boundY, int boundWidth, int boundHeight) {
        this.boundX = boundX;
        this.boundY = boundY;
        this.boundWidth = boundWidth;
        this.boundHeight = boundHeight;
        x = r.nextInt(boundWidth) + boundX;
        y = r.nextInt(boundHeight) + boundY;
    }

    // attacks player when close enough
    public void attack() {
        if (closeEnough() && attackTimer > 5 && isAlive()) {
            PointsRenderer.looseHealth(r.nextInt(10));
            attackTimer = 0;
        }
        attackTimer += Gdx.graphics.getDeltaTime();
    }

    public void boundingArea() {
        if (!underAttack) {
            if (x > boundX + boundWidth) {
                direction = 2;
            }
            if (x < boundX) {
                direction = 3;
            }
            if (y > boundY + boundHeight) {
                direction = 1;
            }
            if (y < boundY) {
                direction = 0;
            }
        }
    }

    public void detectIfUnderAttack() {
        // detect if under attack
        if (attackedHealth > health) {
            underAttack = true;
            attackedHealth = health;
        }
        // set to false if player no longer threat to mob
        if (distanceBetweenMobAndPlayer() > 500) {
            underAttack = false;
        }
    }

    public void draw(SpriteBatch batch, TextureRegion mainMob) {
        if (isAlive()) {
            batch.draw(mainMob, x, y);
        }
    }

    public void follow() {
        // change position if under attack
        if (underAttack && distanceBetweenMobAndPlayer() > 75) {
            if (isBelow() && !(y > boundY + boundHeight && isAbleToMoveUp)) {
                y += speed;
                direction = 0;
            }
            if (isAbove() && !(y < boundY) && isAbleToMoveDown) {
                y -= speed;
                direction = 1;
            }
            if (isToTheLeft() && !(x > boundX + boundWidth) && isAbleToMoveRight) {
                x += speed;
                direction = 3;
            }
            if (isToTheRight() && !(x < boundX) && isAbleToMoveLeft) {
                x -= speed;
                direction = 2;
            }
        }
    }

    // draws health bar above mob
    public void healthBar(ShapeRenderer shapeRenderer, TextureRegion mainMob) {
        if (isAlive()) {
            shapeRenderer.begin(ShapeType.Filled);
            // set to green for full mob health
            shapeRenderer.setColor(0, 255, 0, 1);
            shapeRenderer.rect(x - 8, y + mainMob.getRegionHeight() + 15, 50, 5);
            // set to red for current mob health
            shapeRenderer.setColor(255, 0, 0, 1);
            shapeRenderer.rect(x - 8, y + mainMob.getRegionHeight() + 10, health / 100 * 50, 5);
            shapeRenderer.end();
        }
    }

    // takes away health from mob
    public void looseHealth() {
        if (closeEnough() && TopMenuRenderer.currentTool == 3 && CurrentTool.isTooling && dyingTimer > 1) {
            health -= r.nextInt(20) + 10;
            dyingTimer = 0;
        }
        dyingTimer += Gdx.graphics.getDeltaTime();
    }

    public void movement() {
        // constantly decrease the mobTime
        if (!underAttack && isAlive()) {
            mobTime -= Gdx.graphics.getDeltaTime();
            if (direction == 0 && mobTime > 0) {
                y += speed;
            } else if (direction == 1 && mobTime > 0) {
                y -= speed;
            } else if (direction == 2 && mobTime > 0) {
                x -= speed;
            } else if (direction == 3 && mobTime > 0) {
                x += speed;
            } else if (direction == 4 && mobTime > 0) {
                // 4 means no movement
            } else {
                mobTime = r.nextInt(3) + 1;
                direction = r.nextInt(5) + 1;
            }
        }
    }

    // respawn mobs during day
    public void regeneration() {
        if (health <= 0 && Time.isNight() && distanceBetweenMobAndPlayer() > 544) {
            underAttack = false;
            health = 100;
            attackedHealth = 100;
        }
    }

    public TextureRegion setSprites(TextureRegion upMob_STILL, TextureRegion upMob_LEFT, TextureRegion upMob_RIGHT,
            TextureRegion downMob_STILL, TextureRegion downMob_LEFT, TextureRegion downMob_RIGHT,
            TextureRegion rightMob_STILL, TextureRegion rightMob_LEFT, TextureRegion rightMob_RIGHT,
            TextureRegion leftMob_STILL, TextureRegion leftMob_LEFT, TextureRegion leftMob_RIGHT) {
        if (timer < 4) {
            timer += .1f;
        } else {
            timer = 0;
        }
        // walking right animation
        if (direction == 3) {
            if (timer < 1) {
                return rightMob_STILL;
            } else if (timer > 1 && timer < 2) {
                return rightMob_LEFT;
            } else if (timer > 2 && timer < 3) {
                return rightMob_STILL;
            } else if (timer > 3) {
                return rightMob_RIGHT;
            }
        }
        // walking left animation
        if (direction == 2) {
            if (timer < 1) {
                return leftMob_STILL;
            } else if (timer > 1 && timer < 2) {
                return leftMob_LEFT;
            } else if (timer > 2 && timer < 3) {
                return leftMob_STILL;
            } else if (timer > 3) {
                return leftMob_RIGHT;
            }
        }
        // walking up animation
        if (direction == 0) {
            if (timer < 1) {
                return upMob_STILL;
            } else if (timer > 1 && timer < 2) {
                return upMob_LEFT;
            } else if (timer > 2 && timer < 3) {
                return upMob_STILL;
            } else if (timer > 3) {
                return upMob_RIGHT;
            }
        }
        // walking down animation
        if (direction == 1) {
            if (timer < 1) {
                return downMob_STILL;
            } else if (timer > 1 && timer < 2) {
                return downMob_LEFT;
            } else if (timer > 2 && timer < 3) {
                return downMob_STILL;
            } else if (timer > 3) {
                return downMob_RIGHT;
            }
        }
        return downMob_STILL;
    }

    boolean closeEnough() {
        return Math.sqrt((x - PlayerRenderer.x) * (x - PlayerRenderer.x) + (y - PlayerRenderer.y)
                * (y - PlayerRenderer.y)) < distanceFromMob;
    }

    /** Distance between mob and player */
    float distanceBetweenMobAndPlayer() {
        return (float) Math.sqrt((x - PlayerRenderer.x) * (x - PlayerRenderer.x) + (y - PlayerRenderer.y)
                * (y - PlayerRenderer.y));
    }

    void drawDrops(SpriteBatch batch, Texture itemDropped) {
        if (!isAlive() && onGround) {
            batch.draw(itemDropped, x, y);
        }
    }

    void drops() {
        if (!isAlive() && closeEnough() && onGround) {
            food += 1;
            onGround = false;
        }
    }

    /** Returns if mob is above player */
    boolean isAbove() {
        return PlayerRenderer.y < y;
    }

    // ensures that mob is alive (for drawing purposes)
    boolean isAlive() {
        if (health <= 0) {
            onGround = true;
            return false;
        }
        return true;
    }

    /** Returns if mob is below player */
    boolean isBelow() {
        return PlayerRenderer.y > y;
    }

    /** Returns if mob is to the left of player */
    boolean isToTheLeft() {
        return PlayerRenderer.x > x;
    }

    /** Returns if mob is to the right of player */
    boolean isToTheRight() {
        return PlayerRenderer.x < x;
    }
}
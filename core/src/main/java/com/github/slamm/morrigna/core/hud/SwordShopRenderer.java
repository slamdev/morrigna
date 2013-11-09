package com.github.slamm.morrigna.core.hud;

import java.util.Random;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.github.slamm.morrigna.core.map.PlayerRenderer;
import com.github.slamm.morrigna.core.map.SwordShopOwnerRenderer;
import com.reed.birdseye.Tutorial;

public class SwordShopRenderer extends InputAdapter {

    private static final String DERP = "[Sword Shop] What a nice game this is.";

    private static final int DISTANCE_FROM_SHOP = 100;

    private static final String HELLO = "[Sword Shop] Hello there!";

    private static final String MY_JOB = "[Sword Shop] I love my job.";

    private static final String PURCHASE = "[Sword Shop] Would you like to buy a sword?";

    private static final Random RANDOM = new Random();

    private static final int TIMER_MAX = 15;

    private float timer = 15;

    private static boolean closeEnough() {
        return Math.sqrt((SwordShopOwnerRenderer.X - PlayerRenderer.x) * (SwordShopOwnerRenderer.X - PlayerRenderer.x)
                + (SwordShopOwnerRenderer.Y - PlayerRenderer.y) * (SwordShopOwnerRenderer.Y - PlayerRenderer.y)) < DISTANCE_FROM_SHOP;
    }

    public void render(BitmapFont font, SpriteBatch batch) {
        if (Tutorial.step == 1 && closeEnough()) {
            font.draw(batch, "Yes                    No", 800, 50);
        }
    }

    @Override
    public boolean touchUp(int x, int y, int pointer, int button) {
        if (Tutorial.step == 1 && closeEnough() && x > 785 && x < 836 && y > 470 && y < 515) {
            MessagesRenderer
                    .add("[Sword Shop] Here is a fishing rod. Go catch some fish and trade it for cash to pay me.");
            MessagesRenderer.add("[Sword Shop] It looks like you don't have any money...");
            Tutorial.step += 1;
        }
        return true;
    }

    public void update() {
        timer += Gdx.graphics.getDeltaTime();
        // System.out.println("X: " + Gdx.input.getX() + " Y: " + Gdx.input.getY());
        if (Tutorial.step == 4 && closeEnough()) {
            timer = 0;
            TradeShopRenderer.cash -= 50;
            Tutorial.step = 5;
        }
        if (Tutorial.step == 6 && closeEnough()) {
            timer = 0;
            Tutorial.step = 7;
        }
        textSetter();
    }

    private boolean ableToSend() {
        if (closeEnough() && timer >= TIMER_MAX) {
            timer = 0;
            return true;
        }
        return false;
    }

    private void textSetter() {
        String sender = "";
        if (ableToSend()) {
            if (Tutorial.step == 100) {
                // random text when done with tutorial
                switch (RANDOM.nextInt(4)) {
                case 0:
                    sender = HELLO;
                    break;
                case 1:
                    sender = PURCHASE;
                    break;
                case 2:
                    sender = DERP;
                    break;
                case 3:
                    sender = MY_JOB;
                    break;
                default:
                    sender = "blank message";
                    break;
                }
            }
            if (Tutorial.step < 100) {
                // diferent text for tutorial stage
                switch (Tutorial.step) {
                case 1:
                    sender = PURCHASE;
                    break;
                case 2:
                    sender = "[Sword Shop] Have you obtained any fish yet?";
                    break;
                case 3:
                    sender = "[Sword Shop] Have you traded the fish for money yet?";
                    break;
                case 4:
                    sender = "[Sword Shop] You got the money! Oh no... it seems I have ran out of wood.";
                    MessagesRenderer.add("[Sword Shop] Take this hatchet and go chop some trees for me!");
                    break;
                case 5:
                    sender = "[Sword Shop] Have you gotten that wood yet?";
                    break;
                case 6:
                    sender = "[Sword Shop] Thank you for getting that wood! Here is a sword!";
                    break;
                case 7:
                    sender = "More of this quest will be added soon! Stay tuned! :D";
                    break;
                default:
                    sender = "blank message...";
                    break;
                }
            }
            MessagesRenderer.add(sender);
        }
    }
}
package com.github.slamm.morrigna.core.hud;

import java.util.Random;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.reed.birdseye.Fishing;
import com.reed.birdseye.Player;
import com.reed.birdseye.TradeShopOwner;
import com.reed.birdseye.Tutorial;

public class TradeShopRenderer extends InputAdapter {

    public static int cash;

    public static String cashString;

    private static final String DERP = "[Trade Shop]: What a nice game this is.";

    private static final int DISTANCE_FROM_SHOP = 100;

    private static final String HELLO = "[Trade Shop]: Hello there stranger!";

    private static final String PURCHASE = "[Trade Shop]: Anything you want to trade with me?";

    private static final Random RANDOM = new Random();

    private float timer = 5;

    private static boolean closeEnough() {
        return Math.sqrt((TradeShopOwner.X - Player.x) * (TradeShopOwner.X - Player.x) + (TradeShopOwner.Y - Player.y)
                * (TradeShopOwner.Y - Player.y)) < DISTANCE_FROM_SHOP;
    }

    public void render(BitmapFont font, SpriteBatch batch) {
        if (Tutorial.step == 3 && closeEnough()) {
            font.draw(batch, "Yes                    No", 800, 50);
        }
    }

    @Override
    public boolean touchUp(int x, int y, int pointer, int button) {
        if (Tutorial.step == 3 && closeEnough() && x > 785 && x < 836 && y > 470 && y < 515) {
            MessagesRenderer.add("[Trade Shop] Here is your money, thanks for the business!");
            timer = 0;
            cash -= 50;
            Tutorial.step += 1;
            return true;
        }
        return false;
    }

    public void update() {
        cashString = String.valueOf(cash);
        timer += Gdx.graphics.getDeltaTime();
        if (Fishing.amountOfFish >= 5 && Tutorial.step == 2) {
            Tutorial.step = 3;
        }
        if (cash >= 50 && Tutorial.step == 3) {
            Fishing.amountOfFish -= 5;
            Tutorial.step = 4;
        }
        textSetter();
    }

    private boolean ableToSend() {
        if (closeEnough() && timer >= 5) {
            timer = 0;
            return true;
        }
        return false;
    }

    private void textSetter() {
        String sender = "";
        if (ableToSend()) {
            if (Tutorial.step == 100) {
                switch (RANDOM.nextInt(3)) {
                case 0:
                    sender = HELLO;
                    break;
                case 1:
                    sender = PURCHASE;
                    break;
                case 2:
                    sender = DERP;
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
                    sender = "[Trade Shop] Hello stranger! You look new.";
                    break;
                case 2:
                    sender = "[Trade Shop] If you get 5 fish with that fishing pole I'll trade with you.";
                    break;
                case 3:
                    sender = "[Trade Shop] Do you want to trade your 5 fish for $50?";
                    break;
                case 4:
                    sender = "[Trade Shop] Hello there friend.";
                    break;
                default:
                    throw new RuntimeException("should not get here");
                }
            }
            MessagesRenderer.add(sender);
        }
    }
}
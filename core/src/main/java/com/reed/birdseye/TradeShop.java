package com.reed.birdseye;

import java.util.Random;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.github.slamm.morrigna.core.Assets;
import com.github.slamm.morrigna.core.HudSystem.MessagesRenderer;

public class TradeShop {

    public static int cash = 0;

    public static String cashString;

    String derp = "[Trade Shop]: What a nice game this is.";

    final int DISTANCE_FROM_SHOP = 100;

    String hello = "[Trade Shop]: Hello there stranger!";

    String purchase = "[Trade Shop]: Anything you want to trade with me?";

    Random r = new Random();

    float timer = 5;

    int x = 1838, y = 2906;

    public void draw(SpriteBatch batch) {
        batch.draw(Assets.tradePerson, x, y);
    }

    public void drawInputText(SpriteBatch batch, BitmapFont font) {
        if (Tutorial.step == 3 && closeEnough()) {
            font.draw(batch, "Yes                    No", 800, 50);
        }
    }

    public void handleInput() {
        if (Tutorial.step == 3 && closeEnough()) {
            if (Gdx.input.getX() > 785 && Gdx.input.getX() < 836 && Gdx.input.getY() > 470 && Gdx.input.getY() < 515
                    && Gdx.input.isTouched()) {
                MessagesRenderer.add("[Trade Shop] Here is your money, thanks for the business!");
                timer = 0;
                cash -= 50;
                Tutorial.step += 1;
            }
        }
    }

    public void textSetter() {
        String sender = "";
        if (ableToSend()) {
            if (Tutorial.step == 100) {
                switch (r.nextInt(3)) {
                case 0:
                    sender = hello;
                    break;
                case 1:
                    sender = purchase;
                    break;
                case 2:
                    sender = derp;
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
    }

    boolean ableToSend() {
        if (closeEnough() && timer >= 5) {
            timer = 0;
            return true;
        }
        return false;
    }

    boolean closeEnough() {
        return Math.sqrt((x - Player.x) * (x - Player.x) + (y - Player.y) * (y - Player.y)) < DISTANCE_FROM_SHOP;
    }
}

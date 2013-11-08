package com.github.slamm.morrigna.core;

import java.util.Random;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.TimeUtils;
import com.reed.birdseye.Coal;
import com.reed.birdseye.Fishing;
import com.reed.birdseye.Food;
import com.reed.birdseye.House;
import com.reed.birdseye.Player;
import com.reed.birdseye.Points;
import com.reed.birdseye.SwordShopOwner;
import com.reed.birdseye.TradeShop;
import com.reed.birdseye.Tree;
import com.reed.birdseye.Tutorial;

public class HudSystem {

    public static class Inventory extends InputAdapter {

        static boolean inventoryVisible;

        private static final int ROW_1_Y = 420;

        private static final int ROW_2_Y = 800;

        @Override
        public boolean keyUp(int keycode) {
            if (Keys.ESCAPE == keycode) {
                Player.ableToMove = true;
                Player.drawCharacter = true;
                inventoryVisible = false;
                return true;
            }
            return false;
        }

        public void render(BitmapFont font, SpriteBatch batch) {
            if (inventoryVisible) {
                batch.draw(Assets.inventory, 0, 0);
                // move somewhere else
                Player.ableToMove = false;
                Player.drawCharacter = false;
                // draw amounts
                font.draw(batch, Tree.amountOfWoodString, ROW_1_Y, 388);
                font.draw(batch, Coal.amountOfCoalString, ROW_1_Y, 356);
                font.draw(batch, Fishing.amountOfFishString, ROW_1_Y, 330);
                font.draw(batch, Food.amountOfFoodString, ROW_1_Y, 302);
                // row 2
                font.draw(batch, TradeShop.cashString, ROW_2_Y, 135);
                font.draw(batch, String.valueOf(Food.foodLevel) + "%", ROW_2_Y, 105);
                font.draw(batch, String.valueOf(Points.currentLevel), ROW_2_Y, 76);
            }
        }
    }

    public static class MessagesRenderer {

        private static class Message implements Comparable<Message> {

            private final float id;

            private final String message;

            public Message(String message, float id) {
                this.message = message;
                this.id = id;
            }

            @Override
            public int compareTo(Message o) {
                return Float.compare(id, o.id);
            }
        }

        private static final Array<Message> LIST = new Array<>();

        /**
         * current time for proper sorting latter
         */
        private static float sec;

        public static void add(String message) {
            LIST.add(new Message(message, sec));
        }

        public void render(BitmapFont font, SpriteBatch batch) {
            // batch.draw(Assets.chatBox, 0, 0);
            for (int i = 0; i < LIST.size; i++) {
                font.draw(batch, LIST.get(i).message, 20, i * 30 + 30);
            }
        }

        public void update() {
            sec = TimeUtils.nanoTime() * MathUtils.nanoToSec;
            LIST.sort();
            for (int i = 0; i < LIST.size; i++) {
                if (LIST.get(i).id < sec - 15) {
                    LIST.removeIndex(i);
                }
            }
            LIST.sort();
        }
    }

    public static class SwordShopRenderer extends InputAdapter {

        private static final String DERP = "[Sword Shop] What a nice game this is.";

        private static final int DISTANCE_FROM_SHOP = 100;

        private static final String HELLO = "[Sword Shop] Hello there!";

        private static final String MY_JOB = "[Sword Shop] I love my job.";

        private static final String PURCHASE = "[Sword Shop] Would you like to buy a sword?";

        private static final Random RANDOM = new Random();

        private static final int TIMER_MAX = 15;

        private float timer = 15;

        private static boolean closeEnough() {
            return Math.sqrt((SwordShopOwner.X - Player.x) * (SwordShopOwner.X - Player.x)
                    + (SwordShopOwner.Y - Player.y) * (SwordShopOwner.Y - Player.y)) < DISTANCE_FROM_SHOP;
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
                TradeShop.cash -= 50;
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

    public static class TopMenuRenderer extends InputAdapter {

        /**
         * 0 is first cell, etc, 5 means null conluding that it is not drawn
         */
        public static int currentTool = 5;

        public void render(SpriteBatch batch) {
            batch.draw(Assets.itemSelector, Gdx.graphics.getWidth() / 2 - 250, Gdx.graphics.getHeight() - 75);
            // decides where the currentTool white box is drawn - please excuse these *random* numbers
            switch (currentTool) {
            case 0:
                batch.draw(Assets.currentItem, 230, 465);
                break;
            case 1:
                batch.draw(Assets.currentItem, 230 + Assets.currentItem.getWidth() * 1 - 7 * 1, 465);
                break;
            case 2:
                batch.draw(Assets.currentItem, 230 + Assets.currentItem.getWidth() * 2 - 7 * 2, 465);
                break;
            case 3:
                batch.draw(Assets.currentItem, 230 + Assets.currentItem.getWidth() * 3 - 7 * 3, 465);
                break;
            case 4:
                batch.draw(Assets.currentItem, 230 + Assets.currentItem.getWidth() * 4 - 7 * 4, 465);
                break;
            default:
                break; // seems it 5
            }
        }

        @Override
        public boolean touchUp(int x, int y, int pointer, int button) {
            if (x > 649 && x < 723 && y > 6 && y < 68) {
                // opens invertory
                Inventory.inventoryVisible = true;
            } else if (y < 74 && y > 0 && x > 230 && x < 310) {
                currentTool = 0;
            } else if (y < 74 && y > 0 && x > 310 && x < 390) {
                currentTool = 1;
            } else if (y < 74 && y > 0 && x > 390 && x < 470) {
                currentTool = 2;
            } else if (y < 74 && y > 0 && x > 470 && x < 550) {
                currentTool = 3;
            } else if (y < 74 && y > 0 && x > 550 && x < 630) {
                currentTool = 4;
            } else {
                return false;
            }
            return true;
        }
    }

    private static class ToolsListRenderer {

        private static final int BOX_WIDTH = 80;

        /**
         * draws at appropriate x coordinate
         */
        private static final int TOOL_X = 12;

        /**
         * draws at 65 from top of screen as shown below
         */
        private static final int TOOL_Y = 65;

        private final int startOfTopBar;

        public ToolsListRenderer() {
            startOfTopBar = Gdx.graphics.getWidth() / 2 - Assets.itemSelector.getWidth() / 2;
        }

        public void render(SpriteBatch batch) {
            // draw sword
            if (Tutorial.step >= 7) {
                batch.draw(Assets.toolsMasterAtlas.findRegion("w_shortsword_0"),
                        startOfTopBar + 3 * BOX_WIDTH + TOOL_X, Gdx.graphics.getHeight() - TOOL_Y, 70, 70);
            }
            // draw pick axe
            batch.draw(Assets.toolsMasterAtlas.findRegion("pick"), startOfTopBar + 0 * BOX_WIDTH + TOOL_X,
                    Gdx.graphics.getHeight() - TOOL_Y, 50, 50);
            if (Tutorial.step >= 5) {
                // draw hatchet
                batch.draw(Assets.toolsMasterAtlas.findRegion("gear_swords"), startOfTopBar + 2 * BOX_WIDTH + TOOL_X,
                        Gdx.graphics.getHeight() - TOOL_Y, 50, 50);
            }
            if (Tutorial.step >= 2) {
                // draw fishing rod
                batch.draw(Assets.toolsMasterAtlas.findRegion("fishingrod"), startOfTopBar + 1 * BOX_WIDTH + TOOL_X,
                        Gdx.graphics.getHeight() - TOOL_Y, 80, 80);
            }
        }
    }

    private final SpriteBatch batch;

    private final OrthographicCamera camera;

    private BitmapFont currentFont;

    private House house;

    private Inventory inv;

    private MessagesRenderer messages;

    private Points points;

    private ShapeRenderer shapeRenderer;

    private SwordShopRenderer swordShop;

    private ToolsListRenderer toolsListRenderer;

    private TopMenuRenderer topMenu;

    private TradeShop tradeShop;

    public HudSystem(SpriteBatch batch, OrthographicCamera camera, Points points, TradeShop trade, House house,
            BitmapFont currentFont) {
        this.batch = batch;
        this.camera = camera;
        this.points = points;
        messages = new MessagesRenderer();
        inv = new Inventory();
        swordShop = new SwordShopRenderer();
        tradeShop = trade;
        this.house = house;
        this.currentFont = currentFont;
        shapeRenderer = new ShapeRenderer();
        toolsListRenderer = new ToolsListRenderer();
        topMenu = new TopMenuRenderer();
        InputMultiplexer multiplexer;
        if (Gdx.input.getInputProcessor() instanceof InputMultiplexer) {
            multiplexer = (InputMultiplexer) Gdx.input.getInputProcessor();
        } else {
            multiplexer = new InputMultiplexer(Gdx.input.getInputProcessor());
        }
        multiplexer.addProcessor(topMenu);
        multiplexer.addProcessor(inv);
        multiplexer.addProcessor(swordShop);
        Gdx.input.setInputProcessor(multiplexer);
    }

    public void render(float delta) {
        batch.begin();
        batch.setProjectionMatrix(camera.combined);
        toolsListRenderer.render(batch);
        topMenu.render(batch);
        points.draw(batch);
        messages.render(currentFont, batch);
        inv.render(currentFont, batch);
        swordShop.render(currentFont, batch);
        tradeShop.drawInputText(batch, currentFont);
        house.furnaceGUIdraw(batch, delta, currentFont);
        batch.end();
        shapeRenderer.setProjectionMatrix(camera.combined);
        points.drawBars(shapeRenderer);
    }

    public void update() {
        messages.update();
        swordShop.update();
    }
}
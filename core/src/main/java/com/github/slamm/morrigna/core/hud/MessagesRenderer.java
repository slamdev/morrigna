package com.github.slamm.morrigna.core.hud;

import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.TimeUtils;

public class MessagesRenderer {

    private static class Message implements Comparable<MessagesRenderer.Message> {

        private final float id;

        private final String message;

        public Message(String message, float id) {
            this.message = message;
            this.id = id;
        }

        @Override
        public int compareTo(MessagesRenderer.Message o) {
            return Float.compare(id, o.id);
        }
    }

    private static final Array<MessagesRenderer.Message> LIST = new Array<>();

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
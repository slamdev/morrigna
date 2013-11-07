package com.reed.birdseye;

import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.utils.Array;

public class Messages {

	public static Array<Message> messagesArray = new Array<Message>();

	void drawText(BitmapFont font, SpriteBatch batch) {
		// batch.draw(Assets.chatBox, 0, 0);
		for (int i = 0; i < messagesArray.size; i++) {
			font.draw(batch, messagesArray.get(i).message, 20, i * 30 + 30);
		}
	}

	void removeOldMessages() {
		for (int i = 0; i < messagesArray.size; i++) {
			if (messagesArray.get(i).id < sec - 15) {
				messagesArray.removeIndex(i);
			}
		}
		messagesArray.sort();
	}

	private static float sec;

	/** Returns current time for proper sorting latter */
	public static float getSec() {
		return sec;
	}

	public static void setSec(float sec) {
		Messages.sec = sec;
	}

	static void update() {
		sec = com.badlogic.gdx.utils.TimeUtils.nanoTime() * MathUtils.nanoToSec;

		messagesArray.sort();
	}
}
package com.reed.birdseye;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

public class Level {
	static float middleX = Gdx.graphics.getWidth() / 2 - 16;
	static float middleY = Gdx.graphics.getHeight() / 2 - 24;

	static int grassX = -64; // position for bottom left grass
	static int grassY = -64; // ^

	void draw(SpriteBatch batch) {
		// draws grass image (64 , 64) everywhere
		for (int i = 0; i < Gdx.graphics.getWidth() + 128; i += 64) {
			for (int j = 0; j < Gdx.graphics.getHeight() + 128; j += 64) {
				batch.draw(Assets.grass, grassX + i, grassY + j);
			}
		}
	}

	void update() {
		// sets map back to original position before white background becomes
		// visible
		if (grassY <= -128 || grassY >= 0) {
			grassY = -64;
		}
		if (grassX <= -128 || grassX >= 0) {
			grassX = -64;
		}
		setMap();

	}

	private static int currentMap = 0;

	public static int getCurrentMap() {
		return currentMap;
	}

	/**Sets current map (uses integers for saving)
	 * @param <b>0</b> is Main map <br>
	 * <b>1</b> is river house
	 */
	public static void setCurrentMap(int currentMap) {
		Level.currentMap = currentMap;
	}
	
	
	void setMap() {
		switch (currentMap) {
		case 0: {
			GameScreen.mapRenderer.setMap(Assets.mainTiledMap);
			break;
		}
		case 1: {
			GameScreen.mapRenderer.setMap(Assets.riverHouse);
			break;
		}
		}
	}

}
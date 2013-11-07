package com.reed.birdseye;

import com.badlogic.gdx.Application.ApplicationType;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.FPSLogger;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;
import com.droidinteractive.box2dlight.RayHandler;

public class GameScreen implements Screen {
	Game game;
	OrthographicCamera camera;
	// static for getting and setting position during save / load
	static OrthographicCamera mapCamera;
	// static to modify when entering different map areas.
	static OrthogonalTiledMapRenderer mapRenderer;

	SpriteBatch batch;
	ShapeRenderer shapeRenderer;
	BitmapFont currentFont;
	Level level;
	Player player;
	ArrayListsz arrays;
	TopMenu topMenu;
	Inventory inv;
	CollisionDetection collision;
	Android android;
	Points points;
	Messages message;
	SwordShop swordShop;
	Fishing fishing;
	TradeShop trade;
	World world;
	static RayHandler rayHandler;
	House house;
	Particles smoke;
	Food food;

	public GameScreen(Game game) {
		this.game = game;
		float w = Gdx.graphics.getWidth();
		float h = Gdx.graphics.getHeight();

		batch = new SpriteBatch();
		camera = new OrthographicCamera(w, h);
		mapCamera = new OrthographicCamera(w, h);
		camera.update();
		mapCamera.update();
		mapRenderer = new OrthogonalTiledMapRenderer(Assets.mainTiledMap, batch);
		// translate HUD camera to make bottom left cordinate 0,0
		camera.translate(w / 2, h / 2);
		// translate camera to spawn point
		mapCamera.translate(1422 + 16, 3562 + 24);

		shapeRenderer = new ShapeRenderer();
		level = new Level();
		player = new Player();
		arrays = new ArrayListsz();
		topMenu = new TopMenu();
		inv = new Inventory();
		// collision detection problem?
		fps = new FPSLogger();
		android = new Android();
		points = new Points();
		collision = new CollisionDetection();

		message = new Messages();
		swordShop = new SwordShop();
		fishing = new Fishing();
		trade = new TradeShop();
		arrays.treeArrayEstablisher();
		house = new House();

		if (Gdx.app.getType() == ApplicationType.Android) {
			currentFont = Assets.cgfFont;
		} else
			currentFont = new BitmapFont();

		world = new World(new Vector2(0, 0), true);

		rayHandler = new RayHandler(world);

		/*
		 * int[] maxTextureSize = new int[1]; IntBuffer buf =
		 * BufferUtils.newIntBuffer(16);
		 * Gdx.gl.glGetIntegerv(GL10.GL_MAX_TEXTURE_SIZE, buf); int result =
		 * buf.get(); System.out.println(result); int result2 = buf.get();
		 * System.out.println(result);
		 */

		smoke = new Particles();
		food = new Food();
	}

	int lightX = 400, lightY = 400;
	FPSLogger fps;

	public void update(float deltaTime) {
		// can be changed anytime in time class
		player.setSprites();
		player.move();
		player.input();
		topMenu.input();
		inv.input();
		points.updateLevel();
		level.update();
		Messages.update();
		message.removeOldMessages();
		swordShop.textSetter();
		swordShop.update();
		swordShop.handleInput();
		collision.doCollision();
		inv.input();
		fishing.update();
		fishing.fishCaught();
		trade.textSetter();
		trade.update();
		trade.handleInput();
		house.update();
		Time.update(rayHandler);
		camera.update();
		arrays.mobUpdate();
		food.affectHealth();
		food.looseHunger();
		house.furnace();
		arrays.updateCoal();
		house.addCoalandFood();
	}
	

	public static float xRate = 0;
	public static float yRate = 0;

	public void draw(float deltaTime) {
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		batch.begin();
		// sets camera for drawing static items
			batch.setProjectionMatrix(camera.combined);

			if (Time.isOutdoors()) {
				level.draw(batch);
			}

		batch.end();
			batch.setProjectionMatrix(mapCamera.combined);
			mapCamera.translate(xRate, yRate);
			mapCamera.update();
			mapRenderer.setView(mapCamera);
			mapRenderer.render();

		batch.begin();
			// set camera for drawing moving items.
			batch.setProjectionMatrix(mapCamera.combined);
			swordShop.draw(batch);
			trade.draw(batch);
			arrays.drawTreeTrunk(batch);
			arrays.drawCoal(batch);
			arrays.mobDraw(batch);
			arrays.pigUpdateAndDraw(batch);
		batch.end();
			shapeRenderer.setProjectionMatrix(mapCamera.combined);
			arrays.mobHealthBars(shapeRenderer);
			arrays.pigHealthBars(shapeRenderer);
		batch.begin();
			player.draw(batch, currentFont);
			// set static for tool drawing (so it is affected by lights)
			batch.setProjectionMatrix(camera.combined);
			player.drawCurrent(batch);
			// set camera for drawing moving items.
			batch.setProjectionMatrix(mapCamera.combined);
			arrays.drawBrush(batch, currentFont);
			smoke.smokeUpdateAndDraw(batch, deltaTime);
		batch.end();
			rayHandler.setCombinedMatrix(mapCamera.combined);
			rayHandler.updateAndRender();
		batch.begin();
			// more static items (HUD stuff)
			batch.setProjectionMatrix(camera.combined);
			player.drawTools(batch);
			topMenu.draw(batch, currentFont);
			player.drawTools(batch);
			points.draw(batch);
			message.drawText(currentFont, batch);
			inv.draw(batch, currentFont);
			swordShop.drawInputText(batch, currentFont);
			trade.drawInputText(batch, currentFont);
			house.furnaceGUIdraw(batch, deltaTime, currentFont, shapeRenderer);
		batch.end();
			shapeRenderer.setProjectionMatrix(camera.combined);
			points.drawBars(shapeRenderer);
			

	}

	@Override
	public void render(float delta) {
		update(delta);
		draw(delta);
		handleInput();
	}

	/** Handle input for zooming in and out */
	private void handleInput() {
		if (Gdx.input.isKeyPressed(Input.Keys.O)) {
			camera.zoom += 0.02;
			mapCamera.zoom += 0.02;
		}
		if (Gdx.input.isKeyPressed(Input.Keys.I)) {
			camera.zoom -= 0.02;
			mapCamera.zoom -= 0.02;
		}
		if (Gdx.input.isKeyPressed(Input.Keys.U)) {
			camera.zoom = 1;
			mapCamera.zoom = 1;
		}
	}

	@Override
	public void resize(int width, int height) {
	}

	@Override
	public void show() {
	}

	@Override
	public void hide() {
	}

	@Override
	public void pause() {
	}

	@Override
	public void resume() {
	}

	@Override
	public void dispose() {
	}
}
package com.github.slamm.morrigna.core.map;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;
import com.droidinteractive.box2dlight.RayHandler;
import com.reed.birdseye.Particles;
import com.reed.birdseye.Time;

public class MapRenderSystem {

    /**
     * static to modify when entering different map areas.
     */
    public static MapRenderer mapRenderer;

    public static RayHandler rayHandlerRenderer;

    private final SpriteBatch batch;

    private final OrthographicCamera camera;

    private final CoalRenderer coalRenderer;

    private final CreeperRenderer creeperRenderer;

    private final BitmapFont currentFont;

    private final PigRenderer pigRenderer;

    private final PlayerRenderer playerRenderer;

    private final Particles smokePracticle;

    private final SwordShopOwnerRenderer swordShopOwnerRenderer;

    private final TradeShopOwnerRenderer tradeShopOwnerRenderer;

    private final TreeRenderer treeRenderer;

    public MapRenderSystem(SpriteBatch batch, OrthographicCamera camera, BitmapFont currentFont) {
        this.batch = batch;
        this.camera = camera;
        this.currentFont = currentFont;
        swordShopOwnerRenderer = new SwordShopOwnerRenderer();
        tradeShopOwnerRenderer = new TradeShopOwnerRenderer();
        playerRenderer = new PlayerRenderer();
        mapRenderer = new MapRenderer();
        smokePracticle = new Particles();
        rayHandlerRenderer = new RayHandler(new World(new Vector2(0, 0), true));
        treeRenderer = new TreeRenderer();
        creeperRenderer = new CreeperRenderer();
        pigRenderer = new PigRenderer();
        coalRenderer = new CoalRenderer();
        InputMultiplexer multiplexer;
        if (Gdx.input.getInputProcessor() instanceof InputMultiplexer) {
            multiplexer = (InputMultiplexer) Gdx.input.getInputProcessor();
        } else {
            multiplexer = new InputMultiplexer(Gdx.input.getInputProcessor());
        }
        Gdx.input.setInputProcessor(multiplexer);
    }

    public void render(float delta) {
        mapRenderer.render(camera);
        batch.begin();
        batch.setProjectionMatrix(camera.combined);
        swordShopOwnerRenderer.draw(batch);
        tradeShopOwnerRenderer.draw(batch);
        playerRenderer.draw(batch);
        smokePracticle.smokeUpdateAndDraw(batch, delta);
        treeRenderer.render(currentFont, batch);
        creeperRenderer.render(camera, batch);
        pigRenderer.render(camera, batch);
        coalRenderer.render(batch);
        batch.end();
        rayHandlerRenderer.setCombinedMatrix(camera.combined);
        rayHandlerRenderer.updateAndRender();
    }

    public void update() {
        playerRenderer.update();
        creeperRenderer.update();
        coalRenderer.update();
        Time.update(rayHandlerRenderer);
        input();
    }

    private void input() {
        if (Gdx.input.isKeyPressed(Input.Keys.O)) {
            camera.zoom += 0.02;
        }
        if (Gdx.input.isKeyPressed(Input.Keys.I)) {
            camera.zoom -= 0.02;
        }
        if (Gdx.input.isKeyPressed(Input.Keys.U)) {
            camera.zoom = 1;
        }
    }
}
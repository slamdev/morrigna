package com.github.slamm.morrigna.core;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector3;

public class ScrollableScreen implements Screen, InputProcessor {

    float PIXELS_PER_METER = 32;

    float WORLD_HEIGHT_METERS = 100;

    float WORLD_WIDTH_METERS = 100;

    private SpriteBatch _batch;

    private final OrthographicCamera _camera;

    private final Vector3 _lastMouseScreenPos;

    private final Vector3 _lastMouseWorldDragPos;

    private final Vector3 _lastMouseWorldMovePos;

    private final Vector3 _lastMouseWorldPressPos;

    private final Vector3 _lastMouseWorldReleasePos;

    private int _mouseButtonDown;

    private Texture _texture;

    private float _zoomingQuantity = 0f;

    public ScrollableScreen() {
        Gdx.input.setInputProcessor(this);
        // test texture that is 100m by 100m
        _texture = new Texture(Gdx.files.internal("sc_map.png"));
        _camera = new OrthographicCamera();
        // batching
        _batch = new SpriteBatch();
        // controls
        _lastMouseWorldMovePos = new Vector3();
        _lastMouseWorldDragPos = new Vector3();
        _lastMouseWorldPressPos = new Vector3();
        _lastMouseWorldReleasePos = new Vector3();
        _lastMouseScreenPos = new Vector3();
    }

    @Override
    public void dispose() {
        _texture.dispose();
    }

    @Override
    public void hide() {
    }

    @Override
    public boolean keyDown(int keycode) {
        if (Input.Keys.A == keycode) {
            _zoomingQuantity += 0.02;
        }
        if (Input.Keys.Q == keycode) {
            _zoomingQuantity -= 0.02;
        }
        return false;
    }

    @Override
    public boolean keyTyped(char character) {
        return false;
    }

    @Override
    public boolean keyUp(int keycode) {
        if (Input.Keys.A == keycode) {
            _zoomingQuantity -= 0.02;
        }
        if (Input.Keys.Q == keycode) {
            _zoomingQuantity += 0.02;
        }
        return false;
    }

    @Override
    public boolean mouseMoved(int x, int y) {
        _lastMouseWorldMovePos.set(x, y, 0);
        _camera.unproject(_lastMouseWorldMovePos);
        _lastMouseScreenPos.set(x, y, 0);
        return false;
    }

    @Override
    public void pause() {
    }

    @Override
    public void render(float delta) {
        _camera.zoom += _zoomingQuantity;
        _camera.update();
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        _batch.setProjectionMatrix(_camera.combined);
        _batch.begin();
        // 100mx100m texture in world coordinates
        _batch.draw(_texture, -WORLD_WIDTH_METERS / 2f, -WORLD_HEIGHT_METERS / 2f, WORLD_WIDTH_METERS,
                WORLD_HEIGHT_METERS);
        _batch.end();
    }

    @Override
    public void resize(int width, int height) {
        /*
         * This resize code will ensure that the window is filled with world. The camera position will be maintained during
         * the resize so that whatever it was looking at isn't suddenly displaced or off screen altogether.
         * Zoom is conveniently handled by the camera internals so doesn't need to be taken into account here.
         */
        Vector3 pos = new Vector3(_camera.position);
        // enforce a fixed number of pixels per meter otherwise aspect ratio will skew in one dimension
        _camera.setToOrtho(false, width / PIXELS_PER_METER, height / PIXELS_PER_METER);
        // move the camera back to where it was - zoom hasn't changed so this is ok to do in screen coords.
        _camera.translate(pos.x - _camera.position.x, pos.y - _camera.position.y);
    }

    @Override
    public void resume() {
    }

    @Override
    public boolean scrolled(int amount) {
        return false;
    }

    @Override
    public void show() {
    }

    @Override
    public boolean touchDown(int x, int y, int pointer, int button) {
        _mouseButtonDown = button;
        _lastMouseWorldPressPos.set(x, y, 0);
        _camera.unproject(_lastMouseWorldPressPos);
        System.out.println("Mouse down at world: " + _lastMouseWorldPressPos);
        return false;
    }

    @Override
    public boolean touchDragged(int x, int y, int pointer) {
        if (_mouseButtonDown == Input.Buttons.RIGHT) {
            _camera.translate((x - _lastMouseScreenPos.x) / PIXELS_PER_METER, (y - _lastMouseScreenPos.y)
                    / -PIXELS_PER_METER);
        }
        _lastMouseWorldDragPos.set(x, y, 0);
        _camera.unproject(_lastMouseWorldDragPos);
        _lastMouseWorldMovePos.set(x, y, 0);
        _camera.unproject(_lastMouseWorldMovePos);
        _lastMouseScreenPos.set(x, y, 0);
        return false;
    }

    @Override
    public boolean touchUp(int x, int y, int pointer, int button) {
        _mouseButtonDown = -1;
        _lastMouseWorldReleasePos.set(x, y, 0);
        _camera.unproject(_lastMouseWorldReleasePos);
        System.out.println("Mouse up at world: " + _lastMouseWorldReleasePos);
        return false;
    }
}
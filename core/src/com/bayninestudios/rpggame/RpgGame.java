package com.bayninestudios.rpggame;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapRenderer;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.utils.viewport.Viewport;

public class RpgGame extends ApplicationAdapter implements InputProcessor {
	private SpriteBatch batch;
	private Texture img;
	private TiledMap tiledMap;
	private OrthographicCamera camera;
	private TiledMapRenderer tiledMapRenderer;
	private TiledMapTileLayer collisionLayer;
	private Viewport viewport;
	private ShapeRenderer sr;
	private Player player;
	private float delta;

	@Override
	public void create () {
//        viewport = new FitViewport(320, 200);
        batch = new SpriteBatch();
        sr = new ShapeRenderer();
		img = new Texture("redbird.png");

        float w = Gdx.graphics.getWidth();
        float h = Gdx.graphics.getHeight();

        camera = new OrthographicCamera();
        camera.setToOrtho(false,w,h);
        camera.update();
        tiledMap = new TmxMapLoader().load("town.tmx");
        tiledMapRenderer = new OrthogonalTiledMapRenderer(tiledMap);
        collisionLayer = (TiledMapTileLayer) tiledMap.getLayers().get("Tile Layer 1");
        player = new Player(collisionLayer);
        Gdx.input.setInputProcessor(this);
	}

	@Override
	public void render () {
		delta = Gdx.graphics.getDeltaTime();
		player.movePlayer(delta);
		Gdx.gl.glClearColor(0, 0, 1, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		camera.position.set(player.posX + 8, player.posY + 8, 0);
		camera.update();
        tiledMapRenderer.setView(camera);
        tiledMapRenderer.render();
		batch.begin();
		batch.draw(img, 311, 199);
		batch.end();
		sr.begin(ShapeRenderer.ShapeType.Point);
		sr.setColor(1f,1f,1f,.5f);
		sr.point(319f,199f, 0f);
		sr.point(319f,200f, 0f);
		sr.point(320f,199f, 0f);
		sr.point(320f,200f, 0f);
		sr.end();
	}
	
	@Override
	public void dispose () {
		batch.dispose();
		img.dispose();
		sr.dispose();
	}

	@Override
    public void resize(int width, int height)
    {
//        viewport.update(width, height);
    }

    @Override
	public boolean keyDown (int keycode) {
		if (keycode == Input.Keys.ESCAPE) {
			Gdx.app.exit();
		}
		Gdx.app.log("delta",">>> "+delta);
		if (keycode == Input.Keys.LEFT)
			player.moveX -= player.MOVESPEED;
		if (keycode == Input.Keys.RIGHT)
			player.moveX += player.MOVESPEED;
		if (keycode == Input.Keys.DOWN)
			player.moveY -= player.MOVESPEED;
		if (keycode == Input.Keys.UP)
			player.moveY += player.MOVESPEED;
		return true;
	}

	public boolean keyUp (int keycode) {
		if (keycode == Input.Keys.LEFT)
			player.moveX += player.MOVESPEED;
		if (keycode == Input.Keys.RIGHT)
			player.moveX -= player.MOVESPEED;
		if (keycode == Input.Keys.DOWN)
			player.moveY += player.MOVESPEED;
		if (keycode == Input.Keys.UP)
			player.moveY -= player.MOVESPEED;
		return true;
	}

	public boolean keyTyped (char character) {
		return false;
	}

	public boolean touchDown (int x, int y, int pointer, int button) {
		return false;
	}

	public boolean touchUp (int x, int y, int pointer, int button) {
		return false;
	}

	public boolean touchDragged (int x, int y, int pointer) {
		return false;
	}

	public boolean mouseMoved (int x, int y) {
		return false;
	}

	public boolean scrolled (int amount) {
		return false;
	}
}

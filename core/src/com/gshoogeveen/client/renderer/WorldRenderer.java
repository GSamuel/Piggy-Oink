package com.gshoogeveen.client.renderer;

import java.util.ArrayList;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.Disposable;
import com.gshoogeveen.gameobject.GameObject;
import com.gshoogeveen.world.World;

public class WorldRenderer implements Disposable
{
	public static final int TILE_SIZE = 64;
	
	private World world;
	
	//View Stuff
	private OrthographicCamera camera;
	private SpriteBatch batch;
	
	public WorldRenderer(Stage stage, World world)
	{
		this.world = world;
		batch = new SpriteBatch();

		camera = new OrthographicCamera();
		camera.setToOrtho(true, 960, 640);

		stage.getViewport().setCamera(camera);
	}
	
	public void render()
	{
		Gdx.gl.glClearColor(0.7f, 0.78f, 0.3f, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		camera.position.set((int) (world.getPlayer().getX() * TILE_SIZE),
				(int) (world.getPlayer().getY() * TILE_SIZE), 0);

		camera.update();

		batch.setProjectionMatrix(camera.combined);

		ArrayList<GameObject> objects = world.query2D();
		CameraSorting.sort(camera, objects);

		// render objects
		batch.begin();
		ObjectRenderer.render(batch, camera, objects);
		batch.end();

	}

	@Override
	public void dispose()
	{
		batch.dispose();
	}
}

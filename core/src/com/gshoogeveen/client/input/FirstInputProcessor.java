package com.gshoogeveen.client.input;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.input.GestureDetector.GestureListener;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.gshoogeveen.client.renderer.WorldRenderer;
import com.gshoogeveen.entity.Entity;

public class FirstInputProcessor implements InputProcessor, GestureListener
{
	private Entity player;
	private Stage stage;
	private OrthographicCamera camera;
	private float firstAngle;
	private float prevAngle;

	private boolean w, a, s, d, q, e;

	public FirstInputProcessor(Stage stage, Entity player)
	{
		this.stage = stage;
		this.player = player;
		this.camera = (OrthographicCamera) stage.getCamera();
	}

	@Override
	public boolean keyDown(int keycode)
	{
		q = Gdx.input.isKeyPressed(Input.Keys.Q);
		e = Gdx.input.isKeyPressed(Input.Keys.E);
		w = Gdx.input.isKeyPressed(Input.Keys.W);
		a = Gdx.input.isKeyPressed(Input.Keys.A);
		s = Gdx.input.isKeyPressed(Input.Keys.S);
		d = Gdx.input.isKeyPressed(Input.Keys.D);

		return true;
	}

	@Override
	public boolean keyUp(int keycode)
	{
		q = Gdx.input.isKeyPressed(Input.Keys.Q);
		e = Gdx.input.isKeyPressed(Input.Keys.E);
		w = Gdx.input.isKeyPressed(Input.Keys.W);
		a = Gdx.input.isKeyPressed(Input.Keys.A);
		s = Gdx.input.isKeyPressed(Input.Keys.S);
		d = Gdx.input.isKeyPressed(Input.Keys.D);

		return true;
	}

	@Override
	public boolean keyTyped(char character)
	{
		return false;
	}

	@Override
	public boolean touchDown(int screenX, int screenY, int pointer, int button)
	{
		if (Gdx.input.isTouched(0) && !Gdx.input.isTouched(1))
		{
			Vector3 touchPos = new Vector3();
			touchPos.set(screenX, screenY, 0);
			stage.getCamera().unproject(touchPos);

			float angle = (float) Math.atan2(touchPos.y - player.getY()
					* WorldRenderer.TILE_SIZE, touchPos.x - player.getX()
					* WorldRenderer.TILE_SIZE);
			player.moveTo(angle);
			return true;
		}
		else
			player.stop();
		return false;
	}

	@Override
	public boolean touchUp(int screenX, int screenY, int pointer, int button)
	{
		player.stop();
		return true;
	}

	@Override
	public boolean touchDragged(int screenX, int screenY, int pointer)
	{
		if (Gdx.input.isTouched(0) && !Gdx.input.isTouched(1))
		{
			Vector3 touchPos = new Vector3();
			touchPos.set(screenX, screenY, 0);
			stage.getCamera().unproject(touchPos);

			float angle = (float) Math.atan2(touchPos.y - player.getY()
					* WorldRenderer.TILE_SIZE, touchPos.x - player.getX()
					* WorldRenderer.TILE_SIZE);
			player.moveTo(angle);
			return true;
		} else
			player.stop();
		return false;
	}

	@Override
	public boolean mouseMoved(int screenX, int screenY)
	{
		return false;
	}

	@Override
	public boolean scrolled(int amount)
	{
		return false;
	}

	public void update()
	{
		if (q && !e)
		{
			camera.rotate(-45f * Gdx.graphics.getDeltaTime());
			camera.update();
		}
		if (e && !q)
		{
			camera.rotate(45f * Gdx.graphics.getDeltaTime());
			camera.update();
		}

		if (w || a || s || d)
		{
			float angle = (float) (Math.atan2(camera.up.x, camera.up.y));

			float dx = 0f, dy = 0f;
			if (Gdx.input.isKeyPressed(Input.Keys.A))
				dx -= 1f;
			if (Gdx.input.isKeyPressed(Input.Keys.D))
				dx += 1f;
			if (Gdx.input.isKeyPressed(Input.Keys.W))
				dy -= 1f;
			if (Gdx.input.isKeyPressed(Input.Keys.S))
				dy += 1f;

			if (dx == 0f && dy == 0f)
				player.stop();
			else
				player.moveTo(new Vector2(dx, dy).angleRad() - angle
						- (float) Math.PI);
		} else if (!Gdx.input.isTouched())
			player.stop();
	}

	@Override
	public boolean touchDown(float x, float y, int pointer, int button)
	{
		return false;
	}

	@Override
	public boolean tap(float x, float y, int count, int button)
	{
		return false;
	}

	@Override
	public boolean longPress(float x, float y)
	{
		return false;
	}

	@Override
	public boolean fling(float velocityX, float velocityY, int button)
	{
		return false;
	}

	@Override
	public boolean pan(float x, float y, float deltaX, float deltaY)
	{
		return false;
	}

	@Override
	public boolean panStop(float x, float y, int pointer, int button)
	{
		return false;
	}

	@Override
	public boolean zoom(float initialDistance, float distance)
	{
		return false;
	}

	@Override
	public boolean pinch(Vector2 initP1, Vector2 initP2, Vector2 p1, Vector2 p2)
	{
		Vector2 initDiff = new Vector2();
		initDiff.set(initP1.x-initP2.x, initP1.y-initP2.y);

		Vector2 diff = new Vector2();
		diff.set(p1.x-p2.x, p1.y-p2.y);
		
		float angle1 = initDiff.angle();
		float angle2 = diff.angle();
		
		if(angle1 != firstAngle)
		{
			firstAngle = angle1;
			camera.rotate((angle2-angle1));
		}
		else
			camera.rotate(angle2-prevAngle);

		prevAngle =angle2;
		camera.update();
		return true;
	}

}

package com.gshoogeveen.entity;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;
import com.gshoogeveen.gameobject.GameObject;

public class Entity extends GameObject
{
	public static final float MAX_SPEED = 5f;
	public static final float ACCELERATION = 30f;
	public static final float FRICTION = 20f;

	private Vector2 vel;
	private Vector2 acc;

	public Entity()
	{
		this.vel = new Vector2();
		this.acc = new Vector2();
	}

	public float getVelX()
	{
		return vel.x;
	}

	public float getVelY()
	{
		return vel.y;
	}

	public float getAccX()
	{
		return acc.x;
	}

	public float getAccY()
	{
		return acc.y;
	}

	public float getVelAngle()
	{
		return vel.angleRad();
	}

	public float getAccAngle()
	{
		return acc.angleRad();
	}

	public void setSpeed(float dx, float dy)
	{
		vel.set(dx, dy);
	}

	public void setAccel(float ax, float ay)
	{
		acc.set(ax, ay);
	}

	public void moveTo(float angle)
	{
		acc.set((float) Math.cos(angle), (float) Math.sin(angle));
		acc.scl(ACCELERATION);
	}

	public void moveTo(float x, float y)
	{
		acc.set(x, y);
		acc.nor();
		acc.scl(ACCELERATION);
	}

	public void stop()
	{
		acc.setZero();
	}

	private void limitSpeed()
	{
		if (vel.len2() > MAX_SPEED * MAX_SPEED)
		{
			float angle = vel.angleRad();
			vel.set((float) Math.cos(angle), (float) Math.sin(angle));
			vel.scl(MAX_SPEED);
		}

	}

	private void applyFriction()
	{
		float speed = vel.len();

		if (speed > FRICTION * Gdx.graphics.getDeltaTime())
		{
			speed -= FRICTION * Gdx.graphics.getDeltaTime();
			float angle = vel.angleRad();
			vel.set((float) Math.cos(angle), (float) Math.sin(angle));
			vel.scl(speed);
		} else
			vel.setZero();
	}

	public void update()
	{
		vel.add(acc.x * Gdx.graphics.getDeltaTime(),
				acc.y * Gdx.graphics.getDeltaTime());
		applyFriction();
		limitSpeed();
		pos.add(vel.x * Gdx.graphics.getDeltaTime(),
				vel.y * Gdx.graphics.getDeltaTime());
	}

	public String getUp()
	{
		return this.textureName + "_up";
	}

	public String getDown()
	{
		return this.textureName + "_down";
	}

	public String getLeft()
	{
		return this.textureName + "_left";
	}

	public String getRight()
	{
		return this.textureName + "_right";
	}

}

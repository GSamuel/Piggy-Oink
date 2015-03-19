package com.gshoogeveen.gameobject;

import com.badlogic.gdx.math.Vector2;

public abstract class GameObject
{
	protected String textureName;
	protected Vector2 pos;
	protected float radius;
	protected boolean collision = false;
	protected float movable; // where 0f is not movable and 1f is max move
								// contribution

	public GameObject()
	{
		this.pos = new Vector2();
		this.radius = 0.3f;//TODO not ok
		this.collision = false;
		this.movable = 0f;
	}

	public float getX()
	{
		return pos.x;
	}
	
	public int getIntX()
	{
		return (int)pos.x;
	}

	public float getY()
	{
		return pos.y;
	}
	
	public int getIntY()
	{
		return (int) pos.y;
	}

	public void setPosition(float x, float y)
	{
		pos.set(x, y);
	}

	public void changePosition(float x, float y)
	{
		pos.add(x, y);
	}

	public float getRadius()
	{
		return radius;
	}

	public GameObject setRadius(float radius)
	{
		this.radius = radius;
		return this;
	}

	public boolean getCollision()
	{
		return collision;
	}

	public GameObject setCollision(boolean bool)
	{
		this.collision = bool;
		return this;
	}

	public float getMovable()
	{
		return movable;
	}

	public GameObject setMovable(float movable)
	{
		this.movable = movable;
		return this;
	}
	
	public String getTextureName()
	{
		return textureName;
	}
	
	public GameObject setTextureName(String textureName)
	{
		this.textureName = textureName;
		return this;
	}
	
	public abstract void update();
}

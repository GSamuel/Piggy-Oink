package com.gshoogeveen.client.renderer;

import com.badlogic.gdx.math.Vector2;
import com.gshoogeveen.gameobject.GameObject;

public class CameraWrapper implements Comparable<CameraWrapper>
{
	private GameObject o;
	private Vector2 vec;

	public CameraWrapper(GameObject o)
	{
		this.o = o;
	}
	
	public void updateValue(float angle)
	{
		vec = new Vector2(o.getX(), o.getY()).rotateRad(angle);
	}
	
	public GameObject getObject()
	{
		return o;
	}

	@Override
	public int compareTo(CameraWrapper other)
	{
        if(vec.y < other.vec.y)
        	return 1;
		if(vec.y > other.vec.y)
			return -1;
		return 0;
	}
	
	
}

package com.gshoogeveen.entity;

import com.gshoogeveen.gameobject.GameObject;

public class EntityFactory
{
	public static GameObject newCharacter()
	{
		return new Entity().setTextureName("char").setCollision(true).setMovable(1f).setRadius(0.3f);
	}
}

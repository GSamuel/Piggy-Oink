package com.gshoogeveen.gameobject;

public class GameObjectFactory
{
	public static GameObject newTree()
	{
		return new SimpleGameObject().setTextureName("tree").setCollision(true).setMovable(0f);
	}
	
	public static GameObject newPig()
	{
		return new SimpleGameObject().setTextureName("pig").setCollision(true).setMovable(1f);
	}
	
	public static GameObject newFlower(int index)
	{
		return new SimpleGameObject().setTextureName("flower"+index).setCollision(false);
	}
}

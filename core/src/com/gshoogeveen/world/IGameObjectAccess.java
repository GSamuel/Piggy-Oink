package com.gshoogeveen.world;

import java.util.ArrayList;

import com.gshoogeveen.gameobject.GameObject;

public interface IGameObjectAccess
{
	public ArrayList<GameObject> getTileGameObject(int x, int y);
}

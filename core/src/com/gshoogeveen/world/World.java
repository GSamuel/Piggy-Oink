package com.gshoogeveen.world;

import java.util.ArrayList;

import com.gshoogeveen.collision.CollisionDetector;
import com.gshoogeveen.entity.Entity;
import com.gshoogeveen.entity.EntityFactory;
import com.gshoogeveen.gameobject.GameObject;

public class World implements IGameObjectAccess
{
	private WorldSettings settings;// Nothing in here yet
	private BinQNode qTree;
	private ArrayList<Entity> entities;

	public World()
	{
		qTree = new BinQNode(10, 0, true);
		entities = new ArrayList<Entity>();
	}

	public void update()
	{
		ArrayList<GameObject> objects = new ArrayList<GameObject>();

		qTree.query2D(qTree.getMinX(), qTree.getMinY(), qTree.getMaxX(),
				qTree.getMaxY(), objects);

		for (GameObject o : objects)
			o.update();

		CollisionDetector.collision(objects);// TODO collision between all
												// possible objects atm
		qTree.validate(qTree.getMinX(), qTree.getMinY(), qTree.getMaxX(),
				qTree.getMaxY());
	}

	public ArrayList<GameObject> query2D()
	{
		ArrayList<GameObject> objects = new ArrayList<GameObject>();

		if (entities.size() > 0)
		{
			Entity player = entities.get(0);
			int minX = player.getIntX() - 10;
			int maxX = player.getIntX() + 10;
			int minY = player.getIntY() - 10;
			int maxY = player.getIntY() + 10;

			qTree.query2D(minX, minY, maxX, maxY, objects);
		}

		return objects;
	}

	public void insert(GameObject o)
	{
		qTree.insert(o);
	}

	public Entity getPlayer()
	{
		if (entities.size() > 0)
			return entities.get(0);
		else
			return (Entity) EntityFactory.newCharacter();
	}

	public void setPlayer(Entity player)
	{
		entities.add(player);
		qTree.insert(player);
	}

	public WorldSettings getWorldSettings()
	{
		return settings;
	}

	@Override
	public ArrayList<GameObject> getTileGameObject(int x, int y)
	{
		ArrayList<GameObject> objects = new ArrayList<GameObject>();
		qTree.query2D(x, y, x+1, y+1, objects);
		return objects;
	}
}

package com.gshoogeveen.world;

import java.util.Random;

import com.gshoogeveen.entity.EntityFactory;
import com.gshoogeveen.gameobject.GameObject;
import com.gshoogeveen.gameobject.GameObjectFactory;

public class WorldGen
{
	private static Random random = new Random(System.currentTimeMillis());

	public static void genererateSimpleWorld(World world)
	{
		GameObject o;

		for (int i = 0; i < 1000; i++)
		{
			o = GameObjectFactory.newFlower(i%4);
			o.setPosition(rand(100), rand(100));
			world.insert(o);

			if (i % 4 == 0)
			{
				// trees
				o = GameObjectFactory.newTree();
				o.setPosition(rand(100), rand(100));
				world.insert(o);

				// animals
				o = GameObjectFactory.newPig();
				o.setPosition(rand(100), rand(100));
				world.insert(o);

				// characters
				o = EntityFactory.newCharacter();
				o.setPosition(rand(100), rand(100));
				world.insert(o);
			}
		}
	}

	private static float rand(int num)
	{
		return (float) random.nextFloat() * num - num * 0.5f + 0.5f;
	}
}

package com.gshoogeveen.collision;

import java.util.ArrayList;

import com.badlogic.gdx.math.Vector2;
import com.gshoogeveen.gameobject.GameObject;

public class CollisionDetector
{
	public static void collision(ArrayList<GameObject> objects)
	{
		GameObject a, b;
		for (int i = 0; i < objects.size(); i++)
		{
			a = objects.get(i);
			if (a.getCollision())
			{
				for (int j = i + 1; j < objects.size(); j++)
				{
					b = objects.get(j);
					if (b.getCollision())
					{
						float dist = a.getRadius() + b.getRadius();
						float diffX = a.getX() - b.getX();
						float diffY = a.getY() - b.getY();

						float movA = a.getMovable();
						float movB = b.getMovable();
						float mov = movA + movB;
						if(mov!= 0f)
						{
							movA /= mov;
							movB /= mov;
						}

						Vector2 diff = new Vector2(diffX, diffY);
						if (diff.len2() < dist * dist)
						{
							dist -= diff.len();
							float angle = diff.angleRad();
							a.changePosition((float) Math.cos(angle) * dist
									* movA, (float) Math.sin(angle) * dist
									* movA);
							b.changePosition(-(float) Math.cos(angle) * dist
									* movB, -(float) Math.sin(angle) * dist
									* movB);
						}
					}
				}
			}
		}
	}

	public static void collision(ArrayList<GameObject> entities,
			ArrayList<GameObject> objects)
	{
		GameObject a, b;
		for (int i = 0; i < entities.size(); i++)
		{
			a = entities.get(i);
			if (a.getCollision())
			{
				for (int j = 0; j < objects.size(); j++)
				{
					b = objects.get(j);
					if (b.getCollision())
					{
						float dist = a.getRadius() + b.getRadius();
						float diffX = a.getX() - b.getX();
						float diffY = a.getY() - b.getY();

						Vector2 diff = new Vector2(diffX, diffY);
						if (diff.len2() < dist * dist)
						{
							dist -= diff.len();
							float angle = diff.angleRad();
							a.changePosition((float) Math.cos(angle) * dist,
									(float) Math.sin(angle) * dist);
						}
					}
				}
			}
		}
	}
}

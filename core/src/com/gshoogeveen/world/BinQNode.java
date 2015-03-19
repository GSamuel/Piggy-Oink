package com.gshoogeveen.world;

import java.util.ArrayList;

import com.gshoogeveen.gameobject.GameObject;

public class BinQNode
{
	public static final int maxBucketSize = 1;

	private BinQNode parent;
	private BinQNode[][] child;
	private int valueX, valueY;
	private int index;
	private int minIndex;
	private ArrayList<GameObject> bucket;

	public BinQNode(int index, int minIndex, boolean negative)
	{
		if (negative)
		{
			this.valueX = ~((1 << index) - 1);
			this.valueY = ~((1 << index) - 1);
			this.index = index + 1;
		} else
		{
			this.valueX = 0;
			this.valueY = 0;
			this.index = index;
		}

		this.minIndex = minIndex;
		this.parent = null;

		// show();
	}

	private BinQNode(BinQNode parent, int index, int minÍndex, int valueX,
			int valueY)
	{
		this.parent = parent;
		this.index = index;
		this.minIndex = minÍndex;
		this.valueX = valueX;
		this.valueY = valueY;
		// show();
	}

	public boolean insert(GameObject o)
	{
		if (contains(o))
		{
			if (isLeaf())
			{
				if (bucket == null)
				{
					bucket = new ArrayList<GameObject>();
					bucket.add(o);
				} else if (bucket.size() >= maxBucketSize && index > minIndex)
				{
					this.divide();
					for (GameObject o2 : bucket)
						insertInQuads(o2);
					bucket.clear();
					bucket = null;

					insertInQuads(o);
				} else
					bucket.add(o);
			} else
				insertInQuads(o);

			return true;
		} else
			return false;
	}

	public void insertInQuads(GameObject o)
	{
		child[0][0].insert(o);
		child[0][1].insert(o);
		child[1][0].insert(o);
		child[1][1].insert(o);
	}

	public void query2D(int minX, int minY, int maxX, int maxY,
			ArrayList<GameObject> list)
	{

		if (!intersects(minX, minY, maxX, maxY))
			return;

		if (isLeaf())
		{
			if (bucket != null)
				for (GameObject o : bucket)
					if (o.getX() >= minX && o.getX() < maxX && o.getY() >= minY
							&& o.getY() < maxY)
						list.add(o);

		} else
		{
			child[0][0].query2D(minX, minY, maxX, maxY, list);
			child[0][1].query2D(minX, minY, maxX, maxY, list);
			child[1][0].query2D(minX, minY, maxX, maxY, list);
			child[1][1].query2D(minX, minY, maxX, maxY, list);
		}
	}

	public void validate(int minX, int minY, int maxX, int maxY)
	{
		if (!intersects(minX, minY, maxX, maxY))
			return;
		if (isLeaf())
		{
			if (bucket != null)
			{
				for (int i = 0; i < bucket.size(); i++)
				{
					if (!contains(bucket.get(i)))
					{
						GameObject o = bucket.remove(i--);
						if (parent != null)
							parent.reInsert(o);
					}
				}
				if (bucket.size() == 0)
				{
					bucket = null;
					if (parent != null)
						parent.merge();
				}
			}

		} else
		{
			for (int i = 0; i < 2; i++)
				for (int j = 0; j < 2; j++)
				{
					if (child != null)
						child[i][j].validate(minX, minY, maxX, maxY);
				}
		}
	}

	private void reInsert(GameObject o)
	{
		if (!insert(o))
			if (parent != null)
				parent.reInsert(o);

	}

	public void divide()
	{
		if (index <= minIndex)// can't divide
			return;
		if (isLeaf())
		{
			child = new BinQNode[2][2];
			child[0][0] = new BinQNode(this, index - 1, minIndex, valueX,
					valueY);
			child[0][1] = new BinQNode(this, index - 1, minIndex, valueX,
					(valueY + (1 << (index - 1))));
			child[1][0] = new BinQNode(this, index - 1, minIndex,
					(valueX + (1 << (index - 1))), valueY);
			child[1][1] = new BinQNode(this, index - 1, minIndex,
					(valueX + (1 << (index - 1))),
					(valueY + (1 << (index - 1))));
		}
	}

	public boolean isLeaf()
	{
		return (child == null);
	}

	public int minX()
	{
		return valueX;
	}

	public int maxX()
	{
		return (valueX + (1 << index));
	}

	public int minY()
	{
		return valueY;
	}

	public int maxY()
	{
		return (valueY + (1 << index));
	}

	public boolean contains(GameObject o)
	{
		return o.getX() >= minX() && o.getX() < maxX() && o.getY() >= minY()
				&& o.getY() < maxY();
	}

	public boolean intersects(int minX, int minY, int maxX, int maxY)
	{
		if (maxX < minX() || maxX() < minX)
			return false;
		if (maxY < minY() || maxY() < minY)
			return false;
		return true;
	}

	public int size()
	{
		if (isLeaf())
		{
			if (bucket == null)
				return 0;
			return bucket.size();
		}
		return child[0][0].size() + child[0][1].size() + child[1][0].size()
				+ child[1][1].size();
	}

	public void merge()
	{
		if (!isLeaf() && size() < maxBucketSize)
		{
			bucket = new ArrayList<GameObject>();

			for (int i = 0; i < 2; i++)
				for (int j = 0; j < 2; j++)
					if (child[i][j].bucket != null)
					{
						for (GameObject o : child[i][j].bucket)
							bucket.add(o);
						child[i][j].bucket = null;
						child[i][j] = null;
					}

			child = null;
			if (parent != null)
				parent.merge();
		}
	}

	public int getMinX()
	{
		return valueX;
	}

	public int getMaxX()
	{
		return (valueX + (1 << index));
	}

	public int getMinY()
	{
		return valueY;
	}

	public int getMaxY()
	{
		return (valueY + (1 << index));
	}

	public void show()
	{
		System.out.println(valueX + ":" + (valueX + (1 << index)) + "::"
				+ valueY + ":" + (valueY + (1 << index)));
	}

}

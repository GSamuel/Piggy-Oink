package com.gshoogeveen.client.textures;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class CustomTexRegion extends TextureRegion
{
	private int relX, relY;

	public CustomTexRegion(Texture texture, int x, int y, int width,
			int height, int relX, int relY)
	{
		super(texture, x, y, width, height);
		this.relX = relX;
		this.relY = relY;
	}

	public int getRelX()
	{
		return relX;
	}

	public int getRelY()
	{
		return relY;
	}
}

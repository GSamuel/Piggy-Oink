package com.gshoogeveen.client.textures;

import java.util.HashMap;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.utils.Disposable;

public class TextureBuilder implements Disposable
{
	public final int TILE_SIZE = 64;
	private Texture spriteSheet;
	private static HashMap<String, CustomTexRegion> textureMap = new HashMap<String, CustomTexRegion>();

	public TextureBuilder()
	{
		spriteSheet = new Texture("TileSheet-01.png");

		CustomTexRegion region;

		// Trees
		region = new CustomTexRegion(spriteSheet, 0, TILE_SIZE, TILE_SIZE * 2,
				TILE_SIZE * 2, TILE_SIZE, TILE_SIZE * 2);
		region.flip(false, true);
		textureMap.put("tree", region);

		// Animals
		region = new CustomTexRegion(spriteSheet, TILE_SIZE * 2, TILE_SIZE,
				TILE_SIZE * 2, TILE_SIZE, TILE_SIZE, TILE_SIZE);
		region.flip(false, true);
		textureMap.put("pig", region);

		// Flowers
		region = new CustomTexRegion(spriteSheet, 3 * TILE_SIZE, 0,
				(int) (0.5 * TILE_SIZE), (int) (0.5 * TILE_SIZE),
				(int) (TILE_SIZE * 0.25), 0);
		textureMap.put("flower0", region);
		region = new CustomTexRegion(spriteSheet, 3 * TILE_SIZE,
				(int) (0.5 * TILE_SIZE), (int) (0.5 * TILE_SIZE),
				(int) (0.5 * TILE_SIZE), (int) (TILE_SIZE * 0.25), 0);
		textureMap.put("flower1", region);
		region = new CustomTexRegion(spriteSheet, (int) (3.5 * TILE_SIZE), 0,
				(int) (0.5 * TILE_SIZE), (int) (0.5 * TILE_SIZE),
				(int) (TILE_SIZE * 0.25), 0);
		textureMap.put("flower2", region);
		region = new CustomTexRegion(spriteSheet, (int) (3.5 * TILE_SIZE),
				(int) (0.5 * TILE_SIZE), (int) (0.5 * TILE_SIZE),
				(int) (0.5 * TILE_SIZE), (int) (TILE_SIZE * 0.25), 0);
		textureMap.put("flower3", region);

		// Character

		region = new CustomTexRegion(spriteSheet, 0, 0, TILE_SIZE, TILE_SIZE,
				(int) (TILE_SIZE * 0.5), TILE_SIZE);
		region.flip(false, true);
		textureMap.put("char_down", region);

		region = new CustomTexRegion(spriteSheet, TILE_SIZE, 0, TILE_SIZE,
				TILE_SIZE, (int) (TILE_SIZE * 0.5), TILE_SIZE);
		region.flip(false, true);
		textureMap.put("char_up", region);

		region = new CustomTexRegion(spriteSheet, 2 * TILE_SIZE, 0, TILE_SIZE,
				TILE_SIZE, (int) (TILE_SIZE * 0.5), TILE_SIZE);
		region.flip(false, true);
		textureMap.put("char_left", region);

		region = new CustomTexRegion(spriteSheet, 2 * TILE_SIZE, 0, TILE_SIZE,
				TILE_SIZE, (int) (TILE_SIZE * 0.5), TILE_SIZE);
		region.flip(true, true);
		textureMap.put("char_right", region);
	}

	public static CustomTexRegion getTextureByName(String name)
	{
		return textureMap.get(name);
	}

	@Override
	public void dispose()
	{
		spriteSheet.dispose();
	}
}

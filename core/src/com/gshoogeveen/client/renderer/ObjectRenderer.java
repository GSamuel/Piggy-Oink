package com.gshoogeveen.client.renderer;

import java.util.ArrayList;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.gshoogeveen.client.textures.CustomTexRegion;
import com.gshoogeveen.client.textures.TextureBuilder;
import com.gshoogeveen.entity.Entity;
import com.gshoogeveen.gameobject.GameObject;

public class ObjectRenderer
{
	public static final int TILE_SIZE = 64;

	public static void render(SpriteBatch batch, OrthographicCamera camera,
			ArrayList<GameObject> objects)
	{
		float angle = (float) (Math.atan2(camera.up.x, camera.up.y) * MathUtils.radiansToDegrees);

		for (GameObject o : objects)
		{
			CustomTexRegion region;
			if (o instanceof Entity)
			{
				Entity e = (Entity) o;

				Vector2 newAngle = new Vector2(e.getVelX(),e.getVelY());
				newAngle.rotate(angle);

				float absVX = Math.abs(newAngle.x), absVY = Math
						.abs(newAngle.y);

				String texName;
				if (newAngle.y > 0f && absVY >= absVX)
					texName = e.getUp();
				else if (newAngle.y < 0f && absVY >= absVX)
					texName = e.getDown();
				else if (newAngle.x < 0f && absVX > absVY)
					texName = e.getRight();
				else if (newAngle.x > 0f && absVX > absVY)
					texName = e.getLeft();
				else
					texName = e.getDown();

				region = TextureBuilder.getTextureByName(texName);
			} else
				region = TextureBuilder.getTextureByName(o.getTextureName());
			
			
			batch.draw(region, o.getX() * TILE_SIZE - region.getRelX(),
					o.getY() * TILE_SIZE - region.getRelY(), region.getRelX(),
					region.getRelY(), region.getRegionWidth(),
					region.getRegionHeight(), 1, 1, -angle + 180);
		}
	}
}

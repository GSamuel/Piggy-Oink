package com.gshoogeveen.client.renderer;

import java.util.ArrayList;
import java.util.Collections;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.gshoogeveen.gameobject.GameObject;

public class CameraSorting
{
	
	public static void sort(OrthographicCamera camera, ArrayList<GameObject> objects)
	{
		float angle = (float) (Math.atan2(camera.up.x, camera.up.y));
		
		ArrayList<CameraWrapper> wrappers = new ArrayList<CameraWrapper>();
		
		for(GameObject o : objects)
			wrappers.add(new CameraWrapper(o));
		
		for(CameraWrapper w: wrappers)
			w.updateValue(angle);
		
		Collections.sort(wrappers);
		
		objects.clear();
		
		for(CameraWrapper w: wrappers)
			objects.add(w.getObject());
	}
}

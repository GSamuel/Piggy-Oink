package com.mygdx.game.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.gshoogeveen.client.PiggyOinkClient;

public class ClientDesktopLauncher
{
	public static void main(String[] arg)
	{
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();

		config.title = "Piggy Oink";
		config.useGL30 = false;
		config.width = 960;
		config.height = 640;
		
		new LwjglApplication(new PiggyOinkClient(), config);
	}
}
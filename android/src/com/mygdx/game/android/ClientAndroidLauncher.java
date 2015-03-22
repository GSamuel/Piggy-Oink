package com.mygdx.game.android;

import android.os.Bundle;

import com.badlogic.gdx.backends.android.AndroidApplication;
import com.badlogic.gdx.backends.android.AndroidApplicationConfiguration;
import com.gshoogeveen.client.PiggyOinkClient;
import com.gshoogeveen.logging.LogManagerAndroid;
import com.gshoogeveen.logging.LogManagerCore;

public class ClientAndroidLauncher extends AndroidApplication {
	@Override
	protected void onCreate (Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		LogManagerCore.logManager = new LogManagerAndroid();
		
		AndroidApplicationConfiguration config = new AndroidApplicationConfiguration();
		config.useAccelerometer = false;
		config.useCompass = false;
		initialize(new PiggyOinkClient(), config);
	}
}

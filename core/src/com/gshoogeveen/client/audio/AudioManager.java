package com.gshoogeveen.client.audio;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.utils.Disposable;

public class AudioManager implements Disposable
{
	private Music music;

	public AudioManager()
	{
		music = Gdx.audio.newMusic(Gdx.files.internal("Happy Times.mp3"));
		music.setVolume(0.2f); // sets the volume to half the maximum volume
		music.setLooping(true); // will repeat playback until music.stop() is
								// called
	}

	public void play()
	{
		music.play();
	}
	
	public void pause()
	{
		music.pause();
	}
	
	public void stop()
	{
		music.stop();
	}

	@Override
	public void dispose()
	{
		music.dispose();
	}
}

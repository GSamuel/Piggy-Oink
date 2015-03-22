package com.gshoogeveen.logging;


import android.util.Log;

public class LoggerAndroid implements Logger
{

	private String tag;
	
	public LoggerAndroid(String tag)
	{
		this.tag = tag;
	}
	
	@Override
	public void error(Object message)
	{
		Log.e(tag, message.toString());
	}

	@Override
	public void debug(Object message)
	{
		Log.d(tag, message.toString());
	}

	@Override
	public void fatal(Object message)
	{
		Log.v(tag, message.toString());
	}

	@Override
	public void warn(Object message)
	{
		Log.w(tag, message.toString());
	}

	@Override
	public void trace(Object message)
	{
		Log.i(tag, message.toString());
	}

	@Override
	public void setLevel(Level level)
	{
		//nothing here yet			
	}

}

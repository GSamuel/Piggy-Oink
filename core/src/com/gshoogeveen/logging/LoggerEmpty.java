package com.gshoogeveen.logging;


public class LoggerEmpty implements Logger
{

	@Override
	public void error(Object message)
	{
	}

	@Override
	public void debug(Object message)
	{
	}

	@Override
	public void fatal(Object message)
	{
	}

	@Override
	public void warn(Object message)
	{
	}

	@Override
	public void trace(Object message)
	{
	}

	@Override
	public void setLevel(Level level)
	{
	}

}

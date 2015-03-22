package com.gshoogeveen.logging;

public class LogManagerEmpty implements LogManager
{

	@Override
	public Logger getLogger(Class<?> class1)
	{
		return new LoggerEmpty();
	}

	@Override
	public Logger getLogger(String name)
	{
		return new LoggerEmpty();
	}

}

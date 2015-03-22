package com.gshoogeveen.logging;

public class LogManagerAndroid implements LogManager
{
	@Override
	public Logger getLogger(Class<?> class1)
	{
		return new LoggerAndroid();
	}

	@Override
	public Logger getLogger(String name)
	{
		return new LoggerAndroid();
	}

}

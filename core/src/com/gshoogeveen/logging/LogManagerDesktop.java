package com.gshoogeveen.logging;

public class LogManagerDesktop implements LogManager
{
	@Override
	public Logger getLogger(Class<?> class1)
	{
		return new LoggerDesktop(org.apache.log4j.LogManager.getLogger(class1));
	}

	@Override
	public Logger getLogger(String name)
	{
		return new LoggerDesktop(org.apache.log4j.LogManager.getLogger(name));
	}

}

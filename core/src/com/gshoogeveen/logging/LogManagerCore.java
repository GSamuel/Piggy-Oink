package com.gshoogeveen.logging;



public class LogManagerCore
{
	public static LogManager logManager = new LogManagerEmpty();// initialize with no logging

	public static Logger getLogger(Class<?> class1)
	{
		return logManager.getLogger(class1);
	}

	public static Logger getLogger(String name)
	{
		return logManager.getLogger(name);
	}
}

package com.gshoogeveen.logging;

public class LogManagerCore
{
	private static LogManager logManager = new LogManagerAndroid();// initialize with no logging
	
	public static void setLogManager(LogManager logManager)
	{
		LogManagerCore.logManager = logManager;
		System.out.println(logManager.getClass());
	}

	public static Logger getLogger(Class<?> class1)
	{
		return logManager.getLogger(class1);
	}

	public static Logger getLogger(String name)
	{
		return logManager.getLogger(name);
	}
}

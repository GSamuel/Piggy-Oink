package com.gshoogeveen.logging;




public class LogManagerCore
{
	public static LogManager logManager = new LogManagerEmpty();// initialize with no logging
	public static Level level = Level.DEBUG;

	public static Logger getLogger(Class<?> class1)
	{
		Logger log = logManager.getLogger(class1);
		log.setLevel(level);
		return log;
	}

	public static Logger getLogger(String name)
	{
		Logger log = logManager.getLogger(name);
		log.setLevel(level);
		return log;
	}
}

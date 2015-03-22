package com.gshoogeveen.logging;


public class LoggerDesktop implements Logger
{
	private org.apache.log4j.Logger logger;
	public LoggerDesktop(org.apache.log4j.Logger logger)
	{
		this.logger = logger;
	}
	@Override
	public void error(Object message)
	{
		logger.error(message);
	}
	@Override
	public void debug(Object message)
	{
		logger.debug(message);
	}
	@Override
	public void fatal(Object message)
	{
		logger.fatal(message);
	}
	@Override
	public void warn(Object message)
	{
		logger.warn(message);
	}
	@Override
	public void trace(Object message)
	{
		logger.trace(message);
	}
	@Override
	public void setLevel(Level level)
	{
		switch(level)
		{
		case DEBUG:
			logger.setLevel(org.apache.log4j.Level.DEBUG);
			break;
		case ERROR:
			logger.setLevel(org.apache.log4j.Level.ERROR);
			break;
		case FATAL:
			logger.setLevel(org.apache.log4j.Level.FATAL);
			break;
		case INFO:
			logger.setLevel(org.apache.log4j.Level.INFO);
			break;
		case TRACE:
			logger.setLevel(org.apache.log4j.Level.TRACE);
			break;
		case OFF:
			logger.setLevel(org.apache.log4j.Level.OFF);
			break;
		case ALL:
			logger.setLevel(org.apache.log4j.Level.ALL);
			break;
		case WARN:
			logger.setLevel(org.apache.log4j.Level.WARN);
			break;
			
		}
	}

}

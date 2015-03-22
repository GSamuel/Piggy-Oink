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

}

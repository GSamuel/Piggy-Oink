package com.gshoogeveen.logging;


public interface Logger
{

	public void error(Object message);
	public void debug(Object message);
	public void fatal(Object message);
	public void warn(Object message);
	public void trace(Object message);
	public void setLevel(Level level);

}

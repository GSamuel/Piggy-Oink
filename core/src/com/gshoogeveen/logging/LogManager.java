package com.gshoogeveen.logging;


public interface LogManager
{
	public Logger getLogger(Class<?> class1);

	public Logger getLogger(String name);	
}

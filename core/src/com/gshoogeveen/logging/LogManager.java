package com.gshoogeveen.logging;

public interface LogManager
{
	Logger getLogger(Class<?> class1);

	Logger getLogger(String name);
}

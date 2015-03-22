package com.gshoogeveen.server.dedicated;

import java.util.Properties;

public class DefaultServerProperties extends Properties
{

	/**
	 * 
	 */
	private static final long serialVersionUID = -4841494409950944974L;
	
	public DefaultServerProperties()
	{
		this.put("server-name", "Master server");
		this.put("server-port", "12050");
		this.put("motd", "Welcome to this awesome server");
	}

}

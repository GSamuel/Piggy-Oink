package com.gshoogeveen.server.dedicated;

import java.util.Properties;

public class DefaultClientProperties extends Properties
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 3944291648757821679L;

	
	public DefaultClientProperties()
	{
		this.put("client-name", "Henkie"+Math.random());
		this.put("server-ip", "84.85.165.9");
		this.put("server-port", "12050");
	}
}

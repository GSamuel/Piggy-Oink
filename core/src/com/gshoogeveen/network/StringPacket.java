package com.gshoogeveen.network;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class StringPacket extends Packet
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 4904073960540141127L;
	
	private String name;
	
	public void setName(String name)
	{
		this.name = name;
	}

	@Override
	protected void readObject(ObjectInputStream ois)
			throws ClassNotFoundException,IOException
	{
		this.name = (String) ois.readObject();
	}

	@Override
	protected void writeObject(ObjectOutputStream oos) throws IOException
	{
		oos.writeObject(name);
	}

	@Override
	public void processPacket(INetHandler handler)
	{
		
	}


}

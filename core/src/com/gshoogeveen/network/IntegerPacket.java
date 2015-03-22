package com.gshoogeveen.network;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class IntegerPacket extends Packet
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1950532029391439557L;
	private int value;
	
	public void setValue(int value)
	{
		this.value = value;
	}

	@Override
	protected void readObject(ObjectInputStream ois)
			throws ClassNotFoundException, IOException
	{
		value = ois.readInt();
	}

	@Override
	protected void writeObject(ObjectOutputStream oos) throws IOException
	{
		oos.writeInt(value);
	}
	
	@Override
	public void processPacket(INetHandler handler)
	{
		
	}

}

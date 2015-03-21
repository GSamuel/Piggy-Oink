package com.gshoogeveen.network.play.client;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import com.gshoogeveen.network.INetHandler;
import com.gshoogeveen.network.Packet;
import com.gshoogeveen.network.play.INetHandlerPlayServer;

public class C00PacketKeepAlive extends Packet
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 9204391295606528821L;

	@Override
	protected void readObject(ObjectInputStream ois)
			throws ClassNotFoundException, IOException
	{
		
	}

	@Override
	protected void writeObject(ObjectOutputStream oos) throws IOException
	{
		
	}
	
	private void processPacket(INetHandlerPlayServer handler)
	{
		handler.
	}
	
	@Override
	public void processPacket(INetHandler handler)
	{
		processPacket((INetHandlerPlayServer) handler);
	}

	@Override
	public void show()
	{
		System.out.println("Keep Client Alive!");
	}

}

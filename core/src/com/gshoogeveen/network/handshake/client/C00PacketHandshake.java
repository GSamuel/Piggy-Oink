package com.gshoogeveen.network.handshake.client;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import com.gshoogeveen.network.INetHandler;
import com.gshoogeveen.network.Packet;
import com.gshoogeveen.network.handshake.INetHandlerHandshakeServer;

public class C00PacketHandshake extends Packet
{

	/**
	 * 
	 */
	private static final long serialVersionUID = -4574823889902447713L;

	//Stuff like client version and what the client wants: login, server status.
	
	@Override
	protected void readObject(ObjectInputStream ois)
			throws ClassNotFoundException, IOException
	{
	}

	@Override
	protected void writeObject(ObjectOutputStream oos) throws IOException
	{
	}
	
	private void processPacket(INetHandlerHandshakeServer handler)
	{
		handler.processHandshake(this);
	}

	@Override
	public void processPacket(INetHandler handler)
	{	
		this.processPacket((INetHandlerHandshakeServer)handler);
	}
}

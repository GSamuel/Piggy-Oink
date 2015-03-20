package com.gshoogeveen.server.network;

import com.gshoogeveen.network.handshake.INetHandlerHandshakeServer;
import com.gshoogeveen.network.handshake.client.C00PacketHandshake;

public class NetHandlerHandshakeTCP implements INetHandlerHandshakeServer
{

	@Override
	public void onDisconnect()
	{

	}

	@Override
	public void processHandshake(C00PacketHandshake handshake)
	{
		
	}

}

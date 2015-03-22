package com.gshoogeveen.server.network;

import com.gshoogeveen.network.NetworkManager;
import com.gshoogeveen.network.handshake.INetHandlerHandshakeServer;
import com.gshoogeveen.network.handshake.client.C00PacketHandshake;

public class NetHandlerHandshakeTCP implements INetHandlerHandshakeServer
{

	private NetworkManager networkManager;

	public NetHandlerHandshakeTCP(NetworkManager networkManager)
	{
		this.networkManager = networkManager;
	}

	@Override
	public void onDisconnect()
	{

	}

	@Override
	public void processHandshake(C00PacketHandshake handshake)
	{
		//System.out.println("nice warm hands <3");
	}

}

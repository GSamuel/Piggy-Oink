package com.gshoogeveen.server.network;

import com.gshoogeveen.entity.player.EntityPlayerMP;
import com.gshoogeveen.network.play.INetHandlerPlayServer;
import com.gshoogeveen.network.play.client.C00PacketKeepAlive;

public class NetHandlerPlayServer implements INetHandlerPlayServer
{
	private EntityPlayerMP playerEntity;

	@Override
	public void onDisconnect()
	{

	}

	@Override
	public void processKeepAlive(C00PacketKeepAlive keepAlive)
	{
		System.out.println("still alive");
	}

}

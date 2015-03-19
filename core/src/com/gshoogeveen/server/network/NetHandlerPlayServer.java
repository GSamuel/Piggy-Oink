package com.gshoogeveen.server.network;

import com.gshoogeveen.entity.player.EntityPlayerMP;
import com.gshoogeveen.network.play.INetHandlerPlayServer;

public class NetHandlerPlayServer implements INetHandlerPlayServer
{
	private EntityPlayerMP playerEntity;

	@Override
	public void onDisconnect()
	{

	}

}

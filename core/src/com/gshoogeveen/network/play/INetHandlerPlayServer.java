package com.gshoogeveen.network.play;

import com.gshoogeveen.network.INetHandler;
import com.gshoogeveen.network.play.client.C00PacketKeepAlive;

public interface INetHandlerPlayServer extends INetHandler
{
	public void processKeepAlive(C00PacketKeepAlive keepAlive);
}

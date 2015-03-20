package com.gshoogeveen.network.handshake;

import com.gshoogeveen.network.INetHandler;
import com.gshoogeveen.network.handshake.client.C00PacketHandshake;

public interface INetHandlerHandshakeServer extends INetHandler
{
	public void processHandshake(C00PacketHandshake handshake);
}

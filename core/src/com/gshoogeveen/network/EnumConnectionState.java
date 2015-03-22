package com.gshoogeveen.network;

import java.util.HashMap;

import com.gshoogeveen.logging.LogManagerCore;
import com.gshoogeveen.logging.Logger;
import com.gshoogeveen.network.handshake.INetHandlerHandshakeServer;
import com.gshoogeveen.network.handshake.client.C00PacketHandshake;
import com.gshoogeveen.network.play.INetHandlerPlayServer;
import com.gshoogeveen.network.play.client.C00PacketKeepAlive;


public enum EnumConnectionState
{
	
	HANDSHAKING()
	{
		{
			this.linkPacketWithHandler(C00PacketHandshake.class, INetHandlerHandshakeServer.class);
		}
		
	}
	, PLAY()
	{
		{
			this.linkPacketWithHandler(C00PacketKeepAlive.class, INetHandlerPlayServer.class);
		}
	}, STATUS(), LOGIN();
	
	//private static final HashMap<Class<? extends Packet>, Class<? extends INetHandler>> map = new HashMap<Class<? extends Packet>, Class<? extends INetHandler>>();
	private final HashMap<Class<? extends Packet>, Class<? extends INetHandler>> map;

	private static final Logger logger = LogManagerCore.getLogger(EnumConnectionState.class);
	
	private EnumConnectionState()
	{
		map = new HashMap<Class<? extends Packet>, Class<? extends INetHandler>>();
	}
	
	public boolean validPacketWithHandler(Packet p, INetHandler handler)
	{		
		Class<? extends INetHandler> c = map.get(p.getClass());
		if(c == null)
			return false;

		return map.get(p.getClass()).isAssignableFrom(handler.getClass());
	}
	
	protected void linkPacketWithHandler(Class<? extends Packet> packetClass, Class<? extends INetHandler> handlerClass)
	{
		if(map.get(packetClass) == null)
		{
			map.put(packetClass, handlerClass);
		}
		else
			logger.error("packet already added: "+packetClass+" : "+handlerClass);
		
	}
	
}

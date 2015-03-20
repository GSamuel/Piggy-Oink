package com.gshoogeveen.network;

import com.gshoogeveen.server.network.NetHandlerHandshakeTCP;

public class NetWorkManager
{
	private static EnumConnectionState connectionState;
	private INetHandler netHandler;
	private PacketManager packetManager;
	
	public NetWorkManager(PacketManager packetManager)
	{
		connectionState = EnumConnectionState.HANDSHAKING;
		netHandler = new NetHandlerHandshakeTCP();
		
		this.packetManager = packetManager;
	}
	
	public void setNetHandler(INetHandler netHandler)
	{
		this.netHandler = netHandler;
	}
	
	public void processPackets()
	{
		while(packetManager.packetAvaible())
			packetManager.receivePacket().processPacket(netHandler);
	}
	
	public void sendPacket(Packet p)
	{
		packetManager.sendPacket(p);
	}
}

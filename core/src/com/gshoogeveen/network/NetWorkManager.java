package com.gshoogeveen.network;

import com.badlogic.gdx.utils.Disposable;
import com.gshoogeveen.server.network.NetHandlerHandshakeTCP;

public class NetworkManager implements Disposable
{
	private EnumConnectionState connectionState;
	private INetHandler netHandler;
	private PacketManager packetManager;

	public NetworkManager(PacketManager packetManager)
	{
		connectionState = EnumConnectionState.HANDSHAKING;
		netHandler = new NetHandlerHandshakeTCP(this);
		this.packetManager = packetManager;
	}

	public void setConnectionState(EnumConnectionState state)
	{
		this.connectionState = state;
	}

	public EnumConnectionState getConnectionState()
	{
		return connectionState;
	}

	public void setNetHandler(INetHandler netHandler)
	{
		this.netHandler = netHandler;
	}

	public void processPackets()
	{
		Packet packet;
		while (packetManager.packetAvaible())
		{
			packet = packetManager.receivePacket();
			if (connectionState.validPacketWithHandler(packet, netHandler))
				packet.processPacket(netHandler);
		}
	}

	public void sendPacket(Packet p)
	{
		packetManager.sendPacket(p);
	}

	public boolean streamsOpen()
	{
		return packetManager.streamsOpen();
	}

	public String getSocketAdress()
	{
		return packetManager.getRemoteAdress();

	}

	@Override
	public void dispose()
	{
		packetManager.dispose();
	}
}

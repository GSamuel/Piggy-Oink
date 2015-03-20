package com.gshoogeveen.entity.player;

import com.gshoogeveen.network.PacketManager;

public class EntityPlayerMP extends EntityPlayer
{
	PacketManager packetManager;
	
	public EntityPlayerMP(PacketManager packetManager)
	{
		this.packetManager = packetManager;
	}

}

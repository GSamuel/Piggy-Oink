package com.gshoogeveen.world;

import com.gshoogeveen.entity.player.EntityPlayerMP;

public class WorldClient extends World
{
	private EntityPlayerMP player;
	
	public WorldClient()
	{
		
	}
	
	public void setPlayer(EntityPlayerMP player)
	{
		this.player = player;
	}
}

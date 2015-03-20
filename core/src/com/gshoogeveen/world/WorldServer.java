package com.gshoogeveen.world;

import java.util.ArrayList;

import com.gshoogeveen.entity.player.EntityPlayerMP;

public class WorldServer extends World
{
	private ArrayList<EntityPlayerMP> players;
	
	public WorldServer()
	{
		players = new ArrayList<EntityPlayerMP>();
	}
	
	public void addPlayer(EntityPlayerMP player)
	{
		players.add(player);
	}
	
	
}

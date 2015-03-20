package com.gshoogeveen.server;

import java.util.ArrayList;

import com.badlogic.gdx.ApplicationAdapter;
import com.gshoogeveen.network.PacketManager;
import com.gshoogeveen.world.WorldGen;
import com.gshoogeveen.world.WorldServer;

public class PiggyOinkServer extends ApplicationAdapter
{
	// Objects / Model
	private WorldServer world;
	
	//Connection
	private PiggyServer server;
	private ArrayList<PacketManager> packetManagers;

	@Override
	public void create()
	{
		packetManagers = new ArrayList<PacketManager>();
		
		// Model
		world = new WorldServer();
		WorldGen.genererateSimpleWorld(world);
		/*
		player = (Entity) EntityFactory.newCharacter();
		world.setPlayer(player);*/
		
		//Server Connection
		server = new PiggyServer();
		server.startServer();
	}

	@Override
	public void render()
	{
		super.render();
		while(server.hasPacketManager())
		{
			PacketManager packetManager = server.getPacketManager();
			packetManager.start();
			packetManagers.add(packetManager);
		}
		
		for(PacketManager pm: packetManagers)
			while(pm.packetAvaible())
				pm.receivePacket().show();
		
		//world.update();//model
	}


}

package com.gshoogeveen.server;

import java.util.ArrayList;

import com.badlogic.gdx.Game;
import com.gshoogeveen.network.NetworkManager;
import com.gshoogeveen.server.dedicated.DefaultServerProperties;
import com.gshoogeveen.server.dedicated.PropertyManager;
import com.gshoogeveen.world.WorldGen;
import com.gshoogeveen.world.WorldServer;

public class PiggyOinkServer extends Game
{
	// Objects / Model
	private PropertyManager serverProperties;
	private WorldServer world;
	
	//Connection
	private PiggyServer server;
	private ArrayList<NetworkManager> networkManagers;

	@Override
	public void create()
	{
		serverProperties = new PropertyManager("server.properties", new DefaultServerProperties());
		serverProperties.readPropertyFile();
		serverProperties.writePropertyFile();
		
		networkManagers = new ArrayList<NetworkManager>();
		
		// Model
		world = new WorldServer();
		WorldGen.genererateSimpleWorld(world);
		/*
		player = (Entity) EntityFactory.newCharacter();
		world.setPlayer(player);*/
		
		//Server Connection
		server = new PiggyServer(serverProperties);
		server.startServer();
	}

	@Override
	public void render()
	{
		super.render();
		while(server.hasNetworkManager())
		{
			NetworkManager networkManager = server.getNetworkManager();
			networkManagers.add(networkManager);
		}
		
		for(NetworkManager n: networkManagers)
			n.processPackets();
		
		//world.update();//model
	}


}

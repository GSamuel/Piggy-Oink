package com.gshoogeveen.server;

import com.badlogic.gdx.ApplicationAdapter;
import com.gshoogeveen.entity.Entity;
import com.gshoogeveen.world.World;
import com.gshoogeveen.world.WorldGen;

public class PiggyOinkServer extends ApplicationAdapter
{
	// Objects / Model
	private World world;
	private Entity player;
	
	//Connection
	private PiggyServer server;

	@Override
	public void create()
	{
		// Model
		world = new World();
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
		//world.update();//model
	}


}

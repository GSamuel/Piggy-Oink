package com.gshoogeveen.client;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.gshoogeveen.client.audio.AudioManager;
import com.gshoogeveen.client.input.FirstInputProcessor;
import com.gshoogeveen.client.renderer.WorldRenderer;
import com.gshoogeveen.client.textures.TextureBuilder;
import com.gshoogeveen.entity.Entity;
import com.gshoogeveen.network.NetWorkManager;
import com.gshoogeveen.network.PacketManager;
import com.gshoogeveen.network.StringPacket;
import com.gshoogeveen.network.play.client.C00PacketKeepAlive;
import com.gshoogeveen.world.WorldClient;

public class PiggyOinkClient extends ApplicationAdapter
{

	// Resources
	private AudioManager audio;
	private TextureBuilder texBuilderV2;

	// View Stuff
	private Stage stage;
	private WorldRenderer worldRenderer;

	// Controller stuff
	private FirstInputProcessor iProcessor;

	// Objects / Model
	private WorldClient world;
	private Entity player;

	// Connection
	private PacketManager packetManager;
	private PiggyClient client;
	private NetWorkManager networkManager;

	private double rand = Math.random();

	@Override
	public void create()
	{
		// Assets
		// audio = new AudioManager();
		// audio.play();
		// texBuilderV2 = new TextureBuilder();

		// Model
		// world = new WorldClient();
		// WorldGen.genererateSimpleWorld(world);
		// player = (Entity) EntityFactory.newCharacter();
		// world.setPlayer(player);

		// View
		// stage = new Stage();
		// worldRenderer = new WorldRenderer(stage, world);

		// Input
		// iProcessor = new FirstInputProcessor(stage, player);
		// InputMultiplexer im = new InputMultiplexer();
		// im.addProcessor(new GestureDetector(iProcessor));
		// im.addProcessor(iProcessor);
		// Gdx.input.setInputProcessor(im);

		// Server Connection
		client = new PiggyClient();
		client.connect();
	}

	@Override
	public void render()
	{
		super.render();
		// iProcessor.update();//input
		// world.update();//model
		if (client.connected())
			worldRenderer.render();// view

		if (packetManager == null && client.hasPacketManager())
		{
			packetManager = client.getPacketManager();
			packetManager.start();
		}

		if (packetManager != null)
		{
			StringPacket p = new StringPacket();
			p.setName("" + rand);
			packetManager.sendPacket(p);
			C00PacketKeepAlive p2 = new C00PacketKeepAlive();
			packetManager.sendPacket(p2);
		}
	}

	@Override
	public void dispose()
	{
		audio.dispose();
		texBuilderV2.dispose();
		worldRenderer.dispose();
		stage.dispose();
	}

}

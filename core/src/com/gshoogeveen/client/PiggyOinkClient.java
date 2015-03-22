package com.gshoogeveen.client;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.gshoogeveen.client.audio.AudioManager;
import com.gshoogeveen.client.input.FirstInputProcessor;
import com.gshoogeveen.client.renderer.WorldRenderer;
import com.gshoogeveen.client.textures.TextureBuilder;
import com.gshoogeveen.network.NetworkManager;
import com.gshoogeveen.network.handshake.client.C00PacketHandshake;
import com.gshoogeveen.network.play.client.C00PacketKeepAlive;
import com.gshoogeveen.server.dedicated.DefaultClientProperties;
import com.gshoogeveen.server.dedicated.PropertyManager;
import com.gshoogeveen.world.WorldClient;

public class PiggyOinkClient extends Game
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
	private PropertyManager clientProperties;
	private WorldClient world;

	// Connection
	private NetworkManager networkManager;
	private PiggyClient client;

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
		clientProperties = new PropertyManager("client.properties", new DefaultClientProperties());
		clientProperties.readPropertyFile();
		clientProperties.writePropertyFile();
		
		client = new PiggyClient(clientProperties);
		client.connect();
	}

	@Override
	public void render()
	{
		super.render();
		// iProcessor.update();//input
		// world.update();//model
		//if (client.connected())
			//worldRenderer.render();// view

		if (networkManager == null && client.hasNetworkManager())
			networkManager = client.getNetworkManager();

		if (networkManager != null)
		{
			C00PacketHandshake p= new C00PacketHandshake();
			networkManager.sendPacket(p);
			C00PacketKeepAlive p2 = new C00PacketKeepAlive();
			networkManager.sendPacket(p2);
		}
	}

	@Override
	public void dispose()
	{
		//audio.dispose();
		//texBuilderV2.dispose();
		//worldRenderer.dispose();
		//stage.dispose();
	}

}

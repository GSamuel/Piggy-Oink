package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.input.GestureDetector;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.gshoogeveen.client.audio.AudioManager;
import com.gshoogeveen.client.input.FirstInputProcessor;
import com.gshoogeveen.client.renderer.WorldRenderer;
import com.gshoogeveen.client.textures.TextureBuilder;
import com.gshoogeveen.entity.Entity;
import com.gshoogeveen.entity.EntityFactory;
import com.gshoogeveen.world.World;
import com.gshoogeveen.world.WorldGen;

public class FiveMinMMO extends ApplicationAdapter
{
	// TODO nog te bouwen

	// Create UI
	// main menu
	// create new World
	// load world
	// pause screen
	// return to main menu

	// Saving world to disk
	// World loading (partially)

	// animation
	// World generation
	// networking

	// TODO Nog te fixen
	// rotatie punt bloemen.

	// Resources
	private AudioManager audio;
	private TextureBuilder texBuilderV2;

	// View Stuff
	private Stage stage;
	private WorldRenderer worldRenderer;

	// Controller stuff
	private FirstInputProcessor iProcessor;

	// Objects / Model
	private World world;
	private Entity player;

	@Override
	public void create()
	{
		//Assets
		audio = new AudioManager();
		audio.play();
		texBuilderV2 = new TextureBuilder();
		
		//Model
		world = new World();
		WorldGen.genererateSimpleWorld(world);
		player = (Entity) EntityFactory.newCharacter();
		world.setPlayer(player);

		
		//View
		stage = new Stage();
		worldRenderer = new WorldRenderer(stage, world);

		
		//Input
		iProcessor = new FirstInputProcessor(stage, player);
		InputMultiplexer im = new InputMultiplexer();
		im.addProcessor(new GestureDetector(iProcessor));
		im.addProcessor(iProcessor);
		Gdx.input.setInputProcessor(im);
	}

	@Override
	public void render()
	{
		super.render();
		iProcessor.update();//input
		world.update();//model
		worldRenderer.render();//view
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

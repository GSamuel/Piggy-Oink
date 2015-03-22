package com.gshoogeveen.server;

import java.util.concurrent.ConcurrentLinkedQueue;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Net.Protocol;
import com.badlogic.gdx.net.ServerSocket;
import com.badlogic.gdx.net.ServerSocketHints;
import com.badlogic.gdx.net.Socket;
import com.gshoogeveen.logging.LogManagerCore;
import com.gshoogeveen.logging.Logger;
import com.gshoogeveen.network.NetworkManager;
import com.gshoogeveen.network.PacketManager;

public class PiggyServer implements Runnable
{

	private ServerSocket serverSocket;
	private boolean running;
	
	private ConcurrentLinkedQueue<NetworkManager> networkManagers;

	private static final Logger logger = LogManagerCore.getLogger(PiggyServer.class);

	public PiggyServer()
	{
		networkManagers = new ConcurrentLinkedQueue<NetworkManager>();
		
		ServerSocketHints serverSocketHint = new ServerSocketHints();
		serverSocketHint.acceptTimeout = 0;

		serverSocket = Gdx.net.newServerSocket(Protocol.TCP, 12050,
				serverSocketHint);
	}

	public void startServer()
	{
		if (!running)
		{
			running = true;
			new Thread(this).start();
		}
	}
	
	public boolean hasNetworkManager()
	{
		return networkManagers.size()>0;
	}
	
	public NetworkManager getNetworkManager()
	{
		return networkManagers.remove();
	}

	@Override
	public void run()
	{
		// Loop forever
		Socket socket;
		while (running)
		{
			// Create a socket
			socket = serverSocket.accept(null);
			networkManagers.add(	new NetworkManager(new PacketManager(socket)));
		}
	}

}

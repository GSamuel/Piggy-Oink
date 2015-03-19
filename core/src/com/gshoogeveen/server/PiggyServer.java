package com.gshoogeveen.server;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.concurrent.ConcurrentLinkedQueue;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Net.Protocol;
import com.badlogic.gdx.net.ServerSocket;
import com.badlogic.gdx.net.ServerSocketHints;
import com.badlogic.gdx.net.Socket;
import com.gshoogeveen.network.PacketManager;

public class PiggyServer implements Runnable
{

	private ServerSocket serverSocket;
	private ObjectOutputStream output;
	private ObjectInputStream input;
	private boolean running;
	
	private ConcurrentLinkedQueue<PacketManager> packetManagers;

	public PiggyServer()
	{
		packetManagers = new ConcurrentLinkedQueue<PacketManager>();
		
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
	
	public boolean hasPacketManager()
	{
		return packetManagers.size()>0;
	}
	
	public PacketManager getPacketManager()
	{
		return packetManagers.remove();
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
			System.out.println(socket.getRemoteAddress());
			try
			{
				output = new ObjectOutputStream(socket.getOutputStream());
				input = new ObjectInputStream(socket.getInputStream());
				packetManagers.add(	new PacketManager(input, output));
			} catch (IOException e)
			{
				e.printStackTrace();
			}
		}
	}

}

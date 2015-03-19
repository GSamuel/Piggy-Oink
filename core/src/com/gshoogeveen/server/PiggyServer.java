package com.gshoogeveen.server;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Net.Protocol;
import com.badlogic.gdx.net.ServerSocket;
import com.badlogic.gdx.net.ServerSocketHints;
import com.badlogic.gdx.net.Socket;

public class PiggyServer implements Runnable
{

	private ServerSocket serverSocket;
	private ObjectOutputStream output;
	private ObjectInputStream input;

	public PiggyServer()
	{
		ServerSocketHints serverSocketHint = new ServerSocketHints();
		serverSocketHint.acceptTimeout = 0;

		serverSocket = Gdx.net.newServerSocket(Protocol.TCP, 12050,
				serverSocketHint);
	}

	public void startServer()
	{
		new Thread(this).start();
	}

	@Override
	public void run()
	{
		// Loop forever
		Socket socket;
		while (true)
		{
			// Create a socket
			socket = serverSocket.accept(null);
			System.out.println(socket.getRemoteAddress());
			try
			{
				output = new ObjectOutputStream(socket.getOutputStream());
				input = new ObjectInputStream(socket.getInputStream());
			} catch (IOException e)
			{
				e.printStackTrace();
			}
		}
	}

}

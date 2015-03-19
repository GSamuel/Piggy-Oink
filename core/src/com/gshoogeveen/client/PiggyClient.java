package com.gshoogeveen.client;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Net.Protocol;
import com.badlogic.gdx.net.Socket;
import com.badlogic.gdx.net.SocketHints;
import com.badlogic.gdx.utils.GdxRuntimeException;

public class PiggyClient implements Runnable
{
	private Socket socket;
	private SocketHints socketHints;
	private boolean connected = false;
	private ObjectOutputStream output;
	private ObjectInputStream input;

	public PiggyClient()
	{
		socketHints = new SocketHints();
		socketHints.connectTimeout = 4000;
	}

	public void connect()
	{
		if (!connected)
			new Thread(this).start();
	}
	
	public boolean connected()
	{
		return connected;
	}

	@Override
	public void run()
	{
		try
		{
			socket = Gdx.net.newClientSocket(Protocol.TCP, "192.168.2.20",
					12050, socketHints);
			System.out.println("1");
			output = new ObjectOutputStream(socket.getOutputStream());
			output.flush();
			System.out.println("2");
			input = new ObjectInputStream(socket.getInputStream());
			System.out.println("3");
			this.connected = true;
		} catch (GdxRuntimeException exception)
		{
			this.connected = false;
		} catch (IOException e)
		{
			e.printStackTrace();
		}
	}
}

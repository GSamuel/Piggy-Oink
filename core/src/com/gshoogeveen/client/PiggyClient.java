package com.gshoogeveen.client;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Net.Protocol;
import com.badlogic.gdx.net.Socket;
import com.badlogic.gdx.net.SocketHints;
import com.badlogic.gdx.utils.GdxRuntimeException;
import com.gshoogeveen.network.NetworkManager;
import com.gshoogeveen.network.PacketManager;

public class PiggyClient implements Runnable
{
	private Socket socket;
	private SocketHints socketHints;
	private boolean connected = false;
	private volatile NetworkManager networkManager;

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

	public boolean hasNetworkManager()
	{
		return networkManager != null;
	}
	
	public NetworkManager getNetworkManager()
	{
		return networkManager;
	}

	@Override
	public void run()
	{
		try
		{
			socket = Gdx.net.newClientSocket(Protocol.TCP, "192.168.2.20",
					12050, socketHints);
			this.networkManager = new NetworkManager(new PacketManager(socket));
			this.connected = true;
		} catch (GdxRuntimeException e)
		{
			e.printStackTrace();
			this.connected = false;
		} 
	}
}

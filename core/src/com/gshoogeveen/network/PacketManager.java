package com.gshoogeveen.network;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.concurrent.ConcurrentLinkedQueue;

import com.badlogic.gdx.net.Socket;
import com.badlogic.gdx.utils.Disposable;
import com.gshoogeveen.logging.LogManagerCore;
import com.gshoogeveen.logging.Logger;

public class PacketManager implements Runnable, Disposable
{
	private ObjectOutputStream output;
	private ObjectInputStream input;

	private ConcurrentLinkedQueue<Packet> inboundPackets;
	private boolean streamsOpen = false;
	private boolean receiving = false;
	private Socket socket;
	
	private static final Logger logger = LogManagerCore.getLogger(PacketManager.class);


	public PacketManager(Socket socket)
	{
		this.socket = socket;
		inboundPackets = new ConcurrentLinkedQueue<Packet>();
		this.openStreams();
		this.start();
	}

	public void openStreams()
	{
		if (socket.isConnected())
		{
			try
			{
				output = new ObjectOutputStream(socket.getOutputStream());
				input = new ObjectInputStream(socket.getInputStream());
				streamsOpen = true;
			} catch (IOException e)
			{
				logger.error("IOException: cant open objectStreams");
				//e.printStackTrace();
			}
		}
	}
	
	public String getRemoteAdress()
	{
		return socket.getRemoteAddress();
	}

	public void start()
	{
		if (!receiving && streamsOpen)
		{
			receiving = true;
			new Thread(this).start();
		}
	}

	public void isConnected()
	{
		socket.isConnected();
	}

	public boolean streamsOpen()
	{
		return streamsOpen;
	}
	
	public boolean packetAvaible()
	{
		return inboundPackets.size() > 0;
	}

	public Packet receivePacket()
	{
		return inboundPackets.remove();
	}

	public void sendPacket(Packet p)
	{
		if (streamsOpen)
			try
			{
				output.writeObject(p);
				output.flush();
			} catch (IOException e)
			{
				logger.error("IOException: can't send package, disconnected");
				//e.printStackTrace();
			}
	}

	@Override
	public void run()
	{
		while (receiving && streamsOpen)
		{
			try
			{
				inboundPackets.add((Packet) input.readObject());
			} catch (ClassNotFoundException e)
			{
				logger.error("ClassNotFoundException: received unrecognised package");
				//e.printStackTrace();
			} catch (IOException e)
			{
				logger.error("IOException: cant' receive packages, diconnected");
				//e.printStackTrace();
				receiving = false;
			}
		}
	}

	@Override
	public void dispose()
	{
		socket.dispose();
		streamsOpen = false;
		receiving = false;
	}
}

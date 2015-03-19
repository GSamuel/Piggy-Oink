package com.gshoogeveen.network;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.concurrent.ConcurrentLinkedQueue;

public class PacketManager implements Runnable
{
	private ObjectOutputStream output;
	private ObjectInputStream input;

	private ConcurrentLinkedQueue<Packet> inboundPackets;
	private boolean receiving = false;
	private boolean canReceive = true;
	private boolean canSend = true;

	public PacketManager(ObjectInputStream input, ObjectOutputStream output)
	{
		this.input = input;
		this.output = output;
		inboundPackets = new ConcurrentLinkedQueue<Packet>();
	}

	public void start()
	{
		if (!receiving && canReceive)
		{
			receiving = true;
			new Thread(this).start();
		}
	}

	public boolean avaible()
	{
		return inboundPackets.size() > 0;
	}

	public Packet receivePacket()
	{
		return inboundPackets.remove();
	}

	public void sendPacket(Packet p)
	{
		if (canSend)
			try
			{
				output.writeObject(p);
				output.flush();
			} catch (IOException e)
			{
				e.printStackTrace();
				canSend = false;
			}
	}

	@Override
	public void run()
	{
		while (receiving && canReceive)
		{
			try
			{
				inboundPackets.add((Packet) input.readObject());
			} catch (ClassNotFoundException e)
			{
				e.printStackTrace();
			} catch (IOException e)
			{
				e.printStackTrace();
				receiving = false;
				canReceive = false;
			}
		}
	}
}

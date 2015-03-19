package com.gshoogeveen.network;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class PacketTest
{
	public static void main(String args[])
	{
		String fileName = "packet.ser";
		StringPacket packet1 = new StringPacket();
		packet1.setName("Rosanne is lief <3");
		IntegerPacket packet2 = new IntegerPacket();
		packet2.setValue(1205);

		try
		{
			FileOutputStream fos = new FileOutputStream(fileName);
			ObjectOutputStream oos = new ObjectOutputStream(fos);
			oos.writeObject(packet1);
			oos.writeObject(packet2);
			oos.close();
		} catch (IOException e)
		{
			e.printStackTrace();
		}

		FileInputStream fis;
		try
		{
			fis = new FileInputStream(fileName);
			ObjectInputStream ois = new ObjectInputStream(fis);
			Packet p1 = (Packet) ois.readObject();
			Packet p2 = (Packet) ois.readObject();
			ois.close();
			System.out.println("Packet Object Read=" + p1);
			p1.show();
			System.out.println("Packet Object Read=" + p2);
			p2.show();
		} catch (ClassNotFoundException e)
		{
			e.printStackTrace();
		} catch (IOException e)
		{
			e.printStackTrace();
		}
	}
}

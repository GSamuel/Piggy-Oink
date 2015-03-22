package com.gshoogeveen.network;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

public abstract class Packet implements Serializable
{	
	/**
	 * 
	 */
	private static final long serialVersionUID = 8249432829639350523L;

	protected abstract void readObject(ObjectInputStream ois) throws ClassNotFoundException,IOException;
     
    protected abstract void writeObject(ObjectOutputStream oos) throws IOException;
    
    public abstract void processPacket(INetHandler handler);
}

package com.gshoogeveen.network;

import java.util.HashMap;


public enum EnumConnectionState
{
	HANDSHAKING, PLAY, STATUS, LOGIN;
	
	private static final HashMap<Class, Class> map = new HashMap<Class,Class>();
	
}

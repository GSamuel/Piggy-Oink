package com.gshoogeveen.server.dedicated;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Properties;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.gshoogeveen.logging.LogManagerCore;
import com.gshoogeveen.logging.Logger;

public class PropertyManager
{
	private Properties properties;
	private String fileName;
	private static final Logger logger = LogManagerCore.getLogger(PropertyManager.class);

	public PropertyManager(String fileName)
	{
		this.fileName = fileName;
		properties = new Properties();
	}
	
	public Object getProperty(Object key)
	{
		return properties.get(key);
	}
	
	public void putProperty(Object key, Object value)
	{
		properties.put(key, value);
	}

	public void readPropertyFile()
	{
		FileHandle path = Gdx.files.local(fileName);
		if(Gdx.files.isLocalStorageAvailable() && path.exists())
		{
			InputStream input = path.read();
			
			try
			{
				properties.load(input);
			} catch (IOException e)
			{
				logger.error("can't read properties from "+fileName);
			}
			finally
			{
				if(input != null)
					try
					{
						input.close();
					} catch (IOException e)
					{
						logger.error("can't close InputStream "+fileName);
					}
			}

		}
	}

	public void writePropertyFile()
	{
		FileHandle path = Gdx.files.local(fileName);

		if (Gdx.files.isLocalStorageAvailable())
		{
			OutputStream output = path.write(false);
			try
			{
				properties.store(output, null);
			} catch (IOException e)
			{
				logger.error("can't save properties to file "+fileName);
			} finally
			{
				if (output != null)
					try
					{
						output.close();
					} catch (IOException e)
					{
						logger.error("can't close OutputStream "+fileName);
					}
			}
		}

	}
	
	public void showAll()
	{
		for(Object o:properties.values())
			System.out.println(o.toString());
	}

}

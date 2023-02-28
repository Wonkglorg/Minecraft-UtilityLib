package com.wonkglorg.utilitylib.database;

import com.wonkglorg.utilitylib.logger.Logger;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public abstract class SqLiteDatabase
{
	protected static Connection connection;
	protected final String DATABASE_NAME;
	protected final Path DATABASE_PATH;
	
	/**
	 * Creates a new database file in the specified path
	 * @param name
	 * @param path
	 */
	public SqLiteDatabase(String name, Path path)
	{
		if(name == null || path == null)
		{
			throw new RuntimeException();
		}
		DATABASE_NAME = name.endsWith(".db") ? name : name + ".db";
		DATABASE_PATH = path;
		connect();
	}
	
	/**
	 * Creates a new database file in the plugins datafolder + specified path
	 * @param plugin
	 * @param name
	 * @param path
	 */
	public SqLiteDatabase(JavaPlugin plugin, String name, Path path)
	{
		if(name == null || path == null)
		{
			throw new RuntimeException();
		}
		DATABASE_NAME = name.endsWith(".db") ? name : name + ".db";
		DATABASE_PATH = Path.of(plugin.getDataFolder().getPath() + File.separator + path);
		connect();
	}
	
	public void connect()
	{
		if(connection != null)
		{
			return;
		}
		
		try
		{
			Class.forName("org.sqlite.JDBC");
			new File(String.valueOf(DATABASE_PATH)).mkdirs();
			
			File databaseFile = new File(DATABASE_PATH + File.separator + DATABASE_NAME);
			if(!databaseFile.exists())
			{
				databaseFile.createNewFile();
			}
			
			connection = DriverManager.getConnection("jdbc:sqlite:" + databaseFile.getPath());
		} catch(ClassNotFoundException | SQLException | IOException e)
		{
			Logger.logFatal(e.getClass().getName() + ": " + e.getMessage());
		}
	}
	
	public void disconnect() throws SQLException
	{
		if(connection != null)
		{
			connection.close();
		}
	}
	
	public Connection getConnection()
	{
		connect();
		return connection;
	}
	
}
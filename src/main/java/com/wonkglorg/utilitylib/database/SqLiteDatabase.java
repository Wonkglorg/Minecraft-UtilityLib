package com.wonkglorg.utilitylib.database;

import com.wonkglorg.utilitylib.logger.Logger;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.nio.file.Path;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;

public abstract class SqLiteDatabase
{
	private final JavaPlugin plugin;
	protected static Connection connection;
	protected final String DATABASE_NAME;
	protected final String DATABASE_PATH;
	
	public SqLiteDatabase(JavaPlugin plugin, String name, String... paths)
	{
		this.plugin = plugin;
		if(name == null || paths == null)
		{
			throw new RuntimeException();
		}
		DATABASE_NAME = name.endsWith(".db") ? name : name + ".db";
		StringBuilder pathBuilder = new StringBuilder();
		for(int i = 0; i < paths.length; i++)
		{
			if(paths.length == i + 1)
			{
				pathBuilder.append(paths[i]);
				break;
			}
			
			pathBuilder.append(paths[i]).append(File.separator);
		}
		DATABASE_PATH = plugin.getDataFolder().getPath() + File.separator + pathBuilder;
		connect();
	}
	
	public void createTable(String command)
	{
		connect();
		try(Statement statement = connection.createStatement())
		{
			statement.setQueryTimeout(30);
			statement.executeUpdate(command);
		} catch(SQLException e)
		{
			System.err.println(e.getMessage());
		}
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
			new File(String.valueOf(DATABASE_PATH)).mkdir();
			connection = DriverManager.getConnection("jdbc:sqlite:" + DATABASE_PATH + DATABASE_NAME);
		} catch(ClassNotFoundException | SQLException e)
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
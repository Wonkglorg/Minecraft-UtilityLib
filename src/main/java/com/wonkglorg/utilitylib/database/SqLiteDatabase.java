package com.wonkglorg.utilitylib.database;

import com.wonkglorg.utilitylib.logger.Logger;

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
	
	protected static Connection connection;
	protected final String DATABASE_NAME;
	protected final String DATABASE_PATH;
	
	public SqLiteDatabase(Path path, String name)
	{
		if(name == null || path == null)
		{
			throw new RuntimeException();
		}
		DATABASE_NAME = name.endsWith(".db") ? name : name + ".db";
		DATABASE_PATH = path.toAbsolutePath().toString();
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
			connection = DriverManager.getConnection("jdbc:sqlite:" + DATABASE_PATH + File.separator + DATABASE_NAME);
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
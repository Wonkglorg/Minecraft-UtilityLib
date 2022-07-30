package com.wonkglorg.utilitylib.databases;


import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

public abstract class MySQLDatabase
{
	
	protected static Connection connection;
	protected final String USERNAME;
	protected final String URL;
	protected final String PASSWORD;
	protected final String DATABASE_NAME;
	protected List<String> database_table_strings;
	
	public MySQLDatabase(String url, String username, String password, String databaseName)
	{
		if(username == null || url == null || password == null || databaseName == null)
		{
			throw new RuntimeException();
		}
		USERNAME = username;
		URL = url;
		PASSWORD = password;
		DATABASE_NAME = databaseName;
		database_table_strings = createDatabaseTables();
		if(prepDatabase())
		{
			initializeDatabaseTables();
		}
	}
	
	abstract List<String> createDatabaseTables();
	
	private boolean prepDatabase()
	{
		try
		{
			connection = DriverManager.getConnection("jdbc:mysql://" + URL + "/" + DATABASE_NAME, USERNAME, PASSWORD);
			
		} catch(SQLException e)
		{
			throw new RuntimeException(e);
			
		} catch(SecurityException f)
		{
			throw new SecurityException(f);
		}
		return connection != null;
	}
	
	private void initializeDatabaseTables()
	{
		try(Statement statement = connection.createStatement())
		{
			statement.setQueryTimeout(30);
			if(database_table_strings == null)
			{
				return;
			}
			for(String string : database_table_strings)
			{
				String[] splits = string.split(" ");
				if(splits[0].equalsIgnoreCase("create"))
				{
					if(!tableExists(splits[2]))
					{
						statement.executeUpdate(string);
					}
				}
			}
			
		} catch(SQLException e)
		{
			System.err.println(e.getMessage());
		}
	}
	
	public boolean tableExists(String tableName)
	{
		connect();
		try
		{
			DatabaseMetaData md = connection.getMetaData();
			ResultSet rs = md.getTables(null, null, tableName, null);
			return rs.next();
		} catch(SQLException ex)
		{
			ex.printStackTrace();
		}
		return false;
	}
	
	public void connect()
	{
		if(connection != null)
		{
			return;
		}
		try
		{
			connection = DriverManager.getConnection("jdbc:mysql://" + URL + "/" + DATABASE_NAME, USERNAME, PASSWORD);
		} catch(SQLException e)
		{
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
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
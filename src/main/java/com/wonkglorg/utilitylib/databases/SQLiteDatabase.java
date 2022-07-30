package com.wonkglorg.utilitylib.databases;


import java.io.File;
import java.nio.file.Path;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

public abstract class SQLiteDatabase
{
	protected static Connection connection;
	protected final String DATABASE_NAME;
	protected final String DATABASE_PATH;
	protected List<String> database_table_strings;
	
	public SQLiteDatabase(Path path, String name)
	{
		if(name == null || path == null)
		{
			throw new RuntimeException();
		}
		if(name.endsWith(".db"))
		{
			DATABASE_NAME = name;
		} else
		{
			DATABASE_NAME = name + ".db";
		}
		DATABASE_PATH = path.toAbsolutePath().toString();
		database_table_strings = createDatabaseTables();
		if(prepDatabase())
		{
			initializeDatabaseTables();
		}
	}
	
	/**
	 * Create database tables list.
	 *
	 * @return the list
	 */
	abstract List<String> createDatabaseTables();
	
	private boolean prepDatabase()
	{
		try
		{
			new File(String.valueOf(DATABASE_PATH)).mkdir();
			
			connection = DriverManager.getConnection("jdbc:sqlite:" + DATABASE_PATH + File.separator + DATABASE_NAME);
			
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
			Class.forName("org.sqlite.JDBC");
			connection = DriverManager.getConnection("jdbc:sqlite:" + DATABASE_PATH + File.separator + DATABASE_NAME);
		} catch(ClassNotFoundException | SQLException e)
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
package com.wonkglorg.utilitylib.database;

import org.bukkit.plugin.java.JavaPlugin;

import java.sql.Connection;
import java.sql.SQLException;
/**
 * @author Wonkglorg
 */
@SuppressWarnings("unused")
public abstract class Database
{
	protected Connection connection;
	protected final String DATABASE_NAME;
	protected final DatabaseType databaseType;
	
	public Database(String name, DatabaseType databaseType)
	{
		this.DATABASE_NAME = name;
		this.databaseType = databaseType;
	}
	
	public Database(JavaPlugin plugin, String name, DatabaseType databaseType)
	{
		this.DATABASE_NAME = name;
		this.databaseType = databaseType;
	}
	
	public abstract void connect();
	
	public void disconnect()
	{
		if(connection != null)
		{
			try
			{
				connection.close();
			} catch(SQLException ignored)
			{
			
			}
		}
		
	}
	
	public Connection getConnection()
	{
		connect();
		
		return connection;
	}
	
	public DatabaseType getDatabaseType()
	{
		return databaseType;
	}
	
	public String getDatabaseName()
	{
		return DATABASE_NAME;
	}
	
	public void setConnection(Connection connection)
	{
		this.connection = connection;
	}
	
	public enum DatabaseType
	{
		MYSQL("Mysql", "jdbc:mysql:", "com.mysql.cj.jdbc.Driver"),
		SQLITE("Sqlite", "jdbc:sqlite:", "org.sqlite.JDBC"),
		;
		private final String driver;
		private final String classLoader;
		private final String name;
		
		DatabaseType(String name, String driver, String classLoader)
		{
			this.driver = driver;
			this.classLoader = classLoader;
			this.name = name;
		}
		
		public String getDriver()
		{
			return driver;
		}
		
		public String getClassLoader()
		{
			return classLoader;
		}
		
		public String getName()
		{
			return name;
		}
	}
	
}
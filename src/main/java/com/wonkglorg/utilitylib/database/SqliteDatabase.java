package com.wonkglorg.utilitylib.database;

import com.wonkglorg.utilitylib.logger.Logger;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.sql.DriverManager;
import java.sql.SQLException;

@SuppressWarnings("unused")
public class SqliteDatabase extends Database
{
	protected final String DATABASE_NAME;
	protected final Path DATABASE_PATH;
	
	/**
	 * Creates a new database file in the specified path
	 *
	 * @param name
	 * @param path
	 */
	public SqliteDatabase(String name, Path path)
	{
		super(name, DatabaseType.SQLITE);
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
	 *
	 * @param plugin
	 * @param name
	 * @param path
	 */
	public SqliteDatabase(JavaPlugin plugin, String name, Path path)
	{
		super(name, DatabaseType.SQLITE);
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
			Class.forName(databaseType.getClassLoader());
			new File(String.valueOf(DATABASE_PATH)).mkdirs();
			
			File databaseFile = new File(DATABASE_PATH + File.separator + DATABASE_NAME);
			if(!databaseFile.exists())
			{
				boolean ignored = databaseFile.createNewFile();
			}
			
			connection = DriverManager.getConnection(databaseType.getDriver() + databaseFile.getPath());
		} catch(ClassNotFoundException | SQLException | IOException e)
		{
			Logger.logFatal(e.getClass().getName() + ": " + e.getMessage());
		}
	}
	
}
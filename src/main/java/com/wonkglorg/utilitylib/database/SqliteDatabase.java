package com.wonkglorg.utilitylib.database;

import com.wonkglorg.utilitylib.logger.Logger;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.sql.DriverManager;
import java.sql.SQLException;

@SuppressWarnings("unused")
public class SqliteDatabase extends Database
{
	protected final Path DATABASE_PATH;
	
	public SqliteDatabase(Path path)
	{
		super(path.getFileName().toString(), DatabaseType.SQLITE);
		
		DATABASE_PATH = path;
		connect();
	}
	
	public SqliteDatabase(JavaPlugin plugin, Path path)
	{
		super(path.getFileName().toString(), DatabaseType.SQLITE);
		DATABASE_PATH = Path.of(plugin.getDataFolder().getPath(), path.toString());
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
			
			Files.createFile(DATABASE_PATH);
			
			String connectionString = databaseType.getDriver() + DATABASE_PATH;
			connection = DriverManager.getConnection(connectionString);
			
		} catch(ClassNotFoundException | SQLException | IOException e)
		{
			Logger.logFatal(e.getClass().getName() + ": " + e.getMessage());
		}
	}
	
}
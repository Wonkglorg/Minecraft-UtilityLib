package com.wonkglorg.utilitylib.database;

import com.wonkglorg.utilitylib.logger.Logger;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.DriverManager;
import java.sql.SQLException;

@SuppressWarnings("unused")
public class SqliteDatabase extends Database
{
	protected final String DATABASE_NAME;
	protected final Path DATABASE_PATH;
	protected final Path DESTINATION_PATH;
	
	/**
	 * * Creates a Sqlite database at the specified copyToPath.
	 * * The sourcePath indicates where in the project the database file can be found, it will then be copied to the destinationPath destination.
	 * * If there is no database file it will be created at the destinationPath location.
	 *
	 * @param name
	 * @param sourcePath
	 * @param destinationPath
	 */
	public SqliteDatabase(String name, Path sourcePath, Path destinationPath)
	{
		super(name, DatabaseType.SQLITE);
		if(name == null)
		{
			throw new RuntimeException();
		}
		
		//add the option to define your own name for it the new file too!
		DATABASE_NAME = name.endsWith(".db") ? name : name + ".db";
		DATABASE_PATH = sourcePath;
		DESTINATION_PATH = destinationPath;
		connect();
	}
	
	/**
	 * Creates a Sqlite database inside your plugin folder with the specified name and paths.
	 * The sourcePath indicates where in the project the database file can be found, it will then be copied to the destinationPath destination.
	 * If there is no database file it will be created at the destinationPath location.
	 *
	 * @param plugin
	 * @param name
	 * @param sourcePath
	 * @param destinationPath
	 */
	public SqliteDatabase(JavaPlugin plugin, String name, Path sourcePath, Path destinationPath)
	{
		super(name, DatabaseType.SQLITE);
		if(name == null)
		{
			throw new RuntimeException();
		}
		DATABASE_NAME = name.endsWith(".db") ? name : name + ".db";
		DATABASE_PATH = sourcePath;
		if(destinationPath != null)
		{
			DESTINATION_PATH = Path.of(plugin.getDataFolder().getPath() + File.separator + destinationPath);
		} else
		{
			DESTINATION_PATH = Path.of(plugin.getDataFolder().getPath());
		}
		
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
			File databaseFile = getDatabaseFile();
			
			if(!databaseFile.exists())
			{
				copyDatabaseFile(databaseFile);
			}
			
			String connectionString = databaseType.getDriver() + databaseFile.getPath();
			connection = DriverManager.getConnection(connectionString);
			
		} catch(ClassNotFoundException | SQLException | IOException e)
		{
			Logger.logFatal(e.getClass().getName() + ": " + e.getMessage());
		}
	}
	//SQLITE DATABASE GETS CREATED BUT IS CORRUPTED FOR SOME REASON
	private File getDatabaseFile()
	{
		File databaseFile;
		if(DESTINATION_PATH != null)
		{
			new File(DESTINATION_PATH.toUri()).mkdirs();
			Path databasePath = Paths.get(String.valueOf(DESTINATION_PATH), DATABASE_NAME);
			databaseFile = databasePath.toFile();
		} else
		{
			databaseFile = new File(DATABASE_NAME);
		}
		return databaseFile;
	}
	
	private void copyDatabaseFile(File databaseFile) throws IOException
	{
		String path;
		
		if(DATABASE_PATH != null)
		{
			path = "/" + DATABASE_PATH + "/" + DATABASE_NAME;
		} else
		{
			path = "/" + DATABASE_NAME;
		}
		try(InputStream resourceStream = getClass().getResourceAsStream(path))
		{
			if(resourceStream != null)
			{
				Files.copy(resourceStream, databaseFile.toPath());
			} else
			{
				databaseFile.createNewFile();
			}
		}
	}
	
}
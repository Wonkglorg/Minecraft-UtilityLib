package com.wonkglorg.utilitylib.manager.database;

import com.wonkglorg.utilitylib.base.logger.Logger;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.nio.file.Files;
import java.nio.file.Path;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * @author Wonkglorg
 */
@SuppressWarnings("unused")
public class SqliteDatabase extends Database{
	
	protected final JavaPlugin plugin;
	protected final Path SOURCE_PATH;
	protected final Path DESTINATION_PATH;
	protected final String DATABASE_NAME;
	
	/**
	 * * Creates a Sqlite database at the specified copyToPath.
	 * * The sourcePath indicates where in the project the database file can be found, it will then be copied to the destinationPath destination.
	 * * If there is no database file it will be created at the destinationPath location.
	 * <br>
	 * !!IMPORTANT!!
	 * <br>Use <br>
	 * <pre>
	 *     {@code
	 * <plugin>
	 * 	<groupId>org.apache.maven.plugins</groupId>
	 * 	<artifactId>maven-resources-plugin</artifactId>
	 * 	<version>3.3.1</version>
	 * 	<configuration>
	 * 		<nonFilteredFileExtensions>
	 * 			<nonFilteredFileExtension>db</nonFilteredFileExtension>
	 * 		</nonFilteredFileExtensions>
	 * 	</configuration>
	 * </plugin>
	 * }
	 * </pre>
	 * otherwise sqlite database files will be filtered and become corrupted.
	 *
	 * @param sourcePath
	 * @param destinationPath
	 */
	public SqliteDatabase(Path sourcePath, Path destinationPath) {
		super(destinationPath.getFileName().toString(), DatabaseType.SQLITE);
		String name = destinationPath.getFileName().toString();
		DATABASE_NAME = name.endsWith(".db") ? name : name + ".db";
		SOURCE_PATH = sourcePath;
		DESTINATION_PATH = destinationPath;
		plugin = null;
		connect();
	}
	
	/**
	 * Creates a Sqlite database inside your plugin folder with the specified name and paths.
	 * The sourcePath indicates where in the project the database file can be found, it will then be copied to the destinationPath destination.
	 * If there is no database file it will be created at the destinationPath location.
	 * <br>
	 * !!IMPORTANT!!
	 * <br>Use <br>
	 * <pre>
	 *     {@code
	 * <plugin>
	 * 	<groupId>org.apache.maven.plugins</groupId>
	 * 	<artifactId>maven-resources-plugin</artifactId>
	 * 	<version>3.3.1</version>
	 * 	<configuration>
	 * 		<nonFilteredFileExtensions>
	 * 			<nonFilteredFileExtension>db</nonFilteredFileExtension>
	 * 		</nonFilteredFileExtensions>
	 * 	</configuration>
	 * </plugin>
	 * }
	 * </pre>
	 * otherwise sqlite database files will be filtered and become corrupted.
	 *
	 * @param plugin
	 * @param sourcePath
	 * @param destinationPath
	 */
	public SqliteDatabase(JavaPlugin plugin, Path sourcePath, Path destinationPath) {
		super(destinationPath.getFileName().toString(), DatabaseType.SQLITE);
		this.plugin = plugin;
		String name = destinationPath.getFileName().toString();
		DATABASE_NAME = name.endsWith(".db") ? name : name + ".db";
		SOURCE_PATH = sourcePath;
		DESTINATION_PATH = Path.of(plugin.getDataFolder().getPath(), destinationPath.toString());
		connect();
	}
	
	@Override
	public void connect() {
		if(connection != null){
			return;
		}
		
		try{
			Class.forName(databaseType.getClassLoader());
			
			File databaseFile = DESTINATION_PATH.toAbsolutePath().toFile();
			if(!databaseFile.exists()){
				copyDatabaseFile(databaseFile);
			}
			String connectionString = databaseType.getDriver() + SOURCE_PATH;
			connection = DriverManager.getConnection(connectionString);
			
		} catch(ClassNotFoundException | SQLException | IOException e){
			Logger.logFatal(e.getClass().getName() + ": " + e.getMessage());
		}
	}
	
	private void copyDatabaseFile(File databaseFile) throws IOException {
		try(InputStream resourceStream = getResource(SOURCE_PATH.toString())){
			if(resourceStream != null){
				Files.createDirectories(DESTINATION_PATH.getParent());
				Files.copy(resourceStream, databaseFile.toPath());
			} else {
				databaseFile.createNewFile();
			}
		}
		
	}
	
	private InputStream getResource(String filename) {
		if(filename == null){
			throw new IllegalArgumentException("Filename cannot be null");
		}
		
		try{
			URL url = getClass().getClassLoader().getResource(filename);
			
			if(url == null){
				return null;
			}
			
			URLConnection connection = url.openConnection();
			connection.setUseCaches(false);
			return connection.getInputStream();
		} catch(IOException ex){
			return null;
		}
	}
}
	

package com.wonkglorg.test.config;

import com.wonkglorg.utilitylib.config.Config;
import com.wonkglorg.utilitylib.logger.Logger;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Path;
import java.util.HashSet;
import java.util.Set;

/**
 * Abstract config class
 */
@SuppressWarnings("unused")
public final class ConfigYML extends YamlConfiguration implements Config
{
	//ADD OWN VERSION OF LOAD AND SAVE???? SO I CAN USE DESTINATION AND SOURCE PATH
	//CHECK IF IT ACTUALLY WORKS REDID THE CONSTRUCTORS!!!!!
	/**
	 * The Main.
	 */
	private final JavaPlugin plugin;
	/**
	 * The Name.
	 */
	private final String name;
	/**
	 * The Path.
	 */
	private final Path destinationPath;
	private final Path sourcePath;
	/**
	 * The File.
	 */
	private final File file;
	
	/**
	 * Creates a new config file from the selected path or creates an empty config file if it does not exist yet
	 *
	 * @param plugin
	 * @param path
	 */
	public ConfigYML(@NotNull JavaPlugin plugin, @NotNull Path path)
	{
		this(plugin, path, path);
	}
	
	/**
	 * Creates a new config file in the root directory of your plugin folder, or an empty yml if non exists.
	 *
	 * @param plugin
	 * @param name
	 */
	public ConfigYML(@NotNull JavaPlugin plugin, @NotNull String name)
	{
		this(plugin, Path.of(name), Path.of(name));
	}
	
	/**
	 * Creates a new config file from the selected path copied to the destination path or creates an empty config file if it does not exist yet
	 *
	 * @param plugin
	 * @param sourcePath
	 * @param destination
	 */
	public ConfigYML(@NotNull JavaPlugin plugin, @NotNull Path sourcePath, @NotNull Path destination)
	{
		this.plugin = plugin;
		String name = destination.getFileName().toString();
		this.name = name.endsWith(".yml") ? name : name + ".yml";
		this.destinationPath = destination.endsWith(".yml") ? destination : Path.of(destination.toString(), ".yml");
		this.sourcePath = sourcePath.endsWith(".yml") ? sourcePath : Path.of(sourcePath.toString(), ".yml");
		file = new File(plugin.getDataFolder(), destination.toString());
	}
	
	/**
	 * Gets a section of the config at the set path.
	 *
	 * @param path path inside yml config.
	 * @param deep deep search to get children of children
	 * @return {@link Set} of results.
	 */
	public Set<String> getSection(@NotNull String path, boolean deep)
	{
		
		ConfigurationSection section = getConfigurationSection(path);
		if(section != null)
		{
			return section.getKeys(deep);
		}
		return new HashSet<>();
	}
	
	public @Nullable String getParentPath(@NotNull String path)
	{
		ConfigurationSection configurationSection = getConfigurationSection(path).getParent();
		if(configurationSection == null)
		{
			return null;
		}
		return configurationSection.getCurrentPath();
	}
	
	@Override
	public void updateFiles()
	{
	
	}
	
	@Override
	public void load()
	{
		checkFile();
		try
		{
			load(file);
			Logger.log("Loaded data from " + name + "!");
		} catch(InvalidConfigurationException | IOException e)
		{
			Logger.logFatal(plugin, e.getMessage());
			Logger.logWarn("Error loading data from " + name + "!");
		}
	}
	
	/**
	 * same as load but with specified plugin for logger message
	 *
	 * @param plugin
	 */
	public void load(JavaPlugin plugin)
	{
		checkFile();
		try
		{
			load(file);
			Logger.log(plugin, "Loaded data from " + name + "!");
		} catch(InvalidConfigurationException | IOException e)
		{
			Logger.logFatal(plugin, e.getMessage());
			Logger.logWarn(plugin, "Error loading data from " + name + "!");
		}
	}
	
	@Override
	public void silentLoad()
	{
		checkFile();
		try
		{
			load(file);
		} catch(InvalidConfigurationException | IOException e)
		{
			Logger.logFatal(plugin, e.getMessage());
			Logger.logWarn("Error loading data from " + name + "!");
		}
	}
	
	//create own save method to be able to save it to a specific location and with a specific name?
	
	@Override
	public void save()
	{
		checkFile();
		try
		{
			save(file);
			Logger.log("Saved data to " + name + "!");
		} catch(IOException e)
		{
			Logger.logFatal(plugin, e.getMessage());
			Logger.logWarn("Error saving data to " + name + "!");
		}
	}
	
	@Override
	public void silentSave()
	{
		checkFile();
		try
		{
			save(file);
		} catch(IOException e)
		{
			e.printStackTrace();
			Logger.logWarn("Error saving data to " + name + "!");
		}
	}
	
	@Override
	public String name()
	{
		return name;
	}
	
	@Override
	public String path()
	{
		return destinationPath.toString();
	}
	
	/**
	 * Checks if file exists in path, else create the file and all parent directories needed.
	 */
	private void checkFile()
	{
		if(!file.exists())
		{
			InputStream inputStream = plugin.getResource(destinationPath.toString().replace("\\", File.separator));
			System.out.println(inputStream);
			if(inputStream != null)
			{
				plugin.saveResource(destinationPath.toString(), false);
			} else
			{
				System.out.println("Creating file");
				boolean ignored = file.getParentFile().mkdirs();
				try
				{
					ignored = file.createNewFile();
				} catch(IOException e)
				{
					throw new RuntimeException(e);
				}
			}
		}
		updateFiles();
		
	}
	
	/*
	
	modify this code to save yml and json files, lets me choose a way to save to a different location compared to the same it is already in
	could also allow of adding a saveTo method to save it to a new path
	saveToRename
	would rename and save it in a new location.
		private void copyDatabaseFile(File databaseFile) throws IOException, InterruptedException
	{
		String path;
		if(DATABASE_PATH != null)
		{
			path = "/" + DATABASE_PATH + "/" + DATABASE_NAME;
		} else
		{
			path = "/" + DATABASE_NAME;
		}
		System.out.println("Path: " + path);
		InputStream inputStream = getClass().getResourceAsStream(path);
		if(inputStream != null)
		{
			try(BufferedInputStream bufferedInputStream = new BufferedInputStream(inputStream))
			{
				byte[] buffer = new byte[1024];
				int bytesRead;
				try(ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream())
				{
					while((bytesRead = bufferedInputStream.read(buffer)) != -1)
					{
						System.out.println("Adding....");
						byteArrayOutputStream.write(buffer, 0, bytesRead);
						byteArrayOutputStream.flush();
						
					}
					System.out.println("Finished adding");
					Files.write(databaseFile.toPath(), byteArrayOutputStream.toByteArray());
				}
			}
		} else
		{
			System.out.println("Created new file");
			databaseFile.createNewFile();
		}
	}
	 */
}
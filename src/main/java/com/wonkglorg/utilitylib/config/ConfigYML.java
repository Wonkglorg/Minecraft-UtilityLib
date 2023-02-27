package com.wonkglorg.utilitylib.config;

import com.wonkglorg.utilitylib.logger.Logger;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.io.File;
import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Abstract config class
 */
@SuppressWarnings("unused")
public class ConfigYML extends YamlConfiguration implements Config
{
	
	/**
	 * The Main.
	 */
	protected final JavaPlugin main;
	/**
	 * The Name.
	 */
	protected String name;
	/**
	 * The Path.
	 */
	protected String path;
	/**
	 * The File.
	 */
	protected File file;
	
	/**
	 * Creates a new config object which represents a yml file
	 *
	 * @param main the java plugin
	 * @param name the file name
	 */
	public ConfigYML(JavaPlugin main, String name)
	{
		this.main = main;
		this.name = name + (name.endsWith(".yml") ? "" : ".yml");
		this.path = this.name;
		file = new File(main.getDataFolder(), name);
	}
	
	/**
	 * Creates a new config object which represents a yml file
	 *
	 * @param main the java plugin
	 * @param name the file name
	 * @param paths the path to the file
	 */
	public ConfigYML(JavaPlugin main, String name, String... paths)
	{
		this.main = main;
		this.name = name + (name.endsWith(".yml") ? "" : ".yml");
		StringBuilder pathBuilder = new StringBuilder();
		for(String s : paths)
		{
			pathBuilder.append(s).append(File.separator);
		}
		this.path = pathBuilder + this.name;
		file = new File(main.getDataFolder(), this.path);
		
	}
	
	public ConfigYML(JavaPlugin main, String name, String path)
	{
		this.main = main;
		this.name = name + (name.endsWith(".yml") ? "" : ".yml");
		path = path.startsWith(File.separator) ? path.replaceFirst(File.separator, "") : path;
		this.path = path.endsWith(File.separator) ? path + name : path + File.separator + name;
		file = new File(main.getDataFolder(), this.path);
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
		return getConfigurationSection(path).getParent().getCurrentPath();
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
			e.printStackTrace();
			Logger.logWarn("Error loading data from " + name + "!");
		}
	}
	
	/**
	 * same as load but with specified plugin for logger message
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
			e.printStackTrace();
			Logger.logWarn(plugin,"Error loading data from " + name + "!");
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
			e.printStackTrace();
			Logger.logWarn("Error loading data from " + name + "!");
		}
	}
	
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
			e.printStackTrace();
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
		return path;
	}
	
	/**
	 * Checks if file exists in path, else create the file and all parent directories needed.
	 */
	private void checkFile()
	{
		if(!file.exists())
		{
			main.saveResource(path, false);
			boolean ignored = file.getParentFile().mkdirs();
			return;
		}
		updateFiles();
		
	}
}
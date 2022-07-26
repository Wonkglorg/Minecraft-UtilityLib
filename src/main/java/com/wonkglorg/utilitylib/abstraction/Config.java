package com.wonkglorg.utilitylib.abstraction;

import com.wonkglorg.utilitylib.utils.logger.Logger;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.NotNull;

import java.io.File;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

/**
 * Abstract class to manage configs
 */
public abstract class Config extends YamlConfiguration
{
	
	protected JavaPlugin main;
	protected String name;
	protected File file;
	
	public Config(JavaPlugin main, String name)
	{
		this.main = main;
		this.name = name;
		file = new File(main.getDataFolder(), name);
	}
	
	/**
	 * Gets a section of the config at the set path.
	 *
	 * @param path path inside yml config.
	 * @return {@link Set} of results.
	 */
	public Set<String> getSection(String path)
	{
		ConfigurationSection section = getConfigurationSection(path);
		if(section != null)
		{
			return section.getKeys(false);
		}
		return new HashSet<>();
	}
	
	/**
	 * Sets values at the specified path.
	 *
	 * @param path path inside yml config.
	 * @param values {@link Set} of Strings.
	 */
	public void setSection(String path, Set<String> values)
	{
		set(path, values);
	}
	
	/**
	 * Checks if file exists in path, else create the file and all parent directories needed.
	 */
	private void checkFile()
	{
		if(!file.exists())
		{
			file.getParentFile().mkdirs();
			main.saveResource(name, false);
		}
	}
	
	/**
	 * Load config from file
	 */
	public void loadConfig()
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
	 * Save config to file
	 */
	public void saveConfig()
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
	public @NotNull String getName(){
		return name;
	}
}
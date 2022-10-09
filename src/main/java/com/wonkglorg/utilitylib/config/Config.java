package com.wonkglorg.utilitylib.config;

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
@SuppressWarnings("unused")
public class Config extends YamlConfiguration
{
	
	protected JavaPlugin main;
	protected String name;
	protected String subPathName;
	protected File file;
	
	public Config(JavaPlugin main, String name)
	{
		this.main = main;
		this.name = name + (name.endsWith(".yml") ? "" : ".yml");
		this.subPathName = this.name;
		file = new File(main.getDataFolder(), name);
	}
	
	public Config(JavaPlugin main, String name, String path)
	{
		this.main = main;
		this.name = name + (name.endsWith(".yml") ? "" : ".yml");
		this.subPathName = path + this.name;
		//path = path.startsWith(File.pathSeparator) ? path : File.pathSeparator + path;
		file = new File(main.getDataFolder(), subPathName);
	}
	
	/**
	 * Gets a section of the config at the set path.
	 *
	 * @param path path inside yml config.
	 * @param deep deep search to get children of children
	 * @return {@link Set} of results.
	 */
	public Set<String> getSection(String path, boolean deep)
	{
		ConfigurationSection section = getConfigurationSection(path);
		if(section != null)
		{
			return section.getKeys(deep);
		}
		return new HashSet<>();
	}
	
	public Set<String> getSection(boolean deep, String path)
	{
		ConfigurationSection section = getConfigurationSection(path);
		if(section != null)
		{
			return section.getKeys(deep);
		}
		return new HashSet<>();
	}
	
	public Set<String> getSection(boolean deep, String... path)
	{
		StringBuilder stringBuilder = new StringBuilder();
		for(String s : path)
		{
			stringBuilder.append(s);
			stringBuilder.append(".");
		}
		ConfigurationSection section = getConfigurationSection(removeLastChar(stringBuilder.toString()));
		if(section != null)
		{
			return section.getKeys(deep);
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
			main.saveResource(subPathName, false);
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
	public @NotNull String getName()
	{
		return name;
	}
	
	private String removeLastChar(String str)
	{
		if(str != null && str.length() > 0 && str.charAt(str.length() - 1) == '.')
		{
			str = str.substring(0, str.length() - 1);
		}
		return str;
	}
}
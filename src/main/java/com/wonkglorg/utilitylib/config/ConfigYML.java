package com.wonkglorg.utilitylib.config;

import com.wonkglorg.utilitylib.utils.logger.Logger;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.io.IOException;
import java.util.HashSet;
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
			pathBuilder.append(s);
			pathBuilder.append(File.separator);
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
	public Set<String> getSection(String path, boolean deep)
	{
		ConfigurationSection section = getConfigurationSection(path);
		if(section != null)
		{
			return section.getKeys(deep);
		}
		return new HashSet<>();
	}
	
	@Override
	public void updateFiles()
	{
	
	}
	/*
	@Override
	public void updateFiles()
	{
		//not updating? Find solution
		
		File existingConfigFile = new File(main.getDataFolder(), path);
		FileConfiguration existingFileConfiguration = YamlConfiguration.loadConfiguration(existingConfigFile);
		
		for(String section : getConfigurationSection("").getKeys(true))
		{
			if(existingFileConfiguration.get(section) != null)
			{
				continue;
			}
			
			existingFileConfiguration.set(section, get(section));
		}
		
		try
		{
			//System.out.println("Saving override config " + existingConfigFile.getName());
			existingFileConfiguration.save(existingConfigFile);
			file = existingConfigFile;
		} catch(IOException e)
		{
			e.printStackTrace();
		}
	}
	
	 */
	
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
	 * Gets section.
	 *
	 * @param deep the deep
	 * @param path the path
	 * @return the section
	 */
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
	
	private String removeLastChar(String str)
	{
		if(str != null && str.length() > 0 && str.charAt(str.length() - 1) == '.')
		{
			str = str.substring(0, str.length() - 1);
		}
		return str;
	}
}
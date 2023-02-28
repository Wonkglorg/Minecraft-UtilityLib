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
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashSet;
import java.util.Set;

/**
 * Abstract config class
 */
@SuppressWarnings("unused")
public final class ConfigYML extends YamlConfiguration implements Config
{
	
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
	private final Path path;
	/**
	 * The File.
	 */
	private final File file;
	
	/**
	 * Creates a new config object which represents a yml file
	 *
	 * @param plugin the java plugin
	 * @param name the file name
	 */
	public ConfigYML(JavaPlugin plugin, String name)
	{
		this.plugin = plugin;
		this.name = name + (name.endsWith(".yml") ? "" : ".yml");
		this.path = Path.of(this.name);
		file = new File(plugin.getDataFolder(), name);
	}
	
	/**
	 * Creates a new config object which represents a yml file
	 *
	 * @param plugin the java plugin
	 * @param name the file name
	 * @param paths the path to the file
	 */
	public ConfigYML(JavaPlugin plugin, String name, String... paths)
	{
		this.plugin = plugin;
		this.name = name.endsWith(".yml") ? name : name + ".yml";
		String joinedPath = String.join(File.separator, paths);
		this.path = Paths.get(joinedPath, this.name);
		file = new File(plugin.getDataFolder(), this.path.toString());
	}
	
	public ConfigYML(JavaPlugin main, Path path)
	{
		this.plugin = main;
		String name = path.getName(path.getNameCount()).toString();
		this.name = name.endsWith(".yml") ? name : name + ".yml";
		this.path = Path.of(path.toString(), this.name);
		file = this.path.toFile();
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
		return path.toString();
	}
	
	/**
	 * Checks if file exists in path, else create the file and all parent directories needed.
	 */
	private void checkFile()
	{
		if(!file.exists())
		{
			if(plugin.getResource(path.toString()) != null)
			{
				plugin.saveResource(path.toString(), false);
			} else
			{
				boolean ignored = file.getParentFile().mkdirs();
				try
				{
					ignored = file.createNewFile();
				} catch(IOException e)
				{
					throw new RuntimeException(e);
				}
			}
			return;
		}
		updateFiles();
		
	}
}
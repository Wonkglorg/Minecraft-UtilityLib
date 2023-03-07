package com.wonkglorg.utilitylib.managers;

import com.wonkglorg.utilitylib.config.Config;
import com.wonkglorg.utilitylib.config.ConfigYML;
import com.wonkglorg.utilitylib.logger.Logger;
import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.NotNull;

import java.io.File;
import java.nio.file.AtomicMoveNotSupportedException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import java.util.function.Consumer;

@SuppressWarnings("unused")
public final class ConfigManager implements Manager
{
	
	//add method to reload all configs same for ymls as an easier implementation for reload config commands
	private final JavaPlugin plugin;
	
	public ConfigManager(JavaPlugin plugin)
	{
		this.plugin = plugin;
	}
	
	private final Collection<Config> configs = new ArrayList<>();
	
	public void add(@NotNull Config config)
	{
		configs.add(config);
	}
	
	public void add(@NotNull Config... config)
	{
		configs.addAll(List.of(config));
	}
	
	public void add(@NotNull Collection<Config> config)
	{
		configs.addAll(config);
	}
	
	public void load()
	{
		configs.forEach(Config::load);
	}
	
	public void silentLoad()
	{
		configs.forEach(Config::silentLoad);
	}
	
	public void save()
	{
		configs.forEach(Config::save);
	}
	
	public void silentSave()
	{
		configs.forEach(Config::silentSave);
	}
	
	public Config getConfig(String name)
	{
		for(Config config : configs)
		{
			if(config.name().equalsIgnoreCase(name))
			{
				return config;
			}
		}
		return null;
	}
	
	@Override
	public void onShutdown()
	{
		if(!configs.isEmpty())
		{
			silentSave();
			Logger.log(plugin, "Saved " + configs.size() + " configs!");
		}
	}
	
	@Override
	public void onStartup()
	{
		silentLoad();
		if(!configs.isEmpty())
		{
			Logger.log(plugin, "Loaded " + configs.size() + " configs!");
		}
	}
	
	public Map<String, Config> addAllConfigsFromPath(String... paths)
	{
		if(paths.length == 0)
		{
			return null;
		}
		String first = paths[0];
		String[] more = Arrays.copyOfRange(paths, 1, paths.length);
		Path path = Path.of(first, more);
		return addAllConfigsFromPath(path);
	}
	
	public Map<String, Config> addAllConfigsFromPath(Path path)
	{
		File[] files = Path.of(plugin.getDataFolder().getPath() + File.separator + path).toFile().listFiles();
		Map<String, Config> tempConfigs = new HashMap<>();
		if(files == null)
		{
			return null;
		}
		for(File file : files)
		{
			if(!file.isFile())
			{
				continue;
			}
			Config config = new ConfigYML(plugin, file.getName(), file.getParent());
			configs.add(config);
			tempConfigs.put(file.getName(), config);
		}
		
		return tempConfigs;
	}
}
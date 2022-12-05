package com.wonkglorg.utilitylib.managers;

import com.wonkglorg.utilitylib.config.Config;
import com.wonkglorg.utilitylib.logger.Logger;
import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.function.Consumer;

@SuppressWarnings("unused")
public class ConfigManager implements Manager
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
		silentSave();
		if(!configs.isEmpty())
		{
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
}
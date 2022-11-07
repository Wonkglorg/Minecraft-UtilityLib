package com.wonkglorg.utilitylib.managers;

import com.wonkglorg.utilitylib.config.Config;
import com.wonkglorg.utilitylib.utils.logger.Logger;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("unused")
public class ConfigManager implements Manager
{
	private final List<Config> configs = new ArrayList<>();
	
	public void add(@NotNull Config config)
	{
		configs.add(config);
	}
	
	public void add(@NotNull Config... config)
	{
		configs.addAll(List.of(config));
	}
	
	public void add(@NotNull List<Config> config)
	{
		configs.addAll(config);
	}
	
	public void load()
	{
		configs.forEach(Config::load);
	}
	
	public void save()
	{
		configs.forEach(Config::save);
	}
	
	@Override
	public void onShutdown()
	{
		save();
		Logger.log("Successfully saved " + configs.size() + " configs!" );
	}
	
	@Override
	public void onStartup()
	{
		load();
		Logger.log("Successfully loaded " + configs.size() + " configs!" );
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
}
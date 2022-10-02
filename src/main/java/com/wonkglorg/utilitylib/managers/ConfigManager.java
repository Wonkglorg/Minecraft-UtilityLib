package com.wonkglorg.utilitylib.managers;

import com.wonkglorg.utilitylib.config.Config;

import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("unused")
public class ConfigManager implements Manager
{
	private final List<Config> configs = new ArrayList<>();
	
	public void add(Config config)
	{
		configs.add(config);
	}
	
	public void add(Config... config)
	{
		configs.addAll(List.of(config));
	}
	
	public void add(List<Config> config)
	{
		configs.addAll(config);
	}
	
	public void load()
	{
		configs.forEach(Config::loadConfig);
	}
	
	public void save()
	{
		configs.forEach(Config::saveConfig);
	}
	
	public Config getConfig(String name)
	{
		for(Config config : configs)
		{
			if(config.getName().equalsIgnoreCase(name))
			{
				return config;
			}
		}
		return null;
	}
}
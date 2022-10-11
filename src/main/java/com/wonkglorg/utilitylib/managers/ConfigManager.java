package com.wonkglorg.utilitylib.managers;

import com.wonkglorg.utilitylib.config.Config;
import com.wonkglorg.utilitylib.config.ConfigYML;

import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("unused")
public class ConfigManager implements Manager
{
	private final List<Config> configs = new ArrayList<>();
	
	public void add(ConfigYML config)
	{
		configs.add(config);
	}
	
	public void add(ConfigYML... config)
	{
		configs.addAll(List.of(config));
	}
	
	public void add(List<ConfigYML> config)
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
package com.wonkglorg.utilitylib.managers;

import com.wonkglorg.utilitylib.abstraction.Config;

import java.util.ArrayList;
import java.util.List;

public class ConfigManager
{
	private final List<Config> configs = new ArrayList<>();
	
	public void addConfig(Config config)
	{
		configs.add(config);
	}
	
	public void loadConfigs()
	{
		for(Config config : configs)
		{
			config.loadConfig();
		}
	}
	
	public void saveConfigs()
	{
		for(Config config : configs)
		{
			config.saveConfig();
		}
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
package com.wonkglorg.utilitylib.managers;

import com.wonkglorg.utilitylib.config.Config;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

public class LangManager implements Manager
{
	
	/// REFORMAT ALL AND ADD JAVA DOCS
	
	private final Map<Locale, Config> langMap = new HashMap<>();
	
	LangManager()
	{
	}
	
	public void addLanguage(Locale locale, Config languageConfig)
	{
		langMap.put(locale, languageConfig);
		languageConfig.loadConfig();
	}
	
	public void load()
	{
		for(Config config : langMap.values())
		{
			config.loadConfig();
		}
	}
	
	public void save()
	{
		for(Config config : langMap.values())
		{
			config.saveConfig();
		}
	}
	
	public void addAllLangFilesFromPath(JavaPlugin javaPlugin,String path){
		//Get all files from directory and add them with their name as the locale
		
		langMap.put(Locale.getDefault(Locale.Category.valueOf(path)),new Config(javaPlugin,path));
	}
	
	public String getValue(Locale locale, String message)
	{
		return langMap.get(locale).getString(message);
	}
	
}
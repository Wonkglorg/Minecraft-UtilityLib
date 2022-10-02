package com.wonkglorg.utilitylib.managers;

import com.wonkglorg.utilitylib.config.Config;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.Objects;

public class LangManager implements Manager
{
	
	/// REFORMAT ALL AND ADD JAVA DOCS
	
	private final Map<Locale, Config> langMap = new HashMap<>();
	private Locale defaultLang;
	private boolean loaded = false;
	
	public LangManager()
	{
	}
	
	public LangManager(Locale defaultLang, Config defaultConfig)
	{
		langMap.put(defaultLang, defaultConfig);
		this.defaultLang = defaultLang;
	}
	
	public void setDefaultLang(Locale defaultLang, Config defaultConfig)
	{
		langMap.put(defaultLang, defaultConfig);
		this.defaultLang = defaultLang;
	}
	
	public void addLanguage(Locale locale, Config languageConfig)
	{
		langMap.put(locale, languageConfig);
		languageConfig.loadConfig();
	}
	
	public void load()
	{
		langMap.values().forEach(Config::loadConfig);
	}
	
	public Config getDefaultLang()
	{
		try
		{
			return langMap.get(defaultLang);
		} catch(Exception e)
		{
			return null;
		}
	}
	
	public void save()
	{
		langMap.values().forEach(Config::saveConfig);
	}
	
	public void addAllLangFilesFromPath(JavaPlugin javaPlugin, String path)
	{
		for(File file : Objects.requireNonNull(Path.of(javaPlugin.getDataFolder().getPath() + File.pathSeparator + path).toFile().listFiles()))
		{
			if(!file.isFile())
			{
				continue;
			}
			for(Locale locale : Locale.getAvailableLocales())
			{
				if(locale.getDisplayName().equalsIgnoreCase(file.getName()))
				{
					langMap.put(locale, new Config(javaPlugin, file.getName(), file.getParent()));
				}
			}
		}
	}
	
	public String getValue(Locale locale, String message)
	{
		if(!loaded)
		{
			loaded = true;
			load();
		}
		
		Config config = langMap.containsKey(locale) ? langMap.get(locale) : langMap.get(defaultLang);
		
		return config != null ? config.getString(message) : message;
	}
	
}
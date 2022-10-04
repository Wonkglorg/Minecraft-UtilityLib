package com.wonkglorg.utilitylib.managers;

import com.wonkglorg.utilitylib.config.Config;
import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.NotNull;

import java.io.File;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

public class LangManager implements Manager
{
	
	/// REFORMAT ALL AND ADD JAVA DOCS
	
	private final Map<Locale, Config> langMap = new HashMap<>();
	private Locale defaultLang;
	private boolean loaded = false;
	
	Map<String, String> replacerMap = new HashMap<>();
	
	public LangManager()
	{
	}
	
	public void replace(String replace, String with)
	{
		replacerMap.put(replace, with);
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
		//Add dictionary to get a wider range of possible yml naming for langs
		
		
		for(File file : Path.of(javaPlugin.getDataFolder().getPath() + File.separator + path).toFile().listFiles())
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
	
	public String getValue(@NotNull final Locale locale, @NotNull final String message, @NotNull final String defaultValue)
	{
		if(!loaded)
		{
			loaded = true;
			load();
		}
		
		Config config = langMap.containsKey(locale) ? langMap.get(locale) : langMap.get(defaultLang);
		
		String editString = config != null ? config.getString(message) : defaultValue;
		editString = editString != null ? editString : defaultValue;
		
		final List<String> keys = replacerMap.keySet().stream().toList();
		final List<String> values = replacerMap.values().stream().toList();
		
		
		for(int i = 0; i < keys.size(); i++)
		{
			editString = editString.replace(keys.get(i), values.get(i));
		}
		
		return editString;
		
	}
	
	public String getValue(@NotNull final Locale locale, @NotNull final String message)
	{
		return getValue(locale, message, message);
	}
	
	
}
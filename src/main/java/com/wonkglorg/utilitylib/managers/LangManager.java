package com.wonkglorg.utilitylib.managers;

import com.wonkglorg.utilitylib.config.Config;
import com.wonkglorg.utilitylib.config.ConfigYML;
import com.wonkglorg.utilitylib.utils.logger.Logger;
import org.bukkit.entity.Player;
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
		languageConfig.load();
	}
	
	public void load()
	{
		langMap.values().forEach(Config::load);
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
		langMap.values().forEach(Config::save);
	}
	
	public void addAllLangFilesFromPath(JavaPlugin javaPlugin, String path)
	{
		//Add dictionary to get a wider range of possible yml naming for langs
		
		File[] files = Path.of(javaPlugin.getDataFolder().getPath() + File.separator + path).toFile().listFiles();
		if(files == null)
		{
			Logger.logWarn("No available language files loaded");
			return;
		}
		for(File file : files)
		{
			if(!file.isFile())
			{
				continue;
			}
			for(Locale locale : Locale.getAvailableLocales())
			{
				if(locale.getDisplayName().equalsIgnoreCase(file.getName()))
				{
					Logger.log(locale.getDisplayName() + " has been loaded!");
					langMap.put(locale, new ConfigYML(javaPlugin, file.getName(), file.getParent()));
				}
			}
		}
	}
	
	public String getValue(@NotNull final Locale locale, @NotNull final String value, @NotNull final String defaultValue)
	{
		if(!loaded)
		{
			loaded = true;
			load();
		}
		
		Config config = langMap.containsKey(locale) ? langMap.get(locale) : langMap.get(defaultLang);
		
		String editString = config != null ? config.getStringValue(value) : defaultValue;
		editString = editString != null ? editString : defaultValue;
		
		final List<String> keys = replacerMap.keySet().stream().toList();
		final List<String> values = replacerMap.values().stream().toList();
		
		for(int i = 0; i < keys.size(); i++)
		{
			editString = editString.replace(keys.get(i), values.get(i));
		}
		
		return editString;
		
	}
	
	public Map<Locale, Config> getAllLangs()
	{
		return langMap;
	}
	
	public String getValue(Player player, String value)
	{
		return getValue(player.locale(), value);
	}
	
	public String getValue(@NotNull final Locale locale, @NotNull final String value)
	{
		return getValue(locale, value, value);
	}
	
}
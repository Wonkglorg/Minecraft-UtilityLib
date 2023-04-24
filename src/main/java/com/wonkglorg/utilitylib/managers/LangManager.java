package com.wonkglorg.utilitylib.managers;

import com.wonkglorg.utilitylib.config.Config;
import com.wonkglorg.utilitylib.config.ConfigYML;
import com.wonkglorg.utilitylib.logger.Logger;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.NotNull;

import java.io.File;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author Wonkglorg
 */
@SuppressWarnings("unused")
public final class LangManager implements Manager
{
	private final Map<Locale, Config> langMap = new ConcurrentHashMap<>();
	private Locale defaultLang;
	private boolean loaded = false;
	private final JavaPlugin plugin;
	private boolean isLoaded = false;
	private final Map<String, String> replacerMap = new ConcurrentHashMap<>();
	
	public LangManager(JavaPlugin plugin)
	{
		this.plugin = plugin;
	}
	
	public void replace(String replace, String with)
	{
		replacerMap.put(replace, with);
	}
	
	public LangManager(Locale defaultLang, Config defaultConfig, JavaPlugin plugin)
	{
		this.plugin = plugin;
		langMap.put(defaultLang, defaultConfig);
		this.defaultLang = defaultLang;
	}
	
	public synchronized void setDefaultLang(Locale defaultLang, Config defaultConfig)
	{
		langMap.put(defaultLang, defaultConfig);
		this.defaultLang = defaultLang;
		defaultConfig.load();
	}
	
	public synchronized void addLanguage(Locale locale, Config languageConfig)
	{
		langMap.put(locale, languageConfig);
		languageConfig.silentLoad();
	}
	
	public synchronized void save()
	{
		langMap.values().forEach(Config::save);
	}
	
	public synchronized void silentSave()
	{
		langMap.values().forEach(Config::silentSave);
	}
	
	public synchronized void load()
	{
		langMap.values().forEach(Config::silentLoad);
		
	}
	
	public synchronized void silentLoad()
	{
		langMap.values().forEach(Config::silentLoad);
		
		if(defaultLang == null)
		{
			Logger.logWarn("No default language selected");
		}
	}
	
	public synchronized Config getDefaultLang()
	{
		try
		{
			return langMap.get(defaultLang);
		} catch(Exception e)
		{
			return null;
		}
	}
	
	@Override
	public void onShutdown()
	{
		save();
	}
	
	@Override
	public void onStartup()
	{
		if(isLoaded)
		{
			return;
		}
		isLoaded = true;
		if(defaultLang == null)
		{
			Logger.logWarn(plugin, "No default language selected");
		}
	}
	
	public synchronized void addAllLangFilesFromPath(String... paths)
	{
		if(paths.length == 0)
		{
			return;
		}
		String first = paths[0];
		String[] more = Arrays.copyOfRange(paths, 1, paths.length);
		Path path = Path.of(first, more);
		addAllLangFilesFromPath(path);
	}
	
	public synchronized void addAllLangFilesFromPath(Path path)
	{
		//Add dictionary to get a wider range of possible yml naming for langs
		
		File[] files = Path.of(plugin.getDataFolder().getPath(), path.toString()).toFile().listFiles();
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
			if(!file.getName().endsWith(".yml"))
			{
				continue;
			}
			for(Locale locale : Locale.getAvailableLocales())
			{
				if(locale.getLanguage().equalsIgnoreCase(file.getName()))
				{
					Logger.log(plugin, locale.getLanguage() + " has been loaded!");
					Config config = new ConfigYML(plugin, file.toPath());
					addLanguage(locale, config);
					
				}
			}
		}
	}
	
	//add method to also add all from resources with defining their path? is that possible?
	
	public String getValue(String value)
	{
		return getValue(null, value, value);
	}
	
	public String getValue(Player player, String value)
	{
		return getValue(player.locale(), value);
	}
	
	public String getValue(@NotNull final Locale locale, @NotNull final String value)
	{
		return getValue(locale, value, value);
	}
	
	public String getValue(final Locale locale, @NotNull final String value, @NotNull final String defaultValue)
	{
		if(!loaded)
		{
			loaded = true;
			load();
		}
		Config config;
		if(locale == null)
		{
			config = langMap.get(defaultLang);
		} else
		{
			config = langMap.containsKey(locale) ? langMap.get(locale) : langMap.get(defaultLang);
		}
		
		String editString = config != null ? config.getString(value) : defaultValue;
		editString = editString != null ? editString : defaultValue;
		
		final List<String> keys = replacerMap.keySet().stream().toList();
		final List<String> values = replacerMap.values().stream().toList();
		
		for(int i = 0; i < keys.size(); i++)
		{
			editString = editString.replace(keys.get(i), values.get(i));
		}
		
		return editString;
		
	}
	
	public synchronized Map<Locale, Config> getAllLangs()
	{
		return langMap;
	}
	
	public synchronized Config getLangByFileName(String name)
	{
		for(Config config : langMap.values())
		{
			if(config.name().equalsIgnoreCase(name))
			{
				return config;
			}
		}
		return null;
	}
	
}
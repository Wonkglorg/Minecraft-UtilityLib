package com.wonkglorg.utilitylib.lang;

import com.wonkglorg.utilitylib.config.Config;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

/**
 * The type Lang manager.
 */
public class LangManager
{
	private final List<Lang> langList = new ArrayList<>();
	
	/**
	 * Add lang config file name: en-lang.
	 *
	 * @param lang the lang
	 */
	public void addLang(Lang lang)
	{
		langList.add(lang);
	}
	
	/**
	 * Load langs.
	 */
	public void loadLangs()
	{
		for(Lang config : langList)
		{
			config.getLangFile().loadConfig();
		}
	}
	
	/**
	 * Save langs.
	 */
	public void saveLangs()
	{
		for(Lang config : langList)
		{
			config.getLangFile().saveConfig();
		}
	}
	
	/**
	 * Gets lang.
	 *
	 * @param name the name
	 * @return the lang
	 */
	public Lang getLang(String name)
	{
		for(Lang config : langList)
		{
			if(config.getLangFile().getName().equalsIgnoreCase(name))
			{
				return config;
			}
		}
		return null;
	}
	
	public Config getLangFile(String name)
	{
		for(Lang config : langList)
		{
			if(config.getLangFile().getName().equalsIgnoreCase(name))
			{
				return config.getLangFile();
			}
		}
		return null;
	}
	
	/**
	 * Select lang config if invalid defaults to english if english not given, throws error
	 *
	 * @param lang the lang
	 * @return the config
	 */
	public Lang selectLang(Locale lang)
	{
		
		for(Lang config : langList)
		{
			if(config.getLangFile().getName().startsWith(lang.getISO3Language()) || config.getLangFile().getName().startsWith(lang.getDisplayLanguage()))
			{
				return config;
			}
		}
		return getLang("en-lang");
	}
}
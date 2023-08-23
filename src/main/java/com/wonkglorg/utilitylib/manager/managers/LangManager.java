package com.wonkglorg.utilitylib.manager.managers;

import com.wonkglorg.utilitylib.base.logger.Logger;
import com.wonkglorg.utilitylib.manager.config.Config;
import com.wonkglorg.utilitylib.manager.config.LangConfig;
import org.apache.commons.lang3.StringUtils;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.NotNull;

import java.io.File;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.Locale;
import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author Wonkglorg
 */
@SuppressWarnings("unused")
public final class LangManager implements Manager{
	private final Map<Locale, LangConfig> langMap = new ConcurrentHashMap<>();
	//used when defined within code
	private final Map<String, String> replacerMap = new ConcurrentHashMap<>();
	//used when defining values from yml split was made to easier requeu the external values
	private Locale defaultLang;
	private boolean loaded = false;
	private final JavaPlugin plugin;
	private boolean isLoaded = false;
	private final com.wonkglorg.utilitylib.manager.managers.ConfigManager configManager;
	
	public LangManager(JavaPlugin plugin, ConfigManager configManager) {
		this.plugin = plugin;
		this.configManager = configManager;
	}
	
	public void replace(String replace, String with) {
		replacerMap.put(replace, with);
	}
	
	public synchronized void setDefaultLang(Locale defaultLang, LangConfig defaultConfig) {
		configManager.add(defaultConfig);
		
		langMap.put(defaultLang, defaultConfig);
		this.defaultLang = defaultLang;
		defaultConfig.silentLoad();
	}
	
	public synchronized void addLanguage(Locale locale, LangConfig languageConfig) {
		if(langMap.containsKey(locale)){
			return;
		}
		configManager.add(languageConfig);
		langMap.putIfAbsent(locale, languageConfig);
		languageConfig.silentLoad();
	}
	
	public synchronized void save() {
		langMap.values().forEach(Config::save);
	}
	
	public synchronized void silentSave() {
		langMap.values().forEach(Config::silentSave);
	}
	
	public synchronized void load() {
		langMap.values().forEach(Config::silentLoad);
		
	}
	
	public synchronized void silentLoad() {
		langMap.values().forEach(Config::silentLoad);
		
		if(defaultLang == null){
			Logger.logWarn("No default language selected");
		}
	}
	
	public synchronized Config getDefaultLang() {
		try{
			return langMap.get(defaultLang);
		} catch(Exception e){
			return null;
		}
	}
	
	@Override
	public void onShutdown() {
		//save();
	}
	
	@Override
	public void onStartup() {
		if(isLoaded){
			return;
		}
		isLoaded = true;
		if(defaultLang == null){
			Logger.logWarn(plugin, "No default language selected");
		}
	}
	
	public synchronized void addAllLangFilesFromPath(String... paths) {
		if(paths.length == 0){
			return;
		}
		String first = paths[0];
		String[] more = Arrays.copyOfRange(paths, 1, paths.length);
		Path path = Path.of(first, more);
		addAllLangFilesFromPath(path);
	}
	
	public synchronized void addAllLangFilesFromPath(Path path) {
		//Add dictionary to get a wider range of possible yml naming for langs
		
		File[] files = Path.of(plugin.getDataFolder().getPath(), path.toString()).toFile().listFiles();
		if(files == null){
			Logger.logWarn("No available language files loaded");
			return;
		}
		for(File file : files){
			if(!file.isFile()){
				continue;
			}
			if(!file.getName().endsWith(".yml")){
				continue;
			}
			for(Locale locale : Locale.getAvailableLocales()){
				if(locale.getLanguage().equalsIgnoreCase(file.getName())){
					Logger.log(plugin, locale.getLanguage() + " has been loaded!");
					LangConfig config = new LangConfig(plugin, file.toPath());
					addLanguage(locale, config);
					
				}
			}
		}
	}
	
	//add method to also add all from resources with defining their path? is that possible?
	
	public String getValue(String value) {
		return getValue(null, value, value);
	}
	
	public String getValue(Player player, String value) {
		return getValue(player.locale(), value);
	}
	
	public String getValue(@NotNull final Locale locale, @NotNull final String value) {
		return getValue(locale, value, value);
	}
	
	public String getValue(final Locale locale, @NotNull final String value, @NotNull final String defaultValue) {
		if(!loaded){
			loaded = true;
			load();
		}
		LangConfig config;
		if(locale == null){
			config = langMap.get(defaultLang);
		} else {
			config = langMap.containsKey(locale) ? langMap.get(locale) : langMap.get(defaultLang);
		}
		
		if(config == null){
			Logger.logFatal("No lang file could be loaded!");
			return defaultValue;
		}
		
		String editString = config.getString(value);
		if(editString == null){
			return defaultValue;
		}
		
		for(Entry<String, String> mapValue : replacerMap.entrySet()){
			editString = StringUtils.replace(editString, mapValue.getKey(), mapValue.getValue());
		}
		
		if(config.isUpdateRequest()){
			config.updateReplacerMap();
			
		}
		
		for(Entry<String, String> mapValue : config.getReplacerMap().entrySet()){
			editString = StringUtils.replace(editString, mapValue.getKey(), mapValue.getValue());
		}
		
		return editString;
		
	}
	
	public synchronized Map<Locale, LangConfig> getAllLangs() {
		return langMap;
	}
	
	public synchronized LangConfig getLangByFileName(String name) {
		for(LangConfig config : langMap.values()){
			if(config.name().equalsIgnoreCase(name)){
				return config;
			}
		}
		return null;
	}
	
}
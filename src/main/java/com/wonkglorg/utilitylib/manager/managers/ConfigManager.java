package com.wonkglorg.utilitylib.manager.managers;

import com.wonkglorg.utilitylib.base.logger.Logger;
import com.wonkglorg.utilitylib.manager.config.Config;
import com.wonkglorg.utilitylib.manager.config.ConfigYML;
import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.NotNull;

import java.io.File;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@SuppressWarnings("unused")
public final class ConfigManager implements Manager{
	private final JavaPlugin plugin;
	private boolean isStarted;
	
	public ConfigManager(JavaPlugin plugin) {
		this.plugin = plugin;
	}
	
	private final Collection<Config> configs = new ArrayList<>();
	
	public synchronized void add(@NotNull Config config) {
		configs.add(config);
		config.silentLoad();
	}
	
	public synchronized void add(@NotNull Config... config) {
		configs.addAll(List.of(config));
		Arrays.stream(config).toList().forEach(Config::silentLoad);
	}
	
	public synchronized void add(@NotNull Collection<Config> config) {
		configs.addAll(config);
		config.forEach(Config::silentLoad);
	}
	
	public synchronized void load() {
		configs.forEach(Config::load);
	}
	
	public synchronized void silentLoad() {
		configs.forEach(Config::silentLoad);
	}
	
	public synchronized void save() {
		configs.forEach(Config::save);
	}
	
	public synchronized void silentSave() {
		configs.forEach(Config::silentSave);
	}
	
	/**
	 * Can cause issues if you have multiple configs with the same name not recommended
	 * @param name
	 * @return
	 */
	public synchronized Config getConfigByName(String name) {
		for(Config config : configs){
			if(config.name().equalsIgnoreCase(name)){
				return config;
			}
		}
		return null;
	}
	
	/**
	 * Gets a congig by its path
	 * @param path
	 * @return
	 */
	public synchronized Config getConfig(String path) {
		for(Config config : configs){
			if(config.path().equalsIgnoreCase(path)){
				return config;
			}
		}
		return null;
	}
	
	@Override
	public void onShutdown() {
		if(!configs.isEmpty()){
			silentSave();
			Logger.log(plugin, "Saved " + configs.size() + " configs!");
		}
	}
	
	@Override
	public void onStartup() {
		if(isStarted){
			return;
		}
		isStarted = true;
		if(!configs.isEmpty()){
			Logger.log(plugin, "Loaded " + configs.size() + " configs!");
		}
	}
	
	public synchronized Map<String, Config> addAllConfigsFromPath(String... paths) {
		if(paths.length == 0){
			return null;
		}
		String first = paths[0];
		String[] more = Arrays.copyOfRange(paths, 1, paths.length);
		Path path = Path.of(first, more);
		return addAllConfigsFromPath(path);
	}
	
	public synchronized Map<String, Config> addAllConfigsFromPath(Path path) {
		File[] files = Path.of(plugin.getDataFolder().getPath(), path.toString()).toFile().listFiles();
		Map<String, Config> tempConfigs = new HashMap<>();
		if(files == null){
			return null;
		}
		for(File file : files){
			if(!file.isFile()){
				continue;
			}
			if(!file.getName().endsWith(".yml")){
				continue;
			}
			Config config = new ConfigYML(plugin, file.toPath());
			add(config);
			tempConfigs.put(file.getName(), config);
		}
		
		return tempConfigs;
	}
	
	public Collection<Config> getConfigs() {
		return configs;
	}
}
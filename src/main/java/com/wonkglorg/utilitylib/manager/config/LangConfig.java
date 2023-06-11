package com.wonkglorg.utilitylib.manager.config;

import com.wonkglorg.utilitylib.base.logger.Logger;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class LangConfig extends ConfigYML{
	
	private String placeholderPath = "placeholders";
	private boolean updateRequest = false;
	
	private final Map<String, String> replacerMap = new ConcurrentHashMap<>();
	//private Map<String, String> sortedMap = new HashMap<>();
	
	public LangConfig(@NotNull JavaPlugin plugin, @NotNull Path sourcePath, @NotNull Path destinationPath) {
		super(plugin, sourcePath, destinationPath);
	}
	
	public LangConfig(@NotNull JavaPlugin plugin, @NotNull String name) {
		super(plugin, name);
	}
	
	public LangConfig(@NotNull JavaPlugin plugin, @NotNull Path path) {
		super(plugin, path);
	}
	
	@Override
	public void load() {
		setUpdateRequest(true);
		checkFile();
		try{
			load(FILE);
			Logger.log(PLUGIN, "Loaded data from " + NAME + "!");
		} catch(InvalidConfigurationException | IOException e){
			Logger.logFatal(PLUGIN, e.getMessage());
			Logger.logWarn(PLUGIN, "Error loading data from " + NAME + "!");
		}
	}
	
	public void silentLoad() {
		setUpdateRequest(true);
		checkFile();
		try{
			load(FILE);
		} catch(InvalidConfigurationException | IOException e){
			Logger.logFatal(PLUGIN, e.getMessage());
			Logger.logWarn(PLUGIN, "Error loading data from " + NAME + "!");
		}
	}
	
	public void updateReplacerMap() {
		if(this.isSet(this.getPlaceholderPath())){
			String path = this.getPlaceholderPath();
			for(String placeholderKey : this.getSection(path, false)){
				String placeholderValue = this.getString(path + "." + placeholderKey);
				String searchKey = "%" + placeholderKey + "%";
				replacerMap.put(searchKey, placeholderValue);
			}
		}
		
		//sortedMap = topologicalSort(this.getReplacerMap());
		setUpdateRequest(false);
	}
	
	public Map<String, String> getReplacerMap() {
		return replacerMap;
	}
	
	public void setPlaceholderPath(String placeholderString) {
		this.placeholderPath = placeholderString;
	}
	
	public String getPlaceholderPath() {
		return placeholderPath;
	}
	
	public boolean isUpdateRequest() {
		return updateRequest;
	}
	
	public void setUpdateRequest(boolean updateRequest) {
		this.updateRequest = updateRequest;
	}
	
	/*
	public Map<String, String> getSortedMap() {
		return sortedMap;
	}
		UNUSED TOPOLOGIC MATCHING SORT
	
	private Map<String, String> topologicalSort(Map<String, String> map) {
		Map<String, String> sortedMap = new LinkedHashMap<>();
		Set<String> visited = new HashSet<>();
		
		for(Entry<String, String> entry : map.entrySet()){
			String key = entry.getKey();
			if(!visited.contains(key)){
				visitNode(key, map, visited, sortedMap);
			}
		}
		
		return sortedMap;
	}
	
	Pattern pattern = Pattern.compile("%(.*?)%");
	
	private void visitNode(String key, Map<String, String> map, Set<String> visited, Map<String, String> sortedMap) {
		visited.add(key);
		String value = map.get(key);
		
		Matcher matcher = pattern.matcher(value);
		
		while(matcher.find()){
			String dependency = matcher.group(1);
			if(map.containsKey(dependency) && !visited.contains(dependency)){
				visitNode(dependency, map, visited, sortedMap);
			}
		}
		
		sortedMap.put(key, value);
	}
	*/
	
	
}
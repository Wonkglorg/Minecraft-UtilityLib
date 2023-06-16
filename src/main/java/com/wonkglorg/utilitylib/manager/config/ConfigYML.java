package com.wonkglorg.utilitylib.manager.config;

import com.wonkglorg.utilitylib.base.logger.Logger;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashSet;
import java.util.Set;

/**
 * @author Wonkglorg
 */
@SuppressWarnings("unused")
public class ConfigYML extends YamlConfiguration implements Config{
	
	protected final JavaPlugin PLUGIN;
	protected final String NAME;
	protected final Path SOURCE_PATH;
	protected final Path DESTINATION_PATH;
	protected final File FILE;
	
	/**
	 * Creates a new file at the specified location or copies an existing one from the resource folder based on the sourcePath,
	 * if nothing could be found in the sourcePath it creates a new one. DestinationPath will automatically point to the base server folder
	 * NOT your datafolder. Use "plugin.getDataFolder()" to get your default plugins data folder.
	 *
	 * @param plugin
	 * @param sourcePath path inside the resources folder of your plugin
	 * @param destinationPath path to copy this file to
	 */
	public ConfigYML(@NotNull JavaPlugin plugin, @NotNull Path sourcePath, @NotNull Path destinationPath) {
		this.PLUGIN = plugin;
		this.NAME = destinationPath.getFileName().toString();
		this.SOURCE_PATH = sourcePath;
		this.DESTINATION_PATH = destinationPath;
		FILE = new File(this.DESTINATION_PATH.toString());
	}
	
	/**
	 * Creates a new file at the specified location or copies an existing one from the resource folder based on the name,
	 * if nothing could be found in the resource folder it creates a new one. name will automatically point to the base of the plugin data folder
	 *
	 * @param plugin
	 * @param name Both the name for destination and source
	 */
	public ConfigYML(@NotNull JavaPlugin plugin, @NotNull String name) {
		this(plugin, Path.of(name), Path.of(plugin.getDataFolder().getPath(), name));
	}
	
	/**
	 * Creates a new file at the specified location or copies an existing one from the resource folder based on the path,
	 * if nothing could be found in the resource folder it creates a new one. path will automatically point to the base of the plugin data folder
	 *
	 * @param plugin
	 * @param path both the source and destination path
	 */
	public ConfigYML(@NotNull JavaPlugin plugin, @NotNull Path path) {
		this(plugin, path, Path.of(plugin.getDataFolder().getPath(), path.toString()));
	}
	
	//make version where copy and destination are the same if you just enter 1 path
	
	/**
	 * Gets a section of the config at the set path.
	 *
	 * @param path path inside yml config.
	 * @param deep deep search to get children of children
	 * @return {@link Set} of results.
	 */
	public Set<String> getSection(@NotNull String path, boolean deep) {
		
		ConfigurationSection section = getConfigurationSection(path);
		if(section != null){
			return section.getKeys(deep);
		}
		return new HashSet<>();
	}
	
	public @Nullable String getParentPath(@NotNull String path) {
		ConfigurationSection configurationSection = getConfigurationSection(path).getParent();
		if(configurationSection == null){
			return null;
		}
		return configurationSection.getCurrentPath();
	}
	
	@Override
	public void updateFiles() {
	
	}
	
	public void updateFileValues() {
		YamlConfiguration existingConfig = YamlConfiguration.loadConfiguration(FILE);
		
		ConfigurationSection existingSection = existingConfig.getConfigurationSection("");
		ConfigurationSection newSection = getConfigurationSection("");
		
		if(existingSection != null && newSection != null){
			Set<String> keys = existingSection.getKeys(true);
			
			// Check for missing keys in the new config and add them
			for(String key : keys){
				if(!newSection.contains(key)){
					newSection.set(key, existingSection.get(key));
				}
			}
			
			// Save the updated config to file
			save();
		}
	}
	
	public void load() {
		checkFile();
		try{
			load(FILE);
			Logger.log("Loaded data from " + NAME + "!");
		} catch(InvalidConfigurationException | IOException e){
			Logger.logFatal(PLUGIN, e.getMessage());
			Logger.logWarn("Error loading data from " + NAME + "!");
		}
	}
	
	/**
	 * same as load but with specified plugin for logger message
	 *
	 * @param plugin
	 */
	public void load(JavaPlugin plugin) {
		checkFile();
		try{
			load(FILE);
			Logger.log(plugin, "Loaded data from " + NAME + "!");
		} catch(InvalidConfigurationException | IOException e){
			Logger.logFatal(plugin, e.getMessage());
			Logger.logWarn(plugin, "Error loading data from " + NAME + "!");
		}
	}
	
	public void silentLoad() {
		checkFile();
		try{
			load(FILE);
		} catch(InvalidConfigurationException | IOException e){
			Logger.logFatal(PLUGIN, e.getMessage());
			Logger.logWarn("Error loading data from " + NAME + "!");
		}
	}
	
	public void save() {
		checkFile();
		try{
			
			save(FILE);
			Logger.log("Saved data to " + NAME + "!");
		} catch(IOException e){
			Logger.logFatal(PLUGIN, e.getMessage());
			Logger.logWarn("Error saving data to " + NAME + "!");
		}
	}
	
	public void silentSave() {
		checkFile();
		try{
			save(FILE);
		} catch(IOException e){
			e.printStackTrace();
			Logger.logWarn("Error saving data to " + NAME + "!");
		}
	}
	
	@Override
	public String name() {
		
		return NAME;
	}
	
	@Override
	public String path() {
		return DESTINATION_PATH.toString();
	}
	
	/**
	 * Checks if file exists in path, else create the file and all parent directories needed.
	 */
	protected void checkFile() {
		if(!FILE.exists()){
			FILE.getParentFile().mkdirs();
			InputStream inputStream = PLUGIN.getResource(SOURCE_PATH.toString().replaceAll("\\\\", "/"));
			if(inputStream != null){
				try{
					
					//if something does not work its this!!!!!!!!!!!!
					Files.copy(inputStream, DESTINATION_PATH);
					
				} catch(IOException e){
					Logger.logFatal("Error " + NAME);
					Logger.logWarn("Error Copying data from " + SOURCE_PATH);
					Logger.logWarn("To destination " + DESTINATION_PATH);
				}
			} else {
				boolean ignored = FILE.getParentFile().mkdirs();
				try{
					ignored = FILE.createNewFile();
				} catch(IOException e){
					throw new RuntimeException(e);
				}
			}
		}
		updateFiles();
		
	}
	
	@Override
	public String toString() {
		return String.format("ConfigYML[path=%s,name=%s]", DESTINATION_PATH.toString(), NAME);
	}
	
}
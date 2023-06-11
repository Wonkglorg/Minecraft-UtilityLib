package com.wonkglorg.utilitylib.manager.managers;

import com.wonkglorg.utilitylib.base.logger.Logger;
import com.wonkglorg.utilitylib.manager.database.Database;
import com.wonkglorg.utilitylib.manager.database.Database.DatabaseType;
import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.NotNull;

import javax.annotation.concurrent.ThreadSafe;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@SuppressWarnings("unused")
@ThreadSafe
public class DatabaseManager implements Manager{
	private boolean isLoaded = false;
	private final JavaPlugin plugin;
	private final Map<DatabaseType, Map<String, Database>> databaseTypeMapMap = new ConcurrentHashMap<>();
	
	public DatabaseManager(JavaPlugin plugin) {
		this.plugin = plugin;
	}
	
	public synchronized void add(@NotNull final String name, @NotNull Database database) {
		createDatabaseEntry(database.getDatabaseType());
		databaseTypeMapMap.get(database.getDatabaseType()).put(name, database);
	}
	
	public synchronized void remove(@NotNull final DatabaseType databaseType) {
		databaseTypeMapMap.get(databaseType).values().forEach(Database::disconnect);
		databaseTypeMapMap.remove(databaseType);
	}
	
	public synchronized void remove(@NotNull final DatabaseType databaseType, String name) {
		Map<String, Database> databaseMap = databaseTypeMapMap.get(databaseType);
		if(databaseMap == null){
			return;
		}
		databaseMap.get(name).disconnect();
		databaseMap.remove(name);
	}
	
	public synchronized Database getDatabase(@NotNull final DatabaseType databaseType, String name) {
		createDatabaseEntry(databaseType);
		return databaseTypeMapMap.get(databaseType).get(name);
		
	}
	
	public synchronized void load() {
		for(Map<String, Database> databaseMap : databaseTypeMapMap.values()){
			databaseMap.values().forEach(Database::connect);
		}
	}
	
	public synchronized void unload() {
		for(Map<String, Database> databaseMap : databaseTypeMapMap.values()){
			databaseMap.values().forEach(Database::disconnect);
		}
	}
	
	@Override
	public void onShutdown() {
		databaseTypeMapMap.keySet().forEach(this::remove);
	}
	
	@Override
	public void onStartup() {
		if(isLoaded){
			return;
		}
		isLoaded = true;
		if(!databaseTypeMapMap.isEmpty()){
			for(DatabaseType databaseType : databaseTypeMapMap.keySet()){
				Logger.log(plugin,
						String.format("loading %d tables from %s", databaseTypeMapMap.get(databaseType).values().size(), databaseType.getName()));
			}
		}
		load();
	}
	
	/**
	 * @param key
	 */
	private synchronized void createDatabaseEntry(DatabaseType key) {
		databaseTypeMapMap.putIfAbsent(key, new ConcurrentHashMap<>());
	}
}
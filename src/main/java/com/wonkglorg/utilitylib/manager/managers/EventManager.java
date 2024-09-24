package com.wonkglorg.utilitylib.manager.managers;

import com.wonkglorg.utilitylib.base.logger.Logger;
import org.bukkit.event.Listener;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.NotNull;

import javax.annotation.concurrent.ThreadSafe;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@SuppressWarnings("unused")
@ThreadSafe
public final class EventManager implements Manager{
	private final JavaPlugin plugin;
	private boolean isLoaded = false;
	private final PluginManager pluginManager;
	private final Collection<Listener> listeners;
	
	public EventManager(JavaPlugin plugin) {
		this.plugin = plugin;
		this.pluginManager = plugin.getServer().getPluginManager();
		listeners = new ArrayList<>();
	}
	
	public synchronized void add(@NotNull Listener... listener) {
		listeners.addAll(List.of(listener));
	}
	
	public synchronized void add(@NotNull Collection<Listener> listener) {
		listeners.addAll(listener);
	}
	
	public synchronized void add(@NotNull Listener listener) {
		listeners.add(listener);
	}
	
	public synchronized void load() {
		listeners.forEach(listeners -> pluginManager.registerEvents(listeners, plugin));
	}
	
	@Override
	public void onShutdown() {
	
	}
	
	@Override
	public void onStartup() {
		if(isLoaded){
			return;
		}
		isLoaded = true;
		if(!listeners.isEmpty()){
			Logger.log(plugin, "Loaded " + listeners.size() + " events!");
		}
		load();
	}
}
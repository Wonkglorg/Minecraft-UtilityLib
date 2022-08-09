package com.wonkglorg.utilitylib.event;

import org.bukkit.event.Listener;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;
import java.util.List;
@SuppressWarnings("unused")
public class EventManager
{
	private final JavaPlugin plugin;
	private final PluginManager pluginManager;
	private final List<Listener> listeners;
	
	public EventManager(JavaPlugin plugin)
	{
		this.plugin = plugin;
		this.pluginManager = plugin.getServer().getPluginManager();
		listeners = new ArrayList<>();
	}
	
	public void add(Listener... listener)
	{
		listeners.addAll(List.of(listener));
	}
	
	public void add(List<Listener> listener)
	{
		listeners.addAll(listener);
	}
	
	public void add(Listener listener)
	{
		listeners.add(listener);
	}
	
	public void registerAll()
	{
		for(Listener listener : listeners)
		{
			pluginManager.registerEvents(listener, plugin);
		}
	}
}
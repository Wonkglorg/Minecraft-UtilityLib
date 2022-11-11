package com.wonkglorg.utilitylib.managers;

import org.bukkit.event.Listener;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("unused")
public class EventManager implements Manager
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
	
	public void add(@NotNull Listener... listener)
	{
		listeners.addAll(List.of(listener));
	}
	
	public void add(@NotNull List<Listener> listener)
	{
		listeners.addAll(listener);
	}
	
	public void add(@NotNull Listener listener)
	{
		listeners.add(listener);
	}
	
	
	public void load()
	{
		listeners.forEach(listeners -> pluginManager.registerEvents(listeners, plugin));
	}
	
	@Override
	public void onShutdown()
	{
	
	}
	
	@Override
	public void onStartup()
	{
		load();
	}
}
package com.wonkglorg.utilitylib.listener;

import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

public abstract class EventListener implements Listener
{
	protected final JavaPlugin plugin;
	
	public EventListener(JavaPlugin plugin)
	{
		this.plugin = plugin;
		plugin.getServer().getPluginManager().registerEvents(this, plugin);
	}
	
}
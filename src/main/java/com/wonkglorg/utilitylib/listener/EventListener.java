package com.wonkglorg.utilitylib.listener;

import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

public abstract class EventListener implements Listener
{
	public EventListener(JavaPlugin plugin)
	{
		plugin.getServer().getPluginManager().registerEvents(this, plugin);
	}
	
}
package com.wonkglorg.utilitylib;

import com.wonkglorg.utilitylib.test.JoinListener;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public final class UtilityLib extends JavaPlugin
{
	@Override
	public void onEnable()
	{
		PluginManager manager = getServer().getPluginManager();
		manager.registerEvents(new JoinListener(),this);
	}
	
	@Override
	public void onDisable()
	{
		// Plugin shutdown logic
	}
}

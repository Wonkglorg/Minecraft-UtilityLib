package com.wonkglorg.utilitylib;

import com.wonkglorg.utilitylib.managers.PluginManager;
import org.bukkit.Bukkit;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

@SuppressWarnings("unused")
public abstract class UtilityPlugin extends JavaPlugin
{
	protected static PluginManager manager;
	
	public UtilityPlugin()
	{
		manager = new PluginManager(this);
	}
	
	/**
	 * Executes on plugin startup
	 */
	public abstract void pluginStartup();
	
	/**
	 * Executes on plugin shutdown
	 */
	public abstract void pluginShutdown();
	
	@Override
	public void onEnable()
	{
		pluginStartup();
		manager.onStartup();
	}
	
	@Override
	public void onDisable()
	{
		pluginShutdown();
		manager.onShutdown();
	}
	
	public static PluginManager getManager()
	{
		return manager;
	}
	
	/**
	 * Checks if a specific plugin dependency with a given name exists
	 *
	 * @param pluginName plugin name
	 *
	 * @return
	 */
	public boolean dependencyExists(String pluginName)
	{
		String corePlug = null;
		Plugin[] plugins = Bukkit.getPluginManager().getPlugins();
		for(Plugin testPlug : plugins)
		{
			if(testPlug.getName().contains(pluginName))
			{
				corePlug = testPlug.getName();
				break;
			}
		}
		if(corePlug == null)
		{
			return false;
		}
		Plugin corePlugin = Bukkit.getPluginManager().getPlugin(corePlug);
		return corePlugin != null;
	}
	
}
package com.wonkglorg.utilitylib;

import com.wonkglorg.utilitylib.managers.PluginManager;
import org.bukkit.Bukkit;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

@SuppressWarnings("unused")
public abstract class UtilityPlugin extends JavaPlugin implements PluginInterface
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
	
	/**
	 * add all events in this field using manager.add();
	 */
	public abstract void event();
	
	/**
	 * add all commands in this field using manager.add();
	 */
	public abstract void command();
	
	/**
	 * add all configs in this field using manager.add();
	 */
	public abstract void config();
	
	/**
	 * add all langs in this field using manager.add();
	 */
	public abstract void lang();
	
	/**
	 * add all recipes in this field using manager.add();
	 */
	public abstract void recipe();
	
	/**
	 * add all enchants in this field using manager.add();
	 */
	public abstract void enchant();
	
	/**
	 * loads contents before anything else in the plugin, no configs are loaded yet
	 */
	public abstract void loadBefore();
	
	@Override
	public void onEnable()
	{
		loadBefore();
		langs();
		manager.getLangManager().onStartup();
		configs();
		manager.getConfigManager().onStartup();
		events();
		manager.getEventManager().onStartup();
		commands();
		manager.getCommandManager().onStartup();
		recipes();
		manager.getRecipeManager().onStartup();
		enchants();
		manager.getEnchantManager().onStartup();
	}
	
	@Override
	public void onDisable()
	{
		pluginShutdown();
		manager.onShutdown();
	}
	
	@Override
	public void events()
	{
		event();
	}
	
	@Override
	public void commands()
	{
		command();
	}
	
	@Override
	public void configs()
	{
		config();
	}
	
	@Override
	public void langs()
	{
		lang();
	}
	
	@Override
	public void recipes()
	{
		recipe();
	}
	
	@Override
	public void enchants()
	{
		enchant();
	}
	
	public static PluginManager getManager()
	{
		return manager;
	}
	
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
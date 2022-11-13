package com.wonkglorg.utilitylib;

import com.wonkglorg.utilitylib.managers.PluginManager;
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
		configs();
		events();
		commands();
		recipes();
		enchants();
		
		manager.onStartup();
		pluginStartup();
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
}
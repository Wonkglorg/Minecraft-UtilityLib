package com.wonkglorg.utilitylib;

import com.wonkglorg.utilitylib.managers.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public abstract class WonkyPlugin extends JavaPlugin implements PluginInterface
{
	protected final PluginManager manager = new PluginManager(this);
	
	public abstract void pluginEnable();
	
	public abstract void pluginDisable();
	
	public abstract void event();
	
	public abstract void command();
	
	public abstract void config();
	
	public abstract void lang();
	
	public abstract void recipe();
	
	@Override
	public void onEnable()
	{
		langs();
		configs();
		events();
		commands();
		recipes();
		
		manager.onStartup();
		pluginEnable();
	}
	
	@Override
	public void onDisable()
	{
		pluginDisable();
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
	
	public PluginManager getManager()
	{
		return manager;
	}
}
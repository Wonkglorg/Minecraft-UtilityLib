package com.wonkglorg.utilitylib;

import com.wonkglorg.utilitylib.managers.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public abstract class WonkyPlugin extends JavaPlugin implements PluginInterface
{
	protected final PluginManager manager = new PluginManager(this);
	
	abstract void pluginEnable();
	
	abstract void pluginDisable();
	
	abstract void event();
	
	abstract void command();
	
	abstract void config();
	
	abstract void lang();
	
	abstract void recipe();
	
	@Override
	public void onEnable()
	{
		events();
		commands();
		configs();
		langs();
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
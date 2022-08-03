package com.wonkglorg.utilitylib.abstraction;

import com.wonkglorg.utilitylib.command.Command;
import com.wonkglorg.utilitylib.command.CommandManager;
import com.wonkglorg.utilitylib.config.Config;
import com.wonkglorg.utilitylib.config.ConfigManager;
import com.wonkglorg.utilitylib.event.EventManager;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.NotNull;

import java.util.List;

/**
 * The type Plugin manager.
 */
public abstract class PluginManager
{
	/**
	 * The Config manager.
	 */
	protected final ConfigManager configManager;
	/**
	 * The Command manager.
	 */
	protected final CommandManager commandManager;
	/**
	 * The Event manager.
	 */
	protected final EventManager eventManager;
	
	/**
	 * Instantiates a new Plugin manager.
	 *
	 * @param plugin the plugin
	 */
	public PluginManager(@NotNull JavaPlugin plugin)
	{
		commandManager = new CommandManager();
		configManager = new ConfigManager();
		eventManager = new EventManager(plugin);
		
		eventManager.add(events());
		commandManager.add(commands());
		configManager.add(configs());
	}
	
	/**
	 * Events list.
	 *
	 * @return list of listeners
	 */
	protected abstract List<Listener> events();
	
	/**
	 * Register all commands
	 *
	 * @return list of commands
	 */
	protected abstract List<Command> commands();
	
	/**
	 * Register all configs
	 *
	 * @return list of configs
	 */
	protected abstract List<Config> configs();
	
	/**
	 * Gets config manager.
	 *
	 * @return the config manager
	 */
	public ConfigManager getConfigManager()
	{
		return configManager;
	}
	
	/**
	 * Gets command manager.
	 *
	 * @return the command manager
	 */
	public CommandManager getCommandManager()
	{
		return commandManager;
	}
	
	/**
	 * Gets event manager.
	 *
	 * @return the event manager
	 */
	public EventManager getEventManager()
	{
		return eventManager;
	}
}
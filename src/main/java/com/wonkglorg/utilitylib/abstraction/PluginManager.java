package com.wonkglorg.utilitylib.abstraction;

import com.wonkglorg.utilitylib.command.Command;
import com.wonkglorg.utilitylib.command.CommandManager;
import com.wonkglorg.utilitylib.config.Config;
import com.wonkglorg.utilitylib.config.ConfigManager;
import com.wonkglorg.utilitylib.customrecipe.RecipeManager;
import com.wonkglorg.utilitylib.event.EventManager;
import org.bukkit.event.Listener;
import org.bukkit.inventory.Recipe;
import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.NotNull;

import java.util.List;

/**
 * The type Plugin manager.
 */
@SuppressWarnings("unused")
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
	
	protected final RecipeManager recipeManager;
	
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
		recipeManager = new RecipeManager();
		
		eventManager.add(events());
		commandManager.add(commands());
		configManager.add(configs());
		recipeManager.add(recipes());
	}
	
	public void initializeAll(){
		eventManager.initialize();
		recipeManager.initialize();
		configManager.load();
	}
	
	/**
	 * Add listener events to ht list  be added in the {@link EventManager} and automatically assigned.
	 *
	 * @return list of listeners
	 */
	protected abstract List<Listener> events();
	
	/**
	 * Add all commands to be registered in the {@link CommandManager}.
	 *
	 * @return list of commands
	 */
	protected abstract List<Command> commands();
	
	/**
	 * Add all configs to be assigned and handled by the {@link ConfigManager}
	 *
	 * @return list of configs
	 */
	protected abstract List<Config> configs();
	
	protected abstract List<Recipe> recipes();
	/**
	 * Gets {@link ConfigManager}.
	 *
	 * @return the config manager
	 */
	public ConfigManager getConfigManager()
	{
		return configManager;
	}
	
	/**
	 * Gets {@link CommandManager}.
	 *
	 * @return the command manager
	 */
	public CommandManager getCommandManager()
	{
		return commandManager;
	}
	
	/**
	 * Gets {@link EventManager}.
	 *
	 * @return the event manager
	 */
	public EventManager getEventManager()
	{
		return eventManager;
	}
}
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

/**
 * The type Plugin manager.
 */
@SuppressWarnings("unused")
public class PluginManager
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
	}
	
	public void initializeAll()
	{
		configManager.load();
		eventManager.registerAll();
		recipeManager.registerAll();
	}
	
	public void addEvent(Listener listener)
	{
		eventManager.add(listener);
	}
	
	public void addCommand(Command command)
	{
		commandManager.add(command);
	}
	
	public void addConfig(Config config)
	{
		configManager.add(config);
	}
	
	public void addRecipe(Recipe recipe)
	{
		recipeManager.add(recipe);
	}
	
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
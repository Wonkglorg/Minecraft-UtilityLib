package com.wonkglorg.utilitylib.managers;

import com.wonkglorg.utilitylib.command.Command;
import com.wonkglorg.utilitylib.config.ConfigYML;
import com.wonkglorg.utilitylib.utils.logger.Logger;
import org.bukkit.event.Listener;
import org.bukkit.inventory.Recipe;
import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.NotNull;

import java.util.Locale;

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
	
	protected final LangManager langManager;
	
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
		langManager = new LangManager();
	}
	
	public void registerAll()
	{
		configManager.load();
		if(langManager.getDefaultLang()== null){
			Logger.logWarn("Default lang has not been set!");
		}
		langManager.load();
		eventManager.registerAll();
		recipeManager.registerAll();
	}
	
	public void registerEvents()
	{
		eventManager.registerAll();
	}
	
	public void registerConfig()
	{
		configManager.load();
	}
	
	public void registerRecipes()
	{
		recipeManager.registerAll();
	}
	
	public void registerLangs()
	{
		langManager.load();
	}
	
	public void addEvent(Listener listener)
	{
		eventManager.add(listener);
	}
	
	public void addCommand(Command command)
	{
		commandManager.add(command);
	}
	
	public void addConfig(ConfigYML config)
	{
		configManager.add(config);
	}
	
	public void addRecipe(Recipe recipe)
	{
		recipeManager.add(recipe);
	}
	
	public void addLang(Locale locale, ConfigYML config)
	{
		langManager.addLanguage(locale, config);
	}
	
	public void setDefaultLang(Locale locale, ConfigYML config)
	{
		langManager.setDefaultLang(locale, config);
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
	
	/**
	 * Gets {@link LangManager}.
	 *
	 * @return the lang manager
	 */
	public LangManager getLangManager()
	{
		return langManager;
	}
}
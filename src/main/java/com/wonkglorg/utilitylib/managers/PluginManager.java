package com.wonkglorg.utilitylib.managers;

import com.wonkglorg.utilitylib.command.Command;
import com.wonkglorg.utilitylib.config.Config;
import com.wonkglorg.utilitylib.logger.Logger;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.event.Listener;
import org.bukkit.inventory.Recipe;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

/**
 * The type Plugin manager.
 */
@SuppressWarnings("unused")
public class PluginManager
{
	
	protected final Map<ManagerValues, Manager> managerMap = new HashMap<>();
	protected final JavaPlugin plugin;
	
	/**
	 * PluginManager is designed to make handling and using different types of common classes like configs, commands,events,languages,recipes easier
	 * once all classes are added call onStartup and onShutdown respectively, otherwise things might break
	 *
	 * @param plugin the plugin
	 */
	public PluginManager(@NotNull JavaPlugin plugin)
	{
		this.plugin = plugin;
		
		managerMap.put(ManagerValues.RECIPE, new RecipeManager(plugin));
		managerMap.put(ManagerValues.LANG, new LangManager(plugin));
		managerMap.put(ManagerValues.CONFIG, new ConfigManager(plugin));
		managerMap.put(ManagerValues.ENCHANT, new EnchantmentManager(plugin));
		managerMap.put(ManagerValues.COMMAND, new CommandManager(plugin));
		managerMap.put(ManagerValues.EVENT, new EventManager(plugin));
		
	}
	
	public void onStartup()
	{
		managerMap.values().forEach(Manager::onStartup);
	}
	
	public void onShutdown()
	{
		managerMap.values().forEach(Manager::onShutdown);
	}
	
	public void add(Listener... listener)
	{
		getEventManager().add(listener);
	}
	
	public void add(Command... command)
	{
		getCommandManager().add(command);
	}
	
	public void add(Config... config)
	{
		getConfigManager().add(config);
	}
	
	public void add(Recipe... recipe)
	{
		getRecipeManager().add(recipe);
	}
	
	public void add(Enchantment... enchantment)
	{
		getEnchantManager().add(enchantment);
	}
	
	/**
	 * Check whether the plugin is enabled and exists!
	 *
	 * @param targetPlugin the plugin to check for
	 * @param shutdown if enabled shutdown plugin on error
	 * @return is plugin existing
	 */
	public boolean checkDepend(Plugin targetPlugin, boolean shutdown)
	{
		return checkDepend(targetPlugin.getName(), shutdown);
	}
	
	/**
	 * Check whether the plugin is enabled and exists!
	 *
	 * @param targetPlugin the plugin to check for
	 * @param shutdown if enabled shutdown plugin on error
	 * @return is plugin existing
	 */
	public boolean checkDepend(String targetPlugin, boolean shutdown)
	{
		Plugin pluginToCheck = plugin.getServer().getPluginManager().getPlugin(targetPlugin);
		if(pluginToCheck == null || !pluginToCheck.isEnabled())
		{
			if(shutdown)
			{
				plugin.getServer().getPluginManager().disablePlugin(plugin);
				return false;
			}
			Logger.logWarn(plugin, "Dependency: " + targetPlugin + " could not be enabled");
			return false;
		}
		Logger.log(plugin, "Dependency: " + targetPlugin + " loaded successfully!");
		return true;
	}
	
	public void addLang(Locale locale, Config config)
	{
		getLangManager().addLanguage(locale, config);
	}
	
	public void addDefaultLang(Locale locale, Config config)
	{
		getLangManager().setDefaultLang(locale, config);
	}
	
	/**
	 * Gets {@link ConfigManager}.
	 *
	 * @return the config manager
	 */
	public ConfigManager getConfigManager()
	{
		return (ConfigManager) managerMap.get(ManagerValues.CONFIG);
	}
	
	/**
	 * Gets {@link CommandManager}.
	 *
	 * @return the command manager
	 */
	public CommandManager getCommandManager()
	{
		return (CommandManager) managerMap.get(ManagerValues.COMMAND);
	}
	
	/**
	 * Gets {@link EventManager}.
	 *
	 * @return the event manager
	 */
	public EventManager getEventManager()
	{
		return (EventManager) managerMap.get(ManagerValues.EVENT);
	}
	
	/**
	 * Gets {@link LangManager}.
	 *
	 * @return the lang manager
	 */
	public LangManager getLangManager()
	{
		return (LangManager) managerMap.get(ManagerValues.LANG);
	}
	
	public RecipeManager getRecipeManager()
	{
		return (RecipeManager) managerMap.get(ManagerValues.RECIPE);
	}
	
	public EnchantmentManager getEnchantManager()
	{
		return (EnchantmentManager) managerMap.get(ManagerValues.ENCHANT);
	}
}
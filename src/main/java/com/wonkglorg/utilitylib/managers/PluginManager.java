package com.wonkglorg.utilitylib.managers;

import com.wonkglorg.utilitylib.command.Command;
import com.wonkglorg.utilitylib.config.Config;
import com.wonkglorg.utilitylib.logger.Logger;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.event.Listener;
import org.bukkit.inventory.Recipe;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.util.Consumer;
import org.jetbrains.annotations.NotNull;

import javax.annotation.concurrent.ThreadSafe;
import java.util.Locale;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

/**
 * The type Plugin manager.
 */
@SuppressWarnings("unused")
@ThreadSafe
public final class PluginManager
{
	
	private final Map<ManagerValues, Manager> managerMap = new ConcurrentHashMap<>();
	private final JavaPlugin plugin;
	
	/**
	 * PluginManager is designed to make handling and using different types of common classes like configs, commands,events,languages,recipes easier
	 * once all classes are added call onStartup and onShutdown respectively, otherwise things might break
	 *
	 * @param plugin the plugin
	 */
	public PluginManager(@NotNull JavaPlugin plugin)
	{
		this.plugin = plugin;
	}
	
	/**
	 * Adds a new cooldownManager.
	 *
	 * @param producer action to perform on shutdown for example saving them to a config file
	 * @param consumer action to perform on startup for example loading it from a config file
	 */
	public void addCooldownManager(Consumer<Map<String, Map<UUID, Long>>> producer, Consumer<Map<String, Map<UUID, Long>>> consumer)
	{
		managerMap.put(ManagerValues.COOLDOWN, new CooldownManager(producer, consumer));
	}
	
	public synchronized void onStartup()
	{
		managerMap.values().forEach(Manager::onStartup);
	}
	
	public synchronized void onShutdown()
	{
		managerMap.values().forEach(Manager::onShutdown);
	}
	
	public synchronized void add(Listener... listener)
	{
		getEventManager().add(listener);
	}
	
	public synchronized void add(Command... command)
	{
		getCommandManager().add(command);
	}
	
	public synchronized void add(Config... config)
	{
		getConfigManager().add(config);
	}
	
	public synchronized void add(Recipe... recipe)
	{
		getRecipeManager().add(recipe);
	}
	
	public synchronized void add(Enchantment... enchantment)
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
	public synchronized boolean checkDepend(Plugin targetPlugin, boolean shutdown)
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
	public synchronized boolean checkDepend(String targetPlugin, boolean shutdown)
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
	
	public synchronized void addLang(Locale locale, Config config)
	{
		getLangManager().addLanguage(locale, config);
	}
	
	public synchronized void addDefaultLang(Locale locale, Config config)
	{
		getLangManager().setDefaultLang(locale, config);
	}
	
	/**
	 * Gets {@link ConfigManager}.
	 *
	 * @return the config manager
	 */
	public synchronized ConfigManager getConfigManager()
	{
		if(!managerMap.containsKey(ManagerValues.CONFIG))
		{
			managerMap.put(ManagerValues.CONFIG, new ConfigManager(plugin));
		}
		return (ConfigManager) managerMap.get(ManagerValues.CONFIG);
	}
	
	/**
	 * Gets {@link CommandManager}.
	 *
	 * @return the command manager
	 */
	public synchronized CommandManager getCommandManager()
	{
		if(!managerMap.containsKey(ManagerValues.COMMAND))
		{
			managerMap.put(ManagerValues.COMMAND, new CommandManager(plugin));
		}
		return (CommandManager) managerMap.get(ManagerValues.COMMAND);
	}
	
	/**
	 * Gets {@link EventManager}.
	 *
	 * @return the event manager
	 */
	public synchronized EventManager getEventManager()
	{
		if(!managerMap.containsKey(ManagerValues.EVENT))
		{
			managerMap.put(ManagerValues.EVENT, new EventManager(plugin));
		}
		return (EventManager) managerMap.get(ManagerValues.EVENT);
	}
	
	/**
	 * Gets {@link LangManager}.
	 *
	 * @return the lang manager
	 */
	public synchronized LangManager getLangManager()
	{
		if(!managerMap.containsKey(ManagerValues.LANG))
		{
			managerMap.put(ManagerValues.LANG, new LangManager(plugin));
		}
		return (LangManager) managerMap.get(ManagerValues.LANG);
	}
	
	public synchronized RecipeManager getRecipeManager()
	{
		if(!managerMap.containsKey(ManagerValues.RECIPE))
		{
			managerMap.put(ManagerValues.RECIPE, new RecipeManager(plugin));
		}
		return (RecipeManager) managerMap.get(ManagerValues.RECIPE);
	}
	
	public synchronized EnchantmentManager getEnchantManager()
	{
		
		if(!managerMap.containsKey(ManagerValues.ENCHANT))
		{
			managerMap.put(ManagerValues.ENCHANT, new EnchantmentManager(plugin));
		}
		EnchantmentManager enchantmentManager = (EnchantmentManager) managerMap.get(ManagerValues.ENCHANT);
		if(enchantmentManager == null)
		{
			return null;
		}
		enchantmentManager.onStartup();
		return enchantmentManager;
	}
	
	public enum ManagerValues
	{
		CONFIG(),
		LANG(),
		COMMAND(),
		ENCHANT(),
		RECIPE(),
		EVENT(),
		COOLDOWN(),
		;
	}
}
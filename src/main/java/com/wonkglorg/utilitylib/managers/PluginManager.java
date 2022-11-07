package com.wonkglorg.utilitylib.managers;

import com.wonkglorg.utilitylib.command.Command;
import com.wonkglorg.utilitylib.config.Config;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.event.Listener;
import org.bukkit.inventory.Recipe;
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
	
	/**
	 * PluginManager is designed to make handling and using different types of common classes like configs, commands,events,languages,recipes easier
	 * once all classes are added call onStartup and onShutdown respectively, otherwise things might break
	 *
	 * @param plugin the plugin
	 */
	public PluginManager(@NotNull JavaPlugin plugin)
	{
		for(ManagerValues managerValues : ManagerValues.values())
		{
			if(managerValues.getManager() == null)
			{
				continue;
			}
			managerMap.put(managerValues, managerValues.getManager());
		}
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
	
	public void add(Recipe recipe)
	{
		getRecipeManager().add(recipe);
	}
	public void add(Enchantment enchantment){
		getEnchantManager().add(enchantment);
	}
	
	public void addLang(Locale locale, Config config)
	{
		getLangManager().addLanguage(locale,config);
	}
	
	public void addDefaultLang(Locale locale, Config config)
	{
		getLangManager().setDefaultLang(locale,config);
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
	
	public RecipeManager getRecipeManager(){
		return (RecipeManager) managerMap.get(ManagerValues.RECIPE);
	}
	
	public EnchantmentManager getEnchantManager(){
		return (EnchantmentManager) managerMap.get(ManagerValues.ENCHANT);
	}
}
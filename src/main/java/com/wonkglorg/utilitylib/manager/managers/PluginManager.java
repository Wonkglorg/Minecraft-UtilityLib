package com.wonkglorg.utilitylib.manager.managers;

import com.wonkglorg.utilitylib.base.logger.Logger;
import com.wonkglorg.utilitylib.manager.command.Command;
import com.wonkglorg.utilitylib.manager.config.Config;
import com.wonkglorg.utilitylib.manager.config.LangConfig;
import com.wonkglorg.utilitylib.manager.database.Database;
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
public final class PluginManager{
	
	private final Map<ManagerValues, Manager> managerMap = new ConcurrentHashMap<>();
	private final JavaPlugin plugin;
	
	/**
	 * PluginManager is designed to make handling and using different types of common classes like configs, commands,events,languages,recipes easier
	 * once all classes are added call onStartup and onShutdown respectively, otherwise things might break
	 *
	 * @param plugin the plugin
	 */
	public PluginManager(@NotNull JavaPlugin plugin) {
		this.plugin = plugin;
		addManagers();
	}
	
	/**
	 * Adds a new cooldownManager. With specified consumer and producers
	 *
	 * @param producer action to perform on shutdown for example saving them to a config file
	 * @param consumer action to perform on startup for example loading it from a config file
	 */
	public void addCooldownManager(Consumer<Map<String, Map<UUID, Long>>> producer, Consumer<Map<String, Map<UUID, Long>>> consumer) {
		managerMap.put(ManagerValues.COOLDOWN, new CooldownManager(producer, consumer));
	}
	
	public synchronized void onStartup() {
		managerMap.values().forEach(Manager::onStartup);
	}
	
	public synchronized void onShutdown() {
		managerMap.values().forEach(Manager::onShutdown);
	}
	
	public synchronized void add(Listener... listener) {
		getEventManager().add(listener);
	}
	
	public synchronized void add(Command... command) {
		getCommandManager().add(command);
	}
	
	public synchronized void add(Config... config) {
		getConfigManager().add(config);
	}
	
	public synchronized void add(Recipe... recipe) {
		getRecipeManager().add(recipe);
	}
	
	public synchronized void add(Enchantment... enchantment) {
		getEnchantManager().add(enchantment);
	}
	
	public synchronized void add(String name, Database database) {
		getDatabaseManager().add(name, database);
	}
	
	/**
	 * Check whether the plugin is enabled and exists!
	 *
	 * @param targetPlugin the plugin to check for
	 * @param shutdown if enabled shutdown plugin on error
	 * @return is plugin existing
	 */
	public synchronized boolean checkDepend(Plugin targetPlugin, boolean shutdown) {
		return checkDepend(targetPlugin.getName(), shutdown);
	}
	
	/**
	 * Check whether the plugin is enabled and exists!
	 *
	 * @param targetPlugin the plugin to check for
	 * @param shutdown if enabled shutdown plugin on error
	 * @return is plugin existing
	 */
	public synchronized boolean checkDepend(String targetPlugin, boolean shutdown) {
		Plugin pluginToCheck = plugin.getServer().getPluginManager().getPlugin(targetPlugin);
		if(pluginToCheck == null || !pluginToCheck.isEnabled()){
			if(shutdown){
				plugin.getServer().getPluginManager().disablePlugin(plugin);
				return false;
			}
			Logger.logWarn(plugin, "Dependency: " + targetPlugin + " could not be enabled");
			return false;
		}
		Logger.log(plugin, "Dependency: " + targetPlugin + " loaded successfully!");
		return true;
	}
	
	public synchronized void addLang(Locale locale, LangConfig config) {
		getLangManager().addLanguage(locale, config);
	}
	
	public synchronized void addDefaultLang(Locale locale, LangConfig config) {
		getLangManager().setDefaultLang(locale, config);
	}
	
	public synchronized Manager getManager(ManagerValues value) {
		return managerMap.get(value);
	}
	
	/**
	 * Gets {@link ConfigManager}.
	 *
	 * @return the config manager
	 */
	public synchronized ConfigManager getConfigManager() {
		return (ConfigManager) getManager(ManagerValues.CONFIG);
	}
	
	/**
	 * Gets {@link CommandManager}.
	 *
	 * @return the command manager
	 */
	public synchronized CommandManager getCommandManager() {
		return (CommandManager) getManager(ManagerValues.COMMAND);
	}
	
	/**
	 * Gets {@link EventManager}.
	 *
	 * @return the event manager
	 */
	public synchronized EventManager getEventManager() {
		return (EventManager) getManager(ManagerValues.EVENT);
	}
	
	/**
	 * Gets {@link LangManager}.
	 *
	 * @return the lang manager
	 */
	public synchronized LangManager getLangManager() {
		
		return (LangManager) getManager(ManagerValues.LANG);
	}
	
	/**
	 * Gets {@link DatabaseManager}.
	 *
	 * @return the database manager
	 */
	public synchronized DatabaseManager getDatabaseManager() {
		return (DatabaseManager) getManager(ManagerValues.DATABASE);
	}
	
	public synchronized RecipeManager getRecipeManager() {
		return (RecipeManager) getManager(ManagerValues.RECIPE);
	}
	
	public synchronized EnchantmentManager getEnchantManager() {
		return (EnchantmentManager) getManager(ManagerValues.ENCHANT);
	}
	
	private void addManagers() {
		managerMap.putIfAbsent(ManagerValues.CONFIG, new ConfigManager(plugin));
		managerMap.putIfAbsent(ManagerValues.LANG, new LangManager(plugin, getConfigManager()));
		managerMap.putIfAbsent(ManagerValues.DATABASE, new DatabaseManager(plugin));
		managerMap.putIfAbsent(ManagerValues.EVENT, new EventManager(plugin));
		managerMap.putIfAbsent(ManagerValues.RECIPE, new RecipeManager(plugin));
		managerMap.putIfAbsent(ManagerValues.COMMAND, new CommandManager(plugin));
		managerMap.putIfAbsent(ManagerValues.ENCHANT, new EnchantmentManager(plugin));
		managerMap.putIfAbsent(ManagerValues.COOLDOWN, new CooldownManager());
	}
	
	private enum ManagerValues{
		CONFIG(),
		LANG(),
		COMMAND(),
		ENCHANT(),
		RECIPE(),
		EVENT(),
		COOLDOWN(),
		DATABASE(),
		
	}
}
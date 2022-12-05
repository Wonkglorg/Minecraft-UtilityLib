package com.wonkglorg.utilitylib.logger;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.NotNull;

@SuppressWarnings("unused")
public class Logger
{
	private static final java.util.logging.Logger logger = Bukkit.getLogger();
	
	/**
	 * Sends a logger message in the console.
	 *
	 * @param text message to display.
	 */
	public static void log(@NotNull JavaPlugin plugin, @NotNull String... text)
	{
		for(String string : text)
		{
			plugin.getLogger().info(string);
		}
	}
	
	/**
	 * Sends a logger message in the console.
	 *
	 * @param text message to display.
	 */
	public static void log(@NotNull String... text)
	{
		for(String string : text)
		{
			logger.info(string);
		}
	}
	
	/**
	 * Sends a logger warning message in the console
	 * used when something goes wrong but the plugin
	 * can still run afterwards.
	 *
	 * @param text message to display.
	 */
	public static void logWarn(@NotNull String... text)
	{
		for(String string : text)
		{
			logger.warning(string);
		}
	}
	
	/**
	 * Sends a logger warning message in the console
	 * used when something goes wrong but the plugin
	 * can still run afterwards.
	 *
	 * @param text message to display.
	 */
	public static void logWarn(@NotNull JavaPlugin plugin, @NotNull String... text)
	{
		for(String string : text)
		{
			plugin.getLogger().warning(string);
		}
	}
	
	/**
	 * Sends a logger fatal message in the console
	 * used when something goes wrong and results
	 * in either data loss or unable to further work.
	 *
	 * @param text message to display.
	 */
	public static void logFatal(@NotNull String... text)
	{
		for(String string : text)
		{
			logger.severe(string);
		}
	}
	
	/**
	 * Sends a logger fatal message in the console
	 * used when something goes wrong and results
	 * in either data loss or unable to further work.
	 *
	 * @param text message to display.
	 */
	public static void logFatal(@NotNull JavaPlugin plugin,@NotNull String... text)
	{
		for(String string : text)
		{
			plugin.getLogger().severe(string);
		}
	}
	
}
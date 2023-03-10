package com.wonkglorg.utilitylib.logger;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.NotNull;

import javax.annotation.concurrent.ThreadSafe;

@SuppressWarnings({"unused", "unchecked"})
@ThreadSafe
public final class Logger
{
	private static final java.util.logging.Logger logger = Bukkit.getLogger();
	
	/**
	 * Sends a logger message in the console.
	 *
	 * @param text message to display.
	 */
	public static void log(@NotNull JavaPlugin plugin, @NotNull Object... text)
	{
		for(Object obj : text)
		{
			if(obj instanceof Iterable list)
			{
				//reformat to concate all into 1 string? instead of seperate values?
				list.forEach(o -> plugin.getLogger().info(o.toString()));
				continue;
			}
			plugin.getLogger().info(obj.toString());
		}
	}
	
	/**
	 * Sends a logger message in the console.
	 *
	 * @param text message to display.
	 */
	public static void log(@NotNull Object... text)
	{
		for(Object obj : text)
		{
			if(obj instanceof Iterable list)
			{
				list.forEach(o -> logger.info(o.toString()));
				continue;
			}
			logger.info(obj.toString());
		}
	}
	
	/**
	 * Sends a logger warning message in the console
	 * used when something goes wrong but the plugin
	 * can still run afterwards.
	 *
	 * @param text message to display.
	 */
	public static void logWarn(@NotNull JavaPlugin plugin, @NotNull Object... text)
	{
		for(Object obj : text)
		{
			if(obj instanceof Iterable list)
			{
				list.forEach(o -> plugin.getLogger().info(o.toString()));
				continue;
			}
			plugin.getLogger().info(obj.toString());
		}
	}
	
	/**
	 * Sends a logger warning message in the console
	 * used when something goes wrong but the plugin
	 * can still run afterwards.
	 *
	 * @param text message to display.
	 */
	public static void logWarn(@NotNull Object... text)
	{
		for(Object obj : text)
		{
			if(obj instanceof Iterable list)
			{
				list.forEach(o -> logger.info(o.toString()));
				continue;
			}
			logger.info(obj.toString());
		}
	}
	
	/**
	 * Sends a logger fatal message in the console
	 * used when something goes wrong and results
	 * in either data loss or unable to further function..
	 *
	 * @param text message to display.
	 */
	public static void logFatal(@NotNull JavaPlugin plugin, @NotNull Object... text)
	{
		for(Object obj : text)
		{
			if(obj instanceof Iterable list)
			{
				list.forEach(o -> plugin.getLogger().info(o.toString()));
				continue;
			}
			plugin.getLogger().info(obj.toString());
		}
	}
	
	/**
	 * Sends a logger fatal message in the console
	 * used when something goes wrong and results
	 * in either data loss or unable to further function..
	 *
	 * @param text message to display.
	 */
	public static void logFatal(@NotNull Object... text)
	{
		for(Object obj : text)
		{
			if(obj instanceof Iterable list)
			{
				list.forEach(o -> logger.info(o.toString()));
				continue;
			}
			logger.info(obj.toString());
		}
	}
	
}
package com.wonkglorg.utilitylib.logger;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.NotNull;

import javax.annotation.concurrent.ThreadSafe;
import java.util.Collection;
import java.util.Map;
import java.util.logging.Level;

@SuppressWarnings({"unused", "unchecked"})
@ThreadSafe
public final class Logger
{
	private static final java.util.logging.Logger logger = Bukkit.getLogger();
	
	/**
	 * Sends a logger message in the console using your own logger and level.
	 *
	 * @param logger Logger to be used
	 * @param logType Type of log level
	 * @param text Text to be displayed
	 */
	public static void log(@NotNull final java.util.logging.Logger logger, @NotNull final Level logType, @NotNull Object... text)
	{
		StringBuilder builder = new StringBuilder();
		for(Object obj : text)
		{
			boolean isArray = false;
			if(obj instanceof Collection list)
			{
				list.forEach(o -> builder.append(",").append(o.toString()));
			}
			if(obj instanceof Map map)
				map.forEach((o, o2) -> builder.append(String.format(",(%s , %s)", o.toString(), o2.toString())));
			
			if(builder.length() > 0)
			{
				logger.log(logType, "[ " + builder.toString().replaceFirst(",", "").trim() + " ]");
				continue;
			}
			builder.append(obj);
			logger.log(logType, builder.toString());
			builder.setLength(0);
		}
	}
	
	/**
	 * Sends a logger message in the console.
	 *
	 * @param text message to display.
	 */
	public static void log(@NotNull JavaPlugin plugin, @NotNull Object... text)
	{
		log(plugin.getLogger(), Level.INFO, text);
	}
	
	/**
	 * Sends a logger message in the console.
	 *
	 * @param text message to display.
	 */
	public static void log(@NotNull Object... text)
	{
		log(logger, Level.INFO, text);
	}
	
	/**
	 * Sends a logger warning message in the console used when something goes wrong but the plugin can still run afterwards.
	 *
	 * @param text message to display.
	 */
	public static void logWarn(@NotNull JavaPlugin plugin, @NotNull Object... text)
	{
		log(plugin.getLogger(), Level.WARNING, text);
	}
	
	/**
	 * Sends a logger warning message in the console used when something goes wrong but the plugin can still run afterwards.
	 *
	 * @param text message to display.
	 */
	public static void logWarn(@NotNull Object... text)
	{
		log(logger, Level.WARNING, text);
	}
	
	/**
	 * Sends a logger fatal message in the console used when something goes wrong and results in either data loss or unable to further function..
	 *
	 * @param text message to display.
	 */
	public static void logFatal(@NotNull JavaPlugin plugin, @NotNull Object... text)
	{
		log(plugin.getLogger(), Level.SEVERE, text);
	}
	
	/**
	 * Sends a logger fatal message in the console used when something goes wrong and results in either data loss or unable to further function..
	 *
	 * @param text message to display.
	 */
	public static void logFatal(@NotNull Object... text)
	{
		log(logger, Level.SEVERE, text);
	}
	
	private enum LogType
	{
		INFO,
		WARN,
		FATAL,
	}
	
}
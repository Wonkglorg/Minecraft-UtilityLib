package com.wonkglorg.utilitylib.managers;

import com.wonkglorg.utilitylib.command.Command;
import com.wonkglorg.utilitylib.logger.Logger;
import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.NotNull;

import javax.annotation.concurrent.ThreadSafe;
import java.util.Arrays;
import java.util.Collection;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Command manager class.
 *
 * Helps to access and manage commands.
 */
@SuppressWarnings("unused")
@ThreadSafe
public final class CommandManager implements Manager
{
	/**
	 * Command Map holding all assigned commands and their name
	 */
	private final Map<String, Command> commandMap;
	private final JavaPlugin plugin;
	private boolean isStarted = false;
	
	public CommandManager(JavaPlugin plugin)
	{
		this.plugin = plugin;
		commandMap = new ConcurrentHashMap<>();
	}
	
	/**
	 * Adds a command to the commandMap.
	 *
	 * @param command the command
	 */
	public synchronized void add(@NotNull final Command command)
	{
		commandMap.put(command.getName(), command);
	}
	
	/**
	 * Adds an array of commands to the manager
	 *
	 * @param commands the commands
	 */
	public synchronized void add(@NotNull final Command... commands)
	{
		Arrays.asList(commands).forEach(command -> commandMap.put(command.getName(), command));
	}
	
	/**
	 * Add.
	 *
	 * @param commands the commands
	 */
	public synchronized void add(@NotNull final Collection<Command> commands)
	{
		commands.forEach(command -> commandMap.put(command.getName(), command));
	}
	
	/**
	 * Get command.
	 *
	 * @param command the command
	 * @return the command
	 */
	public synchronized Command get(@NotNull String command)
	{
		return commandMap.get(command);
	}
	
	/**
	 * Gets commands.
	 *
	 * @return the commands
	 */
	public synchronized Map<String, Command> getCommands()
	{
		return commandMap;
	}
	
	@Override
	public void onStartup()
	{
		if(isStarted)
		{
			return;
		}
		isStarted = true;
		if(!commandMap.isEmpty())
		{
			Logger.log(plugin, "Loaded " + commandMap.size() + " commands!");
		}
	}
	
	@Override
	public void onShutdown()
	{
	
	}
}
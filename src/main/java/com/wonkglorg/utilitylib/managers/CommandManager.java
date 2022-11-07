package com.wonkglorg.utilitylib.managers;

import com.wonkglorg.utilitylib.command.Command;
import com.wonkglorg.utilitylib.utils.logger.Logger;
import org.jetbrains.annotations.NotNull;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Command manager class.
 *
 * Helps to access and manage commands.
 */
@SuppressWarnings("unused")
public class CommandManager implements Manager
{
	/**
	 * Command Map holding all assigned commands and their name
	 */
	protected final Map<String, Command> commandMap;
	
	public CommandManager()
	{
		commandMap = new HashMap<>();
	}
	
	/**
	 * Adds a command to the commandMap.
	 *
	 * @param command the command
	 */
	public void add(@NotNull final Command command)
	{
		commandMap.put(command.getName(), command);
	}
	
	/**
	 * Adds an array of commands to the manager
	 *
	 * @param commands the commands
	 */
	public void add(@NotNull final Command... commands)
	{
		Arrays.asList(commands).forEach(command -> commandMap.put(command.getName(), command));
	}
	
	/**
	 * Add.
	 *
	 * @param commands the commands
	 */
	public void add(@NotNull final List<Command> commands)
	{
		commands.forEach(command -> commandMap.put(command.getName(), command));
	}
	
	/**
	 * Get command.
	 *
	 * @param command the command
	 * @return the command
	 */
	public Command get(@NotNull String command)
	{
		return commandMap.get(command);
	}
	
	/**
	 * Gets commands.
	 *
	 * @return the commands
	 */
	public Map<String, Command> getCommands()
	{
		return commandMap;
	}
	
	@Override
	public void onShutdown()
	{
	
	}
	
	@Override
	public void onStartup()
	{
		Logger.log("Successfully loaded " + commandMap.size() + " commands!");
	}
}
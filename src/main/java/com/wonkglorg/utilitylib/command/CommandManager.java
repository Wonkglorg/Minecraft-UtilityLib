package com.wonkglorg.utilitylib.command;

import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CommandManager
{
	Map<String, Command> commandMap;
	
	public CommandManager()
	{
		commandMap = new HashMap<>();
	}
	
	public void add(@NotNull final Command command)
	{
		commandMap.put(command.getName(), command);
	}
	
	public void add(@NotNull final Command... commands)
	{
		for(Command command : commands)
		{
			commandMap.put(command.getName(), command);
		}
	}
	
	public void add(@NotNull final List<Command> commands)
	{
		for(Command command : commands)
		{
			commandMap.put(command.getName(), command);
		}
	}
	
	public Command get(@NotNull String command)
	{
		return commandMap.get(command);
	}
	
	public Map<String, Command> getCommands()
	{
		return commandMap;
	}
}
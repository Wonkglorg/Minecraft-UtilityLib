package com.wonkglorg.utilitylib.command;

import java.util.HashMap;
import java.util.Map;

public class CommandManager
{
	Map<String, Command> commands;
	
	public CommandManager()
	{
		commands = new HashMap<>();
	}
	
	public void add(Command command)
	{
		commands.put(command.getName(), command);
	}
	
	public Command get(String command)
	{
		return commands.get(command);
	}
	
	public Map<String, Command> getCommands()
	{
		return commands;
	}
}
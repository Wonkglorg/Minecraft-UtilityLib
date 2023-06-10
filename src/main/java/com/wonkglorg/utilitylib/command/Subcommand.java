package com.wonkglorg.utilitylib.command;

import org.bukkit.command.CommandSender;

import java.util.List;

public abstract class Subcommand extends Arguments{
	public abstract void execute(CommandSender sender, String[] args);
	
	public abstract List<String> tabComplete(String alias, String[] args);
	
}
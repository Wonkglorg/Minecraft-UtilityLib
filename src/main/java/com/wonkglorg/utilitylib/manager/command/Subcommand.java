package com.wonkglorg.utilitylib.manager.command;

import org.bukkit.command.CommandSender;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

public abstract class Subcommand extends Arguments{
	
	public abstract void execute(CommandSender sender, String[] args);
	
	public abstract List<String> tabComplete(String alias, String[] args);
	
}
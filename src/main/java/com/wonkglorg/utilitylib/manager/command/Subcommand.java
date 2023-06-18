package com.wonkglorg.utilitylib.manager.command;

import org.bukkit.command.CommandSender;

import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;

public abstract class Subcommand extends Arguments{
	protected String[] subCommandNames;
	
	public Subcommand(String... subCommandName) {
		this.subCommandNames = subCommandName;
	}
	
	public boolean matches(String arg) {
		return Arrays.stream(subCommandNames).anyMatch(Predicate.isEqual(arg));
	}
	
	public abstract void execute(CommandSender sender, String[] args);
	
	public abstract List<String> tabComplete(String alias, String[] args);
	
}
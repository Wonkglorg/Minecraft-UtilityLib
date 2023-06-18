package com.wonkglorg.utilitylib.manager.command;

import org.bukkit.command.CommandSender;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

public abstract class Subcommand extends Arguments{
	protected List<String> subCommandList = new ArrayList<>();
	
	public Subcommand(String first, String... subCommandName) {
		subCommandList.add(first);
		subCommandList.addAll(List.of(subCommandName));
		
	}
	
	public List<String> getSubCommandNames() {
		return subCommandList;
	}
	
	public boolean matches(String arg) {
		return subCommandList.stream().anyMatch(Predicate.isEqual(arg));
	}
	
	public abstract void execute(CommandSender sender, String[] args);
	
	public abstract List<String> tabComplete(String alias, String[] args);
	
}
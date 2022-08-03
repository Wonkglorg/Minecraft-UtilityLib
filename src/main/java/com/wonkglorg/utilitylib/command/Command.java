package com.wonkglorg.utilitylib.command;

import com.wonkglorg.utilitylib.utils.logger.Logger;
import org.bukkit.command.CommandSender;
import org.bukkit.command.PluginCommand;
import org.bukkit.command.TabExecutor;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public abstract class Command implements TabExecutor
{
	protected JavaPlugin main;
	private String name;
	
	public Command(@NotNull JavaPlugin main, @NotNull String name)
	{
		this.main = main;
		PluginCommand pluginCommand = main.getCommand(name);
		this.name = name;
		
		if(pluginCommand != null)
		{
			pluginCommand.setExecutor(this);
			pluginCommand.setTabCompleter(this);
		} else
		{
			Logger.logFatal("Command " + name + " could not be loaded missing plugin.yml implementations");
		}
	}
	
	/**
	 * Logic when executing the command
	 *
	 * @param player {@link org.bukkit.entity.Player} entering command.
	 * @param args Following arguments to main command
	 * @return False if command arguments are wrong.
	 */
	public abstract boolean execute(@NotNull Player player, String[] args);
	
	/**
	 * Autocompletion on command args
	 *
	 * @param player {@link org.bukkit.entity.Player} entering the command.
	 * @param args Following arguments to main command.
	 *
	 * @return List of Strings to display for the current argument, returns list of players if null.
	 */
	public abstract List<String> tabComplete(@NotNull Player player, String[] args);
	
	@Override
	public boolean onCommand(@NotNull CommandSender sender,
							 @NotNull org.bukkit.command.Command command,
							 @NotNull String label,
							 @NotNull String[] args)
	{
		if(sender instanceof Player player)
		{
			return execute(player, args);
		}
		return true;
	}
	
	@Override
	public @Nullable List<String> onTabComplete(@NotNull CommandSender sender,
												@NotNull org.bukkit.command.Command command,
												@NotNull String alias,
												@NotNull String[] args)
	{
		if(sender instanceof Player player)
		{
			return tabComplete(player, args);
		}
		return null;
		
	}
	
	public String getName(){
	return name;
	}
}
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
import java.util.Objects;

/**
 * Abstract Command Class.
 *
 * Allows for easy command creation with added tab completion.
 */
public abstract class Command extends Arguments implements TabExecutor
{
	/**
	 * The main plugin to register the command to
	 */
	protected JavaPlugin plugin;
	private final String name;
	
	/**
	 * whether the command is type able from console
	 */
	abstract boolean allowConsole();
	
	/**
	 * Instantiates a new Command.
	 *
	 * @param plugin the plugin
	 * @param name the name
	 */
	public Command(@NotNull JavaPlugin plugin, @NotNull String name)
	{
		this.plugin = plugin;
		PluginCommand pluginCommand = plugin.getCommand(name);
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
	 * Executes when the player finished writing the command and presses enter
	 *
	 * @param player {@link org.bukkit.entity.Player} entering command can be null if allow console is true
	 * @param args Following arguments to main command
	 * @return False if command arguments are wrong.
	 */
	public abstract boolean execute(Player player, String[] args);
	
	/**
	 * Code block executes whenever a player types arguments after the command
	 *
	 * @param player {@link org.bukkit.entity.Player} entering the command.
	 * @param args Following arguments to main command.
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
			super.args = args;
			return execute(player, args);
		}
		if(allowConsole()){
			super.args = args;
			return execute(null,args);
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
			super.args = args;
			return tabComplete(player, args);
		}
		return null;
		
	}
	
	/**
	 * Returns the name of the command.
	 *
	 * @return the name
	 */
	public String getName()
	{
		return name;
	}
	
	@Override
	public String toString()
	{
		return name;
	}
	
	@Override
	public boolean equals(Object obj)
	{
		if(obj == null)
		{
			return false;
		}
		if(obj.getClass() != this.getClass())
		{
			return false;
		}
		return Objects.equals(this.name, ((Command) obj).getName());
	}
	
	@Override
	public int hashCode()
	{
		return 53 * 2 + this.name.hashCode();
	}
}
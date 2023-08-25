package com.wonkglorg.utilitylib.manager.command;


import com.wonkglorg.utilitylib.base.logger.Logger;
import com.wonkglorg.utilitylib.base.message.ChatColor;
import com.wonkglorg.utilitylib.base.message.Message;
import com.wonkglorg.utilitylib.manager.UtilityPlugin;
import com.wonkglorg.utilitylib.manager.config.Config;
import com.wonkglorg.utilitylib.manager.managers.ConfigManager;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * DO NOT USE, JUST A TEST
 */
@Deprecated
public class ReloadCommand extends Command{
	
	private final ConfigManager configManager;
	private final List<String> options = new ArrayList<>();
	
	public ReloadCommand(@NotNull JavaPlugin plugin, @NotNull String name) {
		super(plugin, name);
		
		configManager = UtilityPlugin.manager().getConfigManager();
		
		options.addAll(configManager.getConfigMap().stream().map(Config::name).toList());
		options.add("all");
	}
	
	@Override
	public boolean allowConsole() {
		return true;
	}
	
	@Override
	public boolean execute(Player player, String[] args) {
		if(args.length == 0){
			return false;
		}
		
		String name = argAsString(0);
		
		if(name.equalsIgnoreCase("all")){
			configManager.load();
			return true;
		}
		
		Config config = configManager.getConfig(name);
		if(config == null){
			if(player == null){
				Logger.logWarn(name + " is not a valid config!");
				return true;
			}
			Message.msgPlayer(player, ChatColor.YELLOW + name + ChatColor.GOLD + " is not a valid config!");
			return true;
		}
		config.load();
		if(player == null){
			Logger.logWarn("Reloaded " + name + "!");
			return true;
		}
		Message.msgPlayer(player, ChatColor.GOLD + "Reloaded " + ChatColor.YELLOW + name + ChatColor.GOLD + "!");
		return true;
	}
	
	@Override
	public List<String> tabComplete(@NotNull Player player, String[] args) {
		if(args.length == 1){
			return configManager.getConfigMap().stream().map(Config::name).collect(Collectors.toList());
		}
		return List.of();
	}
}
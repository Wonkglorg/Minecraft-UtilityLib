package com.wonkglorg.utilitylib.command;

import com.wonkglorg.utilitylib.UtilityPlugin;
import com.wonkglorg.utilitylib.config.Config;
import com.wonkglorg.utilitylib.managers.ConfigManager;
import com.wonkglorg.utilitylib.managers.LangManager;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.stream.Collectors;

public class ReloadCommand extends Command{
	
	private final ConfigManager configManager = UtilityPlugin.getManager().getConfigManager();
	private final LangManager langManager = UtilityPlugin.getManager().getLangManager();
	
	public ReloadCommand(@NotNull JavaPlugin plugin, @NotNull String name) {
		super(plugin, name);
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
			reloadConfigAndLangs();
			return true;
		}
		
		Config config = getConfigByName(name);
		if(config != null){
			reloadConfigAndLangs();
			config.load();
			return true;
		}
		
		return true;
	}
	
	private void reloadConfigAndLangs() {
		langManager.reloadLang(langManager.getDefaultLang().name());
		configManager.load();
	}
	
	private Config getConfigByName(String name) {
		return langManager.getAllLangs()
						  .values()
						  .stream()
						  .filter(config -> config.name().equalsIgnoreCase(name))
						  .findFirst()
						  .orElse(configManager.getConfig(name));
	}
	
	@Override
	public List<String> tabComplete(@NotNull Player player, String[] args) {
		if(args.length == 1){
			return configManager.getConfigs().stream().map(Config::name).collect(Collectors.toList());
		}
		return List.of();
	}
}
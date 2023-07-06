package com.wonkglorg.utilitylib.manager.managers;

import com.wonkglorg.utilitylib.base.logger.Logger;
import org.bukkit.Bukkit;
import org.bukkit.inventory.Recipe;
import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@SuppressWarnings("unused")
public final class RecipeManager implements Manager{
	private final List<Recipe> recipes = new ArrayList<>();
	private final JavaPlugin plugin;
	private boolean isLoaded = false;
	
	public RecipeManager(JavaPlugin plugin) {
		this.plugin = plugin;
	}
	
	public void add(@NotNull final Recipe... recipe) {
		recipes.addAll(List.of(recipe));
	}
	
	public void remove(@NotNull final Recipe recipe) {
		recipes.remove(recipe);
	}
	
	public void load() {
		for(Recipe recipe : recipes){
			Bukkit.getServer().addRecipe(recipe);
		}
	}
	
	@Override
	public void onStartup() {
		if(isLoaded){
			return;
		}
		isLoaded = true;
		
		load();
		
		if(!recipes.isEmpty()){
			Logger.log(plugin, "Loaded " + recipes.size() + " recipes!");
		}
	}
	
	/**
	 * [22:35:31 ERROR]: Error occurred while disabling ZenixUtility v1.19.2 (Is it up to date?)
	 * java.util.ConcurrentModificationException: null
	 * at java.util.ArrayList.forEach(ArrayList.java:1513) ~[?:?]
	 * at com.wonkglorg.utilitylib.manager.managers.RecipeManager.onShutdown(RecipeManager.java:52) ~[ZenixUtility-1.19.2.jar:?]
	 * at java.util.concurrent.ConcurrentHashMap$ValuesView.forEach(ConcurrentHashMap.java:4783) ~[?:?]
	 * at com.wonkglorg.utilitylib.manager.managers.PluginManager.onShutdown(PluginManager.java:58) ~[ZenixUtility-1.19.2.jar:?]
	 * at com.wonkglorg.utilitylib.manager.UtilityPlugin.onDisable(UtilityPlugin.java:41) ~[ZenixUtility-1.19.2.jar:?]
	 * at org.bukkit.plugin.java.JavaPlugin.setEnabled(JavaPlugin.java:266) ~[purpur-api-1.19.2-R0.1-SNAPSHOT.jar:?]
	 * at org.bukkit.plugin.java.JavaPluginLoader.disablePlugin(JavaPluginLoader.java:401) ~[purpur-api-1.19.2-R0.1-SNAPSHOT.jar:?]
	 * at org.bukkit.plugin.SimplePluginManager.disablePlugin(SimplePluginManager.java:585) ~[purpur-api-1.19.2-R0.1-SNAPSHOT.jar:?]
	 * at org.bukkit.plugin.SimplePluginManager.disablePlugins(SimplePluginManager.java:562) ~[purpur-api-1.19.2-R0.1-SNAPSHOT.jar:?]
	 * at org.bukkit.craftbukkit.v1_19_R1.CraftServer.disablePlugins(CraftServer.java:545) ~[purpur-1.19.2.jar:git-Purpur-1820]
	 * at net.minecraft.server.MinecraftServer.stopServer(MinecraftServer.java:956) ~[purpur-1.19.2.jar:git-Purpur-1820]
	 * at net.minecraft.server.dedicated.DedicatedServer.stopServer(DedicatedServer.java:848) ~[purpur-1.19.2.jar:git-Purpur-1820]
	 * at net.minecraft.server.MinecraftServer.runServer(MinecraftServer.java:1239) ~[purpur-1.19.2.jar:git-Purpur-1820]
	 * at net.minecraft.server.MinecraftServer.lambda$spin$1(MinecraftServer.java:310
	 */
	
	@Override
	public void onShutdown() {
		Iterator<Recipe> recipeIterator = recipes.stream().iterator();
		
		while(recipeIterator.hasNext()){
			recipeIterator.remove();
		}
	}
	
}
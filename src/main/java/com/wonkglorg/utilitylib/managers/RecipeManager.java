package com.wonkglorg.utilitylib.managers;

import com.wonkglorg.utilitylib.logger.Logger;
import org.bukkit.Bukkit;
import org.bukkit.inventory.Recipe;
import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

@SuppressWarnings("unused")
public final class RecipeManager implements Manager
{
	private final List<Recipe> recipes = new ArrayList<>();
	private final JavaPlugin plugin;
	
	public RecipeManager(JavaPlugin plugin)
	{
		this.plugin = plugin;
	}
	
	public void add(@NotNull final Recipe... recipe)
	{
		recipes.addAll(List.of(recipe));
	}
	
	
	public void remove(@NotNull final Recipe recipe)
	{
		recipes.remove(recipe);
	}
	
	public void load()
	{
		for(Recipe recipe : recipes)
		{
			Bukkit.getServer().addRecipe(recipe);
		}
	}
	
	@Override
	public void onStartup()
	{
		load();
		if(!recipes.isEmpty()){
			Logger.log(plugin, "Loaded " + recipes.size() + " recipes!");
		}
	}
	
	@Override
	public void onShutdown()
	{
		recipes.forEach(this::remove);
	}
	
}
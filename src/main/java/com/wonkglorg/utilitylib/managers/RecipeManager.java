package com.wonkglorg.utilitylib.managers;

import org.bukkit.Bukkit;
import org.bukkit.inventory.Recipe;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

@SuppressWarnings("unused")
public class RecipeManager implements Manager
{
	List<Recipe> recipes = new ArrayList<>();
	
	public void add(@NotNull final Recipe... recipe)
	{
		if(recipes == null)
		{
			recipes = new ArrayList<>();
		}
		recipes.addAll(List.of(recipe));
	}
	
	
	public void remove(@NotNull final Recipe recipe)
	{
		if(recipes == null)
		{
			return;
		}
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
	public void onShutdown()
	{
		recipes.forEach(this::remove);
	}
	
	@Override
	public void onStartup()
	{
		load();
	}
	
}
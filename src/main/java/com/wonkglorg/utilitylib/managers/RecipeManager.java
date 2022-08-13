package com.wonkglorg.utilitylib.managers;

import org.bukkit.Bukkit;
import org.bukkit.inventory.Recipe;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

@SuppressWarnings("unused")
public class RecipeManager
{
	List<Recipe> recipes;
	
	public RecipeManager()
	{
		this.recipes = new ArrayList<>();
	}
	
	public void add(@NotNull final Recipe recipe)
	{
		if(recipes == null)
		{
			recipes = new ArrayList<>();
		}
		recipes.add(recipe);
	}
	
	public void add(@NotNull final Recipe... recipe)
	{
		if(recipes == null)
		{
			recipes = new ArrayList<>();
		}
		recipes.addAll(List.of(recipe));
	}
	
	public void add(@NotNull final List<Recipe> recipe)
	{
		if(recipes == null)
		{
			recipes = new ArrayList<>();
		}
		recipes.addAll(recipe);
	}
	
	public void remove(@NotNull final Recipe recipe)
	{
		if(recipes == null)
		{
			return;
		}
		recipes.remove(recipe);
	}
	
	public void registerAll()
	{
		for(Recipe recipe : recipes)
		{
			Bukkit.getServer().addRecipe(recipe);
		}
	}
	
}
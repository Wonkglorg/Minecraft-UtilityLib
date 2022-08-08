package com.wonkglorg.utilitylib.customrecipe;

import org.bukkit.Bukkit;
import org.bukkit.inventory.Recipe;

import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("unused")
public class RecipeManager
{
	List<Recipe> recipes;
	
	public RecipeManager()
	{
		this.recipes = new ArrayList<>();
	}
	
	public void add(Recipe recipe)
	{
		if(recipes == null)
		{
			recipes = new ArrayList<>();
		}
		recipes.add(recipe);
	}
	
	public void add(Recipe... recipe)
	{
		if(recipes == null)
		{
			recipes = new ArrayList<>();
		}
		recipes.addAll(List.of(recipe));
	}
	
	public void add(List<Recipe> recipe)
	{
		if(recipes == null)
		{
			recipes = new ArrayList<>();
		}
		recipes.addAll(recipe);
	}
	
	public void remove(Recipe recipe)
	{
		if(recipes == null)
		{
			return;
		}
		recipes.remove(recipe);
	}
	
	public void initialize()
	{
		for(Recipe recipe : recipes)
		{
			Bukkit.getServer().addRecipe(recipe);
		}
	}
	
}
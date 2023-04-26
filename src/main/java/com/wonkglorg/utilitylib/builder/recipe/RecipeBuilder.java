package com.wonkglorg.utilitylib.builder.recipe;

import org.bukkit.Bukkit;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.Recipe;
import org.jetbrains.annotations.NotNull;

/**
 * @author Wonkglorg
 */
@SuppressWarnings("unused")
public abstract class RecipeBuilder
{
	
	/**
	 * The Recipe.
	 */
	protected Recipe recipe;
	
	/**
	 * Instantiates a new Recipe builder.
	 *
	 * @param key the key
	 * @param result the result
	 */
	protected RecipeBuilder(@NotNull final NamespacedKey key, @NotNull final ItemStack result)
	{
		result(key, result);
	}
	
	/**
	 * Init recipe.
	 *
	 * @param key the key
	 * @param result the result
	 */
	protected abstract void initRecipe(@NotNull final NamespacedKey key, @NotNull final ItemStack result);
	
	/**
	 * Validate init.
	 */
	protected void validateInit()
	{
		if(recipe == null)
		{
			throw new IllegalStateException("Recipe not yet initiated");
		}
	}
	
	/**
	 * Set the recipe result
	 *
	 * @param key the key
	 * @param result {@link ItemStack}
	 */
	protected void result(@NotNull final NamespacedKey key, @NotNull final ItemStack result)
	{
		initRecipe(key, result);
	}
	
	/**
	 * Builds the recipe
	 *
	 * @return the built {@link Recipe}
	 */
	public Recipe build()
	{
		validateInit();
		return recipe;
	}
	
	/**
	 * Registers the recipe
	 */
	public void register()
	{
		Recipe recipe = build();
		Bukkit.addRecipe(recipe);
	}
}
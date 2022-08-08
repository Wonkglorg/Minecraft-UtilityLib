package com.wonkglorg.utilitylib.customrecipe;

import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapedRecipe;
import org.bukkit.inventory.ShapelessRecipe;

@SuppressWarnings("unused")
public class ShapelessRecipeBuilder extends RecipeBuilder
{
	
	/**
	 * Constructs a new ShapelessRecipeBuilder without content
	 */
	public ShapelessRecipeBuilder()
	{
	}
	
	/**
	 * Constructs a new ShapelessRecipeBuilder for the specified result
	 *
	 * @param result result {@link ItemStack}
	 */
	public ShapelessRecipeBuilder(NamespacedKey key, ItemStack result)
	{
		super(key, result);
	}
	
	@Override
	protected void initRecipe(NamespacedKey key, ItemStack result)
	{
		if(recipe == null)
		{
			recipe = new ShapedRecipe(key, result);
		}
	}
	
	private ShapelessRecipe getRecipe()
	{
		return (ShapelessRecipe) recipe;
	}
	
	/**
	 * Adds an ingredient to the recipe
	 *
	 * @param ingredient ingredient {@link Material}
	 * @return the ShapelessRecipeBuilder
	 */
	public ShapelessRecipeBuilder addIngredient(Material ingredient)
	{
		validateInit();
		getRecipe().addIngredient(ingredient);
		return this;
	}
	
	/**
	 * Adds an ingredient to the recipe
	 *
	 * @param count amount of the ingredient
	 * @param ingredient ingredient {@link Material}
	 * @return the ShapelessRecipeBuilder
	 */
	public ShapelessRecipeBuilder addIngredient(int count, Material ingredient)
	{
		validateInit();
		getRecipe().addIngredient(count, ingredient);
		return this;
	}
	
	/**
	 * Adds an ingredient to the recipe
	 *
	 * @param ingredient ingredient {@link ItemStack}
	 * @return the ShapelessRecipeBuilder
	 */
	public ShapelessRecipeBuilder addIngredient(ItemStack ingredient)
	{
		validateInit();
		getRecipe().addIngredient(ingredient);
		return this;
	}
	
	@Override
	public ShapelessRecipe build()
	{
		return (ShapelessRecipe) super.build();
	}
	
}

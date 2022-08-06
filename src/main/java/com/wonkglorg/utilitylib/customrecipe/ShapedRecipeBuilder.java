package com.wonkglorg.utilitylib.customrecipe;

import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapedRecipe;

import java.util.HashMap;
import java.util.Map;

public class ShapedRecipeBuilder extends RecipeBuilder
{
	private Map<Character, ItemStack> ingredientMap;
	
	/**
	 * Constructs a new ShapedRecipeBuilder for the specified result
	 *
	 * @param result result {@link ItemStack}
	 */
	public ShapedRecipeBuilder(NamespacedKey key, ItemStack result)
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
	
	private ShapedRecipe getRecipe()
	{
		return (ShapedRecipe) this.recipe;
	}
	
	/**
	 * Sets the shape of the recipe
	 *
	 * Example: "xxx",
	 * "xxx",
	 * "xxx"
	 *
	 * @param row1 First row of the recipe
	 * @param row2 Second row of the recipe
	 * @param row3 Third row of the recipe
	 * @return the ShapedRecipeBuilder
	 */
	public ShapedRecipeBuilder withShape(String row1, String row2, String row3)
	{
		validateInit();
		getRecipe().shape(row1, row2, row3);
		return this;
	}
	
	/**
	 * Assigns a shape key to an ingredient
	 *
	 * @param key key given in the shape
	 * @param ingredient ingredient {@link Material}
	 * @return the ShapedRecipeBuilder
	 */
	public ShapedRecipeBuilder addIngredient(char key, Material ingredient)
	{
		validateInit();
		getRecipe().setIngredient(key, ingredient);
		return this;
	}
	
	/**
	 * Assigns a shape key to an ingredient
	 *
	 * @param key key given in the shape
	 * @param ingredient ingredient {@link ItemStack}
	 * @return the ShapedRecipeBuilder
	 */
	public ShapedRecipeBuilder addIngredient(char key, ItemStack ingredient)
	{
		validateInit();
		getRecipe().setIngredient(key,ingredient);
		return this;
	}
	
	@Override
	public ShapedRecipe build()
	{
		return (ShapedRecipe) super.build();
	}
}

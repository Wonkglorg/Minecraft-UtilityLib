package com.wonkglorg.utilitylib.builder.recipe;

import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapedRecipe;
import org.jetbrains.annotations.NotNull;

import java.util.Map;

@SuppressWarnings("unused")
public class ShapedRecipeBuilder extends RecipeBuilder
{
	
	/**
	 * Constructs a new ShapedRecipeBuilder for the specified result
	 *
	 * @param result result {@link ItemStack}
	 */
	public ShapedRecipeBuilder(@NotNull final NamespacedKey key, ItemStack result)
	{
		super(key, result);
	}
	
	@Override
	protected void initRecipe(@NotNull final NamespacedKey key, @NotNull final ItemStack result)
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
	public ShapedRecipeBuilder withShape(@NotNull final String row1, @NotNull final String row2, @NotNull final String row3)
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
	public ShapedRecipeBuilder addIngredient(final char key, @NotNull final Material ingredient)
	{
		validateInit();
		getRecipe().setIngredient(key, ingredient);
		return this;
	}
	/**
	 * Add {@link ShapedRecipeData} to add a recipe to the crafting
	 *
	 * @param shapedRecipeData ingredient {@link ShapedRecipeData}
	 * @return {@link ShapedRecipeBuilder}
	 */
	public ShapedRecipeBuilder addIngredient(ShapedRecipeData... shapedRecipeData)
	{
		validateInit();
		for(ShapedRecipeData recipeData : shapedRecipeData)
		{
			getRecipe().setIngredient(recipeData.character(), recipeData.itemStack());
		}
		return this;
	}
	
	/**
	 * Assigns a shape key to an ingredient using {@link java.util.HashMap}
	 *
	 * @param ingredients ingredients
	 * @return {@link ShapedRecipeBuilder}
	 */
	public ShapedRecipeBuilder addIngredient(Map<Character, ItemStack> ingredients)
	{
		validateInit();
		for(char character : ingredients.keySet())
		{
			getRecipe().setIngredient(character, ingredients.get(character));
		}
		return this;
	}
	
	/**
	 * Assigns a shape key to an ingredient
	 *
	 * @param key key given in the shape
	 * @param ingredient ingredient {@link ItemStack}
	 * @return the ShapedRecipeBuilder
	 */
	public ShapedRecipeBuilder addIngredient(final char key, @NotNull final ItemStack ingredient)
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
	public ShapedRecipeBuilder addIngredients(final char key, @NotNull final ItemStack ingredient)
	{
		validateInit();
		getRecipe().setIngredient(key, ingredient);
		return this;
	}
	
	@Override
	public ShapedRecipe build()
	{
		return (ShapedRecipe) super.build();
	}
}

package com.wonkglorg.utilitylib.utils.builder.recipe;

import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.FurnaceRecipe;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;

/**
 * The type Furnace recipe builder.
 */
@SuppressWarnings("unused")
public class FurnaceRecipeBuilder extends RecipeBuilder
{
	
	/**
	 * Instantiates a new Furnace recipe builder.
	 *
	 * @param key the key
	 * @param result the result
	 */
	public FurnaceRecipeBuilder(@NotNull final NamespacedKey key, @NotNull final ItemStack result)
	{
		super(key, result);
	}
	
	protected void initRecipe(@NotNull final NamespacedKey key, @NotNull final ItemStack result)
	{
		if(recipe == null)
		{
			recipe = new FurnaceRecipe(key, result, Material.BARRIER, 0.0f, 10);
		}
	}
	
	private FurnaceRecipe getRecipe()
	{
		return (FurnaceRecipe) this.recipe;
	}
	
	/**
	 * Sets the input item to be smelted
	 *
	 * @param input the item to be smelted
	 * @return the ShapedRecipeBuilder
	 */
	public FurnaceRecipeBuilder setInput(@NotNull final ItemStack input)
	{
		validateInit();
		getRecipe().setInput(input.getType());
		return this;
	}
	
	/**
	 * Sets input.
	 *
	 * @param input the input
	 * @return the input
	 */
	public FurnaceRecipeBuilder setInput(@NotNull final Material input)
	{
		validateInit();
		getRecipe().setInput(input);
		return this;
	}
	
	/**
	 * Sets cook time.
	 *
	 * @param ticks the ticks
	 * @return the cook time
	 */
	public FurnaceRecipeBuilder setCookTime(final int ticks)
	{
		validateInit();
		getRecipe().setCookingTime(ticks);
		return this;
	}
	
	/**
	 * Sets cook time.
	 *
	 * @param experience the experience
	 * @return the cook time
	 */
	public FurnaceRecipeBuilder setCookTime(final float experience)
	{
		validateInit();
		getRecipe().setExperience(experience);
		return this;
	}
	
	@Override
	public FurnaceRecipe build()
	{
		return (FurnaceRecipe) super.build();
	}
}
package com.wonkglorg.utilitylib.builder.recipe;

import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.BlastingRecipe;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.RecipeChoice.ExactChoice;
import org.jetbrains.annotations.NotNull;

public final class BlastingRecipeBuilder extends RecipeBuilder
{
	/**
	 * Creates a new Blasting Recipe builder
	 * @param key
	 * @param result
	 */
	public BlastingRecipeBuilder(@NotNull NamespacedKey key, @NotNull ItemStack result)
	{
		super(key, result);
	}
	
	@Override
	protected void initRecipe(@NotNull NamespacedKey key, @NotNull ItemStack result)
	{
		if(recipe == null)
		{
			recipe = new BlastingRecipe(key, result, Material.BARRIER, 0.0f, 10);
		}
	}
	
	private BlastingRecipe getRecipe()
	{
		return (BlastingRecipe) this.recipe;
	}
	
	/**
	 * Sets the {@link ItemStack} input.
	 *
	 * @param input {@link ItemStack} to be smelted
	 * @return {@link BlastingRecipeBuilder}
	 */
	public BlastingRecipeBuilder setInput(@NotNull final ItemStack... input)
	{
		validateInit();
		getRecipe().setInputChoice(new ExactChoice(input));
		return this;
	}
	
	/**
	 * Sets the {@link Material} to be smelted
	 *
	 * @param input {@link Material} to be smelted
	 * @return {@link  BlastingRecipeBuilder}
	 */
	public BlastingRecipeBuilder setInput(@NotNull final Material input)
	{
		validateInit();
		getRecipe().setInput(input);
		return this;
	}
	
	/**
	 * Sets the time needed to cook the time
	 *
	 * @param ticks amount in ticks
	 * @return {@link BlastingRecipeBuilder}
	 */
	public BlastingRecipeBuilder setCookTime(final int ticks)
	{
		validateInit();
		getRecipe().setCookingTime(ticks);
		return this;
	}
	
	/**
	 * Sets the expirience gained from cooking
	 *
	 * @param expirience as {@link Float}
	 * @return {@link BlastingRecipeBuilder}
	 */
	public BlastingRecipeBuilder setExpirience(final float expirience)
	{
		validateInit();
		getRecipe().setExperience(expirience);
		return this;
	}
	
	@Override
	public BlastingRecipe build()
	{
		return (BlastingRecipe) super.build();
	}
}
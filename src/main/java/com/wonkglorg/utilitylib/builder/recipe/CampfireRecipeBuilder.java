package com.wonkglorg.utilitylib.builder.recipe;

import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.CampfireRecipe;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.RecipeChoice.ExactChoice;
import org.jetbrains.annotations.NotNull;
@SuppressWarnings("unused")
public final class CampfireRecipeBuilder extends RecipeBuilder
{
	public CampfireRecipeBuilder(@NotNull NamespacedKey key, @NotNull ItemStack result)
	{
		super(key, result);
	}
	
	@Override
	protected void initRecipe(@NotNull NamespacedKey key, @NotNull ItemStack result)
	{
		if(recipe == null)
		{
			recipe = new CampfireRecipe(key, result, Material.BARRIER, 0.0f, 10);
		}
	}
	
	private CampfireRecipe getRecipe()
	{
		return (CampfireRecipe) this.recipe;
	}
	/**
	 * Sets the {@link ItemStack} input.
	 *
	 * @param input {@link ItemStack} to be smelted
	 * @return {@link CampfireRecipeBuilder}
	 */
	public CampfireRecipeBuilder setInput(@NotNull final ItemStack... input)
	{
		validateInit();
		getRecipe().setInputChoice(new ExactChoice(input));
		return this;
	}
	
	/**
	 * Sets the {@link Material} to be smelted
	 *
	 * @param input {@link Material} to be smelted
	 * @return {@link  CampfireRecipeBuilder}
	 */
	public CampfireRecipeBuilder setInput(@NotNull final Material input)
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
	public CampfireRecipeBuilder setCookTime(final int ticks)
	{
		validateInit();
		getRecipe().setCookingTime(ticks);
		return this;
	}
	
	/**
	 * Sets the expirience gained from cooking
	 *
	 * @param expirience as {@link Float}
	 * @return {@link CampfireRecipeBuilder}
	 */
	public CampfireRecipeBuilder setExpirience(final float expirience)
	{
		validateInit();
		getRecipe().setExperience(expirience);
		return this;
	}
	
	@Override
	public CampfireRecipe build()
	{
		return (CampfireRecipe) super.build();
	}
	
}
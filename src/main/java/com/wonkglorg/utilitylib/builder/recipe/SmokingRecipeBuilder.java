package com.wonkglorg.utilitylib.builder.recipe;

import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.FurnaceRecipe;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.Recipe;
import org.bukkit.inventory.SmokingRecipe;
import org.jetbrains.annotations.NotNull;

@SuppressWarnings("unused")
public class SmokingRecipeBuilder extends RecipeBuilder
{
	public SmokingRecipeBuilder(NamespacedKey key, ItemStack result)
	{
		super(key, result);
	}
	@Override
	protected void initRecipe(@NotNull NamespacedKey key, @NotNull ItemStack result)
	{
		if(recipe == null)
		{
			recipe = new FurnaceRecipe(key, result,Material.BARRIER,0.0f,10);
		}
	}
	
	
	private SmokingRecipe getRecipe()
	{
		return (SmokingRecipe) this.recipe;
	}
	
	/**
	 * Sets the input item to be smelted
	 *
	 *
	 * @param input the item to be smelted
	 * @return the ShapedRecipeBuilder
	 */
	public SmokingRecipeBuilder setInput(ItemStack input)
	{
		validateInit();
		getRecipe().setInput(input.getType());
		return this;
	}
	
	public SmokingRecipeBuilder setInput(Material input)
	{
		validateInit();
		getRecipe().setInput(input);
		return this;
	}
	
	public SmokingRecipeBuilder setCookTime(int ticks){
		validateInit();
		getRecipe().setCookingTime(ticks);
		return this;
	}
	
	public SmokingRecipeBuilder setExpirience(float experience){
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
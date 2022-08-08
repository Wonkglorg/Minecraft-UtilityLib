package com.wonkglorg.utilitylib.customrecipe;

import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.FurnaceRecipe;
import org.bukkit.inventory.ItemStack;

@SuppressWarnings("unused")
public class FurnaceRecipeBuilder extends RecipeBuilder
{
	
	public FurnaceRecipeBuilder(NamespacedKey key, ItemStack result)
	{
		super(key, result);
	}
	protected void initRecipe(NamespacedKey key, ItemStack result)
	{
		if(recipe == null)
		{
			recipe = new FurnaceRecipe(key, result,Material.BARRIER,0.0f,10);
		}
	}
	
	
	private FurnaceRecipe getRecipe()
	{
		return (FurnaceRecipe) this.recipe;
	}
	
	/**
	 * Sets the input item to be smelted
	 *
	 *
	 * @param input the item to be smelted
	 * @return the ShapedRecipeBuilder
	 */
	public FurnaceRecipeBuilder setInput(ItemStack input)
	{
		validateInit();
		getRecipe().setInput(input.getType());
		return this;
	}
	
	public FurnaceRecipeBuilder setInput(Material input)
	{
		validateInit();
		getRecipe().setInput(input);
		return this;
	}
	
	public FurnaceRecipeBuilder setCookTime(int ticks){
		validateInit();
		getRecipe().setCookingTime(ticks);
		return this;
	}
	
	public FurnaceRecipeBuilder setCookTime(float experience){
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
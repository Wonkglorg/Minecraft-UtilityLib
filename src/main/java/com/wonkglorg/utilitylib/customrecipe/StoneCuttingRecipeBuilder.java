package com.wonkglorg.utilitylib.customrecipe;

import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.StonecuttingRecipe;

public class StoneCuttingRecipeBuilder extends RecipeBuilder
{
	public StoneCuttingRecipeBuilder(NamespacedKey key, ItemStack result)
	{
		super(key, result);
	}
	
	private StonecuttingRecipe getRecipe()
	{
		return (StonecuttingRecipe) this.recipe;
	}
	
	@Override
	protected void initRecipe(NamespacedKey key, ItemStack result)
	{
		if(recipe == null)
		{
			recipe = new StonecuttingRecipe(key, result, Material.BARRIER);
		}
	}
	
	public StoneCuttingRecipeBuilder setInput(ItemStack input)
	{
		validateInit();
		getRecipe().setInput(input.getType());
		return this;
	}
	
	public StoneCuttingRecipeBuilder setInput(Material input)
	{
		validateInit();
		getRecipe().setInput(input);
		return this;
	}
	
	@Override
	public StonecuttingRecipe build()
	{
		return (StonecuttingRecipe) super.build();
	}
}
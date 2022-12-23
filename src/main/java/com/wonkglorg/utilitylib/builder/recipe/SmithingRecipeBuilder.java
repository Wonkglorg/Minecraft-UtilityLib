package com.wonkglorg.utilitylib.builder.recipe;

import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.RecipeChoice;
import org.bukkit.inventory.RecipeChoice.MaterialChoice;
import org.bukkit.inventory.SmithingRecipe;
import org.jetbrains.annotations.NotNull;

@SuppressWarnings("unused")
public class SmithingRecipeBuilder extends RecipeBuilder
{
	
	public SmithingRecipeBuilder(NamespacedKey key, ItemStack result)
	{
		super(key, result);
	}
	
	private NamespacedKey key;
	private ItemStack result;
	private Material base;
	private Material addition;
	
	@Override
	protected void initRecipe(@NotNull NamespacedKey key, @NotNull ItemStack result)
	{
		if(recipe == null)
		{
			this.key = key;
			this.result = result;
			recipe = new SmithingRecipe(key, result, new MaterialChoice(Material.AIR), new MaterialChoice(Material.BARRIER));
		}
	}
	
	private SmithingRecipe getRecipe()
	{
		return (SmithingRecipe) this.recipe;
	}
	
	public SmithingRecipeBuilder setBase(Material base)
	{
		validateInit();
		this.base = base;
		recipe = new SmithingRecipe(key, result, new RecipeChoice.MaterialChoice(this.base), new MaterialChoice(this.addition));
		return this;
	}
	
	public SmithingRecipeBuilder setAddition(Material addition)
	{
		validateInit();
		this.addition = addition;
		recipe = new SmithingRecipe(key, result, new RecipeChoice.MaterialChoice(this.base), new MaterialChoice(this.addition));
		return this;
	}
	
	@Override
	public SmithingRecipe build()
	{
		return (SmithingRecipe) super.build();
	}
}
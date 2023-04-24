package com.wonkglorg.utilitylib.builder.recipe;

import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.StonecuttingRecipe;
import org.bukkit.inventory.recipe.CookingBookCategory;
import org.jetbrains.annotations.NotNull;
/**
 * @author Wonkglorg
 */
@SuppressWarnings("unused")
public final class StoneCuttingRecipeBuilder extends RecipeBuilder
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
	protected void initRecipe(@NotNull NamespacedKey key, @NotNull ItemStack result)
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
	
	/**
	 * Set the group of this recipe. Recipes with the same group may be grouped
	 * together when displayed in the client.
	 *
	 * @param group recipe group. An empty string denotes no group. May not be
	 * null.
	 */
	public StoneCuttingRecipeBuilder setGroup(String group)
	{
		validateInit();
		getRecipe().setGroup(group);
		return this;
	}
	
	@Override
	public StonecuttingRecipe build()
	{
		return (StonecuttingRecipe) super.build();
	}
}
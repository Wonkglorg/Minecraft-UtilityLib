package com.wonkglorg.utilitylib.builder.recipe;

import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.FurnaceRecipe;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.SmokingRecipe;
import org.bukkit.inventory.recipe.CookingBookCategory;
import org.jetbrains.annotations.NotNull;

/**
 * @author Wonkglorg
 */
@SuppressWarnings("unused")
public final class SmokingRecipeBuilder extends RecipeBuilder{
	public SmokingRecipeBuilder(NamespacedKey key, ItemStack result) {
		super(key, result);
	}
	
	@Override
	protected void initRecipe(@NotNull NamespacedKey key, @NotNull ItemStack result) {
		if(recipe == null){
			recipe = new FurnaceRecipe(key, result, Material.BARRIER, 0.0f, 10);
		}
	}
	
	private SmokingRecipe getRecipe() {
		return (SmokingRecipe) this.recipe;
	}
	
	/**
	 * Sets the input item to be smelted
	 *
	 * @param input the item to be smelted
	 * @return the ShapedRecipeBuilder
	 */
	public SmokingRecipeBuilder setInput(ItemStack input) {
		validateInit();
		getRecipe().setInput(input.getType());
		return this;
	}
	
	public SmokingRecipeBuilder setInput(Material input) {
		validateInit();
		getRecipe().setInput(input);
		return this;
	}
	
	public SmokingRecipeBuilder setCookTime(int ticks) {
		validateInit();
		getRecipe().setCookingTime(ticks);
		return this;
	}
	
	public SmokingRecipeBuilder setExperience(float experience) {
		validateInit();
		getRecipe().setExperience(experience);
		return this;
	}
	
	public SmokingRecipeBuilder setCategory(CookingBookCategory category) {
		validateInit();
		getRecipe().setCategory(category);
		return this;
	}
	
	/**
	 * Set the group of this recipe. Recipes with the same group may be grouped together when displayed in the client.
	 *
	 * @param group recipe group. An empty string denotes no group. May not be null.
	 */
	public SmokingRecipeBuilder setGroup(String group) {
		validateInit();
		getRecipe().setGroup(group);
		return this;
	}
	
	@Override
	public FurnaceRecipe build() {
		return (FurnaceRecipe) super.build();
	}
}
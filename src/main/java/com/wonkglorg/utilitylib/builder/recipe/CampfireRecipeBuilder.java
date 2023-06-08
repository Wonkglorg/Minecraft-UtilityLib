package com.wonkglorg.utilitylib.builder.recipe;

import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.CampfireRecipe;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.RecipeChoice.ExactChoice;
import org.bukkit.inventory.recipe.CookingBookCategory;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;

/**
 * @author Wonkglorg
 */
@SuppressWarnings("unused")
public final class CampfireRecipeBuilder extends RecipeBuilder{
	public CampfireRecipeBuilder(@NotNull NamespacedKey key, @NotNull ItemStack result) {
		super(key, result);
	}
	
	@Override
	protected void initRecipe(@NotNull NamespacedKey key, @NotNull ItemStack result) {
		if(recipe == null){
			recipe = new CampfireRecipe(key, result, Material.BARRIER, 0.0f, 10);
		}
	}
	
	private CampfireRecipe getRecipe() {
		return (CampfireRecipe) this.recipe;
	}
	
	/**
	 * Sets the {@link ItemStack} input.
	 *
	 * @param input {@link ItemStack} to be smelted
	 * @return {@link CampfireRecipeBuilder}
	 */
	public CampfireRecipeBuilder setInput(@NotNull final ItemStack... input) {
		validateInit();
		getRecipe().setInputChoice(new ExactChoice(Objects.requireNonNull(input)));
		return this;
	}
	
	/**
	 * Sets the {@link Material} to be smelted
	 *
	 * @param input {@link Material} to be smelted
	 * @return {@link  CampfireRecipeBuilder}
	 */
	public CampfireRecipeBuilder setInput(@NotNull final Material input) {
		validateInit();
		getRecipe().setInput(Objects.requireNonNull(input));
		return this;
	}
	
	/**
	 * Sets the time needed to cook the time
	 *
	 * @param ticks amount in ticks
	 * @return {@link BlastingRecipeBuilder}
	 */
	public CampfireRecipeBuilder setCookTime(final int ticks) {
		validateInit();
		getRecipe().setCookingTime(ticks);
		return this;
	}
	
	/**
	 * Sets the experience gained from cooking
	 *
	 * @param experience as {@link Float}
	 * @return {@link CampfireRecipeBuilder}
	 */
	public CampfireRecipeBuilder setExperience(final float experience) {
		validateInit();
		getRecipe().setExperience(experience);
		return this;
	}
	
	public CampfireRecipeBuilder setCategory(CookingBookCategory category) {
		validateInit();
		getRecipe().setCategory(category);
		return this;
	}
	
	/**
	 * Set the group of this recipe. Recipes with the same group may be grouped together when displayed in the client.
	 *
	 * @param group recipe group. An empty string denotes no group. May not be null.
	 */
	public CampfireRecipeBuilder setGroup(String group) {
		validateInit();
		getRecipe().setGroup(group);
		return this;
	}
	
	@Override
	public CampfireRecipe build() {
		return (CampfireRecipe) super.build();
	}
	
}
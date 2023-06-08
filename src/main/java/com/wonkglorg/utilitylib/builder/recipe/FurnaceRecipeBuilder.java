package com.wonkglorg.utilitylib.builder.recipe;

import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.FurnaceRecipe;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.RecipeChoice.ExactChoice;
import org.bukkit.inventory.recipe.CookingBookCategory;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;

/**
 * @author Wonkglorg
 * <p>
 * <p>
 * The type Furnace recipe builder.
 */
@SuppressWarnings("unused")
public final class FurnaceRecipeBuilder extends RecipeBuilder{
	
	public FurnaceRecipeBuilder(@NotNull NamespacedKey key, @NotNull ItemStack result) {
		super(key, result);
	}
	
	@Override
	protected void initRecipe(@NotNull NamespacedKey key, @NotNull ItemStack result) {
		if(recipe == null){
			recipe = new FurnaceRecipe(key, result, Material.BARRIER, 0.0f, 10);
		}
	}
	
	private FurnaceRecipe getRecipe() {
		return (FurnaceRecipe) this.recipe;
	}
	
	/**
	 * Sets the {@link ItemStack} input.
	 *
	 * @param input {@link ItemStack} to be smelted
	 * @return {@link CampfireRecipeBuilder}
	 */
	public FurnaceRecipeBuilder setInput(@NotNull final ItemStack... input) {
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
	public FurnaceRecipeBuilder setInput(@NotNull final Material input) {
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
	public FurnaceRecipeBuilder setCookTime(final int ticks) {
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
	public FurnaceRecipeBuilder setExpirience(final float expirience) {
		validateInit();
		getRecipe().setExperience(expirience);
		return this;
	}
	
	public FurnaceRecipeBuilder setCategory(CookingBookCategory category) {
		validateInit();
		getRecipe().setCategory(category);
		return this;
	}
	
	public FurnaceRecipeBuilder setGroup(String group) {
		validateInit();
		getRecipe().setGroup(group);
		return this;
	}
	
	@Override
	public FurnaceRecipe build() {
		return (FurnaceRecipe) super.build();
	}
}
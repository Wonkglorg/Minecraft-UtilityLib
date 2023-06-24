package com.wonkglorg.utilitylib.builder.recipe;

import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapedRecipe;
import org.bukkit.inventory.ShapelessRecipe;
import org.bukkit.inventory.recipe.CraftingBookCategory;
import org.jetbrains.annotations.NotNull;

import java.util.Map;

/**
 * @author Wonkglorg
 */
@SuppressWarnings("unused")
public final class ShapelessRecipeBuilder extends RecipeBuilder{
	
	/**
	 * Constructs a new ShapelessRecipeBuilder for the specified result
	 *
	 * @param result result {@link ItemStack}
	 */
	public ShapelessRecipeBuilder(NamespacedKey key, ItemStack result) {
		super(key, result);
	}
	
	@Override
	protected void initRecipe(@NotNull NamespacedKey key, @NotNull ItemStack result) {
		if(recipe == null){
			recipe = new ShapelessRecipe(key, result);
		}
	}
	
	private ShapelessRecipe getRecipe() {
		return (ShapelessRecipe) recipe;
	}
	
	/**
	 * Adds an ingredient to the recipe
	 *
	 * @param ingredients ingredient {@link Material}
	 * @return the ShapelessRecipeBuilder
	 */
	public ShapelessRecipeBuilder addIngredient(Material... ingredients) {
		validateInit();
		for(Material ingredient : ingredients){
			getRecipe().addIngredient(ingredient);
		}
		return this;
	}
	
	/**
	 * Adds an ingredient to the recipe
	 *
	 * @param count amount of the ingredient
	 * @param ingredient ingredient {@link Material}
	 * @return the ShapelessRecipeBuilder
	 */
	public ShapelessRecipeBuilder addIngredient(int count, Material ingredient) {
		validateInit();
		getRecipe().addIngredient(count, ingredient);
		return this;
	}
	
	/**
	 * Adds an ingredient to the recipe
	 *
	 * @param ingredients ingredient {@link Material}
	 * @return the ShapelessRecipeBuilder
	 */
	public ShapelessRecipeBuilder addIngredient(Map<Material, Integer> ingredients) {
		validateInit();
		for(Material material : ingredients.keySet()){
			getRecipe().addIngredient(ingredients.get(material), material);
		}
		return this;
	}
	
	/**
	 * Adds an ingredient to the recipe
	 *
	 * @param ingredients ingredient {@link ItemStack}
	 * @return the ShapelessRecipeBuilder
	 */
	public ShapelessRecipeBuilder addIngredient(ItemStack... ingredients) {
		validateInit();
		for(ItemStack ingredient : ingredients){
			getRecipe().addIngredient(ingredient);
			
		}
		return this;
	}
	
	public ShapelessRecipeBuilder setCategory(CraftingBookCategory category) {
		validateInit();
		getRecipe().setCategory(category);
		return this;
	}
	
	/**
	 * Set the group of this recipe. Recipes with the same group may be grouped together when displayed in the client.
	 *
	 * @param group recipe group. An empty string denotes no group. May not be null.
	 */
	public ShapelessRecipeBuilder setGroup(String group) {
		validateInit();
		getRecipe().setGroup(group);
		return this;
	}
	
	@Override
	public ShapelessRecipe build() {
		return (ShapelessRecipe) super.build();
	}
	
}

package com.wonkglorg.utilitylib.builder.recipe;

import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.RecipeChoice;
import org.bukkit.inventory.RecipeChoice.ExactChoice;
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
	private ItemStack result = new ItemStack(Material.BARRIER);
	private RecipeChoice baseItemChoice = new RecipeChoice.MaterialChoice(Material.BARRIER);
	private RecipeChoice additionItemChoice = new RecipeChoice.MaterialChoice(Material.BARRIER);
	;
	private boolean copyNbt = false;
	
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
	
	/**
	 * Sets the {@link ItemStack}  put in the first slot of the smithing table.
	 * Note: if ItemStack is used, the entire ItemStack has to match exactly, with all enchants. nbt values and amount.
	 *
	 * @param itemStack {@link ItemStack}
	 * @return {@link SmithingRecipeBuilder}
	 */
	public SmithingRecipeBuilder setBase(ItemStack itemStack)
	{
		baseItemChoice = new ExactChoice(itemStack);
		return this;
	}
	
	/**
	 * Sets the {@link Material}  put in the first slot of the smithing table
	 *
	 * @param material {@link Material}
	 * @return {@link SmithingRecipeBuilder}
	 */
	public SmithingRecipeBuilder setBase(Material material)
	{
		baseItemChoice = new MaterialChoice(material);
		return this;
	}
	
	/**
	 * Sets the {@link Material}  put in the second slot of the smithing table
	 *
	 * @param material {@link Material}
	 * @return {@link SmithingRecipeBuilder}
	 */
	public SmithingRecipeBuilder setAddition(Material material)
	{
		additionItemChoice = new MaterialChoice(material);
		return this;
	}
	/**
	 * Sets the {@link ItemStack}  put in the second slot of the smithing table.
	 * Note: if ItemStack is used, the entire ItemStack has to match exactly, with all enchants. nbt values and amount.
	 *
	 * @param itemStack {@link ItemStack}
	 * @return {@link SmithingRecipeBuilder}
	 */
	public SmithingRecipeBuilder setAddition(ItemStack itemStack)
	{
		additionItemChoice = new ExactChoice(itemStack);
		return this;
	}
	
	/**
	 *  Whether to copy the nbt of the item set in the first slot to the output item.
	 * @param copy True or False
	 * @return {@link SmithingRecipeBuilder}
	 */
	public SmithingRecipeBuilder copyNbt(boolean copy)
	{
		copyNbt = copy;
		return this;
	}
	
	@Override
	public SmithingRecipe build()
	{
		recipe = new SmithingRecipe(key, result, baseItemChoice, additionItemChoice, copyNbt);
		return (SmithingRecipe) super.build();
	}
}
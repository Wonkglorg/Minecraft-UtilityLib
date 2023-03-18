package com.wonkglorg.utilitylib.item;

import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.Optional;
import java.util.function.BiPredicate;

@SuppressWarnings("unused")
public enum ItemTrait
{
	
	/**
	 * For comparing the durability of two items
	 */
	DURABILITY((a, b) -> a.getDurability() == b.getDurability()),
	/**
	 * For comparing the amount of two items
	 */
	AMOUNT((a, b) -> a.getAmount() == b.getAmount()),
	/**
	 * For comparing the display name of two items
	 */
	NAME((a, b) -> Optional.ofNullable(a.getItemMeta())
						   .map(ItemMeta::displayName)
						   .equals(Optional.ofNullable(b.getItemMeta()).map(ItemMeta::displayName))),
	/**
	 * For comparing the lore of two items
	 */
	LORE((a, b) -> Optional.ofNullable(a.getItemMeta()).map(ItemMeta::lore).equals(Optional.ofNullable(b.getItemMeta()).map(ItemMeta::lore))),
	/**
	 * For comparing the enchantments of two items
	 */
	ENCHANTMENTS((a, b) -> a.getEnchantments().equals(b.getEnchantments())),
	/**
	 * For comparing the types of two items
	 */
	TYPE((a, b) -> a.getType() == b.getType());
	
	private final BiPredicate<ItemStack, ItemStack> compare;
	
	ItemTrait(BiPredicate<ItemStack, ItemStack> compare)
	{
		this.compare = compare;
	}
	
	/**
	 * Compares this trait on the two items
	 *
	 * @param a The first item
	 * @param b The second item
	 * @return True if the trait is the same on both items, false otherwise
	 */
	public boolean compare(ItemStack a, ItemStack b)
	{
		return compare.test(a, b);
	}
}
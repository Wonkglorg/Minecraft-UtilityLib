package com.wonkglorg.utilitylib.builder.equipment;

import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;

/**
 * @author Wonkglorg
 */
@SuppressWarnings("unused")
public final class EquipmentItem{
	private ItemStack itemStack;
	private float dropChance;
	
	public EquipmentItem(@NotNull ItemStack itemStack) {
		this.itemStack = itemStack;
	}
	
	public EquipmentItem(ItemStack itemStack, float dropChance) {
		this.itemStack = Objects.requireNonNull(itemStack);
		this.dropChance = dropChance;
	}
	
	public ItemStack getItemStack() {
		return itemStack;
	}
	
	public float getDropChance() {
		return dropChance;
	}
	
	public void setItemStack(@NotNull ItemStack itemStack) {
		this.itemStack = Objects.requireNonNull(itemStack);
	}
	
	public void setDropChance(float dropChance) {
		this.dropChance = dropChance;
	}
	
	@Override
	public String toString() {
		return "ItemStack: " + itemStack + ", Drop-Chance: " + dropChance;
	}
}
package com.wonkglorg.utilitylib.builder.equipment;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;

public class EquipmentItem
{
	private ItemStack itemStack;
	private float dropChance;
	
	public EquipmentItem(@NotNull ItemStack itemStack)
	{
		this.itemStack = itemStack;
	}
	
	public EquipmentItem(ItemStack itemStack, float dropChance)
	{
		this.itemStack = itemStack;
		this.dropChance = dropChance;
	}
	
	public ItemStack getItemStack()
	{
		return itemStack != null ? itemStack : new ItemStack(Material.AIR);
	}
	
	public float getDropChance()
	{
		return dropChance;
	}
	
	public void setItemStack(ItemStack itemStack)
	{
		this.itemStack = itemStack;
	}
	
	public void setDropChance(float dropChance)
	{
		this.dropChance = dropChance;
	}
}
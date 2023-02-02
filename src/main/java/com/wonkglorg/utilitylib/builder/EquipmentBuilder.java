package com.wonkglorg.utilitylib.builder;

import org.bukkit.entity.LivingEntity;
import org.bukkit.inventory.EntityEquipment;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.Map;

public class EquipmentBuilder
{
	private final LivingEntity livingEntity;
	Map<@NotNull EquipmentSlot, @NotNull EquipmentItem> equipmentMap = new HashMap<>();
	private boolean silent = true;
	
	public EquipmentBuilder(@NotNull LivingEntity livingEntity)
	{
		this.livingEntity = livingEntity;
	}
	
	public LivingEntity build()
	{
		
		EntityEquipment equipment = livingEntity.getEquipment();
		
		if(equipment == null)
		{
			return livingEntity;
		}
		
		for(EquipmentSlot equipmentSlot : equipmentMap.keySet())
		{
			EquipmentItem equipmentItem = equipmentMap.get(equipmentSlot);
			equipment.setItem(equipmentSlot, equipmentMap.get(equipmentSlot).getItemStack(), silent);
			equipment.setDropChance(equipmentSlot, equipmentItem.getDropChance());
			
		}
		
		return livingEntity;
		
	}
	
	public EquipmentBuilder setHelmet(ItemStack helmet)
	{
		if(helmet == null)
		{
			return this;
		}
		equipmentMap.put(EquipmentSlot.HEAD, new EquipmentItem(helmet, 0));
		return this;
	}
	
	public EquipmentBuilder setHelmet(ItemStack helmet, float dropChance)
	{
		if(helmet == null)
		{
			return this;
		}
		equipmentMap.put(EquipmentSlot.HEAD, new EquipmentItem(helmet, dropChance));
		return this;
	}
	
	public EquipmentBuilder setChestplate(ItemStack chestplate)
	{
		if(chestplate == null)
		{
			return this;
		}
		equipmentMap.put(EquipmentSlot.CHEST, new EquipmentItem(chestplate, 0));
		return this;
	}
	
	public EquipmentBuilder setChestplate(ItemStack chestplate, float dropChance)
	{
		if(chestplate == null)
		{
			return this;
		}
		equipmentMap.put(EquipmentSlot.CHEST, new EquipmentItem(chestplate, dropChance));
		return this;
	}
	
	public EquipmentBuilder setLeggings(ItemStack leggings)
	{
		if(leggings == null)
		{
			return this;
		}
		equipmentMap.put(EquipmentSlot.LEGS, new EquipmentItem(leggings, 0));
		return this;
	}
	
	public EquipmentBuilder setLeggings(ItemStack leggings, float dropChance)
	{
		if(leggings == null)
		{
			return this;
		}
		equipmentMap.put(EquipmentSlot.LEGS, new EquipmentItem(leggings, dropChance));
		return this;
	}
	
	public EquipmentBuilder setBoots(ItemStack boots)
	{
		if(boots == null)
		{
			return this;
		}
		equipmentMap.put(EquipmentSlot.FEET, new EquipmentItem(boots, 0));
		return this;
	}
	
	public EquipmentBuilder setBoots(ItemStack boots, float dropChance)
	{
		if(boots == null)
		{
			return this;
		}
		equipmentMap.put(EquipmentSlot.FEET, new EquipmentItem(boots, dropChance));
		return this;
	}
	
	public EquipmentBuilder setMainHand(ItemStack mainHand)
	{
		if(mainHand == null)
		{
			return this;
		}
		equipmentMap.put(EquipmentSlot.HAND, new EquipmentItem(mainHand, 0));
		return this;
	}
	
	public EquipmentBuilder setMainHand(ItemStack mainHand, float dropChance)
	{
		if(mainHand == null)
		{
			return this;
		}
		equipmentMap.put(EquipmentSlot.HAND, new EquipmentItem(mainHand, dropChance));
		return this;
	}
	
	public EquipmentBuilder setOffHand(ItemStack mainHand)
	{
		if(mainHand == null)
		{
			return this;
		}
		equipmentMap.put(EquipmentSlot.OFF_HAND, new EquipmentItem(mainHand, 0));
		return this;
	}
	
	public EquipmentBuilder setOffHand(ItemStack offHand, float dropChance)
	{
		if(offHand == null)
		{
			return this;
		}
		equipmentMap.put(EquipmentSlot.OFF_HAND, new EquipmentItem(offHand, dropChance));
		return this;
	}
	
	public EquipmentBuilder setSilent(boolean silent)
	{
		this.silent = silent;
		return this;
	}
	
	public EquipmentBuilder setItem(EquipmentSlot equipmentSlot, ItemStack itemStack)
	{
		if(equipmentSlot == null || itemStack == null)
		{
			return this;
		}
		equipmentMap.put(equipmentSlot, new EquipmentItem(itemStack, 0));
		return this;
	}
	
	public EquipmentBuilder setItem(EquipmentSlot equipmentSlot, ItemStack itemStack, float dropChance)
	{
		if(equipmentSlot == null || itemStack == null)
		{
			return this;
		}
		equipmentMap.put(equipmentSlot, new EquipmentItem(itemStack, dropChance));
		return this;
	}
	
	private static class EquipmentItem
	{
		private ItemStack itemStack;
		private float dropChance;
		
		public EquipmentItem()
		{
		}
		
		public EquipmentItem(ItemStack itemStack)
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
			return itemStack;
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
	
	;
	
}
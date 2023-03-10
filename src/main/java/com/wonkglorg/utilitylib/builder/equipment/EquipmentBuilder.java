package com.wonkglorg.utilitylib.builder.equipment;

import org.bukkit.entity.LivingEntity;
import org.bukkit.inventory.EntityEquipment;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;

import javax.annotation.concurrent.ThreadSafe;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * This class  constructs and updates a LivingEntity's equipment
 * using an {@link EntityEquipment} object.
 */
@SuppressWarnings("unused")
@ThreadSafe
public final class EquipmentBuilder
{
	private LivingEntity livingEntity;
	private final ConcurrentHashMap<@NotNull EquipmentSlot, @NotNull EquipmentItem> equipmentMap = new ConcurrentHashMap<>();
	private boolean silent = true;
	private final Object lock = new Object();
	
	/**
	 * Constructs a new {@code EquipmentBuilder}.
	 */
	public EquipmentBuilder()
	{
	}
	
	/**
	 * Constructs a new {@code EquipmentBuilder} with the given {@code LivingEntity}.
	 *
	 * @param livingEntity the {@link LivingEntity} to update.
	 */
	public EquipmentBuilder(@NotNull LivingEntity livingEntity)
	{
		this.livingEntity = livingEntity;
	}
	
	/**
	 * Updates the {@link LivingEntity}'s equipment with the equipment set using this builder.
	 * <p>
	 * If the {@code livingEntity} has not been set, this method will return null.
	 *
	 * @return the updated {@link LivingEntity}.
	 */
	public synchronized LivingEntity build()
	{
		if(livingEntity == null)
		{
			return null;
		}
		
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
	
	/**
	 * Sets the {@link LivingEntity} to update.
	 *
	 * @param livingEntity the {@link LivingEntity} to update.
	 */
	public synchronized void setLivingEntity(@NotNull LivingEntity livingEntity)
	{
		this.livingEntity = livingEntity;
	}
	
	/**
	 * Sets the helmet equipment for the {@code LivingEntity}.
	 *
	 * @param helmet the helmet {@link ItemStack}.
	 * @return the {@code EquipmentBuilder} object.
	 */
	public synchronized EquipmentBuilder setHelmet(ItemStack helmet)
	{
		if(helmet == null)
		{
			return this;
		}
		equipmentMap.put(EquipmentSlot.HEAD, new EquipmentItem(helmet, 0));
		return this;
	}
	
	public synchronized EquipmentBuilder setHelmet(ItemStack helmet, float dropChance)
	{
		if(helmet == null)
		{
			return this;
		}
		equipmentMap.put(EquipmentSlot.HEAD, new EquipmentItem(helmet, dropChance));
		return this;
	}
	
	public synchronized EquipmentBuilder setChestplate(ItemStack chestplate)
	{
		if(chestplate == null)
		{
			return this;
		}
		equipmentMap.put(EquipmentSlot.CHEST, new EquipmentItem(chestplate, 0));
		return this;
	}
	
	public synchronized EquipmentBuilder setChestplate(ItemStack chestplate, float dropChance)
	{
		if(chestplate == null)
		{
			return this;
		}
		equipmentMap.put(EquipmentSlot.CHEST, new EquipmentItem(chestplate, dropChance));
		return this;
	}
	
	public synchronized EquipmentBuilder setLeggings(ItemStack leggings)
	{
		if(leggings == null)
		{
			return this;
		}
		equipmentMap.put(EquipmentSlot.LEGS, new EquipmentItem(leggings, 0));
		return this;
	}
	
	public synchronized EquipmentBuilder setLeggings(ItemStack leggings, float dropChance)
	{
		if(leggings == null)
		{
			return this;
		}
		equipmentMap.put(EquipmentSlot.LEGS, new EquipmentItem(leggings, dropChance));
		return this;
	}
	
	public synchronized EquipmentBuilder setBoots(ItemStack boots)
	{
		if(boots == null)
		{
			return this;
		}
		equipmentMap.put(EquipmentSlot.FEET, new EquipmentItem(boots, 0));
		return this;
	}
	
	public synchronized EquipmentBuilder setBoots(ItemStack boots, float dropChance)
	{
		if(boots == null)
		{
			return this;
		}
		equipmentMap.put(EquipmentSlot.FEET, new EquipmentItem(boots, dropChance));
		return this;
	}
	
	public synchronized EquipmentBuilder setMainHand(ItemStack mainHand)
	{
		if(mainHand == null)
		{
			return this;
		}
		equipmentMap.put(EquipmentSlot.HAND, new EquipmentItem(mainHand, 0));
		return this;
	}
	
	public synchronized EquipmentBuilder setMainHand(ItemStack mainHand, float dropChance)
	{
		if(mainHand == null)
		{
			return this;
		}
		equipmentMap.put(EquipmentSlot.HAND, new EquipmentItem(mainHand, dropChance));
		return this;
	}
	
	public synchronized EquipmentBuilder setOffHand(ItemStack mainHand)
	{
		if(mainHand == null)
		{
			return this;
		}
		equipmentMap.put(EquipmentSlot.OFF_HAND, new EquipmentItem(mainHand, 0));
		return this;
	}
	
	public synchronized EquipmentBuilder setOffHand(ItemStack offHand, float dropChance)
	{
		if(offHand == null)
		{
			return this;
		}
		equipmentMap.put(EquipmentSlot.OFF_HAND, new EquipmentItem(offHand, dropChance));
		return this;
	}
	
	public synchronized EquipmentBuilder setSilent(boolean silent)
	{
		this.silent = silent;
		return this;
	}
	
	public synchronized EquipmentBuilder setItem(EquipmentSlot equipmentSlot, ItemStack itemStack)
	{
		if(equipmentSlot == null || itemStack == null)
		{
			return this;
		}
		equipmentMap.put(equipmentSlot, new EquipmentItem(itemStack, 0));
		return this;
	}
	
	public synchronized EquipmentBuilder setItem(EquipmentSlot equipmentSlot, ItemStack itemStack, float dropChance)
	{
		if(equipmentSlot == null || itemStack == null)
		{
			return this;
		}
		equipmentMap.put(equipmentSlot, new EquipmentItem(itemStack, dropChance));
		return this;
	}
	
	public EquipmentBuilder setItem(EquipmentSlot equipmentSlot, EquipmentItem equipmentItem)
	{
		if(equipmentSlot == null || equipmentItem == null)
		{
			return this;
		}
		
		equipmentMap.put(equipmentSlot, equipmentItem);
		return this;
	}
	
}
package com.wonkglorg.utilitylib.builder.equipment;

import org.bukkit.entity.LivingEntity;
import org.bukkit.inventory.EntityEquipment;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;

import javax.annotation.concurrent.ThreadSafe;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author Wonkglorg
 * <p>
 * <p>
 * This class  constructs and updates a LivingEntity's equipment using an {@link EntityEquipment} object.
 */
@SuppressWarnings("unused")
@ThreadSafe
public final class EquipmentBuilder{
	private LivingEntity livingEntity;
	private final Map<@NotNull EquipmentSlot, @NotNull EquipmentItem> equipmentMap = new ConcurrentHashMap<>();
	private boolean silent = true;
	private final Object lock = new Object();
	
	/**
	 * Constructs a new {@code EquipmentBuilder}.
	 */
	public EquipmentBuilder() {
		
	}
	
	/**
	 * Constructs a new {@code EquipmentBuilder} with the given {@code LivingEntity}.
	 *
	 * @param livingEntity the {@link LivingEntity} to update.
	 */
	public EquipmentBuilder(@NotNull LivingEntity livingEntity) {
		this.livingEntity = Objects.requireNonNull(livingEntity);
	}
	
	/**
	 * Updates the {@link LivingEntity}'s equipment with the equipment set using this builder.
	 * <p>
	 * If the {@code livingEntity} has not been set, this method will return null.
	 *
	 * @return the updated {@link LivingEntity}.
	 */
	public synchronized LivingEntity build() {
		EntityEquipment equipment = livingEntity.getEquipment();
		if(equipment == null){
			return livingEntity;
		}
		for(EquipmentSlot equipmentSlot : equipmentMap.keySet()){
			com.wonkglorg.utilitylib.builder.equipment.EquipmentItem equipmentItem = equipmentMap.get(equipmentSlot);
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
	public synchronized EquipmentBuilder setLivingEntity(@NotNull LivingEntity livingEntity) {
		this.livingEntity = livingEntity;
		return this;
	}
	
	public synchronized EquipmentBuilder setSilent(boolean silent) {
		this.silent = silent;
		return this;
	}
	
	public synchronized EquipmentBuilder setItem(EquipmentSlot equipmentSlot, ItemStack itemStack) {
		return setItem(equipmentSlot, new com.wonkglorg.utilitylib.builder.equipment.EquipmentItem(itemStack, 0));
	}
	
	public synchronized EquipmentBuilder setItem(EquipmentSlot equipmentSlot, ItemStack itemStack, float dropChance) {
		return setItem(equipmentSlot, new com.wonkglorg.utilitylib.builder.equipment.EquipmentItem(itemStack, dropChance));
	}
	
	public EquipmentBuilder setItem(@NotNull EquipmentSlot equipmentSlot, @NotNull EquipmentItem equipmentItem) {
		equipmentMap.put(Objects.requireNonNull(equipmentSlot), Objects.requireNonNull(equipmentItem));
		return this;
	}
	
}
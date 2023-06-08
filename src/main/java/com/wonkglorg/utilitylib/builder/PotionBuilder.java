package com.wonkglorg.utilitylib.builder;

import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.PotionMeta;
import org.bukkit.potion.PotionData;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.potion.PotionType;

import java.util.List;
import java.util.Map;

/**
 * @author Wonkglorg
 */
@SuppressWarnings("unused")
public final class PotionBuilder extends ItemBuilder{
	/**
	 *
	 */
	public PotionBuilder(int amount, String name, List<String> lore, List<ItemFlag> flags, Map<Enchantment, Integer> enchantments) {
		super(Material.POTION, amount, name, lore, 0, null, flags, enchantments);
	}
	
	/**
	 *
	 */
	public PotionBuilder(int amount, String name) {
		super(Material.POTION, amount, name);
	}
	
	/**
	 *
	 */
	public PotionBuilder(int amount) {
		super(Material.POTION, amount);
	}
	
	/**
	 *
	 */
	public PotionBuilder(ItemFlag... flags) {
		super(Material.POTION, flags);
	}
	
	/**
	 *
	 */
	public PotionBuilder(String... lore) {
		super(Material.POTION, List.of(lore));
	}
	
	/**
	 *
	 */
	public PotionBuilder(String name) {
		super(Material.POTION, name);
	}
	
	/**
	 *
	 */
	public PotionBuilder() {
		super(Material.POTION);
	}
	
	/**
	 *
	 */
	public PotionMeta getMeta() {
		if(super.getMeta() == null){
			super.setItem(new ItemStack(getMaterial(), getAmount()));
			super.setMeta(getItem().getItemMeta());
		}
		return (PotionMeta) super.getMeta();
	}
	
	/**
	 * @param potionEffectType the potion effect type
	 * @return {@link PotionBuilder}
	 */
	public PotionBuilder effect(PotionType potionEffectType) {
		this.getMeta().setBasePotionData(new PotionData(potionEffectType));
		return this;
	}
	
	/**
	 * @return {@link PotionBuilder}
	 */
	public PotionBuilder effect(PotionType potionEffectType, boolean extended, boolean upgraded) {
		this.getMeta().setBasePotionData(new PotionData(potionEffectType, extended, upgraded));
		return this;
	}
	
	/**
	 * @return {@link PotionBuilder}
	 */
	public PotionBuilder effect(PotionEffectType potionEffectType, int duration, int value) {
		this.getMeta().addCustomEffect(new PotionEffect(potionEffectType, duration, value), true);
		return this;
	}
}
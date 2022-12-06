package com.wonkglorg.utilitylib.enchants;

import com.wonkglorg.utilitylib.message.Convert;
import com.wonkglorg.utilitylib.message.Message;
import net.kyori.adventure.text.Component;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public abstract class CustomEnchantment extends Enchantment
{
	private final String name;
	
	public CustomEnchantment(@NotNull NamespacedKey key, String name)
	{
		super(key);
		this.name = name;
	}
	
	abstract List<Enchantment> conflicts();
	
	abstract List<Material> enchantAble();
	
	public abstract boolean isTradeable();
	
	@Override
	public @NotNull String getName()
	{
		return name;
	}
	
	@Override
	public boolean conflictsWith(@NotNull Enchantment other)
	{
		return conflicts().contains(other);
	}
	
	@Override
	public boolean canEnchantItem(@NotNull ItemStack item)
	{
		return enchantAble().contains(item.getType());
	}
	
	@Override
	public @NotNull Component displayName(int level)
	{
		return Message.color(getName() + " " + Convert.toRoman(level));
	}
	
}
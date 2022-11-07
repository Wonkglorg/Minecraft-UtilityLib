package com.wonkglorg.utilitylib.managers;

import org.bukkit.NamespacedKey;
import org.bukkit.enchantments.Enchantment;
import org.jetbrains.annotations.NotNull;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
@SuppressWarnings("unchecked")
public class EnchantmentManager implements Manager
{
	List<Enchantment> enchantmentList = new ArrayList<>();
	
	public void add(@NotNull Enchantment... enchantment)
	{
		enchantmentList.addAll(List.of(enchantment));
	}
	
	@Override
	public void onShutdown()
	{
		unregisterEnchants();
	}
	
	@Override
	public void onStartup()
	{
		registerEnchantments();
	}
	
	public void registerEnchantments()
	{
		enchantmentList.forEach(this::registerEnchantment);
	}
	
	private void registerEnchantment(@NotNull Enchantment enchantment)
	{
		try
		{
			Field f = Enchantment.class.getDeclaredField("acceptingNew");
			f.setAccessible(true);
			f.set(null, true);
			Enchantment.registerEnchantment(enchantment);
		} catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
	private void unregisterEnchants()
	{
		try
		{
			Field keyField = Enchantment.class.getDeclaredField("byKey");
			
			keyField.setAccessible(true);
			HashMap<NamespacedKey, Enchantment> byKey = (HashMap<NamespacedKey, Enchantment>) keyField.get(null);
			enchantmentList.forEach(enchantment -> byKey.remove(enchantment.getKey()));
			Field nameField = Enchantment.class.getDeclaredField("byName");
			nameField.setAccessible(true);
			HashMap<String, Enchantment> byName = (HashMap<String, Enchantment>) nameField.get(null);
			enchantmentList.forEach(enchantment -> byName.remove(enchantment.getName()));
		} catch(Exception ignored)
		{
		}
	}
}
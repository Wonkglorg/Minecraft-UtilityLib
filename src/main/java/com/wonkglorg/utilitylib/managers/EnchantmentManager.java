package com.wonkglorg.utilitylib.managers;

import com.wonkglorg.utilitylib.logger.Logger;
import org.bukkit.NamespacedKey;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.NotNull;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;

@SuppressWarnings("unchecked")
public class EnchantmentManager implements Manager
{
	private final Collection<Enchantment> enchantmentList = new ArrayList<>();
	private final JavaPlugin plugin;
	
	public EnchantmentManager(JavaPlugin plugin)
	{
		this.plugin = plugin;
	}
	
	public void add(@NotNull Enchantment... enchantment)
	{
		enchantmentList.addAll(List.of(enchantment));
	}
	
	public void add(@NotNull Collection<Enchantment> enchantment)
	{
		enchantmentList.addAll(enchantment);
	}
	
	public void add(@NotNull Enchantment enchantment)
	{
		enchantmentList.add(enchantment);
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
		if(!enchantmentList.isEmpty()){
			Logger.log(plugin, "Loaded " + enchantmentList.size() + " enchants!");
		}
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
package com.wonkglorg.utilitylib.convert;

import com.wonkglorg.utilitylib.config.Config;
import net.kyori.adventure.key.Key;
import org.bukkit.NamespacedKey;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;

import java.util.HashMap;
import java.util.Map;
/**
 * @author Wonkglorg
 */
@SuppressWarnings("unused")
public final class ConvertTextToItem
{
	private final String path;
	private final Config config;
	private ItemStack item;
	private String name;
	private String description;
	private Map<Enchantment, Integer> enchantments;
	
	public ConvertTextToItem(Config config, String path)
	{
		this.path = path;
		this.config = config;
	}
	
	/**
	 * Converts String to enchantment using specific format
	 *
	 * Enchantment String format
	 * CategoryName:
	 * 		fireAspect: 3
	 * 		flame: 5
	 *
	 *
	 * @param path Path to check for the formatted String
	 * @return Enchantment Integer Map
	 */
	public Map<Enchantment, Integer> stringToEnchantment(String path)
	{
		check();
		Map<Enchantment, Integer> map = new HashMap<>();
		
		if(config.getString(path) == null)
		{
			return null;
		}
		for(String s : config.getStringList(path))
		{
			Enchantment enchantment = Enchantment.getByKey(new NamespacedKey(Key.MINECRAFT_NAMESPACE, s));
			int level = config.getInt(path + "." + s);
			map.put(enchantment, level);
		}
		
		return map;
	}
	
	public String enchantmentToString(){
		//converts enchantment map to string for yml
		return null;
	}
	
	//Also add enchant options to only check specific path
	
	public ItemStack build()
	{
		ItemStack item;
		
		return null;
	}
	
	private boolean check()
	{
		if(config == null || path == null)
		{
			return false;
		}
		return true;
	}
	
}
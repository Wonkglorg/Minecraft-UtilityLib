package com.wonkglorg.utilitylib.builder;

import com.wonkglorg.utilitylib.utils.DateUtils;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@SuppressWarnings("unused")
public final class CooldownBuilder
{
	//maybe rework how the cooldowns are applied?
	public static Map<String, Map<UUID, Long>> cooldowns = new HashMap<>();
	
	/**
	 * @param
	 * @return
	 */
	public static Map<UUID, Long> getCooldownMap(String key)
	{
		return cooldowns.getOrDefault(key, null);
	}
	
	/**
	 *
	 */
	public static void clear()
	{
		cooldowns.clear();
	}
	
	/**
	 * @param key
	 */
	public static void createCooldown(String key)
	{
		cooldowns.putIfAbsent(key, new HashMap<>());
	}
	
	public static void removeCooldown(String key, UUID uuid)
	{
		
		createCooldown(key);
		
		getCooldownMap(key).remove(uuid);
	}
	
	/**
	 * @param key
	 * @param player
	 */
	public static void removeCooldown(String key, Player player)
	{
		removeCooldown(key, player.getUniqueId());
	}
	
	public static void addCooldown(String key, UUID uuid, int seconds)
	{
		
		createCooldown(key);
		
		long next = System.currentTimeMillis() + seconds * 1000L;
		getCooldownMap(key).put(uuid, next);
	}
	
	public static void addEntry(String key, Player player, Long time)
	{
		createCooldown(key);
		
		getCooldownMap(key).put(player.getUniqueId(), time);
	}
	
	public static void addEntry(String key, UUID uuid, Long time)
	{
		createCooldown(key);
		
		getCooldownMap(key).put(uuid, time);
	}
	
	/**
	 * @param key
	 * @param player
	 * @param seconds
	 */
	public static void addCooldown(String key, Player player, int seconds)
	{
		addCooldown(key, player.getUniqueId(), seconds);
	}
	
	/**
	 * @param key
	 * @param uuid
	 * @return boolean
	 */
	public static boolean isCooldown(String key, UUID uuid)
	{
		
		createCooldown(key);
		Map<UUID, Long> map = cooldowns.get(key);
		
		return (map.containsKey(uuid)) && (System.currentTimeMillis() <= map.get(uuid));
	}
	
	/**
	 * @param key
	 * @param player
	 * @return boolean
	 */
	public static boolean isCooldown(String key, Player player)
	{
		return isCooldown(key, player.getUniqueId());
	}
	
	/**
	 * @param key
	 * @param uuid
	 * @return long
	 */
	public static long getCooldown(String key, UUID uuid)
	{
		
		createCooldown(key);
		Map<UUID, Long> map = cooldowns.get(key);
		
		return map.getOrDefault(uuid, 0L) - System.currentTimeMillis();
	}
	
	/**
	 * @param key
	 * @param player
	 * @return long
	 */
	public static long getCooldown(String key, Player player)
	{
		return getCooldown(key, player.getUniqueId());
	}
	
	/**
	 * @param key
	 * @param player
	 * @return
	 */
	public static String getCooldownAsString(String key, UUID player)
	{
		return DateUtils.toString(getCooldown(key, player));
	}
	
}

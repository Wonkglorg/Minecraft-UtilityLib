package com.wonkglorg.utilitylib.managers;

import com.wonkglorg.utilitylib.message.Convert;
import org.bukkit.entity.Player;
import org.bukkit.util.Consumer;

import javax.annotation.concurrent.ThreadSafe;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

@SuppressWarnings("unused")
@ThreadSafe
public final class CooldownManager implements Manager
{
	//maybe rework how the cooldowns are applied?
	public static Map<String, Map<UUID, Long>> cooldowns = new ConcurrentHashMap<>();
	private boolean isStarted = false;
	private Consumer<Map<String, Map<UUID, Long>>> producer;
	private Consumer<Map<String, Map<UUID, Long>>> consumer;
	
	public CooldownManager(Consumer<Map<String, Map<UUID, Long>>> producer, Consumer<Map<String, Map<UUID, Long>>> consumer)
	{
		this.producer = producer;
		this.consumer = consumer;
	}
	
	public CooldownManager()
	{
	}
	
	/**
	 * @param
	 * @return
	 */
	public synchronized Map<UUID, Long> getCooldownMap(String key)
	{
		return cooldowns.getOrDefault(key, null);
	}
	
	/**
	 *
	 */
	public synchronized void clear()
	{
		cooldowns.clear();
	}
	
	/**
	 * @param key
	 */
	public synchronized void createCooldown(String key)
	{
		cooldowns.putIfAbsent(key, new HashMap<>());
	}
	
	public synchronized void removeCooldown(String key, UUID uuid)
	{
		
		createCooldown(key);
		
		getCooldownMap(key).remove(uuid);
	}
	
	/**
	 * @param key
	 * @param player
	 */
	public synchronized void removeCooldown(String key, Player player)
	{
		removeCooldown(key, player.getUniqueId());
	}
	
	public synchronized void addCooldown(String key, UUID uuid, int seconds)
	{
		
		createCooldown(key);
		
		long next = System.currentTimeMillis() + seconds * 1000L;
		getCooldownMap(key).put(uuid, next);
	}
	
	public synchronized void addEntry(String key, Player player, Long time)
	{
		createCooldown(key);
		
		getCooldownMap(key).put(player.getUniqueId(), time);
	}
	
	public synchronized void addEntry(String key, UUID uuid, Long time)
	{
		createCooldown(key);
		
		getCooldownMap(key).put(uuid, time);
	}
	
	/**
	 * @param key
	 * @param player
	 * @param seconds
	 */
	public synchronized void addCooldown(String key, Player player, int seconds)
	{
		addCooldown(key, player.getUniqueId(), seconds);
	}
	
	/**
	 * @param key
	 * @param uuid
	 * @return boolean
	 */
	public synchronized boolean isCooldown(String key, UUID uuid)
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
	public synchronized boolean isCooldown(String key, Player player)
	{
		return isCooldown(key, player.getUniqueId());
	}
	
	/**
	 * @param key
	 * @param uuid
	 * @return long
	 */
	public synchronized long getCooldown(String key, UUID uuid)
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
	public synchronized long getCooldown(String key, Player player)
	{
		return getCooldown(key, player.getUniqueId());
	}
	
	/**
	 * @param key
	 * @param player
	 * @return
	 */
	public synchronized String getCooldownAsString(String key, UUID player)
	{
		return Convert.toTime(getCooldown(key, player));
	}
	
	@Override
	public void onShutdown()
	{
		if(consumer == null)
		{
			return;
		}
		consumer.accept(cooldowns);
	}
	
	@Override
	public void onStartup()
	{
		if(isStarted)
		{
			return;
		}
		isStarted = true;
		if(producer == null)
		{
			return;
		}
		producer.accept(cooldowns);
	}
}

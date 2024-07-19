package com.wonkglorg.utilitylib.manager.managers;


import com.wonkglorg.utilitylib.base.Convert;
import org.bukkit.entity.Player;

import javax.annotation.concurrent.ThreadSafe;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

@SuppressWarnings("unused")
@ThreadSafe
public final class CooldownManager {
    //maybe rework how the cooldowns are applied?
    /**
     * A cooldown map that is split into different cooldowns, ehere each entry is a list of players and an entry of "time of addition + cooldown"
     */
    private final Map<String, Map<UUID, Cooldown>> cooldowns = new ConcurrentHashMap<>();

    public CooldownManager() {
    }

    /**
     * @param
     * @return
     */
    public synchronized Map<UUID, Cooldown> getCooldowns(String key) {
        return cooldowns.getOrDefault(key, null);
    }

    /**
     *
     */
    public synchronized void clear() {
        cooldowns.clear();
    }

    /**
     * @param key
     */
    public synchronized void createCooldown(String key) {
        cooldowns.putIfAbsent(key, new HashMap<>());
    }

    public synchronized void removeCooldown(String key, UUID uuid) {

        createCooldown(key);

        getCooldowns(key).remove(uuid);
    }

    /**
     * @param key
     * @param player
     */
    public synchronized void removeCooldown(String key, Player player) {
        removeCooldown(key, player.getUniqueId());
    }

    public synchronized void addCooldown(String key, UUID uuid, int seconds) {
        createCooldown(key);

        long next = System.currentTimeMillis() + seconds * 1000L;
        getCooldowns(key).put(uuid, new Cooldown(next));
    }

    public synchronized void addEntry(String key, Player player, long time) {
        createCooldown(key);

        getCooldowns(key).put(player.getUniqueId(), new Cooldown(time));
    }

    public synchronized void addEntry(String key, UUID uuid, Long time) {
        createCooldown(key);

        getCooldowns(key).put(uuid, new Cooldown(time));
    }

    /**
     * @param key
     * @param player
     * @param seconds
     */
    public synchronized void addCooldown(String key, Player player, int seconds) {
        addCooldown(key, player.getUniqueId(), seconds);
    }

    /**
     * Checks if the player has a cooldown left for the specified key
     *
     * @param key  the key to check
     * @param uuid the player to check
     * @return boolean true if the player has a cooldown left false if no entry exists or it expired.
     */
    public synchronized boolean isCooldown(String key, UUID uuid) {

        createCooldown(key);
        return getCooldown(key, uuid).isExpired();
    }

    /**
     * Checks if the player has a cooldown left for the specified key
     *
     * @param key    the key to check
     * @param player the player to check
     * @return boolean true if the player has a cooldown left false if no entry exists or it expired.
     */
    public synchronized boolean isCooldown(String key, Player player) {
        return isCooldown(key, player.getUniqueId());
    }

    /**
     * gets the remaining cooldown for the player or an empty cooldown with 0 time if no entry exists
     *
     * @param key  the key to check
     * @param uuid the player to check
     * @return the remaining cooldown
     */
    public synchronized Cooldown getCooldown(String key, UUID uuid) {
        createCooldown(key);
        return cooldowns.get(key).getOrDefault(uuid, new Cooldown(0));
    }

    /**
     * @param key
     * @param player
     * @return long
     */
    public synchronized Cooldown getCooldown(String key, Player player) {
        return getCooldown(key, player.getUniqueId());
    }

    /**
     * @param key
     * @param player
     * @return
     */
    public synchronized String getCooldownAsString(String key, UUID player) {
        return Convert.toTime((getCooldown(key, player)).getRemainingTime());
    }

    public Map<String, Map<UUID, Cooldown>> getCooldownMap() {
        return cooldowns;
    }

}

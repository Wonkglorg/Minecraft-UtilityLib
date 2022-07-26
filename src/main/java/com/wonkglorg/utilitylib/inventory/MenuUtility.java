package com.wonkglorg.utilitylib.inventory;

import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Predicate;

/**
 * Unique to each Player when opening a menu, stores data.
 */
@SuppressWarnings("unused")
public class MenuUtility
{
	protected final Player owner;
	private static final Map<Player, MenuUtility> menuUtilityMap = new HashMap<>();
	
	/**
	 * Instantiates a new Menu utility.
	 *
	 * @param player the player
	 */
	public MenuUtility(Player player)
	{
		this.owner = player;
	}
	
	/**
	 * Gets owner.
	 *
	 * @return {@link Player} owning the menu.
	 */
	public Player getOwner()
	{
		return owner;
	}
	
	/**
	 * Loads the MenuUtility for the specified {@link Player} or creates a new one if there is non.
	 *
	 * @param player {@link Player} opening the menu.
	 * @return MenuUtility linked to the {@link Player}.
	 */
	public static MenuUtility get(@NotNull Player player, MenuUtility menuUtility)
	{
		
		//find way to make menuUtility get method more variable
		MenuUtility playerMenuUtility;
		menuUtilityMap.keySet().removeIf(Predicate.not(Player::isValid));
		if(!(menuUtilityMap.containsKey(player)))
		{
			playerMenuUtility = menuUtility != null ?  menuUtility : new MenuUtility(player);
			menuUtilityMap.put(player, playerMenuUtility);
			
			return playerMenuUtility;
		}
		return menuUtilityMap.get(player);
	}
	
	/**
	 * Loads the MenuUtility for the specified {@link Player} or creates a new one if there is non.
	 *
	 * @param player {@link Player} opening the menu.
	 * @return MenuUtility linked to the {@link Player}.
	 */
	public static MenuUtility get(@NotNull Player player)
	{
		return get(player, null);
	}
	
	/**
	 * Get MenuUtility map.
	 *
	 * @return MenuUtility map
	 */
	public static Map<Player, MenuUtility> getMenuUtilityMap()
	{
		return menuUtilityMap;
	}
	
	/**
	 * Add entry to MenuUtility map.
	 *
	 * @param player the player
	 * @param menuUtility the MenuUtility instance
	 */
	public static void addEntry(Player player, MenuUtility menuUtility)
	{
		menuUtilityMap.put(player, menuUtility);
	}
	
	/**
	 * Remove entry from MenuUtility map.
	 * Returns true if removed, false if nothing changed.
	 *
	 * @param player the player
	 * @return the boolean
	 */
	public static boolean removeEntry(Player player)
	{
		return menuUtilityMap.remove(player) != null;
	}
	
}

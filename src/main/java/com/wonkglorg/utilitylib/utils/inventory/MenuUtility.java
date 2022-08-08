package com.wonkglorg.utilitylib.utils.inventory;

import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.Map;

/**
 * Unique to each Player when opening a menu, stores data.
 */
@SuppressWarnings("unused")
public class MenuUtility
{
	
	private final Player owner;
	
	public MenuUtility(Player player)
	{
		this.owner = player;
	}
	
	/**
	 *
	 * @return {@link Player} owning the menu.
	 */
	public Player getOwner()
	{
		return owner;
	}
	
	private static final Map<Player, MenuUtility> menuUtilityMap = new HashMap<>();
	
	/**
	 * Loads the MenuUtility for the specified {@link Player} or creates a new one if there is non.
	 *
	 * @param player {@link Player} opening the menu.
	 * @return MenuUtility linked to the {@link Player}.
	 */
	public static MenuUtility get(@NotNull Player player)
	{
		MenuUtility playerMenuUtility;
		if(!(menuUtilityMap.containsKey(player)))
		{
			playerMenuUtility = new MenuUtility(player);
			menuUtilityMap.put(player, playerMenuUtility);
			
			return playerMenuUtility;
		} else
		{
			return menuUtilityMap.get(player);
		}
	}
	
}

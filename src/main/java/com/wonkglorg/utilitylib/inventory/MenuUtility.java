package com.wonkglorg.utilitylib.inventory;

import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Predicate;

@SuppressWarnings("unused")
public class MenuUtility
{
	protected final Player owner;
	private static final Map<Player, MenuUtility> menuUtilityMap = new HashMap<>();
	
	public MenuUtility(Player player)
	{
		this.owner = player;
	}
	/**
	 * Gets owner of the menu.
	 *
	 * @return {@link Player} owning the menu.
	 */
	public Player getOwner()
	{
		return owner;
	}
	
	public void addEntry(Player player, MenuUtility menuUtility)
	{
		menuUtilityMap.put(player, menuUtility);
	}
	
	public boolean removeEntry(Player player)
	{
		return menuUtilityMap.remove(player) != null;
	}
	/**
	 * Loads the MenuUtility for the specified {@link Player} or creates a new default implementation if there is non.
	 *
	 * @param player {@link Player} opening the menu.
	 * @return MenuUtility linked to the {@link Player}.
	 */
	public static MenuUtility get(@NotNull Player player, MenuUtility menuUtility)
	{
		
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
	
}
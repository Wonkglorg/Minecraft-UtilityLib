package com.wonkglorg.utilitylib.inventory;

import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Predicate;

@SuppressWarnings("unused")
public class MenuUtilityBackup
{
	protected final Player owner;
	private static final Map<Player, MenuUtilityBackup> menuUtilityMap = new HashMap<>();
	
	public MenuUtilityBackup(Player player)
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
	
	public void addEntry(Player player, MenuUtilityBackup menuUtility)
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
	public static MenuUtilityBackup get(@NotNull Player player, MenuUtilityBackup menuUtility)
	{
		
		MenuUtilityBackup playerMenuUtility;
		menuUtilityMap.keySet().removeIf(Predicate.not(Player::isValid));
		if(!(menuUtilityMap.containsKey(player)))
		{
			playerMenuUtility = menuUtility != null ?  menuUtility : new MenuUtilityBackup(player);
			menuUtilityMap.put(player, playerMenuUtility);
			
			return playerMenuUtility;
		}
		return menuUtilityMap.get(player);
	}
	
}
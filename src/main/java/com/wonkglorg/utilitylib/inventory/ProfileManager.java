package com.wonkglorg.utilitylib.inventory;

import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.Map;

public class ProfileManager<T extends Profile>
{
	protected Map<Player, T> utilityMap = new HashMap<>();
	
	public T get(Player player, T menuUtility)
	{
		T menuInstance = utilityMap.get(player);
		
		if(menuInstance == null)
		{
			menuInstance = menuUtility;
			utilityMap.put(player, menuUtility);
		}
		return menuInstance;
	}
	
	public void remove(Player player)
	{
		utilityMap.remove(player);
	}
	
}
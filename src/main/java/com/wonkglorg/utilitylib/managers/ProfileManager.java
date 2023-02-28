package com.wonkglorg.utilitylib.managers;

import com.wonkglorg.utilitylib.inventory.Profile;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.Map;

public class ProfileManager<T extends Profile> implements Manager
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
	
	@Override
	public void onShutdown()
	{
		utilityMap.clear();
	}
	
	@Override
	public void onStartup()
	{
	
	}
}
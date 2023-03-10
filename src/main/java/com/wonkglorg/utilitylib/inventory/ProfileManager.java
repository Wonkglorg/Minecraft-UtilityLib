package com.wonkglorg.utilitylib.inventory;

import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Predicate;

@SuppressWarnings("unused")
public final class ProfileManager<T extends Profile>
{
	private T menu;
	private final Map<Player, T> utilityMap = new HashMap<>();
	
	public ProfileManager(@NotNull T menu)
	{
		this.menu = menu;
	}
	
	public void setDefaultMenu(@NotNull T menu)
	{
		this.menu = menu;
	}
	
	public T get(Player player)
	{
		T profile = (T) menu.clone();
		profile.setOwner(player);
		utilityMap.keySet().removeIf(Predicate.not(Player::isValid));
		return utilityMap.computeIfAbsent(player, k -> profile);
	}
	
	public void remove(Player player)
	{
		utilityMap.remove(player);
	}
	
}
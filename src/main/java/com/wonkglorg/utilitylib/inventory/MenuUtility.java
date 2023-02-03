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
public class MenuUtility extends AbstractMenuUtility
{
	
	public MenuUtility(Player player)
	{
		super(player);
	}
}

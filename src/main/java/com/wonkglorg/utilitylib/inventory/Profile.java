package com.wonkglorg.utilitylib.inventory;

import org.bukkit.entity.Player;

@SuppressWarnings("unused")
public class Profile
{
	protected final Player owner;
	
	public Profile(Player player)
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
	
}
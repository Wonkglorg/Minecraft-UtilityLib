package com.wonkglorg.utilitylib.inventory;

import org.bukkit.entity.Player;

@SuppressWarnings("unused")
public class Profile implements Cloneable
{
	protected Player owner;
	
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
	
	public void setOwner(Player owner)
	{
		this.owner = owner;
	}
	
	@Override
	public Profile clone()
	{
		try
		{
			return (Profile) super.clone();
		} catch(CloneNotSupportedException e)
		{
			throw new AssertionError();
		}
	}
	
}
package com.wonkglorg.utilitylib.inventory.profile;

import org.bukkit.entity.Player;

@SuppressWarnings("unused")
public class MenuProfile implements Cloneable{
	protected Player owner;
	
	public MenuProfile(Player player) {
		this.owner = player;
	}
	
	/**
	 * Gets owner of the menu.
	 *
	 * @return {@link Player} owning the menu.
	 */
	public Player getOwner() {
		return owner;
	}
	
	public void setOwner(Player owner) {
		this.owner = owner;
	}
	
	@Override
	public MenuProfile clone() {
		try{
			return (MenuProfile) super.clone();
		} catch(CloneNotSupportedException e){
			throw new AssertionError();
		}
	}
	
}
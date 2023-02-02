package com.wonkglorg.utilitylib.builder;

import com.wonkglorg.utilitylib.selection.Cuboid;
import org.bukkit.Location;

@SuppressWarnings("unused")
public class SelectionBuilder
{
	protected Cuboid cuboid;
	protected Location location1, location2;
	
	public Cuboid build()
	{
		return new Cuboid(location1, location2);
	}
	
	public SelectionBuilder setCorner1(Location location)
	{
		this.location1 = location;
		return this;
	}
	
	public SelectionBuilder setCorner2(Location location)
	{
		this.location2 = location;
		return this;
	}
	
}
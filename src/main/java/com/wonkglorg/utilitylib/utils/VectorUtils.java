package com.wonkglorg.utilitylib.utils;

import org.bukkit.Location;
import org.bukkit.util.Vector;

public class VectorUtils
{
	/**
	 * Generates a vector between 2 points
	 * @param a
	 * @param b
	 * @param normalize
	 * @return
	 */
	public Vector genVec(Location loc1, Location loc2)
	{
		return loc2.toVector().subtract(loc1.toVector());
	}
	
}
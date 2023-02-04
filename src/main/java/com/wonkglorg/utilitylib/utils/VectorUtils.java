package com.wonkglorg.utilitylib.utils;

import org.bukkit.Location;
import org.bukkit.util.Vector;

public class VectorUtils
{
	/**
	 * Generates a vector between 2 points
	 * @param loc1
	 * @param loc2
	 * @return
	 */
	public static Vector genVec(Location loc1, Location loc2)
	{
		return loc2.toVector().subtract(loc1.toVector());
	}
	
}
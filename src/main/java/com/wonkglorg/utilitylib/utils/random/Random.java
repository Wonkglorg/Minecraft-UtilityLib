package com.wonkglorg.utilitylib.utils.random;

import org.bukkit.util.Vector;

/**
 * The type Random.
 */
public class Random
{
	/**
	 * @param velocity starting velocity
	 * @param spray degree offset from velocity
	 */
	public static Vector ProjectileSpread(Vector velocity, double spray)
	{
		double speed = velocity.length();
		Vector direction = new Vector(velocity.getX() / speed, velocity.getY() / speed, velocity.getZ() / speed);
		return new Vector(direction.getX() + (Math.random() - 0.5) / spray,
				direction.getY() + (Math.random() - 0.5) / spray,
				direction.getZ() + (Math.random() - 0.5) / spray).normalize().multiply(speed);
	}
}
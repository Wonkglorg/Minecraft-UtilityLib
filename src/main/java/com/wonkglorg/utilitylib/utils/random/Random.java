package com.wonkglorg.utilitylib.utils.random;

import org.bukkit.util.Vector;

/**
 * The type Random.
 */
@SuppressWarnings("unused")
public class Random
{
	/**
	 * Rand projectile spread vector.
	 *
	 * @param velocity starting velocity
	 * @param spray degree offset from velocity
	 * @return the vector
	 */
	public static Vector RandProjectileSpread(Vector velocity, double spray)
	{
		double speed = velocity.length();
		Vector direction = new Vector(velocity.getX() / speed, velocity.getY() / speed, velocity.getZ() / speed);
		return new Vector(direction.getX() + (Math.random() - 0.5) * spray,
				direction.getY() + (Math.random() - 0.5) * spray,
				direction.getZ() + (Math.random() - 0.5) * spray).normalize().multiply(speed);
	}
	
	/**
	 * Rand projectile speed vector.
	 *
	 * @param velocity the velocity
	 * @param randSpeed the rand speed
	 * @return the vector
	 */
	public static Vector RandProjectileSpeed(Vector velocity, double randSpeed)
	{
		double speed = velocity.length() + Math.random() * randSpeed;
		return new Vector(velocity.getX(), velocity.getY(), velocity.getZ()).normalize().multiply(speed);
	}
	
}
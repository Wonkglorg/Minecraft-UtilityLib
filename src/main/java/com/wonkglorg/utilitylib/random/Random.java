package com.wonkglorg.utilitylib.random;

import org.bukkit.util.Vector;

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

/**
 * The type Random.
 */
@SuppressWarnings("unused")
public final class Random
{
	/**
	 * Rand projectile spread vector.
	 *
	 * @param velocity starting velocity
	 * @param spray degree offset from velocity
	 * @return the vector
	 */
	public static Vector ProjectileSpread(Vector velocity, double spray)
	{
		double speed = velocity.length();
		Vector direction = new Vector(velocity.getX() / speed, velocity.getY() / speed, velocity.getZ() / speed);
		
		//test if this return statement works?
		return direction.add(new Vector((Math.random() - 0.5) * spray, (Math.random() - 0.5) * spray, (Math.random() - 0.5) * spray))
						.normalize()
						.multiply(speed);
	}
	
	/**
	 * Rand projectile speed vector.
	 *
	 * @param velocity the velocity
	 * @param randSpeed the rand speed
	 * @return the vector
	 */
	public static Vector ProjectileSpeed(Vector velocity, double randSpeed)
	{
		double speed = velocity.length() + Math.random() * randSpeed;
		return new Vector(velocity.getX(), velocity.getY(), velocity.getZ()).normalize().multiply(speed);
	}
	
	/**
	 * Returns an Integer between a and b
	 *
	 * @param a first number
	 * @param b second number
	 * @return number between a and b
	 */
	public static int getNumberBetween(int a, int b)
	{
		if(a == b)
		{
			return a;
		}
		int min = Math.min(a, b);
		int max = Math.max(a, b);
		return ThreadLocalRandom.current().nextInt(min, max);
	}
	
	/**
	 * Returns a Double between a and b
	 *
	 * @param a first number
	 * @param b second number
	 * @return number between a and b
	 */
	public static double getNumberBetween(double a, double b)
	{
		if(a == b)
		{
			return a;
		}
		double min = Math.min(a, b);
		double max = Math.max(a, b);
		return ThreadLocalRandom.current().nextDouble(min, max);
	}
	
	/**
	 * Returns a Long between a and b
	 *
	 * @param a first number
	 * @param b second number
	 * @return number between a and b
	 */
	public static long getNumberBetween(long a, long b)
	{
		if(a == b)
		{
			return a;
		}
		long min = Math.min(a, b);
		long max = Math.max(a, b);
		return ThreadLocalRandom.current().nextLong(min, max);
	}
	
	/**
	 * Get random element from list
	 *
	 * @param element element
	 * @return element
	 */
	public static <T> T randomElement(List<T> element)
	{
		if(element.size() == 0)
		{
			return null;
		}
		if(element.size() == 1)
		{
			return element.get(0);
		}
		return element.get(ThreadLocalRandom.current().nextInt(element.size()));
	}
	
}
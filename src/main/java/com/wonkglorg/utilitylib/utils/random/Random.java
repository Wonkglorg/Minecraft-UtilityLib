package com.wonkglorg.utilitylib.utils.random;

import org.bukkit.util.Vector;

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

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
	public static Vector ProjectileSpread(Vector velocity, double spray)
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
	public static Vector ProjectileSpeed(Vector velocity, double randSpeed)
	{
		double speed = velocity.length() + Math.random() * randSpeed;
		return new Vector(velocity.getX(), velocity.getY(), velocity.getZ()).normalize().multiply(speed);
	}
	
	/**
	 * Returns an Integer between a and b
	 *
	 * @param a
	 * @param b
	 * @return number between a and b
	 */
	public static int getNumberBetween(int a, int b)
	{
		return ThreadLocalRandom.current().nextInt(a, b);
	}
	
	/**
	 * Returns a Double between a and b
	 *
	 * @param a
	 * @param b
	 * @return number between a and b
	 */
	public static double getNumberBetween(double a, double b)
	{
		return ThreadLocalRandom.current().nextDouble(a, b);
	}
	
	/**
	 * Returns a Float between a and b
	 *
	 * @param a
	 * @param b
	 * @return number between a and b
	 */
	public static float getNumberBetween(float a, float b)
	{
		return ThreadLocalRandom.current().nextFloat(a, b);
	}
	
	
	/**
	 * Returns a Long between a and b
	 *
	 * @param a
	 * @param b
	 * @return number between a and b
	 */
	public static long getNumberBetween(long a, long b)
	{
		return ThreadLocalRandom.current().nextLong(a, b);
	}
	
	/**
	 * Get random element from list
	 *
	 * @param element
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
		java.util.Random random = new java.util.Random();
		return element.get(random.nextInt(element.size()));
	}
	
}
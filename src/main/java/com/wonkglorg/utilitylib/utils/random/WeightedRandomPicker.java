package com.wonkglorg.utilitylib.utils.random;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Creates a randomized weighted list picker.
 *
 * @param <T> the type parameter
 */
@SuppressWarnings("unused")
public final class WeightedRandomPicker<T>
{
	
	private class Entry
	{
		/**
		 * The Accumulated weight.
		 */
		double accumulatedWeight;
		/**
		 * The Object.
		 */
		T object;
	}
	
	private final List<Entry> entries = new ArrayList<>();
	private double accumulatedWeight;
	private final Random rand = new Random();
	
	/**
	 * Add entry to list.
	 *
	 * @param object the object
	 * @param weight the weight
	 */
	public void addEntry(T object, double weight)
	{
		accumulatedWeight += weight;
		Entry e = new Entry();
		e.object = object;
		e.accumulatedWeight = accumulatedWeight;
		entries.add(e);
	}
	
	/**
	 * Gets a random element from the list can not return null
	 *
	 * @return the element
	 */
	public T getRandom()
	{
		double r = rand.nextDouble() * accumulatedWeight;
		
		for(Entry entry : entries)
		{
			if(entry.accumulatedWeight >= r)
			{
				return entry.object;
			}
		}
		return null;
	}
	
	/**
	 * Gets a random element from the list can return null if the accumulated weight is less than the threshold
	 *
	 * @param threshold the threshold to compare
	 * @return the t
	 */
	public T getRandom(double threshold)
	{
		
		if(threshold < accumulatedWeight)
		{
			return getRandom();
		}
		
		double r = rand.nextDouble() * threshold;
		for(Entry entry : entries)
		{
			if(entry.accumulatedWeight >= r)
			{
				return entry.object;
			}
		}
		return null;
		
	}
	
	/**
	 * Gets all entries from the list
	 *
	 * @return the entries
	 */
	public List<Entry> getEntries()
	{
		return entries;
	}
	
	/**
	 * Gets accumulated weight of all values combines.
	 *
	 * @return the accumulated weight
	 */
	public double getAccumulatedWeight()
	{
		return accumulatedWeight;
	}
}

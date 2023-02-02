package com.wonkglorg.utilitylib.maps;

import java.util.HashMap;

/**
 * Creates a HashMap with a default value if the return value is null
 * @param <K>
 * @param <V>
 */
public class DefaultHashMap<K, V> extends HashMap<K, V>
{
	protected V defaultValue;
	
	public DefaultHashMap(V defaultValue)
	{
		this.defaultValue = defaultValue;
	}
	
	@Override
	public V get(Object k)
	{
		return containsKey(k) ? super.get(k) : defaultValue;
	}
}
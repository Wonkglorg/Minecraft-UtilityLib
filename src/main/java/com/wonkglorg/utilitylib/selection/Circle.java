package com.wonkglorg.utilitylib.selection;

import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.configuration.serialization.ConfigurationSerializable;
import org.jetbrains.annotations.NotNull;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class Circle implements Iterable<Block>, Cloneable, ConfigurationSerializable, Selection
{
	
	
	//check how this works
	
	@NotNull
	@Override
	public Iterator<Block> iterator()
	{
		return null;
	}
	
	@Override
	public @NotNull Map<String, Object> serialize()
	{
		return null;
	}
	
	@Override
	public List<Block> getBlocks()
	{
		return null;
	}
	
	@Override
	public World getWorld()
	{
		return null;
	}
	
	@Override
	public Location getCenter()
	{
		return null;
	}
	
	@Override
	public void expand()
	{
	
	}
	
	@Override
	public void contract()
	{
	
	}
}
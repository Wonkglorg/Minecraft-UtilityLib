package com.wonkglorg.utilitylib.selection;

import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.block.Block;

import java.util.List;

public interface Selection
{
	List<Block> getBlocks();
	
	World getWorld();
	
	Location getCenter();
	
	void expand();
	
	void contract();
	
	
	
	
}
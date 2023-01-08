package com.wonkglorg.utilitylib.utils.fun;

import net.kyori.adventure.text.BlockNBTComponent.WorldPos.Coordinate;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.FallingBlock;
import org.bukkit.util.Vector;

import java.util.List;

public class ExplosionBuilder
{
	private Location location;
	private List<Block> blocks;
	private float power;
	private boolean physics;
	private double chance = 100;
	private double launchPower;
	
	/*
	public void position(Location location)
	{
		this.location = location;
	}
	
	public void power(float power)
	{
		this.power = power;
	}
	
	 */
	
	public void blocks(List<Block> blocks)
	{
		this.blocks = blocks;
	}
	
	public void blockConversionChance(double chance)
	{
		this.chance = chance;
	}
	
	public void explosionLaunchPower(double launchPower)
	{
		this.launchPower = launchPower;
	}
	
	public void fallingBlocks(boolean physics)
	{
		this.physics = physics;
	}
	
	public void build()
	{
		if(blocks == null)
		{
			return;
		}
		
		if(launchPower == 0)
		{
			return;
		}
		
		blocks.forEach(block ->
		{
			if(physics)
			{
				if(chance >= Math.random() * 100)
				{
					Vector vector = new Vector(-5 + Math.random() * launchPower, -5 + Math.random() * launchPower, -5 + Math.random() * launchPower);
					
					FallingBlock fallingBlock = block.getWorld().spawnFallingBlock(block.getLocation(), block.getBlockData());
					fallingBlock.setDropItem(false);
					fallingBlock.setVelocity(vector);
					
					block.setType(Material.AIR);
				}
			}
		});
		
		//option to set how much % of blocks should be converted
		
		//how strong explosion power if not preset explosion event
		
	}
	
}
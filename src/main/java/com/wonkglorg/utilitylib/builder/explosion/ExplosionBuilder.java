package com.wonkglorg.utilitylib.builder.explosion;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.block.Block;
import org.bukkit.entity.FallingBlock;
import org.bukkit.entity.TNTPrimed;
import org.bukkit.event.Listener;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.util.Vector;

import java.util.List;

public class ExplosionBuilder implements Listener
{
	private final JavaPlugin plugin;
	private Location location;
	private List<Block> blocks;
	private float power;
	private boolean physics;
	private double chance = 100;
	private double launchPower;
	private int fuseTime = 80;
	private boolean allowChainExplosions = false;
	private boolean incendiary = false;
	
	public ExplosionBuilder(JavaPlugin plugin)
	{
		this.plugin = plugin;
	}
	
	public ExplosionBuilder(JavaPlugin plugin,
							List<Block> blocks,
							double chance,
							double launchPower,
							boolean physics,
							int fuseTime,
							boolean incendiary)
	{
		this.plugin = plugin;
		this.chance = chance;
		this.blocks = blocks;
		this.launchPower = launchPower;
		this.physics = physics;
		this.fuseTime = fuseTime;
		this.incendiary = incendiary;
		
	}
	
	public ExplosionBuilder blocks(List<Block> blocks)
	{
		this.blocks = blocks;
		return this;
	}
	
	public ExplosionBuilder createExplosion(Location location, float power, boolean incendiary)
	{
		location.createExplosion(power, incendiary);
		
		return this;
	}
	
	public ExplosionBuilder blockConversionChance(double chance)
	{
		this.chance = chance;
		return this;
	}
	
	public ExplosionBuilder explosionLaunchPower(double launchPower)
	{
		this.launchPower = launchPower;
		return this;
	}
	
	public ExplosionBuilder fallingBlocks(boolean physics)
	{
		this.physics = physics;
		return this;
	}
	
	public ExplosionBuilder setFuseTimeOnTnTChain(int fuseTime)
	{
		this.fuseTime = fuseTime;
		return this;
	}
	
	public ExplosionBuilder setIncendiary(boolean incendiary)
	{
		this.incendiary = incendiary;
		return this;
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
		for(Block block : blocks)
		{
			if(block.getType() == Material.TNT && allowChainExplosions)
			{
				block.setType(Material.AIR);
				TNTPrimed tnt = block.getWorld().spawn(block.getLocation(), TNTPrimed.class);
				tnt.setFuseTicks(fuseTime);
				tnt.setIsIncendiary(incendiary);
				PersistentDataContainer dataContainer = tnt.getPersistentDataContainer();
				dataContainer.set(new NamespacedKey(plugin, "explosion"), PersistentDataType.STRING, "explosion");
				dataContainer.set(new NamespacedKey(plugin, "chance"), PersistentDataType.DOUBLE, chance);
				dataContainer.set(new NamespacedKey(plugin, "physics"), PersistentDataType.STRING, String.valueOf(physics));
				dataContainer.set(new NamespacedKey(plugin, "fuseTime"), PersistentDataType.INTEGER, fuseTime);
				dataContainer.set(new NamespacedKey(plugin, "launch_power"), PersistentDataType.DOUBLE, launchPower);
				dataContainer.set(new NamespacedKey(plugin, "incendiary"), PersistentDataType.STRING, String.valueOf(incendiary));
				
				continue;
			}
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
		}
		
		//option to set how much % of blocks should be converted
		
		//how strong explosion power if not preset explosion event
		
	}
	
}
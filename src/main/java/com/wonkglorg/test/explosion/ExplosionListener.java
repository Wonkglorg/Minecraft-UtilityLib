package com.wonkglorg.utilitylib.builder.explosion;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityExplodeEvent;
import org.bukkit.plugin.java.JavaPlugin;

public class ExplosionListener implements Listener
{
	private JavaPlugin plugin;
	private static ExplosionListener explosionListener;
	
	private ExplosionListener(JavaPlugin plugin)
	{
		this.plugin = plugin;
	}
	
	public static ExplosionListener get(JavaPlugin plugin)
	{
		if(explosionListener == null)
		{
			explosionListener = new ExplosionListener(plugin);
		}
		return explosionListener;
		
	}
	
	@EventHandler
	public void onExplosion(EntityExplodeEvent e)
	{
		/*
		PersistentDataContainer dataContainer = e.getEntity().getPersistentDataContainer();
		boolean hasKey = dataContainer.has(new NamespacedKey(plugin,"explosion"));
		double chance = dataContainer.get(new NamespacedKey(plugin,"chance"), PersistentDataType.DOUBLE);
		double launchPower= dataContainer.get(new NamespacedKey(plugin,"launch_power"), PersistentDataType.DOUBLE);
		boolean physics = dataContainer.get(new NamespacedKey(plugin,"chance"), PersistentDataType.BYTE);
		int fuseTime = dataContainer.get(new NamespacedKey(plugin,"fuseTime"), PersistentDataType.INTEGER);
		boolean incendiary = dataContainer.get(new NamespacedKey(plugin,"incendiary"), PersistentDataType.BYTE);
		if(hasKey){
			new ExplosionBuilder(plugin,e.blockList(),chance,launchPower,physics,fuseTime,incendiary);
		}
		
		 */
	}
	
}
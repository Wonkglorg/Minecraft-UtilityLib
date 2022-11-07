package com.wonkglorg.utilitylib.utils.players;

import com.wonkglorg.utilitylib.utils.message.Message;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Consumer;

public class PlayerUtil
{
	
	private static final List<UUID> teleportPlayers = new ArrayList<>();
	
	/**
	 * Allows you to clear a player's inventory, remove potion effects and put
	 * him on life support
	 *
	 * @param player
	 */
	public static void clearPlayer(Player player)
	{
		player.getInventory().clear();
		player.getInventory().setBoots(null);
		player.getInventory().setChestplate(null);
		player.getInventory().setLeggings(null);
		player.getInventory().setHelmet(null);
		player.getPlayer().setItemOnCursor(null);
		player.getPlayer().setFireTicks(0);
		player.getPlayer().getOpenInventory().getTopInventory().clear();
		player.setGameMode(GameMode.SURVIVAL);
		player.getPlayer().getActivePotionEffects().forEach(e ->
		{
			player.getPlayer().removePotionEffect(e.getType());
		});
	}
	
	/**
	 * Allows you to check if an inventory will contain armor or items
	 *
	 * @param player
	 * @return boolean
	 */
	public static boolean inventoryContainsItem(Player player)
	{
		
		ItemStack itemStack = player.getInventory().getBoots();
		if(itemStack != null)
		{
			return true;
		}
		
		itemStack = player.getInventory().getChestplate();
		if(itemStack != null)
		{
			return true;
		}
		
		itemStack = player.getInventory().getLeggings();
		if(itemStack != null)
		{
			return true;
		}
		
		itemStack = player.getInventory().getHelmet();
		if(itemStack != null)
		{
			return true;
		}
		
		for(ItemStack itemStack1 : player.getInventory().getContents())
		{
			if(itemStack1 != null)
			{
				return true;
			}
		}
		
		return false;
	}
	
	/**
	 * Allows you to check if the inventory is full
	 *
	 * @param player
	 * @return true if the player's inventory is full
	 */
	public static boolean hasFullInventory(Player player)
	{
		int slot = 0;
		PlayerInventory inventory = player.getInventory();
		for(int a = 0; a != 36; a++)
		{
			ItemStack itemStack = inventory.getContents()[a];
			if(itemStack == null)
			{
				slot++;
			}
		}
		return slot == 0;
	}
	
	/**
	 * Remove the item from the player's main hand
	 *
	 * @param player
	 */
	public static void removeItemInMainHand(Player player)
	{
		removeItemInMainHand(player, 64);
	}
	
	/**
	 * Remove the item from the player's offhand
	 *
	 * @param player
	 */
	public static void removeItemInMOffHand(Player player)
	{
		removeItemInOffHand(player, 64);
	}
	
	/**
	 * Remove the item from the player's main hand
	 *
	 * @param player
	 * @param amount of items to withdraw
	 */
	public static void removeItemInMainHand(Player player, int amount)
	{
		ItemStack itemInMainHand = player.getInventory().getItemInMainHand();
		if(itemInMainHand.getAmount() > amount)
		{
			itemInMainHand.setAmount(itemInMainHand.getAmount() - amount);
		} else
		
		{
			player.getInventory().setItemInMainHand(new ItemStack(Material.AIR));
		}
		player.updateInventory();
	}
	
	/**
	 * Remove the item from the player's off hand
	 *
	 * @param player
	 * @param amount of items to withdraw
	 */
	public static void removeItemInOffHand(Player player, int amount)
	{
		ItemStack itemInMainHand = player.getInventory().getItemInMainHand();
		if(itemInMainHand.getAmount() > amount)
		{
			itemInMainHand.setAmount(itemInMainHand.getAmount() - amount);
		} else
		
		{
			player.getInventory().setItemInMainHand(new ItemStack(Material.AIR));
		}
		player.updateInventory();
	}
	
	/**
	 * Check if two locations are identical
	 *
	 * @param l location
	 * @param l2 location
	 * @return true if both rentals are the same
	 */
	public static boolean compareLocation(Location l, Location l2)
	{
		return (l.getBlockX() == l2.getBlockX()) &&
			   (l.getBlockY() == l2.getBlockY()) &&
			   (l.getBlockZ() == l2.getBlockZ()) &&
			   l.getWorld().getName().equals(l2.getWorld().getName());
	}
	
	/**
	 * Teleport a player to a given location with a given delay
	 *
	 * @param player who will be teleported
	 * @param delay before the teleportation of the player
	 * @param location where the player will be teleported
	 */
	public static void teleport(Player player, int delay, Location location)
	{
		teleport(player, delay, location, null);
	}
	
	/**
	 * Teleport a player to a given location with a given delay
	 *
	 * @param player who will be teleported
	 * @param delay before the teleportation of the player
	 * @param location where the player will be teleported
	 * @param cmd executed when the player is teleported or not
	 */
	public static void teleport(Player player, int delay, Location location, Consumer<Boolean> cmd)
	{
		if(teleportPlayers.contains(player.getUniqueId()))
		{
			Message.msgPlayer(player, "Error cannot teleport to yourself");
			return;
		}
		ScheduledExecutorService ses = Executors.newScheduledThreadPool(1);
		Location playerLocation = player.getLocation();
		AtomicInteger verif = new AtomicInteger(delay);
		teleportPlayers.add(player.getUniqueId());
		if(!location.getChunk().isLoaded())
		{
			location.getChunk().load();
		}
		ses.scheduleWithFixedDelay(() ->
		{
			if(!compareLocation(playerLocation, player.getLocation()))
			{
				Message.msgPlayer(player, "Cancelled teleport");
				ses.shutdown();
				teleportPlayers.remove(player.getUniqueId());
				if(cmd != null)
				{
					cmd.accept(false);
				}
				return;
			}
			int currentSecond = verif.getAndDecrement();
			if(!player.isOnline())
			{
				ses.shutdown();
				teleportPlayers.remove(player.getUniqueId());
				return;
			}
			if(currentSecond == 0)
			{
				ses.shutdown();
				teleportPlayers.remove(player.getUniqueId());
				player.teleport(location);
				Message.msgPlayer(player, "Success Teleported " + player.getName() + "!");
				if(cmd != null)
				{
					cmd.accept(true);
				}
			} else
			{
				Message.msgPlayer(player, "Teleporting in: " + currentSecond);
			}
		}, 0, 1, TimeUnit.SECONDS);
	}
	
}
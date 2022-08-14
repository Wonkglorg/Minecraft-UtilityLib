package com.wonkglorg.utilitylib.utils;

import com.sun.source.util.Plugin;
import com.wonkglorg.utilitylib.utils.builder.CooldownBuilder;
import com.wonkglorg.utilitylib.utils.builder.TimerBuilder;
import com.wonkglorg.utilitylib.utils.entity.EntityUtil;
import com.wonkglorg.utilitylib.utils.message.Message;
import com.wonkglorg.utilitylib.utils.serializer.SerializeItems;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.block.BlockFace;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.potion.PotionEffectType;

import javax.naming.Name;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Locale;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;
import java.util.UUID;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.BiConsumer;
import java.util.function.Consumer;

@SuppressWarnings("unused")
public class Utils extends Message
{
	
	
	// EDIT AND MOVE STUFF FROM THIS FILE JUST TEMPORARY AND CLEAN UP
	private static final List<UUID> teleportPlayers = new ArrayList<>();
	
	/**
	 * Allows to encode an itemstack in base64
	 *
	 * @param item - ItemStack
	 * @return the encoded item
	 */
	public static String encode(ItemStack item)
	{
		return SerializeItems.itemToBase64(item);
	}
	
	/**
	 * Allows to decode a string in ItemStack
	 *
	 * @param item - the encoded itemstack
	 * @return the decoded item
	 */
	public static ItemStack decode(String item)
	{
		return SerializeItems.itemFromBase64(item);
	}
	
	/**
	 * Allows to obtain a random number between a and b
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
	 * Allows you to check if the inventory is full
	 *
	 * @param player
	 * @return true if the player's inventory is full
	 */
	public static boolean hasInventoryFull(Player player)
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
	 * Gives an item to the player, if the player's inventory is full then the
	 * item will drop to the ground
	 *
	 * @param player
	 * @param item
	 */
	public static void give(Player player, ItemStack item)
	{
		if(hasInventoryFull(player))
		{
			player.getWorld().dropItem(player.getLocation(), item);
		} else
		{
			player.getInventory().addItem(item);
		}
	}
	
	/**
	 * Allows to check if an itemstack has a display name
	 *
	 * @return boolean
	 */
	public static boolean hasDisplayName(ItemStack itemStack)
	{
		return itemStack.hasItemMeta() && itemStack.getItemMeta().hasDisplayName();
	}
	
	/**
	 * Check if the item name is the same as the given string
	 *
	 * @param itemStack
	 * @param name
	 * @return true if the item name is the same as string
	 */
	public static boolean compareLocation(ItemStack itemStack, String name)
	{
		return hasDisplayName(itemStack) && itemStack.getItemMeta().getDisplayName().equals(name);
	}
	
	/**
	 * Check if the item name contains the given string
	 *
	 * @param itemStack
	 * @param name
	 * @return true if the item name contains the string
	 */
	public static boolean contains(ItemStack itemStack, String name)
	{
		return hasDisplayName(itemStack) && itemStack.getItemMeta().getDisplayName().contains(name);
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
				msgPlayer(player, "Success Teleported " + player.getName() + "!");
				if(cmd != null)
				{
					cmd.accept(true);
				}
			} else
			{
				msgPlayer(player, "Teleporting in: " + currentSecond);
			}
		}, 0, 1, TimeUnit.SECONDS);
	}
	
	/**
	 * Format a double in a String
	 *
	 * @param decimal
	 * @return formatting current duplicate
	 */
	public static String format(double decimal)
	{
		return format(decimal, "#.##");
	}
	
	/**
	 * Format a double in a String
	 *
	 * @param decimal
	 * @param format
	 * @return formatting current double according to the given format
	 */
	public static String format(double decimal, String format)
	{
		DecimalFormat decimalFormat = new DecimalFormat(format);
		return decimalFormat.format(decimal);
	}
	
	
	/**
	 * @param delay
	 * @param runnable
	 */
	public static void schedule(long delay, Runnable runnable)
	{
		new Timer().schedule(new TimerTask()
		{
			
			@Override
			public void run()
			{
				if(runnable != null)
				{
					runnable.run();
				}
			}
		}, delay);
	}
	
	/**
	 * @param string
	 * @return
	 */
	public static String name(String string)
	{
		String name = string.replace("_", " ").toLowerCase();
		return name.substring(0, 1).toUpperCase() + name.substring(1);
	}
	
	/**
	 * @param string
	 * @return
	 */
	public static String name(Material string)
	{
		String name = string.name().replace("_", " ").toLowerCase();
		return name.substring(0, 1).toUpperCase() + name.substring(1);
	}
	
	/**
	 * @param items
	 * @return
	 */
	public static int getMaxPage(Collection<?> items)
	{
		return (items.size() / 45) + 1;
	}
	
	/**
	 * @param items
	 * @param a
	 * @return
	 */
	public static int getMaxPage(Collection<?> items, int a)
	{
		return (items.size() / a) + 1;
	}
	
	/**
	 * @param value
	 * @param total
	 * @return
	 */
	public static double percent(double value, double total)
	{
		return (double) ((value * 100) / total);
	}
	
	/**
	 * @param total
	 * @param percent
	 * @return
	 */
	public static double percentNum(double total, double percent)
	{
		return (double) (total * (percent / 100));
	}
	
	/**
	 * Schedule task with timer
	 *
	 * @param delay
	 * @param count
	 * @param runnable
	 */
	public static void schedule(JavaPlugin plugin, long delay, int count, Runnable runnable)
	{
		new Timer().scheduleAtFixedRate(new TimerTask()
		{
			int tmpCount = 0;
			
			@Override
			public void run()
			{
				
				if(!plugin.isEnabled())
				{
					this.cancel();
					return;
				}
				
				if(tmpCount > count)
				{
					this.cancel();
					return;
				}
				
				tmpCount++;
				Bukkit.getScheduler().runTask(plugin, runnable);
				
			}
		}, 0, delay);
	}
	
	public static TimerTask scheduleFix(JavaPlugin plugin, long delay, BiConsumer<TimerTask, Boolean> consumer)
	{
		return scheduleFix(plugin, delay, delay, consumer);
	}
	
	public static TimerTask scheduleFix(JavaPlugin plugin, long startAt, long delay, BiConsumer<TimerTask, Boolean> consumer)
	{
		TimerTask task = new TimerTask()
		{
			@Override
			public void run()
			{
				if(!plugin.isEnabled())
				{
					cancel();
					consumer.accept(this, false);
					return;
				}
				Bukkit.getScheduler().runTask(plugin, () -> consumer.accept(this, true));
			}
		};
		new Timer().scheduleAtFixedRate(task, startAt, delay);
		return task;
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
		Random random = new Random();
		return element.get(random.nextInt(element.size()));
	}
	
	/**
	 * @param flagString
	 * @return
	 */
	public static ItemFlag getFlag(String flagString)
	{
		for(ItemFlag flag : ItemFlag.values())
		{
			if(flag.name().equalsIgnoreCase(flagString))
			{
				return flag;
			}
		}
		return null;
	}
	
	
	/**
	 * @param list
	 * @return
	 */
	public static <T> List<T> reverse(List<T> list)
	{
		List<T> tmpList = new ArrayList<>();
		for(int index = list.size() - 1; index != -1; index--)
		{
			tmpList.add(list.get(index));
		}
		return tmpList;
	}
	
	/**
	 * @param price
	 * @return
	 */
	public static String price(long price)
	{
		return String.format("%,d", price);
	}
	
	/**
	 * @param value
	 * @return
	 */
	public static String getDisplayBalance(double value)
	{
		if(value < 10000)
		{
			return format(value, "#.#");
		} else if(value < 1000000)
		{
			return (int) (value / 1000) + "k ";
		} else if(value < 1000000000)
		{
			return format((value / 1000) / 1000, "#.#") + "m ";
		} else if(value < 1000000000000L)
		{
			return (int) (((value / 1000) / 1000) / 1000) + "M ";
		} else
		{
			return "too much";
		}
	}
	
	/**
	 * @param value
	 * @return
	 */
	public static String getDisplayBalance(long value)
	{
		if(value < 10000)
		{
			return format(value, "#.#");
		} else if(value < 1000000)
		{
			return (int) (value / 1000) + "k ";
		} else if(value < 1000000000)
		{
			return format((value / 1000) / 1000, "#.#") + "m ";
		} else if(value < 1000000000000L)
		{
			return (int) (((value / 1000) / 1000) / 1000) + "M ";
		} else
		{
			return "too much";
		}
	}
	
	/**
	 * Allows you to count the number of items in inventory
	 *
	 * @param inventory
	 * @param material
	 * @return
	 */
	public static int count(Inventory inventory, Material material)
	{
		int count = 0;
		for(ItemStack itemStack : inventory.getContents())
		{
			if(itemStack != null && itemStack.getType().equals(material))
			{
				count += itemStack.getAmount();
			}
		}
		return count;
	}
	
	/**
	 * Allows you to count the number of items in inventory
	 *
	 * @param inventory
	 * @param itemStack
	 * @return
	 */
	public static int count(Inventory inventory, ItemStack itemStack)
	{
		int count = 0;
		for(ItemStack contents : inventory.getContents())
		{
			if(contents != null && contents.isSimilar(itemStack))
			{
				count += contents.getAmount();
			}
		}
		return count;
	}
	
	public static Enchantment enchantFromString(String str)
	{
		for(Enchantment enchantment : Enchantment.values())
		{
			if(enchantment.getName().equalsIgnoreCase(str))
			{
				return enchantment;
			}
		}
		return null;
	}
	
	/**
	 * @param direction
	 * @return
	 */
	public static BlockFace getClosestFace(float direction)
	{
		
		direction = direction % 360;
		
		if(direction < 0)
		{
			direction += 360;
		}
		
		direction = Math.round(direction / 45);
		
		return switch((int) direction)
				{
					case 1 -> BlockFace.NORTH_WEST;
					case 2 -> BlockFace.NORTH;
					case 3 -> BlockFace.NORTH_EAST;
					case 4 -> BlockFace.EAST;
					case 5 -> BlockFace.SOUTH_EAST;
					case 6 -> BlockFace.SOUTH;
					case 7 -> BlockFace.SOUTH_WEST;
					default -> BlockFace.WEST;
				};
	}
	
	/**
	 * @param price
	 * @return
	 */
	public static String betterPrice(long price)
	{
		StringBuilder betterPrice = new StringBuilder();
		String[] splitPrice = String.valueOf(price).split("");
		int current = 0;
		for(int a = splitPrice.length - 1; a > -1; a--)
		{
			current++;
			if(current > 3)
			{
				betterPrice.append(".");
				current = 1;
			}
			betterPrice.append(splitPrice[a]);
		}
		StringBuilder builder = new StringBuilder().append(betterPrice);
		builder.reverse();
		return builder.toString();
	}
	
	/**
	 * @param player
	 * @param cooldown
	 * @return
	 */
	public static String timerFormat(Player player, String cooldown)
	{
		return TimerBuilder.getStringTime(CooldownBuilder.getCooldownPlayer(cooldown, player) / 1000);
	}
	
	/**
	 * @param player
	 * @param cooldown
	 * @return
	 */
	public static boolean isCooldown(Player player, String cooldown)
	{
		return isCooldown(player, cooldown, 0);
	}
	
	/**
	 * @param player
	 * @param cooldown
	 * @param timer
	 * @return
	 */
	public static boolean isCooldown(Player player, String cooldown, int timer)
	{
		if(CooldownBuilder.isCooldown(cooldown, player))
		{
			//USE NEW ACTIONBAR FROM ADVNETURE API
			//ActionBar.sendActionBar(player,
					//String.format(".", timerFormat(player, cooldown));
			return true;
		}
		if(timer > 0)
		{
			CooldownBuilder.addCooldown(cooldown, player, timer);
		}
		return false;
	}
	
	/**
	 * @param l
	 * @return
	 */
	public static String format(long l)
	{
		return format(l, ' ');
	}
	
	/**
	 * @param l
	 * @param c
	 * @return
	 */
	public static String format(long l, char c)
	{
		DecimalFormat formatter = (DecimalFormat) NumberFormat.getInstance(Locale.US);
		DecimalFormatSymbols symbols = formatter.getDecimalFormatSymbols();
		symbols.setGroupingSeparator(c);
		formatter.setDecimalFormatSymbols(symbols);
		return formatter.format(l);
	}
	
	/**
	 * @param configuration
	 * @return
	 */
	public static PotionEffectType getPotion(String configuration)
	{
		for(PotionEffectType effectType : PotionEffectType.values())
		{
			if(effectType.getName().equalsIgnoreCase(configuration))
			{
				return effectType;
			}
		}
		return null;
	}
	
	/**
	 * Allows to execute a runnable in an asynmetrical way
	 *
	 * @param runnable
	 */
	public static void runAsync(Plugin plugin, Runnable runnable)
	{
		//Bukkit.getScheduler().runTaskAsynchronously(plugin, runnable);
	}
	
	/**
	 * Turns back time for a human
	 *
	 * @param second
	 * @return string
	 */
	public static String getStringTime(long second)
	{
		return TimerBuilder.getStringTime(second);
	}
	
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
	public static boolean inventoryHasItem(Player player)
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
	
}

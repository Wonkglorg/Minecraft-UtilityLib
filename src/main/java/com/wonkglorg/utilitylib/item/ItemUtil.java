package com.wonkglorg.utilitylib.item;

import com.wonkglorg.utilitylib.message.Message;
import net.kyori.adventure.text.Component;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.OfflinePlayer;
import org.bukkit.SkullType;
import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeModifier;
import org.bukkit.attribute.AttributeModifier.Operation;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.Damageable;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.SkullMeta;
import org.bukkit.persistence.PersistentDataType;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import java.util.function.BiPredicate;

@SuppressWarnings("unused")
public final class ItemUtil
{
	
	/**
	 * Renames an ItemStack, functionally identical to {@link ItemUtil#setName(ItemStack, String)} but kept for legacy reasons
	 *
	 * @param item The ItemStack to be renamed
	 * @param name The name to give the ItemStack
	 *
	 * @return The renamed ItemStack
	 */
	public static ItemStack rename(@NotNull final ItemStack item, @NotNull final String name)
	{
		ItemMeta meta = item.getItemMeta();
		meta.displayName(Message.color(name));
		item.setItemMeta(meta);
		return item;
	}
	
	/**
	 * Renames an ItemStack
	 *
	 * @param item The ItemStack to be renamed
	 * @param name The name to give the ItemStack
	 *
	 * @return The renamed ItemStack
	 */
	public static ItemStack setName(@NotNull final ItemStack item, @NotNull final String name)
	{
		return rename(item, name);
	}
	
	/**
	 * Set a single line of lore for an ItemStack
	 *
	 * @param item The ItemStack to be given lore
	 * @param line The line of lore to be given
	 *
	 * @return The modified ItemStack
	 */
	public static ItemStack setLore(@NotNull final ItemStack item, @NotNull final String line)
	{
		ItemMeta meta = item.getItemMeta();
		List<Component> lore = new ArrayList<>();
		lore.add(Message.color(line));
		meta.lore(lore);
		item.setItemMeta(meta);
		return item;
	}
	
	/**
	 * Set multiple lines of lore for an ItemStack
	 *
	 * @param item The ItemStack to be given lore
	 * @param lore The lines of lore to be given
	 *
	 * @return The modified ItemStack
	 */
	public static ItemStack setLore(@NotNull final ItemStack item, @NotNull final List<String> lore)
	{
		ItemMeta meta = item.getItemMeta();
		meta.lore(Message.color(lore));
		item.setItemMeta(meta);
		return item;
	}
	
	/**
	 * Add a line of lore to an ItemStack
	 *
	 * @param item The ItemStack to be given lore
	 * @param line The line of lore to add
	 *
	 * @return The modified ItemStack
	 */
	public static ItemStack addLore(@NotNull final ItemStack item, @NotNull final String line)
	{
		ItemMeta meta = item.getItemMeta();
		List<Component> lore = meta.lore();
		lore = lore == null ? new ArrayList<>() : lore;
		lore.add(Message.color(line));
		meta.lore(lore);
		item.setItemMeta(meta);
		return item;
	}
	
	/**
	 * Adds multiple lines of lore to an ItemStack
	 *
	 * @param item The ItemStack to be given lore
	 * @param lines The lines or lore to add
	 *
	 * @return The modified ItemStack
	 */
	public static ItemStack addLore(@NotNull final ItemStack item, @NotNull final Iterable<String> lines)
	{
		ItemMeta meta = item.getItemMeta();
		List<Component> lore = meta.lore();
		lore = lore == null ? new ArrayList<>() : lore;
		for(String line : lines)
		{
			lore.add(Message.color(line));
		}
		meta.lore(lore);
		item.setItemMeta(meta);
		return item;
	}
	
	/**
	 * Remove a specific line of lore from an ItemStack if present in an ItemStack
	 *
	 * @param item The ItemStack to remove lore from
	 * @param line The line of lore to remove
	 *
	 * @return The modified ItemStack
	 */
	public static ItemStack removeLoreLine(@NotNull final ItemStack item, @NotNull final String line)
	{
		ItemMeta meta = item.getItemMeta();
		List<Component> lore = meta.lore();
		if(lore == null)
		{
			return item;
		}
		lore.remove(Message.color(line));
		meta.lore(lore);
		item.setItemMeta(meta);
		return item;
	}
	
	/**
	 * Removes a specific index-line of lore from an ItemStack if present in an ItemStack
	 *
	 * @param item The ItemStack to remove lore from
	 * @param index The index of the line of lore to remove
	 *
	 * @return The modified ItemStack
	 */
	public static ItemStack removeLoreLine(@NotNull final ItemStack item, final int index)
	{
		ItemMeta meta = item.getItemMeta();
		List<Component> lore = meta.lore();
		if(lore == null)
		{
			return item;
		}
		if(index < 0 || index > lore.size())
		{
			throw new IllegalArgumentException("Value out of bounds (" + index + ")");
		}
		lore.remove(index);
		meta.lore(lore);
		item.setItemMeta(meta);
		return item;
	}
	
	/**
	 * Set multiple lines of lore for an ItemStack
	 *
	 * @param item The ItemStack to be given lore
	 * @param lore The lines of lore to be given
	 *
	 * @return The modified ItemStack
	 */
	public static ItemStack setLore(@NotNull final ItemStack item, @NotNull final String... lore)
	{
		return setLore(item, Arrays.asList(lore));
	}
	
	/**
	 * Sets an item to be unbreakable
	 *
	 * @param item The item to make unbreakable
	 *
	 * @return The unbreakable item
	 */
	public static ItemStack setUnbreakable(@NotNull final ItemStack item)
	{
		ItemMeta meta = item.getItemMeta();
		meta.setUnbreakable(true);
		item.setItemMeta(meta);
		return item;
	}
	
	/**
	 * Add an enchantment to an ItemStack
	 *
	 * @param item The ItemStack to be enchanted
	 * @param enchant The Enchantment to add to the ItemStack
	 * @param level The level of the Enchantment
	 *
	 * @return The enchanted ItemStack
	 */
	public static ItemStack addEnchant(@NotNull final ItemStack item, @NotNull final Enchantment enchant, final int level)
	{
		ItemMeta meta = item.getItemMeta();
		meta.addEnchant(enchant, level, true);
		if(level == 0)
		{
			meta.removeEnchant(enchant);
		}
		item.setItemMeta(meta);
		return item;
	}
	
	public static Enchantment enchantFromString(@NotNull final String str)
	{
		for(Enchantment enchantment : Enchantment.values())
		{
			if(enchantment.getKey().asString().equalsIgnoreCase(str))
			{
				return enchantment;
			}
		}
		return null;
	}
	
	/**
	 * Add an attribute to the item
	 *
	 * @param item The item to have an attribute added
	 * @param attribute The Attribute to be added
	 * @param modifier The AttributeModifier to be added
	 *
	 * @return The modified ItemStack
	 */
	public static ItemStack addAttribute(@NotNull final ItemStack item, @NotNull final Attribute attribute, @NotNull final AttributeModifier modifier)
	{
		ItemMeta meta = item.getItemMeta();
		meta.addAttributeModifier(attribute, modifier);
		item.setItemMeta(meta);
		return item;
	}
	
	/**
	 * Add an attribute to the item
	 *
	 * @param item The item to have an attribute added
	 * @param attribute The Attribute to be added
	 * @param amount The amount to modify it by
	 * @param operation The operation by which the value will be modified
	 *
	 * @return The modified item
	 */
	public static ItemStack addAttribute(@NotNull final ItemStack item,
										 @NotNull final Attribute attribute,
										 final double amount,
										 @NotNull final Operation operation)
	{
		ItemMeta meta = item.getItemMeta();
		AttributeModifier modifier = new AttributeModifier(attribute.toString(), amount, operation);
		meta.addAttributeModifier(attribute, modifier);
		item.setItemMeta(meta);
		return item;
	}
	
	/**
	 * Adds ItemFlags to the item
	 *
	 * @param item The item to add ItemFlags to
	 * @param flags The ItemFlags to add
	 *
	 * @return The modified item
	 */
	public static ItemStack addItemFlags(@NotNull final ItemStack item, final ItemFlag... flags)
	{
		ItemMeta meta = item.getItemMeta();
		meta.addItemFlags(flags);
		item.setItemMeta(meta);
		return item;
	}
	
	/**
	 * Removes  ItemFlags from the item
	 *
	 * @param item The item to add ItemFlags to
	 * @param flags The ItemFlags to add
	 *
	 * @return The modified item
	 */
	public static ItemStack removeItemFlags(@NotNull final ItemStack item, @NotNull final ItemFlag... flags)
	{
		ItemMeta meta = item.getItemMeta();
		meta.removeItemFlags(flags);
		item.setItemMeta(meta);
		return item;
	}
	
	/**
	 * Sets the custom model data of the item
	 *
	 * @param item The item to set the custom model data for
	 * @param customModelData The custom model data to set
	 *
	 * @return The modified item
	 */
	public static ItemStack setCustomModelData(ItemStack item, int customModelData)
	{
		ItemMeta meta = item.getItemMeta();
		meta.setCustomModelData(customModelData);
		item.setItemMeta(meta);
		return item;
	}
	
	/**
	 * Adds persistent data to the item
	 *
	 * @param item The item to add persistent data to
	 * @param key The key to add the data under
	 * @param type The type of the data
	 * @param data The data to store
	 * @param <T> The primary object type
	 * @param <Z> The retrieved object type
	 *
	 * @return The modified item
	 */
	public static <T, Z> ItemStack addPersistentTag(ItemStack item, NamespacedKey key, PersistentDataType<T, Z> type, Z data)
	{
		ItemMeta meta = item.getItemMeta();
		meta.getPersistentDataContainer().set(key, type, data);
		item.setItemMeta(meta);
		return item;
	}
	
	/**
	 * Add an attribute to the item
	 *
	 * @param item The item to have an attribute added
	 * @param attribute The Attribute to be added
	 * @param amount The amount to modify it by
	 * @param operation The operation by which the value will be modified
	 * @param slot The slot this attribute will be effective in
	 *
	 * @return The modified item
	 */
	public static ItemStack addAttribute(ItemStack item, Attribute attribute, double amount, Operation operation, EquipmentSlot slot)
	{
		ItemMeta meta = item.getItemMeta();
		AttributeModifier modifier = new AttributeModifier(UUID.randomUUID(), attribute.toString(), amount, operation, slot);
		meta.addAttributeModifier(attribute, modifier);
		item.setItemMeta(meta);
		return item;
	}
	
	/**
	 * Damages an item
	 *
	 * @param item The item to damage
	 * @param amount How much damage to apply
	 *
	 * @return The damaged item
	 *
	 * @throws IllegalArgumentException if the item is not damageable
	 */
	public static ItemStack damage(ItemStack item, int amount)
	{
		ItemMeta meta = item.getItemMeta();
		if(meta == null)
		{
			return item;
		}
		if(meta instanceof Damageable damageable)
		{
			damageable.setDamage(amount);
		}
		item.setItemMeta(meta);
		return item;
	}
	
	/**
	 * Counts the number of the given item in the given inventory
	 *
	 * @param inv The inventory to count the items in
	 * @param item The item to count
	 * @param comparison A filter to compare items for counting
	 *
	 * @return The number of items found
	 */
	public static int count(Inventory inv, ItemStack item, BiPredicate<ItemStack, ItemStack> comparison)
	{
		int count = 0;
		for(ItemStack i : inv)
		{
			if(comparison.test(item, i))
			{
				count += i.getAmount();
			}
		}
		return count;
	}
	
	/**
	 * Counts the number of the given item in the given inventory
	 *
	 * @param inv The inventory to count the items in
	 * @param item The item to count
	 *
	 * @return The number of items found
	 */
	public static int count(Inventory inv, ItemStack item)
	{
		return count(inv, item, ItemStack::isSimilar);
	}
	
	/**
	 * Counts the number of items of the given type in the given inventory
	 *
	 * @param inv The inventory to count the items in
	 * @param type The type of item to count
	 *
	 * @return The number of items found
	 */
	public static int count(Inventory inv, Material type)
	{
		return count(inv, new ItemStack(type), (a, b) -> compare(a, b, ItemTrait.TYPE));
	}
	
	/**
	 * Removes the specified amount of the given item from the given inventory
	 *
	 * @param inv The inventory to remove the items from
	 * @param item The item to be removed
	 * @param amount The amount of items to remove
	 * @param comparison A filter to compare items for removal
	 *
	 * @return Whether the amount specified could be removed. False if it removed less than specified.
	 */
	public static boolean remove(Inventory inv, ItemStack item, int amount, BiPredicate<ItemStack, ItemStack> comparison)
	{
		ItemStack[] contents = inv.getContents();
		for(int i = 0; i < contents.length && amount > 0; i++)
		{
			if(!comparison.test(item, contents[i]))
			{
				continue;
			}
			if(amount >= contents[i].getAmount())
			{
				amount -= contents[i].getAmount();
				contents[i] = null;
				if(amount == 0)
				{
					inv.setContents(contents);
					return true;
				}
				continue;
			}
			contents[i].setAmount(contents[i].getAmount() - amount);
			inv.setContents(contents);
			return true;
		}
		inv.setContents(contents);
		return false;
	}
	
	/**
	 * Removes the specified amount of the given item from the given inventory
	 *
	 * @param inv The inventory to remove the items from
	 * @param item The item to be removed
	 * @param amount The amount of items to remove
	 *
	 * @return Whether the amount specified could be removed. False if it removed less than specified.
	 */
	public static boolean remove(Inventory inv, ItemStack item, int amount)
	{
		return remove(inv, item, amount, ItemStack::isSimilar);
	}
	
	/**
	 * Removes the specified amount of the given item type from the given inventory
	 *
	 * @param inv The inventory to remove the items from
	 * @param type The item type to be removed
	 * @param amount The amount of items to remove
	 *
	 * @return Whether the amount specified could be removed. False if it removed less than specified.
	 */
	public static boolean remove(Inventory inv, Material type, int amount)
	{
		return remove(inv, new ItemStack(type), amount, (a, b) -> compare(a, b, ItemTrait.TYPE));
	}
	
	/**
	 * Remove all matching items up to a maximum, returning the number that were removed
	 *
	 * @param inv The inventory to count and remove items from
	 * @param item The item to count and remove
	 * @param max The maximum number of items to remove
	 * @param comparison A filter to compare items for counting and removal
	 *
	 * @return How many items were removed
	 */
	public static int countAndRemove(Inventory inv, ItemStack item, int max, BiPredicate<ItemStack, ItemStack> comparison)
	{
		int count = count(inv, item, comparison);
		count = Math.min(max, count);
		remove(inv, item, count, comparison);
		return count;
	}
	
	/**
	 * Remove all matching items up to a maximum, returning the number that were removed
	 *
	 * @param inv The inventory to count and remove items from
	 * @param item The item to count and remove
	 * @param max The maximum number of items to remove
	 *
	 * @return How many items were removed
	 */
	public static int countAndRemove(Inventory inv, ItemStack item, int max)
	{
		return countAndRemove(inv, item, max, ItemStack::isSimilar);
	}
	
	/**
	 * Remove all matching items up to a maximum, returning the number that were removed
	 *
	 * @param inv The inventory to count and remove items from
	 * @param type The item type to count and remove
	 * @param max The maximum number of items to remove
	 *
	 * @return How many items were removed
	 */
	public static int countAndRemove(Inventory inv, Material type, int max)
	{
		return countAndRemove(inv, new ItemStack(type), max, (a, b) -> compare(a, b, ItemTrait.TYPE));
	}
	
	/**
	 * Remove all matching items, returning the number that were removed
	 *
	 * @param inv The inventory to count and remove items from
	 * @param item The item to count and remove
	 *
	 * @return How many items were removed
	 */
	public static int countAndRemove(Inventory inv, ItemStack item)
	{
		return countAndRemove(inv, item, Integer.MAX_VALUE, ItemStack::isSimilar);
	}
	
	/**
	 * Remove all items of a specified type, returning the number that were removed
	 *
	 * @param inv The inventory to count and remove items from
	 * @param type The item type to count and remove
	 *
	 * @return How many items were removed
	 */
	public static int countAndRemove(Inventory inv, Material type)
	{
		return countAndRemove(inv, new ItemStack(type), Integer.MAX_VALUE, (a, b) -> compare(a, b, ItemTrait.TYPE));
	}
	
	/**
	 * @param enchantment
	 * @param itemStack
	 *
	 * @return
	 */
	public static boolean hasEnchant(Enchantment enchantment, ItemStack itemStack)
	{
		return itemStack.hasItemMeta() && itemStack.getItemMeta().hasEnchants() && itemStack.getItemMeta().hasEnchant(enchantment);
	}
	
	/**
	 * Compares the traits of two items
	 *
	 * @param first The first ItemStack
	 * @param second The second ItemStack
	 * @param traits The ItemTraits to compare
	 *
	 * @return Whether the two items are identical in terms of the traits provided. Returns true if both items are null, and false if only one is
	 * null.
	 */
	public static boolean compare(ItemStack first, ItemStack second, ItemTrait... traits)
	{
		if(first == second)
		{
			return true;
		}
		if(first == null || second == null)
		{
			return false;
		}
		for(ItemTrait trait : traits)
		{
			if(!trait.compare(first, second))
			{
				return false;
			}
		}
		return true;
	}
	
	/**
	 * Compares the type, name, and lore of two items
	 *
	 * @param first The first ItemStack
	 * @param second The second ItemStack
	 *
	 * @return Whether the two items are identical in terms of type, name, and lore. Returns true if both items are null, and false if only one is
	 * null.
	 */
	public static boolean compare(ItemStack first, ItemStack second)
	{
		return compare(first, second, ItemTrait.TYPE, ItemTrait.NAME, ItemTrait.LORE);
	}
	
	/**
	 * Creates a mock inventory clone of the given inventory. Do not try to open this inventory for players, it will throw an error.
	 *
	 * @param inv The inventory to clone
	 *
	 * @return A mock clone inventory
	 */
	public static Inventory cloneInventory(Inventory inv)
	{
		ItemStack[] contents = new ItemStack[inv.getSize()];
		for(int i = 0; i < inv.getSize(); i++)
		{
			ItemStack item = inv.getItem(i);
			if(item == null)
			{
				continue;
			}
			contents[i] = item.clone();
		}
		return new MockInventory(contents, inv.getHolder(), inv.getType());
	}
	
	/**
	 * Calculates the minimum chest size (next highest multiple of 9) required to fit the given number of item stacks
	 *
	 * @param items The number of item stacks
	 *
	 * @return The minimum chest size to accommodate the items
	 */
	public static int minimumChestSize(int items)
	{
		return (int) Math.max(9, Math.ceil(items / 9d) * 9);
	}
	
	/**
	 * Checks whether an item is empty, meaning it is either null or air
	 *
	 * @param item The item to check
	 *
	 * @return Whether the item is empty
	 */
	public static boolean isEmpty(ItemStack item)
	{
		return item == null || item.getType() == Material.AIR;
	}
	
	/**
	 * Creates an Armor Set out of 4 {@link ItemStack}.
	 *
	 * @param helmet {@link ItemStack} to use as helmet.
	 * @param chestplate {@link ItemStack} to use as chestplate.
	 * @param leggings {@link ItemStack} to use as leggings.
	 * @param boots {@link ItemStack} to use as boots.
	 *
	 * @return Array of armor {@link ItemStack}.
	 */
	public static ItemStack[] createArmorSet(ItemStack helmet, ItemStack chestplate, ItemStack leggings, ItemStack boots)
	{
		ItemStack[] armor = new ItemStack[4];
		armor[3] = helmet;
		armor[2] = chestplate;
		armor[1] = leggings;
		armor[0] = boots;
		return armor;
	}
	
	/**
	 * Creates a custom playerHead from a Texture String. Only enter exact value
	 *
	 * @param texture Texture string of the head.
	 * @param name Name of the head.
	 *
	 * @return {@link ItemStack}.
	 */
	public static ItemStack createCustomHead(String texture, String name, String... description)
	{
		return createCustomHead(texture, name, List.of(description));
	}
	
	/**
	 * Creates a custom playerHead from a Texture String. Only enter exact value
	 *
	 * @param texture Texture string of the head.
	 * @param name Name of the head.
	 *
	 * @return {@link ItemStack}.
	 */
	public static ItemStack createCustomHead(String texture, String name, List<String> description)
	{
		ItemStack mobHead = new ItemStack(Material.PLAYER_HEAD, 1, (byte) SkullType.PLAYER.ordinal());
		if(texture == null || texture.isEmpty() || texture.contains(" "))
		{
			texture = "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNWM5MGNhNTA3M2M0OWI4OThhNmY4Y2RiYzcyZTZhY2EwYTQyNWVjODNiYzQzNTVlM2I4MzRmZDg1OTI4MmJkZCJ9fX0=";
		}
		UUID hashAsId = new UUID(texture.hashCode(), texture.hashCode());
		Bukkit.getUnsafe().modifyItemStack(mobHead, "{SkullOwner:{Id:\"" + hashAsId + "\",Properties:{textures:[{Value:\"" + texture + "\"}]}}}");
		ItemMeta meta = mobHead.getItemMeta();
		if(name != null)
		{
			meta.displayName(Message.color(name));
		}
		if(description != null)
		{
			meta.lore(Message.color(description));
		}
		mobHead.setItemMeta(meta);
		
		return mobHead;
	}
	
	//https://api.mojang.com/users/profiles/minecraft/INSERT PLAYER NAME HERE
	
	/**
	 * Gets player head from {@link UUID}
	 *
	 * @param uuid {@link UUID} from a specific {@link org.bukkit.OfflinePlayer}
	 * @param name Name of the skull returns name of player if not specified
	 * @param description description of the skull
	 *
	 * @return {@link ItemStack}.
	 */
	public static ItemStack createPlayerHead(UUID uuid, String name, String... description)
	{
		
		ItemStack item = new ItemStack(Material.PLAYER_HEAD);
		SkullMeta meta = (SkullMeta) item.getItemMeta();
		
		OfflinePlayer offlinePlayer = Bukkit.getOfflinePlayer(uuid);
		
		String cName = name != null ? name : offlinePlayer.getName();
		if(cName == null)
		{
			cName = "Player Head";
		}
		meta.setOwningPlayer(offlinePlayer);
		meta.displayName(Message.color(cName));
		meta.lore(Message.color(List.of(description)));
		
		item.setItemMeta(meta);
		return item;
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
	 * Gets player head from {@link UUID}
	 *
	 * @param uuid {@link UUID} from a specific {@link org.bukkit.OfflinePlayer}
	 *
	 * @return {@link ItemStack}.
	 */
	public static ItemStack createPlayerHead(UUID uuid)
	{
		return createPlayerHead(uuid, null);
	}
	
}
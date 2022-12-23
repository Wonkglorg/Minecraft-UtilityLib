package com.wonkglorg.utilitylib.builder;

import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Multimap;
import com.wonkglorg.utilitylib.message.Message;
import org.bukkit.Material;
import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeModifier;
import org.bukkit.attribute.AttributeModifier.Operation;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.Damageable;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@SuppressWarnings("unused")
public class ItemBuilder implements Cloneable
{
	private ItemStack item;
	private final Material material;
	private int amount;
	private String name;
	private List<String> lore;
	private int durability;
	private ItemMeta meta;
	private boolean unbreakable;
	private List<ItemFlag> flags;
	private Map<Enchantment, Integer> enchantments;
	private int customModelData;
	
	private Multimap<Attribute, AttributeModifier> modifiers;
	
	public ItemBuilder(Material material,
					   int amount,
					   String name,
					   List<String> lore,
					   int durability,
					   ItemMeta meta,
					   List<ItemFlag> flags,
					   Map<Enchantment, Integer> enchantments)
	{
		super();
		this.material = material;
		this.amount = amount;
		this.name = name;
		this.lore = lore;
		this.durability = durability;
		this.meta = meta;
		this.flags = flags;
		this.enchantments = enchantments;
	}
	
	public ItemBuilder(Material material)
	{
		this(material, 1);
	}
	
	public ItemBuilder(Material material, ItemFlag... itemFlags)
	{
		this(material, 1, null, null, 0, null, List.of(itemFlags), null);
	}
	
	public ItemBuilder(Material material, String name)
	{
		this(material, 1, name);
	}
	
	public ItemBuilder(Material material, List<String> description)
	{
		this(material, 1, null, description);
	}
	
	public ItemBuilder(Material material, int amount)
	{
		this(material, amount, null);
	}
	
	public ItemBuilder(Material material, int amount, String name)
	{
		this(material, amount, name, null);
	}
	
	public ItemBuilder(Material material, int amount, String name, List<String> description)
	{
		this(material, amount, name, description, 0, null, null, null);
	}
	
	public ItemBuilder(ItemStack item){
		meta = item.getItemMeta();
		material = item.getType();
		amount = item.getAmount();
	}
	//Eddit COLOR.COLOR to Message.color
	
	public ItemStack build()
	{
		item = item != null ? item : new ItemStack(material, amount);
		
		meta = meta != null ? meta : item.getItemMeta();
		
		if(flags != null)
		{
			flags.forEach(flag -> meta.addItemFlags(flag));
		}
		
		if(name != null)
		{
			meta.displayName(Message.color(name));
		}
		
		if(lore != null)
		{
			meta.lore(Message.color(lore));
		}
		
		meta.setUnbreakable(unbreakable);
		
		if(enchantments != null)
		{
			enchantments.forEach((e, l) -> meta.addEnchant(e, l, true));
		}
		
		if(modifiers != null)
		{
			meta.setAttributeModifiers(modifiers);
		}
		if(customModelData != 0)
		{
			meta.setCustomModelData(customModelData);
		}
		
		if(durability != 0 && item instanceof Damageable damageable)
		{
			damageable.setDamage(durability);
		}
		item.setItemMeta(meta);
		return item;
	}
	
	/**
	 * Add enchantment
	 *
	 * @param enchantment
	 * @param value
	 * @return {@link ItemBuilder}
	 */
	public ItemBuilder addEnchant(Enchantment enchantment, int value)
	{
		if(enchantments == null)
		{
			enchantments = new HashMap<>();
		}
		enchantments.put(enchantment, value);
		return this;
	}
	
	/**
	 * @param name
	 * @return {@link ItemBuilder}
	 */
	public ItemBuilder setName(String name)
	{
		this.name = name;
		return this;
	}
	
	/**
	 * @param durability
	 * @return {@link ItemBuilder}
	 */
	public ItemBuilder durability(int durability)
	{
		this.durability = durability;
		return this;
	}
	
	/**
	 * @param flag
	 * @return {@link ItemBuilder}
	 */
	public ItemBuilder setFlag(ItemFlag flag)
	{
		if(flags == null)
		{
			flags = new ArrayList<>();
		}
		this.flags.add(flag);
		return this;
	}
	
	/**
	 * @param lore
	 * @return {@link ItemBuilder}
	 */
	public ItemBuilder setLore(String... lore)
	{
		this.lore = Arrays.asList(lore);
		return this;
	}
	
	/**
	 * @param format
	 * @return {@link ItemBuilder}
	 */
	public ItemBuilder addLoreLine(String format)
	{
		if(lore == null)
		{
			lore = new ArrayList<String>();
		}
		lore.add(format);
		return this;
	}
	
	/**
	 * @return {@link ItemBuilder}
	 */
	public ItemBuilder glow()
	{
		addEnchant(Enchantment.LUCK, 1);
		setFlag(ItemFlag.HIDE_ENCHANTS);
		return this;
	}
	
	public ItemBuilder unbreakable(boolean unbreakable)
	{
		this.unbreakable = unbreakable;
		return this;
	}
	
	public ItemBuilder addAttribute(Attribute attribute, double amount, Operation operation)
	{
		if(modifiers == null)
		{
			modifiers = ArrayListMultimap.create();
		}
		modifiers.put(attribute, new AttributeModifier(attribute.toString(), amount, operation));
		return this;
	}
	
	public ItemBuilder setCustomModelData(int customModelData)
	{
		this.customModelData = customModelData;
		return this;
	}
	/*
	Still in testing how to best add variable for it?
	public <T, Z> ItemBuilder addPersistentTag(NamespacedKey key, PersistentDataType<T, Z> type, Z data) {
		meta.getPersistentDataContainer().set(key, type, data);
		return this;
	}
	
	 */
	
	@Override
	public ItemBuilder clone()
	{
		try
		{
			return (ItemBuilder) super.clone();
		} catch(CloneNotSupportedException e)
		{
			throw new AssertionError();
		}
	}
	
	public ItemStack getItem()
	{
		return item;
	}
	
	public void setItem(ItemStack item)
	{
		this.item = item;
	}
	
	public Material getMaterial()
	{
		return material;
	}
	
	public int getAmount()
	{
		return amount;
	}
	
	public void setAmount(int amount)
	{
		this.amount = amount;
	}
	
	public String getName()
	{
		return name;
	}
	
	public List<String> getLore()
	{
		return lore;
	}
	
	public void setLore(List<String> lore)
	{
		this.lore = lore;
	}
	
	public int getDurability()
	{
		return durability;
	}
	
	public void setDurability(int durability)
	{
		this.durability = durability;
	}
	
	public ItemMeta getMeta()
	{
		return meta;
	}
	
	public void setMeta(ItemMeta meta)
	{
		this.meta = meta;
	}
	
	public List<ItemFlag> getFlags()
	{
		return flags;
	}
	
	public void setFlags(List<ItemFlag> flags)
	{
		this.flags = flags;
	}
	
	public Map<Enchantment, Integer> getEnchantments()
	{
		return enchantments;
	}
	
	public void setEnchantments(Map<Enchantment, Integer> enchantments)
	{
		this.enchantments = enchantments;
	}
	
	public boolean isUnbreakable()
	
	{
		return unbreakable;
	}
	
	public void setUnbreakable(boolean unbreakable)
	{
		this.unbreakable = unbreakable;
	}
	
	public int getCustomModelData()
	{
		return customModelData;
	}
	
	public Multimap<Attribute, AttributeModifier> getModifiers()
	{
		return modifiers;
	}
	
	public void setModifiers(Multimap<Attribute, AttributeModifier> modifiers)
	{
		this.modifiers = modifiers;
	}
}
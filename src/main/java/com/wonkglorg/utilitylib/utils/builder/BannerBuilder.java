package com.wonkglorg.utilitylib.utils.builder;

import org.bukkit.DyeColor;
import org.bukkit.Material;
import org.bukkit.block.banner.Pattern;
import org.bukkit.block.banner.PatternType;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.BannerMeta;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class BannerBuilder extends ItemBuilder
{
	private List<DyeColor> colorList;
	private List<PatternType> patternList;
	
	public BannerBuilder(DyeColor color,
						 int amount,
						 String name,
						 List<String> lore,
						 int durability,
						 ItemMeta meta,
						 List<ItemFlag> flags,
						 Map<Enchantment, Integer> enchantments)
	{
		super(Material.valueOf(color.name() + "_BANNER"), amount, name, lore, durability, meta, flags, enchantments);
	}
	
	public BannerBuilder(DyeColor color)
	{
		super(Material.valueOf(color.name() + "_BANNER"));
	}
	
	public BannerBuilder(DyeColor color, ItemFlag... itemFlags)
	{
		super(Material.valueOf(color.name() + "_BANNER"), itemFlags);
	}
	
	public BannerBuilder(DyeColor color, String name)
	{
		super(Material.valueOf(color.name() + "_BANNER"), name);
	}
	
	public BannerBuilder(DyeColor color, List<String> description)
	{
		super(Material.valueOf(color.name() + "_BANNER"), description);
	}
	
	public BannerBuilder(DyeColor color, int amount)
	{
		super(Material.valueOf(color.name() + "_BANNER"), amount);
	}
	
	public BannerBuilder(DyeColor color, int amount, String name)
	{
		super(Material.valueOf(color.name() + "_BANNER"), amount, name);
	}
	
	public BannerBuilder(DyeColor color, int amount, String name, List<String> description)
	{
		super(Material.valueOf(color.name() + "_BANNER"), amount, name, description);
	}
	
	public BannerBuilder addLayer(DyeColor color, PatternType patternType)
	{
		if(colorList == null)
		{
			colorList = new ArrayList<>();
		}
		if(patternList == null)
		{
			patternList = new ArrayList<>();
		}
		colorList.add(color);
		patternList.add(patternType);
		return this;
	}
	
	public BannerBuilder replaceColorAt(int index, DyeColor color)
	{
		if(colorList == null)
		{
			colorList = new ArrayList<>();
		}
		if(index >= colorList.size())
		{
			colorList.set(index, color);
		}
		return this;
	}
	
	public BannerBuilder replacePatternAt(int index, PatternType type)
	{
		if(patternList == null)
		{
			patternList = new ArrayList<>();
		}
		if(index >= patternList.size())
		{
			patternList.set(index, type);
		}
		return this;
	}
	
	@Override
	public ItemStack build()
	{
		List<Pattern> patterns = new ArrayList<>();
		for(int i = 0; i < patternList.size(); i++)
		{
			patterns.add(new Pattern(colorList.get(i), patternList.get(i)));
		}
		super.setItem(new ItemStack(getMaterial(), getAmount()));
		BannerMeta meta = (BannerMeta) super.getItem().getItemMeta();
		meta.setPatterns(patterns);
		super.setMeta(meta);
		return super.build();
	}
	
}
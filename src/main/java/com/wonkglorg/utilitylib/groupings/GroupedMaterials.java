package com.wonkglorg.utilitylib.groupings;

import org.bukkit.Material;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author Wonkglorg
 */
@SuppressWarnings("unused")
public final class GroupedMaterials
{
	
	private Set<Material> chestPlate, leggings;
	
	public Set<Material> getChestPlates()
	{
		if(chestPlate == null)
		{
			chestPlate = Arrays.stream(Material.values()).filter(material -> material.name().contains("CHESTPLATE")).collect(Collectors.toSet());
		}
		return chestPlate;
	}
	
	public Set<Material> getLeggings()
	{
		if(leggings == null)
		{
			leggings = Arrays.stream(Material.values()).filter(material -> material.name().contains("LEGGINGS")).collect(Collectors.toSet());
		}
		return leggings;
	}
	
	public static List<Material> getBoots()
	{
		return List.of(Material.LEATHER_BOOTS,
				Material.CHAINMAIL_BOOTS,
				Material.IRON_BOOTS,
				Material.GOLDEN_BOOTS,
				Material.DIAMOND_BOOTS,
				Material.NETHERITE_BOOTS);
	}
	
	//TOOLS
	public static List<Material> getSwords()
	{
		return List.of(Material.WOODEN_SWORD,
				Material.STONE_SWORD,
				Material.IRON_SWORD,
				Material.GOLDEN_SWORD,
				Material.DIAMOND_SWORD,
				Material.NETHERITE_SWORD);
	}
	
	public static List<Material> getPickaxes()
	{
		return List.of(Material.WOODEN_PICKAXE,
				Material.STONE_PICKAXE,
				Material.IRON_PICKAXE,
				Material.GOLDEN_PICKAXE,
				Material.DIAMOND_PICKAXE,
				Material.NETHERITE_PICKAXE);
	}
	
	public static List<Material> getAxes()
	{
		return List.of(Material.WOODEN_AXE, Material.STONE_AXE, Material.IRON_AXE, Material.GOLDEN_AXE, Material.DIAMOND_AXE, Material.NETHERITE_AXE);
	}
	
	public static List<Material> getShovels()
	{
		return List.of(Material.WOODEN_SHOVEL,
				Material.STONE_SHOVEL,
				Material.IRON_SHOVEL,
				Material.GOLDEN_SHOVEL,
				Material.DIAMOND_SHOVEL,
				Material.NETHERITE_SHOVEL);
	}
	
	public static List<Material> getHoes()
	{
		return List.of(Material.WOODEN_HOE, Material.STONE_HOE, Material.IRON_HOE, Material.GOLDEN_HOE, Material.DIAMOND_HOE, Material.NETHERITE_HOE);
	}
	
	public static List<Material> getTools()
	{
		List<Material> tools = new ArrayList<>();
		tools.addAll(getPickaxes());
		tools.addAll(getShovels());
		tools.addAll(getAxes());
		tools.addAll(getHoes());
		return tools;
	}
	
	public static List<Material> getWeapons()
	{
		List<Material> weapons = new ArrayList<>();
		weapons.addAll(getSwords());
		weapons.addAll(List.of(Material.TRIDENT, Material.BOW, Material.CROSSBOW));
		return weapons;
	}
	
	//STONE
	public static List<Material> getNaturalStoneTypes()
	{
		return List.of(Material.STONE, Material.GRANITE, Material.DIORITE, Material.ANDESITE, Material.DEEPSLATE, Material.CALCITE, Material.TUFF);
	}
	
	public static List<Material> getOresStone()
	{
		return List.of(Material.COAL_ORE,
				Material.IRON_ORE,
				Material.COPPER_ORE,
				Material.GOLD_ORE,
				Material.REDSTONE_ORE,
				Material.EMERALD_ORE,
				Material.LAPIS_ORE,
				Material.DIAMOND_ORE,
				Material.ANCIENT_DEBRIS);
	}
	
	public static List<Material> getOresDeepslate()
	{
		return List.of(Material.DEEPSLATE_COAL_ORE,
				Material.DEEPSLATE_IRON_ORE,
				Material.DEEPSLATE_COPPER_ORE,
				Material.DEEPSLATE_GOLD_ORE,
				Material.DEEPSLATE_REDSTONE_ORE,
				Material.DEEPSLATE_EMERALD_ORE,
				Material.DEEPSLATE_LAPIS_ORE,
				Material.DEEPSLATE_DIAMOND_ORE);
	}
	
	public static List<Material> getOres()
	{
		List<Material> ores = new ArrayList<>();
		ores.addAll(getOresStone());
		ores.addAll(getOresDeepslate());
		return ores;
	}
	
	//WOOD
	
	//BLOCKS
	
	//SLABS
	
	public static Collection<Material> getSlabWood()
	{
		return null;
	}
	
	public static List<Material> getSlabStone()
	{
		List<Material> materials = new ArrayList<>();
		for(Material material : Material.values())
		{
			if(material.toString().contains("slab"))
			{
			
			}
		}
		return null;
	}
	
	public static Set<Material> getWoodMaterials()
	{
		Set<Material> woodStuff = new HashSet<>();
		for(Material material : Material.values())
		{
			if(material.name().startsWith("OAK") || material.name().startsWith("SPRUCE"))
			{
				woodStuff.add(material);
			}
		}
		return null;
	}
	
	public static Set<Material> tools()
	{
		Set<Material> set = new HashSet<>();
		set.addAll(hoe());
		set.addAll(shovel());
		set.addAll(shovel());
		set.addAll(axe());
		set.addAll(pickaxe());
		return set;
	}
	
	public static Set<Material> hoe()
	{
		return Arrays.stream(Material.values()).filter(material -> material.name().contains("HOE")).collect(Collectors.toSet());
	}
	
	public static Set<Material> sword()
	{
		return Arrays.stream(Material.values()).filter(material -> material.name().contains("SWORD")).collect(Collectors.toSet());
	}
	
	public static Set<Material> shovel()
	{
		return Arrays.stream(Material.values()).filter(material -> material.name().contains("SHOVEL")).collect(Collectors.toSet());
	}
	
	public static Set<Material> axe()
	{
		return Arrays.stream(Material.values()).filter(material -> material.name().contains("AXE")).collect(Collectors.toSet());
	}
	
	public static Set<Material> pickaxe()
	{
		return Arrays.stream(Material.values()).filter(material -> material.name().contains("PICKAXE")).collect(Collectors.toSet());
	}
	
	public static Set<Material> doors()
	{
		return Arrays.stream(Material.values()).filter(material -> material.name().contains("DOOR")).collect(Collectors.toSet());
	}
	
	public static Set<Material> oak()
	{
		return Arrays.stream(Material.values()).filter(material -> material.name().contains("OAK")).collect(Collectors.toSet());
	}
	
	public static Set<Material> spruce()
	{
		return Arrays.stream(Material.values()).filter(material -> material.name().contains("SPRUCE")).collect(Collectors.toSet());
	}
	
	public static Set<Material> food()
	{
		return Arrays.stream(Material.values()).filter(Material::isEdible).collect(Collectors.toSet());
	}
	
	public static Set<Material> fuel()
	{
		return Arrays.stream(Material.values()).filter(Material::isBurnable).collect(Collectors.toSet());
	}
	
	public static Set<Material> blocks()
	{
		return Arrays.stream(Material.values()).filter(Material::isBlock).collect(Collectors.toSet());
	}
	
	public static Set<Material> items()
	{
		return Arrays.stream(Material.values()).filter(Material::isItem).collect(Collectors.toSet());
	}
	
	public static Set<Material> musicDiscs()
	{
		return Arrays.stream(Material.values()).filter(Material::isRecord).collect(Collectors.toSet());
	}
	
	public static Set<Material> legacyMaterials()
	{
		return Arrays.stream(Material.values()).filter(Material::isLegacy).collect(Collectors.toSet());
	}
	
	public static Set<Material> getCollidable()
	{
		return Arrays.stream(Material.values()).filter(Material::isCollidable).collect(Collectors.toSet());
	}
	
	public static Set<Material> type(@NotNull String name)
	{
		return Arrays.stream(Material.values()).filter(material -> material.name().contains(name)).collect(Collectors.toSet());
	}
	
}
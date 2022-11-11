package com.wonkglorg.utilitylib.utils.groupings;

import org.bukkit.Material;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@SuppressWarnings("unused")
public class GroupedItems
{
	
	private static final Set<Material> helmets = new HashSet<>();
	private static final Set<Material> chestPlate = new HashSet<>();
	private static final Set<Material> leggings = new HashSet<>();
	private static final Set<Material> boots = new HashSet<>();
	
	private static final Set<Material> slabs = new HashSet<>();
	private static final Set<Material> stair = new HashSet<>();
	
	static
	{
		for(Material material : Material.values())
		{
			String stringMaterial = material.toString().toLowerCase();
			if(stringMaterial.contains("helmet"))
			{
				helmets.add(material);
			}
			if(stringMaterial.contains("chestplate"))
			{
				chestPlate.add(material);
			}
			if(stringMaterial.contains("leggings"))
			{
				leggings.add(material);
			}
			if(stringMaterial.contains("boots"))
			{
				boots.add(material);
			}
			if(stringMaterial.contains("slab"))
			{
				slabs.add(material);
			}
			if(stringMaterial.contains("stair"))
			{
				stair.add(material);
			}
		}
		
	}
	
	//ARMOR
	public static List<Material> getHelmets()
	{
		return List.of(Material.LEATHER_HELMET,
				Material.CHAINMAIL_HELMET,
				Material.IRON_HELMET,
				Material.GOLDEN_HELMET,
				Material.DIAMOND_HELMET,
				Material.NETHERITE_HELMET,
				Material.TURTLE_HELMET);
	}
	
	public static List<Material> getChestPlates()
	{
		return List.of(Material.LEATHER_CHESTPLATE,
				Material.CHAINMAIL_CHESTPLATE,
				Material.IRON_CHESTPLATE,
				Material.GOLDEN_CHESTPLATE,
				Material.DIAMOND_CHESTPLATE,
				Material.NETHERITE_CHESTPLATE);
	}
	
	public static List<Material> getLeggings()
	{
		return List.of(Material.LEATHER_LEGGINGS,
				Material.CHAINMAIL_LEGGINGS,
				Material.IRON_LEGGINGS,
				Material.GOLDEN_LEGGINGS,
				Material.DIAMOND_LEGGINGS,
				Material.NETHERITE_LEGGINGS);
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
	
	public static List<Material> getArmor()
	{
		List<Material> armor = new ArrayList<>();
		armor.addAll(getHelmets());
		armor.addAll(getChestPlates());
		armor.addAll(getLeggings());
		armor.addAll(getBoots());
		return armor;
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
	
	public static Set<Material> getSlabs()
	{
		return slabs;
	}
	
	public static Set<Material> getStairs()
	{
		return stair;
	}
	public static Set<Material> getArmorLoop()
	{
		Set<Material> armor = new HashSet<>();
		armor.addAll(helmets);
		armor.addAll(chestPlate);
		armor.addAll(leggings);
		armor.addAll(boots);
		return armor;
	}
	
	
	public static Set<Material> getWoodMaterials(){
		Set<Material> woodStuff = new HashSet<>();
		for(Material material : Material.values()){
			if(material.name().startsWith("OAK") || material.name().startsWith("SPRUCE")){
				woodStuff.add(material);
			}
		}
		return null;
	}
	//STAIRS
	
	//DOORS
	
	//CONTAINERS
	
	//REDSTONE
}
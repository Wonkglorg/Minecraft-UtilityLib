package com.wonkglorg.utilitylib.utils.groupings;

import org.bukkit.entity.EntityType;

import java.util.ArrayList;
import java.util.List;

public class GroupedEntities
{
	private static final List<EntityType> farmAnimals = new ArrayList<>();
	private static final List<EntityType> landAnimals = new ArrayList<>();
	private static final List<EntityType> landHostiles = new ArrayList<>();
	
	private static final List<EntityType> landMobs = new ArrayList<>();
	private static final List<EntityType> passive = new ArrayList<>();
	private static final List<EntityType> fish = new ArrayList<>();
	private static final List<EntityType> seaAnimals = new ArrayList<>();
	private static final List<EntityType> seaHostiles = new ArrayList<>();
	private static final List<EntityType> seaMobs = new ArrayList<>();
	
	static
	{
		//Land
		addFarmAnimals();
		addLandAnimals();
		addLandHostiles();
		addLandMobs();
		
		//Sea
		addFish();
		addSeaAnimals();
		addSeaHostiles();
		addSeaMobs();
		
		//Bosses
		
		//Other
		
		//ALL
		
	}
	
	private static void addFarmAnimals()
	{
		farmAnimals.addAll(List.of(EntityType.CHICKEN,
				EntityType.COW,
				EntityType.PIG,
				EntityType.SHEEP,
				EntityType.HORSE,
				EntityType.MULE,
				EntityType.DONKEY,
				EntityType.MUSHROOM_COW));
	}
	
	private static void addLandAnimals()
	{
		landAnimals.addAll(farmAnimals);
		landAnimals.addAll(List.of(EntityType.CAT,
				EntityType.STRIDER,
				EntityType.FOX,
				EntityType.SKELETON_HORSE,
				EntityType.ZOMBIE_HORSE,
				EntityType.FROG,
				EntityType.OCELOT,
				EntityType.PARROT,
				EntityType.RABBIT,
				EntityType.TURTLE,
				EntityType.LLAMA,
				EntityType.TRADER_LLAMA));
	}
	
	private static void addLandHostiles()
	{
		landHostiles.addAll(List.of(EntityType.CREEPER,
				EntityType.EVOKER,
				EntityType.HUSK,
				EntityType.PILLAGER,
				EntityType.RAVAGER,
				EntityType.SILVERFISH,
				EntityType.SKELETON,
				EntityType.SLIME,
				EntityType.SPIDER,
				EntityType.STRAY,
				EntityType.VEX,
				EntityType.VINDICATOR,
				EntityType.WARDEN,
				EntityType.WITCH,
				EntityType.ZOMBIE,
				EntityType.ZOMBIE_VILLAGER));
	}
	
	private static void addLandMobs(){
		landMobs.addAll(landAnimals);
		landMobs.addAll(landHostiles);
	}
	private static void addFish()
	{
		fish.addAll(List.of(EntityType.COD, EntityType.SALMON, EntityType.TROPICAL_FISH, EntityType.PUFFERFISH));
	}
	
	private static void addSeaAnimals()
	{
		seaAnimals.addAll(List.of(EntityType.AXOLOTL,
				EntityType.SQUID,
				EntityType.GLOW_SQUID,
				EntityType.DOLPHIN,
				EntityType.TURTLE,
				EntityType.TADPOLE));
		seaAnimals.addAll(fish);
	}
	
	private static void addSeaHostiles()
	{
		seaHostiles.addAll(List.of(EntityType.GUARDIAN, EntityType.ELDER_GUARDIAN, EntityType.DROWNED));
	}
	
	private static void addSeaMobs()
	{
		seaMobs.addAll(seaAnimals);
		seaMobs.addAll(seaHostiles);
	}
	
}
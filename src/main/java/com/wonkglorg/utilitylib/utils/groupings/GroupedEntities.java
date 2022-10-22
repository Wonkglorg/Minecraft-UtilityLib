package com.wonkglorg.utilitylib.utils.groupings;

import org.bukkit.entity.EntityType;

import java.util.Set;

public class GroupedEntities
{
	
	public static Set<EntityType> passive()
	{
		return Set.of(EntityType.ALLAY,
				EntityType.AXOLOTL,
				EntityType.BAT,
				EntityType.CAT,
				EntityType.CHICKEN,
				EntityType.COD,
				EntityType.COW,
				EntityType.DONKEY,
				EntityType.FOX,
				EntityType.FROG,
				EntityType.GLOW_SQUID,
				EntityType.HORSE,
				EntityType.MUSHROOM_COW,
				EntityType.MULE,
				EntityType.OCELOT,
				EntityType.PARROT,
				EntityType.PIG,
				EntityType.PUFFERFISH,
				EntityType.RABBIT,
				EntityType.SALMON,
				EntityType.SHEEP,
				EntityType.SKELETON_HORSE,
				EntityType.SNOWMAN,
				EntityType.SQUID,
				EntityType.STRIDER,
				EntityType.TADPOLE,
				EntityType.TROPICAL_FISH,
				EntityType.TURTLE,
				EntityType.VILLAGER,
				EntityType.WANDERING_TRADER);
	}
	
	public static Set<EntityType> animals()
	{
		return null;
	
	}
	
	public static Set<EntityType> tamable()
	{
		return null;
	}
	
	public static Set<EntityType> neutral()
	{
		return Set.of(EntityType.BEE,
				EntityType.CAVE_SPIDER,
				EntityType.DOLPHIN,
				EntityType.ENDERMAN,
				EntityType.GOAT,
				EntityType.IRON_GOLEM,
				EntityType.LLAMA,
				EntityType.PANDA,
				EntityType.PIGLIN,
				EntityType.POLAR_BEAR,
				EntityType.SPIDER,
				EntityType.TRADER_LLAMA,
				EntityType.WOLF,
				EntityType.ZOMBIFIED_PIGLIN);
	}
	
	public static Set<EntityType> hostile()
	{
		return Set.of(EntityType.BLAZE,
				EntityType.CREEPER,
				EntityType.DROWNED,
				EntityType.ELDER_GUARDIAN,
				EntityType.ENDERMITE,
				EntityType.EVOKER,
				EntityType.GHAST,
				EntityType.GUARDIAN,
				EntityType.HOGLIN,
				EntityType.HUSK,
				EntityType.MAGMA_CUBE,
				EntityType.PHANTOM,
				EntityType.PIGLIN_BRUTE,
				EntityType.PILLAGER,
				EntityType.RAVAGER,
				EntityType.SHULKER,
				EntityType.SILVERFISH,
				EntityType.SKELETON,
				EntityType.SLIME,
				EntityType.STRAY,
				EntityType.VEX,
				EntityType.VINDICATOR,
				EntityType.WARDEN,
				EntityType.WITCH,
				EntityType.WITHER_SKELETON,
				EntityType.ZOGLIN,
				EntityType.ZOMBIE,
				EntityType.ZOMBIE_VILLAGER);
	}
	
	public static Set<EntityType> boss()
	{
		return Set.of(EntityType.WITHER, EntityType.ENDER_DRAGON, EntityType.ELDER_GUARDIAN);
	}
	
	public static Set<EntityType> aquatic()
	{
		return null;
	}
	
	public static Set<EntityType> fish()
	{
		return Set.of(EntityType.SALMON, EntityType.COD, EntityType.PUFFERFISH, EntityType.TROPICAL_FISH);
	}
	
	public static Set<EntityType> undead()
	{
		return Set.of(EntityType.DROWNED,
				EntityType.HUSK,
				EntityType.PHANTOM,
				EntityType.SKELETON,
				EntityType.SKELETON_HORSE,
				EntityType.STRAY,
				EntityType.WITHER,
				EntityType.WITHER_SKELETON,
				EntityType.ZOGLIN,
				EntityType.ZOMBIE,
				EntityType.ZOMBIE_HORSE,
				EntityType.ZOMBIE_VILLAGER);
	}
	
	public static Set<EntityType> projectile()
	{
		return Set.of(EntityType.ARROW,
				EntityType.FIREWORK,
				EntityType.SPLASH_POTION,
				EntityType.EVOKER_FANGS,
				EntityType.SNOWBALL,
				EntityType.EGG,
				EntityType.TRIDENT,
				EntityType.ENDER_PEARL,
				EntityType.ENDER_SIGNAL,
				EntityType.THROWN_EXP_BOTTLE,
				EntityType.SMALL_FIREBALL,
				EntityType.FIREBALL,
				EntityType.DRAGON_FIREBALL);
	}
	
	public static Set<EntityType> projectilePlayer()
	{
		
		return Set.of(EntityType.ARROW,
				EntityType.FIREWORK,
				EntityType.SPLASH_POTION,
				EntityType.SNOWBALL,
				EntityType.EGG,
				EntityType.FISHING_HOOK,
				EntityType.TRIDENT,
				EntityType.ENDER_PEARL,
				EntityType.THROWN_EXP_BOTTLE,
				EntityType.ENDER_SIGNAL);
	}
	
	public static Set<EntityType> objects()
	{
		return null;
	}
	
}
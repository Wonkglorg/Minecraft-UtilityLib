package com.wonkglorg.utilitylib.groupings;

import org.bukkit.Bukkit;
import org.bukkit.entity.Animals;
import org.bukkit.entity.Boss;
import org.bukkit.entity.Breedable;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Fish;
import org.bukkit.entity.Flying;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Monster;
import org.bukkit.entity.Projectile;
import org.bukkit.entity.Tameable;
import org.bukkit.entity.Vehicle;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;
@SuppressWarnings("unused")
public final class GroupedEntities
{
	
	public static Set<EntityType> animals()
	{
		return Arrays.stream(EntityType.values())
					 .filter(entityType -> entityType.getEntityClass() != null && Animals.class.isAssignableFrom(entityType.getEntityClass()))
					 .collect(Collectors.toSet());
		
	}
	
	
	public static Set<EntityType> tamable()
	{
		return Arrays.stream(EntityType.values())
					 .filter(entityType -> entityType.getEntityClass() != null && Tameable.class.isAssignableFrom(entityType.getEntityClass()))
					 .collect(Collectors.toSet());
	}
	
	public static Set<EntityType> breedable()
	{
		return Arrays.stream(EntityType.values())
					 .filter(entityType -> entityType.getEntityClass() != null && Breedable.class.isAssignableFrom(entityType.getEntityClass()))
					 .collect(Collectors.toSet());
	}
	
	public static Set<EntityType> hostile()
	{
		return Arrays.stream(EntityType.values())
					 .filter(entityType -> entityType.getEntityClass() != null && Monster.class.isAssignableFrom(entityType.getEntityClass()))
					 .collect(Collectors.toSet());
	}
	
	public static Set<EntityType> boss()
	{
		return Arrays.stream(EntityType.values())
					 .filter(entityType -> entityType.getEntityClass() != null && Boss.class.isAssignableFrom(entityType.getEntityClass()))
					 .collect(Collectors.toSet());
	}
	
	public static Set<EntityType> aquatic()
	{
		return Arrays.stream(EntityType.values())
					 .filter(entityType -> entityType.getEntityClass() != null && Breedable.class.isAssignableFrom(entityType.getEntityClass()))
					 .collect(Collectors.toSet());
	}
	
	public static Set<EntityType> fish()
	{
		return Arrays.stream(EntityType.values())
					 .filter(entityType -> entityType.getEntityClass() != null && Fish.class.isAssignableFrom(entityType.getEntityClass()))
					 .collect(Collectors.toSet());
	}
	
	public static Set<EntityType> flying(){
		return Arrays.stream(EntityType.values())
					 .filter(entityType -> entityType.getEntityClass() != null && Flying.class.isAssignableFrom(entityType.getEntityClass()))
					 .collect(Collectors.toSet());
	}
	
	public static Set<EntityType> vehicle(){
		return Arrays.stream(EntityType.values())
					 .filter(entityType -> entityType.getEntityClass() != null && Vehicle.class.isAssignableFrom(entityType.getEntityClass()))
					 .collect(Collectors.toSet());
	}
	public static Set<EntityType> projectilePlayer()
	{
		
		return Arrays.stream(EntityType.values())
					 .filter(entityType -> entityType.getEntityClass() != null && Projectile.class.isAssignableFrom(entityType.getEntityClass()))
					 .collect(Collectors.toSet());
	}
	
	public static Set<EntityType> objects()
	{
		return Arrays.stream(EntityType.values())
					 .filter(entityType -> entityType.getEntityClass() != null && !LivingEntity.class.isAssignableFrom(entityType.getEntityClass()))
					 .collect(Collectors.toSet());
	}
	
	public static Set<EntityType> getByClass(Class<Bukkit> bukkitClass)
	{
		return Arrays.stream(EntityType.values())
					 .filter(entityType -> entityType.getEntityClass() != null && bukkitClass.isAssignableFrom(entityType.getEntityClass()))
					 .collect(Collectors.toSet());
	}
	
	public static Set<EntityType> getByClass(Set<EntityType> entityTypes,Class<Bukkit> bukkitClass)
	{
		return entityTypes.stream()
						  .filter(entityType -> entityType.getEntityClass() != null && bukkitClass.isAssignableFrom(entityType.getEntityClass()))
						  .collect(Collectors.toSet());
	}
	
}
package com.wonkglorg.utilitylib.utils.entity;

import org.bukkit.NamespacedKey;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.metadata.MetadataValue;
import org.bukkit.persistence.PersistentDataType;

public final class EntityUtil
{
	
	public static Entity setMetaData(Entity entity, String metaData, MetadataValue metaDataValue)
	{
		entity.setMetadata(metaData, metaDataValue);
		return entity;
	}
	
	public static <T, Z> LivingEntity setPersistentData(LivingEntity entity,
														NamespacedKey namespacedKey,
														PersistentDataType<T, Z> persistentDataType,
														Z value)
	{
		entity.getPersistentDataContainer().set(namespacedKey, persistentDataType, value);
		return entity;
	}
	
	public static <T, Z> Z getPersistentData(LivingEntity entity, NamespacedKey namespacedKey, PersistentDataType<T, Z> persistentDataType)
	{
		return entity.getPersistentDataContainer().get(namespacedKey, persistentDataType);
	}
	//ADD PERSISTENT DATA TO ENTITY  AND CHECK IF EXISTING ETC
	
}
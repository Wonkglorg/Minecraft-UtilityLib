package com.wonkglorg.utilitylib.config;

import org.bukkit.Color;
import org.bukkit.Location;
import org.bukkit.OfflinePlayer;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.Map;
import java.util.Set;

public class ConfigJson implements Config
{
	
	@Override
	public Set<String> getSection(@NotNull String path, boolean deep)
	{
		return null;
	}
	
	@Override
	public String getString(@NotNull String path)
	{
		return null;
	}
	
	@Override
	public int getInt(@NotNull String path)
	{
		return 0;
	}
	
	@Override
	public double getDouble(@NotNull String path)
	{
		return 0;
	}
	
	@Override
	public long getLong(@NotNull String path)
	{
		return 0;
	}
	
	@Override
	public boolean getBoolean(@NotNull String path)
	{
		return false;
	}
	
	@Override
	public List<Map<?, ?>> getMapList(@NotNull String path)
	{
		return null;
	}
	
	@Override
	public List<String> getComments(@NotNull String path)
	{
		return null;
	}
	
	@Override
	public String getCurrentPath()
	{
		return null;
	}
	
	@Override
	public <T> T getObject(@NotNull String path, @NotNull Class<T> clazz)
	{
		return null;
	}
	
	@Override
	public Location getLocation(@NotNull String path)
	{
		return null;
	}
	
	@Override
	public ItemStack getItemStack(@NotNull String path)
	{
		return null;
	}
	
	@Override
	public Color getColor(@NotNull String path)
	{
		return null;
	}
	
	@Override
	public OfflinePlayer getOfflinePlayer(@NotNull String path)
	{
		return null;
	}
	
	@Override
	public boolean contains(@NotNull String path)
	{
		return false;
	}
	
	@Override
	public void set(@NotNull String path, Object value)
	{
	
	}
	
	@Override
	public void updateFiles()
	{
	
	}
	
	@Override
	public void load()
	{
	
	}
	
	@Override
	public void silentLoad()
	{
	
	}
	
	@Override
	public void save()
	{
	
	}
	
	@Override
	public void silentSave()
	{
	
	}
	
	@Override
	public String name()
	{
		return null;
	}
	
	@Override
	public String path()
	{
		return null;
	}
}
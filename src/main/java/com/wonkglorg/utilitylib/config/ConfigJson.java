package com.wonkglorg.utilitylib.config;

import java.util.Set;

public class ConfigJson implements Config
{
	
	@Override
	public Set<String> getSection(String path, boolean deep)
	{
		return null;
	}
	
	@Override
	public String getString(String path)
	{
		return null;
	}
	
	@Override
	public int getInt(String path)
	{
		return 0;
	}
	
	@Override
	public double getDouble(String path)
	{
		return 0;
	}
	
	@Override
	public long getLong(String path)
	{
		return 0;
	}
	
	@Override
	public boolean getBoolean(String path)
	{
		return false;
	}
	
	@Override
	public boolean contains(String path)
	{
		return false;
	}
	
	@Override
	public void set(String path, Object value)
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
	public void save()
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
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
	public String getStringValue(String path)
	{
		return null;
	}
	
	@Override
	public int getIntValue(String path)
	{
		return 0;
	}
	
	@Override
	public double getDoubleValue(String path)
	{
		return 0;
	}
	
	@Override
	public long getLongValue(String path)
	{
		return 0;
	}
	
	@Override
	public void setSection(String path, String value)
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
	
	//Add json compatablity
	
	
}
package com.wonkglorg.utilitylib.message.date_components;

public enum DateType
{
	MILLISECONDS("Ms", 1),
	SECONDS("S", 1000L),
	MINUTES("m", 1000L * 60),
	HOUR("H", 1000L * 60 * 60),
	DAY("D", 1000L * 60 * 60 * 24),
	WEEK("W", 1000L * 60 * 60 * 24 * 7),
	MONTH("M", 1000L * 60 * 60 * 24 * 7 * 30),
	YEAR("Y", 1000L * 60 * 60 * 24 * 7 * 30 * 365);
	
	private final String preset;
	private final long toMilliseconds;
	
	DateType(String preset, long toMilliseconds)
	{
		this.preset = preset;
		this.toMilliseconds = toMilliseconds;
	}
	
	public String getPreset()
	{
		return preset;
	}
	
	public long getMilliseconds()
	{
		return toMilliseconds;
	}
}
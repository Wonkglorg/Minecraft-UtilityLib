package com.wonkglorg.utilitylib.utils;

public enum Date
{
	MILLISECONDS("Ms", 0),
	SECONDS("S", 1000L),
	MINUTES("Min", 1000L * 60),
	HOUR("H", 1000L * 60 * 60),
	DAY("D", 1000L * 60 * 60 * 24),
	WEEK("W", 1000L * 60 * 60 * 24 * 7),
	MONTH("M", 1000L * 60 * 60 * 24 * 7 * 30),
	YEAR("Y", 1000L * 60 * 60 * 24 * 7 * 30 * 265),
	;
	private final String preset;
	private final long toMilliseconds;
	
	Date(String preset, long toMilliseconds)
	{
		this.preset = preset;
		this.toMilliseconds = toMilliseconds;
	}
	
	public String getPreset()
	{
		return preset;
	}
	
	public long getToMilliseconds()
	{
		return toMilliseconds;
	}
	
	//Add more variations to accept
	public static Date getDateFromString(String s)
	{
		switch(s)
		{
			case "Ms" ->
			{
				return MILLISECONDS;
			}
			case "S" ->
			{
				return SECONDS;
			}
			case "Min" ->
			{
				return MINUTES;
			}
			case "H" ->
			{
				return HOUR;
			}
			case "D" ->
			{
				return DAY;
			}
			case "W" ->
			{
				return WEEK;
			}
			case "M" ->
			{
				return MONTH;
			}
			case "Y" ->
			{
				return YEAR;
			}
		}
		return MILLISECONDS;
	}
}
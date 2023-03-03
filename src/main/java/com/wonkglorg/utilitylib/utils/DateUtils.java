package com.wonkglorg.utilitylib.utils;

public enum DateUtils
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
	
	DateUtils(String preset, long toMilliseconds)
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
	
	public static long toMilliseconds(String timeString)
	{
		String[] timeParts = timeString.split(" ");
		long timeInMs = 0;
		for(String part : timeParts)
		{
			int num = Integer.parseInt(part.substring(0, part.length() - 1));
			if(part.endsWith("y"))
			{
				timeInMs += (long) num * 365 * 24 * 60 * 60 * 1000;
			} else if(part.endsWith("d"))
			{
				timeInMs += (long) num * 24 * 60 * 60 * 1000;
			} else if(part.endsWith("h"))
			{
				timeInMs += (long) num * 60 * 60 * 1000;
			} else if(part.endsWith("m"))
			{
				timeInMs += (long) num * 60 * 1000;
			} else if(part.endsWith("s"))
			{
				timeInMs += num * 1000L;
			} else if(part.endsWith("ms"))
			{
				timeInMs += Integer.parseInt(part.substring(0, part.length() - 2));
			}
		}
		return timeInMs;
	}
	
	//Add extra parameters that let you choose biggest shorted, or overall time format, and then calculate correctly
	
	public static String toString(long timeInMs)
	{
		long years = timeInMs / (365L * 24 * 60 * 60 * 1000);
		timeInMs -= years * 365 * 24 * 60 * 60 * 1000;
		int days = (int) (timeInMs / (24 * 60 * 60 * 1000));
		timeInMs -= (long) days * 24 * 60 * 60 * 1000;
		int hours = (int) (timeInMs / (60 * 60 * 1000));
		timeInMs -= (long) hours * 60 * 60 * 1000;
		int minutes = (int) (timeInMs / (60 * 1000));
		timeInMs -= (long) minutes * 60 * 1000;
		int seconds = (int) (timeInMs / 1000);
		timeInMs -= seconds * 1000L;
		
		StringBuilder sb = new StringBuilder();
		if(years > 0)
		{
			sb.append(years).append("y ");
		}
		if(days > 0)
		{
			sb.append(days).append("d ");
		}
		if(hours > 0)
		{
			sb.append(hours).append("h ");
		}
		if(minutes > 0)
		{
			sb.append(minutes).append("m ");
		}
		if(seconds > 0)
		{
			sb.append(seconds).append("s ");
		}
		if(timeInMs > 0)
		{
			sb.append(timeInMs).append("ms");
		}
		return sb.toString().trim();
	}
	
}
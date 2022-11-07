package com.wonkglorg.utilitylib.utils.message;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;
import java.util.TreeMap;

public class Convert
{
	private final static TreeMap<Integer, String> romanMap = new TreeMap<>();
	
	static
	{
		romanMap.put(1000, "M");
		romanMap.put(900, "CM");
		romanMap.put(500, "D");
		romanMap.put(400, "CD");
		romanMap.put(100, "C");
		romanMap.put(90, "XC");
		romanMap.put(50, "L");
		romanMap.put(40, "XL");
		romanMap.put(10, "X");
		romanMap.put(9, "IX");
		romanMap.put(5, "V");
		romanMap.put(4, "IV");
		romanMap.put(1, "I");
		
	}
	
	public static String toRoman(int number)
	{
		int l = romanMap.floorKey(number);
		if(number == l)
		{
			return romanMap.get(number);
		}
		return romanMap.get(l) + toRoman(number - l);
	}
	
	public static int fromRoman(String roman)
	{
		return (int) fromRoman(roman, roman.length() - 1, 0);
	}
	
	private static double fromRoman(String roman, int pos, double rightNumeral)
	{
		if(pos < 0)
		{
			return 0;
		}
		char ch = roman.charAt(pos);
		double value = Math.floor(Math.pow(10, "IXCM".indexOf(ch))) + 5 * Math.floor(Math.pow(10, "VLD".indexOf(ch)));
		return value * Math.signum(value + 0.5 - rightNumeral) + fromRoman(roman, pos - 1, value);
	}
	
	private static final DateFormat formatter = new SimpleDateFormat("'Y:'yyyy 'M:'MM 'd:'dd 'h:'hh 'm:'mm 's:'ss 'ms:'SSS");
	
	public static String toDate(long milliseconds)
	{
		return toDate(milliseconds, formatter);
	}
	
	public static String toDate(long milliseconds, DateFormat dateFormat)
	{
		Date date = new Date(milliseconds);
		dateFormat.setTimeZone(TimeZone.getTimeZone("UTC"));
		return dateFormat.format(date);
	}
	
	public static long fromFormattedDate(String date)
	{
		return fromFormattedDate(date, formatter);
	}
	
	public static long fromFormattedDate(String date, DateFormat formatter)
	{
		try
		{
			return formatter.parse(date).getTime();
		} catch(ParseException e)
		{
			return 0;
		}
	}
	
	/**
	 * @param value
	 * @return
	 */
	public static String toBalance(double value)
	{
		/*
		if(value < 10000)
		{
			return format(value, "#.#");
		} else if(value < 1000000)
		{
			return (int) (value / 1000) + "k ";
		} else if(value < 1000000000)
		{
			return Utils.format((value / 1000) / 1000, "#.#") + "m ";
		} else if(value < 1000000000000L)
		{
			return (int) (((value / 1000) / 1000) / 1000) + "M ";
		} else
		{
			return "too much";
		}
		
		 */
		return "";
	}
	
	/**
	 * @param value
	 * @return
	 */
	public static String toBalance(long value)
	{
		/*
		if(value < 10000)
		{
			return Utils.format(value, "#.#");
		} else if(value < 1000000)
		{
			return (int) (value / 1000) + "k ";
		} else if(value < 1000000000)
		{
			return Utils.format((value / 1000) / 1000, "#.#") + "m ";
		} else if(value < 1000000000000L)
		{
			return (int) (((value / 1000) / 1000) / 1000) + "M ";
		} else
		{
			return "too much";
		}
		
		 */
		return "";
	}
	
}
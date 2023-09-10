package com.wonkglorg.utilitylib.base;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.TreeMap;
import java.util.concurrent.TimeUnit;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@SuppressWarnings("unused")
public final class Convert{
	private final static TreeMap<Integer, String> romanMap = new TreeMap<>();
	private final static Pattern pattern = Pattern.compile("(\\d+)([A-Za-z]+)");
	
	static {
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
	
	/**
	 * Converts a time string into millisecond time. Example : 10d 5m 8s 10ms
	 *
	 * @param timeString time string to be converted
	 * @return millisecond representation of the input string
	 */
	public static long fromTime(String timeString) {
		long timeInMs = 0;
		if(timeString == null || timeString.isEmpty()){
			return 0;
		}
		
		Matcher matcher = pattern.matcher(timeString);
		while(matcher.find()){
			long value = Long.parseLong(matcher.group(1));
			String suffix = matcher.group(2);
			
			for(DateType dateType : DateType.values()){
				if(suffix.endsWith(dateType.getPreset())){
					timeInMs += value * dateType.getMilliseconds();
					break;
				}
			}
			
		}
		return timeInMs;
	}
	
	/**
	 * Converts milliseconds into a human-readable time format, all dateTypes added will be displayed.
	 *
	 * @param timeInMs The time in milliseconds
	 * @param formats all dateTypes to be displayed
	 * @return Formatted time string
	 */
	public static String toTime(long timeInMs, DateType... formats) {
		
		Comparator<DateType> comparator = Comparator.comparingLong(DateType::getMilliseconds).reversed();
		List<DateType> dateList = Arrays.stream(formats).sorted(comparator).toList();
		
		StringBuilder sb = new StringBuilder();
		for(DateType dateType : dateList){
			long value = timeInMs / dateType.getMilliseconds();
			timeInMs %= dateType.getMilliseconds();
			
			if(value > 0){
				sb.append(value).append(dateType.getPreset()).append(" ");
			}
		}
		
		return sb.toString().trim();
	}
	
	/**
	 * Converts milliseconds into a human-readable time format
	 *
	 * @param timeInMs The time in milliseconds
	 * @return Formatted time string
	 */
	public static String toTime(long timeInMs) {
		return toTime(timeInMs, DateType.values());
	}
	
	public static String toRoman(int number) {
		int l = romanMap.floorKey(number);
		if(number == l){
			return romanMap.get(number);
		}
		return romanMap.get(l) + toRoman(number - l);
	}
	
	public static int fromRoman(String roman) {
		return (int) fromRoman(roman, roman.length() - 1, 0);
	}
	
	private static double fromRoman(String roman, int pos, double rightNumeral) {
		if(pos < 0){
			return 0;
		}
		char ch = roman.charAt(pos);
		double value = Math.floor(Math.pow(10, "IXCM".indexOf(ch))) + 5 * Math.floor(Math.pow(10, "VLD".indexOf(ch)));
		return value * Math.signum(value + 0.5 - rightNumeral) + fromRoman(roman, pos - 1, value);
	}
	
	/**
	 * @param value value
	 * @return balance
	 */
	public static String toBalance(double value) {
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
	 * @param value value
	 * @return balance
	 */
	public static String toBalance(long value) {
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
	
	public static long fromBalance(String balanceString) {
		return 0;
	}

	public enum DateType{
		MILLISECONDS("ms", 1),
		SECONDS("s", 1000L),
		MINUTES("m", 1000L * 60),
		HOUR("h", 1000L * 60 * 60),
		DAY("d", 1000L * 60 * 60 * 24),
		WEEK("W", 1000L * 60 * 60 * 24 * 7),
		MONTH("M", 1000L * 60 * 60 * 24 * 7 * 30),
		YEAR("Y", 1000L * 60 * 60 * 24 * 7 * 30 * 365);
		
		private final String preset;
		private final long toMilliseconds;
		
		DateType(String preset, long toMilliseconds) {
			this.preset = preset;
			this.toMilliseconds = toMilliseconds;
		}
		
		public String getPreset() {
			return preset;
		}
		
		public long getMilliseconds() {
			return toMilliseconds;
		}
	}
}
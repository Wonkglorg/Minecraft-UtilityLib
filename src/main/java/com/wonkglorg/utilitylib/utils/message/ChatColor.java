package com.wonkglorg.utilitylib.utils.message;

import net.kyori.adventure.text.format.TextDecoration;

import java.util.HashMap;
import java.util.Map;

public class ChatColor
{
	private static final String preset = "&";
	private static final Map<String, TextDecoration> decorationMap = new HashMap<>();
	
	public ChatColor()
	{
		decorationMap.put(Bold(), TextDecoration.BOLD);
		decorationMap.put(Italic(), TextDecoration.ITALIC);
		decorationMap.put(Obfuscated(), TextDecoration.OBFUSCATED);
		decorationMap.put(Underlined(), TextDecoration.UNDERLINED);
		decorationMap.put(StrikeThrough(), TextDecoration.STRIKETHROUGH);
	}
	
	public static String HexColor(String hex)
	{
		return preset + hex;
	}
	
	public static String YELLOW()
	{
		return Color.YELLOW.getHexColor();
	}
	
	public static String Reset()
	{
		return preset + "r";
	}
	
	public static String Strip()
	{
		return Reset();
	}
	
	public static String Bold()
	{
		return preset + "b";
	}
	
	public static String Italic()
	{
		return preset + "i";
	}
	
	public static String Obfuscated()
	{
		return preset + "o";
	}
	
	public static String Magic()
	{
		return Obfuscated();
	}
	
	public static String StripColor()
	{
		return preset + "c";
	}
	
	public static String StripDecoration()
	{
		return preset + "d";
	}
	
	public static String StrikeThrough()
	{
		return preset + "s";
	}
	
	public static String Underlined()
	{
		return preset + "u";
	}
	
	public static String LIME()
	{
		return Color.LIME.getHexColor();
	}
	
	public static String GREEN()
	{
		return Color.GREEN.getHexColor();
	}
	
	public static String DARK_GREEN()
	{
		return Color.DARK_GREEN.getHexColor();
	}
	
	public static String CYAN()
	{
		return Color.CYAN.getHexColor();
	}
	
	public static String BLUE()
	{
		return Color.BLUE.getHexColor();
	}
	
	public static String DARK_PURPLE()
	{
		return Color.DARK_PURPLE.getHexColor();
	}
	
	public static String PURPLE()
	{
		return Color.PURPLE.getHexColor();
	}
	
	public static String PINK()
	{
		return Color.PINK.getHexColor();
	}
	
	public static String MAGENTA()
	{
		return Color.MAGENTA.getHexColor();
	}
	
	public static String RED()
	{
		return Color.RED.getHexColor();
	}
	
	public static String ORANGE()
	{
		return Color.ORANGE.getHexColor();
	}
	
	public static String GOLD()
	{
		return Color.GOLD.getHexColor();
	}
	
	public static String BLACK()
	{
		return Color.BLACK.getHexColor();
	}
	
	public static String WHITE()
	{
		return Color.WHITE.getHexColor();
	}
	
	public static String DARK_GRAY()
	{
		return Color.DARK_GRAY.getHexColor();
	}
	
	public static String LIGHT_GRAY()
	{
		return Color.LIGHT_GRAY.getHexColor();
	}
	
	public static String GRAY()
	{
		return Color.GRAY.getHexColor();
	}
	
	public static TextDecoration StringToComponent(String s)
	{
		s = s.startsWith(preset) ? s : preset + s;
		return decorationMap.get(s);
	}
}
package com.wonkglorg.utilitylib.utils.message;

import net.kyori.adventure.text.format.TextDecoration;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * The type Chat color.
 */
@SuppressWarnings("unused")
public class ChatColor
{
	//maybe convert to enum? not sure
	private static final String preset = "&";
	
	/**
	 * Hex color string.
	 *
	 * @param hex the hex
	 * @return the string
	 */
	public static String HexColor(String hex)
	{
		hex = hex.startsWith("#") ? hex : "#" + hex;
		return preset + hex;
	}
	
	/**
	 * The constant Reset.
	 */
	public static String Reset = preset + "r";
	
	/**
	 * The constant Strip.
	 */
	public static String Strip = Reset;
	
	/**
	 * The constant Bold.
	 */
	public static String Bold = preset + "b";
	
	/**
	 * The constant Italic.
	 */
	public static String Italic = preset + "i";
	
	/**
	 * The constant Obfuscated.
	 */
	public static String Obfuscated = preset + "o";
	
	/**
	 * The constant Magic.
	 */
	public static String Magic = Obfuscated;
	
	/**
	 * The constant StripColor.
	 */
	public static String StripColor = preset + "c";
	
	/**
	 * The constant StripDecoration.
	 */
	public static String StripDecoration = preset + "d";
	
	/**
	 * The constant StrikeThrough.
	 */
	public static String StrikeThrough = preset + "s";
	
	/**
	 * The constant Underlined.
	 */
	public static String Underlined = preset + "u";
	public static String BLACK = Color.BLACK.getColor();
	public static String DARK_BLUE = Color.DARK_BLUE.getColor();
	public static String DARK_GREEN = Color.DARK_GREEN.getColor();
	public static String DARK_AQUA = Color.DARK_AQUA.getColor();
	public static String DARK_RED = Color.DARK_RED.getColor();
	public static String DARK_PURPLE = Color.DARK_PURPLE.getColor();
	public static String GOLD = Color.GOLD.getColor();
	public static String GRAY = Color.GRAY.getColor();
	public static String DARK_GRAY = Color.DARK_GRAY.getColor();
	public static String BLUE = Color.BLUE.getColor();
	public static String GREEN = Color.GREEN.getColor();
	public static String AQUA = Color.AQUA.getColor();
	public static String RED = Color.RED.getColor();
	public static String LIGHT_PURPLE = Color.LIGHT_PURPLE.getColor();
	public static String YELLOW = Color.YELLOW.getColor();
	public static String WHITE = Color.WHITE.getColor();
	
	private static final Map<Character, TextDecoration> decorationMap = new HashMap<>();
	private static final Map<Character, String> textColorMap = new HashMap<>();
	
	static
	{
		decorationMap.put(Bold.charAt(1), TextDecoration.BOLD);
		decorationMap.put(Italic.charAt(1), TextDecoration.ITALIC);
		decorationMap.put(Obfuscated.charAt(1), TextDecoration.OBFUSCATED);
		decorationMap.put(Underlined.charAt(1), TextDecoration.UNDERLINED);
		decorationMap.put(StrikeThrough.charAt(1), TextDecoration.STRIKETHROUGH);
		
		textColorMap.put('0', BLACK);
		textColorMap.put('1', DARK_BLUE);
		textColorMap.put('2', DARK_GREEN);
		textColorMap.put('3', DARK_AQUA);
		textColorMap.put('4', DARK_RED);
		textColorMap.put('5', DARK_PURPLE);
		textColorMap.put('6', GOLD);
		textColorMap.put('7', GRAY);
		textColorMap.put('8', DARK_GRAY);
		textColorMap.put('9', BLUE);
		textColorMap.put('a', GREEN);
		textColorMap.put('b', AQUA);
		textColorMap.put('c', RED);
		textColorMap.put('d', LIGHT_PURPLE);
		textColorMap.put('e', YELLOW);
		textColorMap.put('f', WHITE);
	}
	
	/**
	 * String to component text decoration.
	 *
	 * @param s the s
	 * @return the text decoration
	 */
	public static TextDecoration charToComponent(char s)
	{
		return decorationMap.get(s);
	}
	
	public static String charToColor(Character s)
	{
		return textColorMap.get(s).replace(preset, "");
	}
	
	public static Set<Character> colorCharacters()
	{
		return textColorMap.keySet();
	}
	
	public static Set<Character> decorationCharacters()
	{
		return decorationMap.keySet();
	}
	
}
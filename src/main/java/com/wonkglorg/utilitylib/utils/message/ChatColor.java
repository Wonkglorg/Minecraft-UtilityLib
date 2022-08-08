package com.wonkglorg.utilitylib.utils.message;

import net.kyori.adventure.text.format.TextDecoration;

import java.util.HashMap;
import java.util.Map;

/**
 * The type Chat color.
 */
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
	
	/**
	 * The constant YELLOW.
	 */
	public static String YELLOW = Color.YELLOW.getHexColor();
	
	/**
	 * The constant LIME.
	 */
	public static String LIME = Color.LIME.getHexColor();
	
	/**
	 * The constant GREEN.
	 */
	public static String GREEN = Color.GREEN.getHexColor();
	
	/**
	 * The constant DARK_GREEN.
	 */
	public static String DARK_GREEN = Color.DARK_GREEN.getHexColor();
	
	/**
	 * The constant CYAN.
	 */
	public static String CYAN = Color.CYAN.getHexColor();
	
	/**
	 * The constant BLUE.
	 */
	public static String BLUE = Color.BLUE.getHexColor();
	
	/**
	 * The constant DARK_PURPLE.
	 */
	public static String DARK_PURPLE = Color.DARK_PURPLE.getHexColor();
	
	/**
	 * The constant PURPLE.
	 */
	public static String PURPLE = Color.PURPLE.getHexColor();
	
	/**
	 * The constant PINK.
	 */
	public static String PINK = Color.PINK.getHexColor();
	
	/**
	 * The constant MAGENTA.
	 */
	public static String MAGENTA = Color.MAGENTA.getHexColor();
	
	/**
	 * The constant RED.
	 */
	public static String RED = Color.RED.getHexColor();
	
	/**
	 * The constant ORANGE.
	 */
	public static String ORANGE = Color.ORANGE.getHexColor();
	
	/**
	 * The constant GOLD.
	 */
	public static String GOLD = Color.GOLD.getHexColor();
	
	/**
	 * The constant BLACK.
	 */
	public static String BLACK = Color.BLACK.getHexColor();
	
	/**
	 * The constant WHITE.
	 */
	public static String WHITE = Color.WHITE.getHexColor();
	
	/**
	 * The constant DARK_GRAY.
	 */
	public static String DARK_GRAY = Color.DARK_GRAY.getHexColor();
	
	/**
	 * The constant LIGHT_GRAY.
	 */
	public static String LIGHT_GRAY = Color.LIGHT_GRAY.getHexColor();
	
	/**
	 * The constant GRAY.
	 */
	public static String GRAY = Color.GRAY.getHexColor();
	
	private static final Map<String, TextDecoration> decorationMap = new HashMap<>();
	
	static
	{
		decorationMap.put(Bold, TextDecoration.BOLD);
		decorationMap.put(Italic, TextDecoration.ITALIC);
		decorationMap.put(Obfuscated, TextDecoration.OBFUSCATED);
		decorationMap.put(Underlined, TextDecoration.UNDERLINED);
		decorationMap.put(StrikeThrough, TextDecoration.STRIKETHROUGH);
	}
	
	/**
	 * String to component text decoration.
	 *
	 * @param s the s
	 * @return the text decoration
	 */
	public static TextDecoration StringToComponent(String s)
	{
		s = s.startsWith(preset) ? s : preset + s;
		return decorationMap.get(s);
	}
}
package com.wonkglorg.utilitylib.base.message;

import com.wonkglorg.utilitylib.base.message.color_components.Color;
import com.wonkglorg.utilitylib.base.message.color_components.RGB;
import net.kyori.adventure.text.format.TextDecoration;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

/**
 * Constant values to set colors in chat using the {@link Message} class
 */
@SuppressWarnings("unused")
public final class ChatColor{
	public final static String preset = "&";
	
	public final static String Reset = preset + "r";
	public final static String Strip = Reset;
	public final static String Bold = preset + "b";
	public final static String Italic = preset + "i";
	public final static String Obfuscated = preset + "o";
	public final static String Magic = Obfuscated;
	public final static String StripColor = preset + "c";
	public final static String StripDecoration = preset + "d";
	public final static String StrikeThrough = preset + "s";
	
	public final static String Underlined = preset + "u";
	public final static String BLACK = preset + "#000000";
	public final static String DARK_BLUE = preset + "#555555";
	public final static String DARK_GREEN = preset + "#00AA00";
	public final static String DARK_AQUA = preset + "#00AAAA";
	public final static String DARK_RED = preset + "#AA0000";
	public final static String DARK_PURPLE = preset + "#AA00AA";
	public final static String GOLD = preset + "#FFAA00";
	public final static String GRAY = preset + "#AAAAAA";
	public final static String DARK_GRAY = preset + "#555555";
	public final static String BLUE = preset + "#5555FF";
	public final static String GREEN = preset + "#55FF55";
	public final static String AQUA = preset + "#55FFFF";
	public final static String RED = preset + "#FF5555";
	public final static String LIGHT_PURPLE = preset + "#FF55FF";
	public final static String YELLOW = preset + "#FFFF55";
	public final static String WHITE = preset + "#FFFFFF";
	
	private static final Map<Character, TextDecoration> decorationMap = new HashMap<>();
	private static final Map<Character, String> textColorMap = new HashMap<>();
	
	static {
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
	 * Converts a hex String into the required format for {@link Message}
	 * <p>
	 * format: "#00ff00"
	 */
	
	public static String hexToChatColor(String s) {
		Objects.requireNonNull(s);
		if(s.startsWith("&")){
			return s;
		}
		return preset + s;
		
	}
	
	/**
	 * Converts rgb to a hex representation to be used in {@link Message}
	 */
	public static String rgbToChatColor(int r, int g, int b) {
		return preset + Color.toHex(r, g, b);
	}
	
	/**
	 * Converts an RGB object to a hex representation to be used in {@link Message}
	 */
	public static String rgbToChatColor(RGB rgb) {
		Objects.requireNonNull(rgb);
		return preset + rgb.toHex();
	}
	
	/**
	 * Returns the represented Textdecoration based on the chararacter
	 */
	public static TextDecoration charToComponent(char s) {
		return decorationMap.get(s);
	}
	
	/**
	 * Returns a hex representation of the character color
	 */
	public static String charToColor(Character s) {
		return textColorMap.get(s).replace(preset, "");
	}
	
	/**
	 * Returns a Set of all valid color characters
	 */
	
	public static Set<Character> colorCharacters() {
		return textColorMap.keySet();
	}
	
	/**
	 * Returns a Set of all valid decoration characters
	 */
	public static Set<Character> decorationCharacters() {
		return decorationMap.keySet();
	}
	
}
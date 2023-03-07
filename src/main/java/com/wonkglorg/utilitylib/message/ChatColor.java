package com.wonkglorg.utilitylib.message;

import net.kyori.adventure.text.format.TextDecoration;
import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * The type Chat color.
 */
@SuppressWarnings("unused")
public final class ChatColor
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
	
	/**
	 * Creates a gradient going through all colors in order as they get listed in the colors array
	 * @param text Text to be displayed
	 * @param colors Colors to be used, they keep their order as entered
	 * @return A string formatted &#%02x%02x%02x(character)
	 */
	public static String createGradient(@NotNull String text, @NotNull java.awt.Color... colors) {
		return createGradient(text, 1.0, colors);
	}
	/**
	 * Creates a gradient with a shift going through all colors in order as they get listed in the colors array when modifying the shift value it
	 * shifts the colors to create a waving gradient effect
	 * @param text Text to be displayed
	 * @param shiftValue shift value shifts the colors based on its input from 0 to 255, if the value exceeds 255 it starts back from 0
	 * @param colors Colors to be used, they keep their order as entered
	 * @return A string formatted &#%02x%02x%02x(character)
	 */
	public static String createGradient(@NotNull String text, double shiftValue, @NotNull java.awt.Color... colors) {
		StringBuilder result = new StringBuilder();
		int length = text.length();
		float step = 1f / (length - 1);
		
		for (int i = 0; i < length; i++) {
			float ratio = i * step;
			java.awt.Color color = getColorAtRatio(ratio, colors, shiftValue * i);
			String hex = String.format("#%02x%02x%02x", color.getRed(), color.getGreen(), color.getBlue());
			result.append(hex);
			result.append(text.charAt(i));
		}
		
		return result.toString();
	}
	
	private static java.awt.Color getColorAtRatio(float ratio, java.awt.Color[] colors, double shiftValue) {
		if (ratio <= 0) {
			return colors[0];
		} else if (ratio >= 1) {
			return colors[colors.length - 1];
		}
		
		int index = (int) (ratio * (colors.length - 1));
		float startRatio = index * 1f / (colors.length - 1);
		float endRatio = (index + 1) * 1f / (colors.length - 1);
		
		java.awt.Color startColor = shiftColor(colors[index], shiftValue);
		java.awt.Color endColor = shiftColor(colors[index + 1], shiftValue);
		
		float blendRatio = (ratio - startRatio) / (endRatio - startRatio);
		
		int red = (int) (startColor.getRed() + (endColor.getRed() - startColor.getRed()) * blendRatio);
		int green = (int) (startColor.getGreen() + (endColor.getGreen() - startColor.getGreen()) * blendRatio);
		int blue = (int) (startColor.getBlue() + (endColor.getBlue() - startColor.getBlue()) * blendRatio);
		
		return new java.awt.Color(red, green, blue);
	}
	
	private static java.awt.Color shiftColor(java.awt.Color color, double shiftValue) {
		int r = (color.getRed() + (int) shiftValue) % 256;
		int g = (color.getGreen() + (int) shiftValue) % 256;
		int b = (color.getBlue() + (int) shiftValue) % 256;
		return new java.awt.Color(r, g, b);
	}
	
	public static Set<Character> decorationCharacters()
	{
		return decorationMap.keySet();
	}
	
}
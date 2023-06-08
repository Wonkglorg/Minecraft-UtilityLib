package com.wonkglorg.utilitylib.message.color_components;

import org.jetbrains.annotations.NotNull;

import java.util.Objects;

@SuppressWarnings("unused")
public final class Color{
	
	private final RGB color;
	
	public Color(@NotNull final String hex) {
		this.color = hexToRGB(Objects.requireNonNull(hex));
	}
	
	public Color(@NotNull final RGB color) {
		this.color = Objects.requireNonNull(color);
	}
	
	public Color(final int r, final int g, final int b) {
		this.color = new RGB(r, g, b);
	}
	
	public Color(@NotNull final java.awt.Color color) {
		Objects.requireNonNull(color);
		this.color = new RGB(color.getRed(), color.getGreen(), color.getBlue());
	}
	
	public int getRed() {
		return this.color.r();
	}
	
	public int getGreen() {
		return this.color.g();
	}
	
	public int getBlue() {
		return this.color.b();
	}
	
	public static String toHex(int r, int g, int b) {
		return new RGB(r, g, b).toHex();
	}
	
	public static RGB hexToRGB(@NotNull String hex) {
		
		Objects.requireNonNull(hex);
		if(hex.charAt(0) == '#'){
			hex = hex.substring(1);
		}
		
		int r = Integer.parseInt(hex.substring(0, 2), 16);
		int g = Integer.parseInt(hex.substring(2, 4), 16);
		int b = Integer.parseInt(hex.substring(4, 6), 16);
		
		return new RGB(r, g, b);
	}
	
	public static RGB hslToRGB(float h, float s, float l) {
		float c = (1 - Math.abs(2 * l - 1)) * s;
		float x = c * (1 - Math.abs((h / 60) % 2 - 1));
		float m = l - c / 2;
		float r, g, b;
		if(h >= 0 && h < 60){
			r = c;
			g = x;
			b = 0;
		} else if(h >= 60 && h < 120){
			r = x;
			g = c;
			b = 0;
		} else if(h >= 120 && h < 180){
			r = 0;
			g = c;
			b = x;
		} else if(h >= 180 && h < 240){
			r = 0;
			g = x;
			b = c;
		} else if(h >= 240 && h < 300){
			r = x;
			g = 0;
			b = c;
		} else {
			r = c;
			g = 0;
			b = x;
		}
		r = Math.round((r + m) * 255);
		g = Math.round((g + m) * 255);
		b = Math.round((b + m) * 255);
		return new RGB((int) r, (int) g, (int) b);
	}
	
	public static RGB cmykToRGB(float c, float m, float y, float k) {
		int r = Math.round((1 - c) * (1 - k) * 255);
		int g = Math.round((1 - m) * (1 - k) * 255);
		int b = Math.round((1 - y) * (1 - k) * 255);
		return new RGB(r, g, b);
	}
	
	/**
	 * Creates a gradient going through all colors in order as they get listed in the colors array
	 *
	 * @param text Text to be displayed
	 * @param colors Colors to be used, they keep their order as entered
	 * @return A string formatted &#%02x%02x%02x(character)
	 */
	public static String createGradient(@NotNull String text, @NotNull Color... colors) {
		return createGradient(text, 1.0, colors);
	}
	
	/**
	 * Creates a gradient with a shift going through all colors in order as they get listed in the colors array when modifying the shift value it
	 * shifts the colors to create a waving gradient effect
	 *
	 * @param text Text to be displayed
	 * @param shiftValue shift value shifts the colors based on its input from 0 to 255, if the value exceeds 255 it starts back from 0
	 * @param colors Colors to be used, they keep their order as entered
	 * @return A string formatted &#%02x%02x%02x(character)
	 */
	public static String createGradient(@NotNull String text, double shiftValue, @NotNull Color... colors) {
		StringBuilder result = new StringBuilder();
		int length = text.length();
		float step = 1f / (length - 1);
		
		for(int i = 0; i < length; i++){
			float ratio = i * step;
			Color color = getColorAtRatio(ratio, colors, shiftValue * i);
			String hex = String.format("#%02x%02x%02x", color.getRed(), color.getGreen(), color.getBlue());
			result.append(hex);
			result.append(text.charAt(i));
		}
		
		return result.toString();
	}
	
	/**
	 * Returns a color from the colors array based on how far the current value is between min and max.
	 * <p>
	 * For example can be used in % where 100% is green 50% is yellow and 0% is red. input the colors red yellow green, min value of 0 max value 0f
	 * 100 and the current value based on the current $
	 *
	 * @param colors
	 * @param minVal
	 * @param maxVal
	 * @param currentVal
	 * @return
	 */
	public static String getHexColorBetween(double minVal, double maxVal, double currentVal, Color... colors) {
		if(colors.length == 0){
			return null;
		}
		if(colors.length == 1){
			
			return String.format("#%02x%02x%02x", colors[0].getRed(), colors[0].getGreen(), colors[0].getBlue());
		}
		double percentage = (currentVal - minVal) / (maxVal - minVal);
		percentage = Math.max(0.0, Math.min(1.0, percentage));
		
		Color color1 = colors[0];
		Color color2 = colors[1];
		if(colors.length > 2){
			double segmentSize = 1.0 / (colors.length - 1);
			double segmentIndex = percentage / segmentSize;
			int lowerIndex = (int) Math.floor(segmentIndex);
			int upperIndex = (int) Math.ceil(segmentIndex);
			if(upperIndex >= colors.length){
				upperIndex = colors.length - 1;
				lowerIndex = upperIndex - 1;
			}
			color1 = colors[lowerIndex];
			color2 = colors[upperIndex];
			percentage = (percentage - lowerIndex * segmentSize) / segmentSize;
		}
		int red = (int) Math.round(color1.getRed() + percentage * (color2.getRed() - color1.getRed()));
		int green = (int) Math.round(color1.getGreen() + percentage * (color2.getGreen() - color1.getGreen()));
		int blue = (int) Math.round(color1.getBlue() + percentage * (color2.getBlue() - color1.getBlue()));
		
		return String.format("#%02x%02x%02x", red, green, blue);
	}
	
	private static Color getColorAtRatio(float ratio, Color[] colors, double shiftValue) {
		if(ratio <= 0){
			return colors[0];
		} else if(ratio >= 1){
			return colors[colors.length - 1];
		}
		int index = (int) (ratio * (colors.length - 1));
		float startRatio = index * 1f / (colors.length - 1);
		float endRatio = (index + 1) * 1f / (colors.length - 1);
		Color startColor = shiftColor(colors[index], shiftValue);
		Color endColor = shiftColor(colors[index + 1], shiftValue);
		
		float blendRatio = (ratio - startRatio) / (endRatio - startRatio);
		
		int red = (int) (startColor.getRed() + (endColor.getRed() - startColor.getRed()) * blendRatio);
		int green = (int) (startColor.getGreen() + (endColor.getGreen() - startColor.getGreen()) * blendRatio);
		int blue = (int) (startColor.getBlue() + (endColor.getBlue() - startColor.getBlue()) * blendRatio);
		
		return new Color(red, green, blue);
	}
	
	private static Color shiftColor(Color color, double shiftValue) {
		int r = (color.getRed() + (int) shiftValue) % 256;
		int g = (color.getGreen() + (int) shiftValue) % 256;
		int b = (color.getBlue() + (int) shiftValue) % 256;
		return new Color(r, g, b);
	}
	
	@Override
	public String toString() {
		return color.toHex();
	}
}
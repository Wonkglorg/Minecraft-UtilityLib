package com.wonkglorg.utilitylib.utils;

import org.jetbrains.annotations.NotNull;

public class ColorUtils
{
	
	/**
	 * Returns a new {@link java.awt.Color} from the input string
	 *
	 * @param hex
	 * @return
	 * @throws IllegalArgumentException
	 */
	public static java.awt.Color hexToColor(@NotNull String hex) throws IllegalArgumentException
	{
		if(hex.startsWith("#"))
		{
			hex = hex.substring(1);
		}
		
		int red = Integer.parseInt(hex.substring(0, 2), 16);
		int green = Integer.parseInt(hex.substring(2, 4), 16);
		int blue = Integer.parseInt(hex.substring(4, 6), 16);
		
		return new java.awt.Color(red, green, blue);
	}
}
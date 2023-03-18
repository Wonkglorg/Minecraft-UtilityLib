package com.wonkglorg.utilitylib.message.color_components;

import org.jetbrains.annotations.NotNull;

@SuppressWarnings("unused")
public final class Color
{
	
	private final RGB color;
	
	public Color(@NotNull final String hex)
	{
		this.color = RGB.hexToRGB(hex);
	}
	
	public Color(@NotNull final RGB color)
	{
		this.color = color;
	}
	
	public Color(final int r, final int g, final int b)
	{
		this.color = new RGB(r, g, b);
	}
	
	public Color(@NotNull final java.awt.Color color)
	{
		this.color = new RGB(color.getRed(), color.getGreen(), color.getBlue());
	}
	public int getRed()
	{
		return this.color.r();
	}
	
	public int getGreen()
	{
		return this.color.g();
	}
	
	public int getBlue()
	{
		return this.color.b();
	}
	
}
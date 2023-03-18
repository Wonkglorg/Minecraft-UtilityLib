package com.wonkglorg.utilitylib.message.color_components;

import org.jetbrains.annotations.NotNull;

public record RGB(int r, int g, int b)
{
	
	public String toHex()
	{
		return String.format("#%02X%02X%02X", r, g, b);
	}
	
	public static RGB hexToRGB(@NotNull String hex)
	{
		if(hex.charAt(0) == '#')
		{
			hex = hex.substring(1);
		}
		
		int r = Integer.parseInt(hex.substring(0, 2), 16);
		int g = Integer.parseInt(hex.substring(2, 4), 16);
		int b = Integer.parseInt(hex.substring(4, 6), 16);
		
		return new RGB(r, g, b);
	}
	
	public static RGB hslToRGB(float h, float s, float l)
	{
		float c = (1 - Math.abs(2 * l - 1)) * s;
		float x = c * (1 - Math.abs((h / 60) % 2 - 1));
		float m = l - c / 2;
		float r, g, b;
		if(h >= 0 && h < 60)
		{
			r = c;
			g = x;
			b = 0;
		} else if(h >= 60 && h < 120)
		{
			r = x;
			g = c;
			b = 0;
		} else if(h >= 120 && h < 180)
		{
			r = 0;
			g = c;
			b = x;
		} else if(h >= 180 && h < 240)
		{
			r = 0;
			g = x;
			b = c;
		} else if(h >= 240 && h < 300)
		{
			r = x;
			g = 0;
			b = c;
		} else
		{
			r = c;
			g = 0;
			b = x;
		}
		r = Math.round((r + m) * 255);
		g = Math.round((g + m) * 255);
		b = Math.round((b + m) * 255);
		return new RGB((int) r, (int) g, (int) b);
	}
	
	public static RGB cmykToRGB(float c, float m, float y, float k)
	{
		int r = Math.round((1 - c) * (1 - k) * 255);
		int g = Math.round((1 - m) * (1 - k) * 255);
		int b = Math.round((1 - y) * (1 - k) * 255);
		return new RGB(r, g, b);
	}
}
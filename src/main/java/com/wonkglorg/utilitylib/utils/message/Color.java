package com.wonkglorg.utilitylib.utils.message;

@SuppressWarnings("unused")
public enum Color
{
	BLACK("000000"),
	DARK_GRAY("555555"),
	GRAY("AAAAAA"),
	WHITE("FFFFFF"),
	DARK_PURPLE("AA00AA"),
	LIGHT_PURPLE("FF55FF"),
	DARK_BLUE("0000AA"),
	BLUE("5555FF"),
	DARK_GREEN("00AA00"),
	DARK_AQUA("00AAAA"),
	DARK_RED("AA0000"),
	GOLD("FFAA00"),
	GREEN("55FF55"),
	AQUA("55FFFF"),
	RED("FF5555"),
	YELLOW("FFFF55"),
	
	;
	
	private final String hexColor;
	
	Color(final String hexColor)
	{
		this.hexColor = hexColor;
	}
	
	public String getHexColor()
	{
		return "#" + hexColor;
	}
	
	public String getColor()
	{
		return "&#" + hexColor;
	}
}
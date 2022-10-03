package com.wonkglorg.utilitylib.utils.message;

@SuppressWarnings("unused")
public enum Color
{
	BLACK("000000"),
	DARK_BLUE("0000AA"),
	DARK_GREEN("00AA00"),
	DARK_AQUA("00AAAA"),
	DARK_RED("AA0000"),
	DARK_PURPLE("AA00AA"),
	GOLD("FFAA00"),
	GRAY("FFAA00"),
	DARK_GRAY("555555"),
	BLUE("5555FF"),
	GREEN("55FF55"),
	AQUA("55FFFF"),
	RED("FF5555"),
	LIGHT_PURPLE("FF55FF"),
	YELLOW("FFFF55"),
	WHITE("FFFFFF"),
	
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
package com.wonkglorg.utilitylib.utils.message;

public enum Color
{
	YELLOW("&#FFFF00"),
	LIME("&#32CD32"),
	GREEN("&#00FF00"),
	DARK_GREEN("&#013220"),
	CYAN("&#00FFFF"),
	BLUE("&#0000FF"),
	DARK_PURPLE("&#301934"),
	PURPLE("&#800080"),
	PINK("&#FFC0CB"),
	MAGENTA("&#FF00FF"),
	RED("&#ff0000"),
	ORANGE("&#FFA500"),
	GOLD("&#FFD700"),
	BLACK("&#000000"),
	WHITE("&#FFFFFF"),
	DARK_GRAY("&#a9a9a9"),
	LIGHT_GRAY("&#eedd82"),
	GRAY("&#808080");
	
	private final String hexColor;
	Color(final String hexColor){
		this.hexColor = hexColor;
	}
	public String getHexColor()
	{
		return hexColor;
	}
}
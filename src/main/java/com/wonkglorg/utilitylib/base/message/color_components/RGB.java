package com.wonkglorg.utilitylib.base.message.color_components;

public record RGB(int r, int g, int b){
	
	public String toHex() {
		return String.format("#%02X%02X%02X", r, g, b);
	}
}
package com.wonkglorg.utilitylib.inventory;

public enum InventorySize
{
	TINY(9),
	SMALL(18),
	MEDIUM(27),
	BIG(36),
	LARGE(45),
	MAX(54);
	private final int size;
	
	InventorySize(int size)
	{
		this.size = size;
	}
	
	public int getSize()
	{
		return size;
	}
}
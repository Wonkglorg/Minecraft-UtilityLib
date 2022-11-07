package com.wonkglorg.utilitylib.managers;

public enum ManagerValues
{
	CONFIG(new ConfigManager()),
	LANG(new LangManager()),
	COMMAND(new CommandManager()),
	ENCHANT(new EnchantmentManager()),
	RECIPE(new RecipeManager()),
	EVENT(null)
	;
	private final Manager manager;
	ManagerValues(Manager manager)
	{
		this.manager = manager;
	}
	
	public Manager getManager()
	{
		return manager;
	}
}
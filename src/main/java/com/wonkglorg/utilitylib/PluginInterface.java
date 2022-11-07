package com.wonkglorg.utilitylib;


/**
 * The interface Main.
 */
@SuppressWarnings("unused")
public interface PluginInterface
{
	
	/**
	 * Register all events
	 */
	void events();
	
	/**
	 * Register all commands
	 */
	void commands();
	
	/**
	 * Register all configs
	 */
	void configs();
	void langs();
	void recipes();
}
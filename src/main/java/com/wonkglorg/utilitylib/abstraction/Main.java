package com.wonkglorg.utilitylib.abstraction;


/**
 * The interface Main.
 */
@SuppressWarnings("unused")
public interface Main
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
}
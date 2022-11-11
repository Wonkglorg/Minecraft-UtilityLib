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
	 * Register all configs files
	 */
	void configs();
	/**
	 * Register all lang files
	 */
	void langs();
	/**
	 * Register all recipes
	 */
	void recipes();
	/**
	 * Register all enchants
	 */
	void enchants();
}
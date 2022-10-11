package com.wonkglorg.utilitylib.config;

import java.util.Set;

public interface Config
{
	/**
	 * Gets values from a specific section.
	 * @param path
	 * @param deep
	 * @return
	 */
	Set<String> getSection(String path, boolean deep);
	
	String getStringValue(String path);
	
	int getIntValue(String path);
	
	double getDoubleValue(String path);
	
	long getLongValue(String path);
	
	//get world and get player etc?
	
	/**
	 * Sets value for a specific section.
	 * @param path
	 * @param value
	 */
	void setSection(String path, String value);
	
	/**
	 * Loads config from file.
	 */
	void load();
	
	/**
	 * Saves config to file.
	 */
	void save();
	
	/**
	 * Returns the name of the config
	 * @return
	 */
	String name();
}
package com.wonkglorg.utilitylib.config;

import java.util.Set;

public interface Config
{
	/**
	 * Gets values from a specific section.
	 *
	 * @param path
	 * @param deep
	 * @return
	 */
	Set<String> getSection(String path, boolean deep);
	
	String getString(String path);
	
	/**
	 * Returns the int value of a specified path
	 *
	 * @param path Path to check
	 * @return the int value if existing, otherwise 0
	 */
	int getInt(String path);
	
	/**
	 * Returns the double value of a specified path
	 *
	 * @param path Path to check
	 * @return the double value if existing, otherwise 0
	 */
	double getDouble(String path);
	
	/**
	 * Returns the long value of a specified path
	 *
	 * @param path Path to check
	 * @return the long value if existing, otherwise 0
	 */
	long getLong(String path);
	
	/**
	 * Returns the boolean value of the specified path
	 *
	 * @param path Path to check
	 * @return true if the value exists and is set to true, false otherwise
	 */
	boolean getBoolean(String path);
	
	/**
	 * Checks if a path exists within the config
	 *
	 * @param path Path to check
	 * @return true if the path exists, false otherwise
	 */
	boolean contains(String path);
	
	/**
	 * Sets value for a specific section.
	 *
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
	 *
	 * @return Config file name
	 */
	String name();
	
	/**
	 * Returns the path of the config including the name
	 *
	 * @return Config file path
	 */
	String path();
}
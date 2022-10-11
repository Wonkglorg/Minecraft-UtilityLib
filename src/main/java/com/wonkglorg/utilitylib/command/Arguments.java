package com.wonkglorg.utilitylib.command;

import com.wonkglorg.utilitylib.utils.location.LocationUtils;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.OfflinePlayer;
import org.bukkit.World;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

@SuppressWarnings("unused")
public abstract class Arguments
{
	
	protected String[] args;
	protected int parentCount = 0;
	
	/**
	 * Returns the argument at the specified index as a string or null if no argument exists at that index
	 *
	 * @param index starts at 0 for the first argument
	 * @return String representation of the argument
	 */
	protected String argAsString(int index)
	{
		try
		{
			return this.args[index + this.parentCount];
		} catch(Exception e)
		{
			return null;
		}
	}
	
	/**
	 * Compares 2 strings with each other, returns falls if either is null or they are not equal. Does not check capital letters
	 *
	 * @param compare first string
	 * @param to second string
	 * @return boolean
	 */
	protected boolean compareString(String compare, String to)
	{
		if(compare == null || to == null)
		{
			return false;
		}
		return compare.equalsIgnoreCase(to);
	}
	
	/**
	 * Returns the argument at the specified index as a string or the default value if no argument exists at that index
	 *
	 * @param index starts at 0 for the first argument
	 * @param defaultValue default value to be returned instead
	 * @return String representation of the argument or the default value
	 */
	protected String argAsString(int index, @NotNull String defaultValue)
	{
		try
		{
			return this.args[index + this.parentCount];
		} catch(Exception e)
		{
			return defaultValue;
		}
	}
	
	/**
	 * Returns the argument at the specified index as a boolean
	 * returns true if the string is not empty and the string is equal to true,
	 * does not check capital letters
	 *
	 * @param index {@link Integer} the index
	 * @return Boolean representation of the argument at the specified index
	 */
	protected boolean argAsBoolean(int index)
	{
		return Boolean.parseBoolean(argAsString(index));
	}
	
	/**
	 * Returns the argument at the specified index as a boolean or the default value if non exists
	 * returns true if the string is not empty and the string is equal to true,
	 * does not check capital letters
	 *
	 * @param index {@link Integer} the index
	 * @param defaultValue the default boolean to be returned if the given argument is not a boolean
	 * @return Boolean representation of the argument at the specified index
	 */
	protected boolean argAsBoolean(int index, boolean defaultValue)
	{
		try
		{
			String s = argAsString(index);
			if(s == null || !s.equalsIgnoreCase("true") && !s.equalsIgnoreCase("false"))
			{
				return defaultValue;
			}
			return Boolean.parseBoolean(s);
		} catch(Exception e)
		{
			return defaultValue;
		}
	}
	
	/**
	 * Returns the argument at the specified index as an Integer
	 * or 0 if the argument is not a valid integer
	 *
	 * @param index {@link Integer} the index
	 * @return Integer representation of the argument at a specified index
	 */
	protected int argAsInteger(int index)
	{
		try
		{
			return Integer.parseInt(argAsString(index));
		} catch(Exception e)
		{
			return 0;
		}
	}
	
	/**
	 * Returns the argument at the specified index as an Integer
	 * or the default value if the argument is not a valid integer
	 *
	 * @param index {@link Integer} the index
	 * @param defaultValue default {@link Integer} to return
	 * @return Integer representation of the argument at a specified index
	 */
	protected int argAsInteger(int index, int defaultValue)
	{
		try
		{
			return Integer.parseInt(argAsString(index));
		} catch(Exception e)
		{
			return defaultValue;
		}
	}
	
	/**
	 * Returns the argument at the specified index as a Long or
	 * 0 if the argument is not a valid Long
	 *
	 * @param index {@link Long} the index
	 * @return Long representation of the argument at a specified index
	 */
	protected long argAsLong(int index)
	{
		try
		{
			return Long.parseLong(argAsString(index));
		} catch(Exception e)
		{
			return 0;
		}
	}
	
	/**
	 * Returns the argument at the specified index as a Long
	 * or the default value if the argument is not a valid Long
	 *
	 * @param index {@link Long} the index
	 * @param defaultValue default {@link Long} to return
	 * @return Long representation of the argument at a specified index
	 */
	protected long argAsLong(int index, long defaultValue)
	{
		try
		{
			return Long.parseLong(argAsString(index));
		} catch(Exception e)
		{
			return defaultValue;
		}
	}
	
	/**
	 * Returns the argument at the specified index as a Double
	 * or 0 if the argument is not a valid Double
	 *
	 * @param index {@link Double} the index
	 * @return Double representation of the argument at a specified index
	 */
	protected double argAsDouble(int index)
	{
		try
		{
			return Double.parseDouble(argAsString(index).replace(",", "."));
		} catch(Exception e)
		{
			return 0;
		}
	}
	
	/**
	 * Returns the argument at the specified index as a Double
	 * or the default value if the argument is not a valid Double
	 *
	 * @param index {@link Double} the index
	 * @param defaultValue default double to return
	 * @return Double representation of the argument at a specified index
	 */
	protected double argAsDouble(int index, double defaultValue)
	{
		try
		{
			return Double.parseDouble(argAsString(index).replace(",", "."));
		} catch(Exception e)
		{
			return defaultValue;
		}
	}
	
	/**
	 * Returns the argument at the specified index as a {@link Player}
	 * or null if the argument is not a valid {@link Player}
	 *
	 * @param index {@link Player} the index
	 * @return {@link Player} representation of the argument at a specified index
	 */
	protected Player argAsPlayer(int index)
	{
		return argAsString(index) == null ? null : Bukkit.getPlayer(argAsString(index));
	}
	
	/**
	 * Returns the argument at the specified index as a {@link Player}
	 * or default value if the argument is not a valid {@link Player}
	 *
	 * @param index {@link Player} the index
	 * @param defaultValue default value to return
	 * @return {@link Player} representation of the argument at a specified index
	 */
	protected Player argAsPlayer(int index, @NotNull Player defaultValue)
	{
		try
		{
			return argAsString(index) == null ? null : Bukkit.getPlayer(argAsString(index));
		} catch(Exception e)
		{
			return defaultValue;
		}
	}
	
	/**
	 * Returns the argument at the specified index as a {@link OfflinePlayer}
	 * or null if the argument is not a valid {@link OfflinePlayer}
	 *
	 * @param index {@link OfflinePlayer} the index
	 * @return {@link OfflinePlayer} representation of the argument at a specified index
	 */
	protected OfflinePlayer argAsOfflinePlayer(int index)
	{
		return argAsString(index) == null ? null : Bukkit.getOfflinePlayer(argAsString(index));
	}
	
	/**
	 * Returns the argument at the specified index as a {@link OfflinePlayer}
	 * or the default value if the argument is not a valid {@link OfflinePlayer}
	 *
	 * @param index {@link OfflinePlayer} the index
	 * @param defaultValue default value to return
	 * @return {@link OfflinePlayer} representation of the argument at a specified index
	 */
	protected OfflinePlayer argAsOfflinePlayer(int index, @NotNull OfflinePlayer defaultValue)
	{
		try
		{
			return argAsString(index) == null ? defaultValue : Bukkit.getOfflinePlayer(argAsString(index));
		} catch(Exception e)
		{
			return defaultValue;
		}
	}
	
	/**
	 * Returns the argument at the specified index as a {@link Location}
	 * or null if the argument is not a valid {@link Location}
	 *
	 * @param index {@link Location} the index
	 * @return {@link Location} representation of the argument at a specified index
	 */
	protected Location argAsLocation(int index)
	{
		return argAsString(index) == null ? null : LocationUtils.changeStringLocationToLocation(argAsString(index));
	}
	
	/**
	 * Returns the argument at the specified index as a {@link Location}
	 * or the default value if the argument is not a valid {@link Location}
	 *
	 * @param index {@link Location} the index
	 * @param defaultValue default value to return
	 * @return {@link Location} representation of the argument at a specified index
	 */
	protected Location argAsLocation(int index, @NotNull Location defaultValue)
	{
		try
		{
			return argAsString(index) == null ? defaultValue : LocationUtils.changeStringLocationToLocation(argAsString(index));
		} catch(Exception e)
		{
			return defaultValue;
		}
	}
	
	/**
	 * Returns the argument at the specified index as a {@link EntityType}
	 * or null if the argument is not a valid {@link EntityType}
	 *
	 * @param index {@link EntityType} the index
	 * @return {@link EntityType} representation of the argument at a specified index
	 */
	protected EntityType argAsEntityType(int index)
	{
		return argAsString(index) == null ? null : EntityType.valueOf(argAsString(index).toUpperCase());
	}
	
	/**
	 * Returns the argument at the specified index as a {@link EntityType}
	 * or the default value if the argument is not a valid {@link EntityType}
	 *
	 * @param index {@link EntityType} the index
	 * @param defaultValue default value to return
	 * @return {@link EntityType} representation of the argument at a specified index
	 */
	protected EntityType argAsEntityType(int index, @NotNull EntityType defaultValue)
	{
		try
		{
			return argAsString(index) == null ? null : EntityType.valueOf(argAsString(index).toUpperCase());
		} catch(Exception e)
		{
			return defaultValue;
		}
	}
	
	/**
	 * Returns the argument at the specified index as a {@link World}
	 * or null if the argument is not a valid {@link World}
	 *
	 * @param index {@link World} the index
	 * @return {@link World} representation of the argument at a specified index
	 */
	protected World argAsWorld(int index)
	{
		try
		{
			return argAsString(index) == null ? null : Bukkit.getWorld(argAsString(index).toUpperCase());
		} catch(Exception e)
		{
			return null;
		}
	}
	
	/**
	 * Returns the argument at the specified index as a {@link World}
	 * or default value if the argument is not a valid {@link World}
	 *
	 * @param index {@link World} the index
	 * @param defaultValue default value to return
	 * @return {@link World} representation of the argument at a specified index
	 */
	protected World argAsWorld(int index, @NotNull World defaultValue)
	{
		try
		{
			return argAsString(index) == null ? defaultValue : Bukkit.getWorld(argAsString(index).toUpperCase());
		} catch(Exception e)
		{
			return defaultValue;
		}
	}
	
}

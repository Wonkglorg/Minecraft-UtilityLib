package com.wonkglorg.utilitylib.command;

import com.wonkglorg.utilitylib.utils.location.LocationUtils;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.OfflinePlayer;
import org.bukkit.World;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;

@SuppressWarnings("unused")
public abstract class Arguments{

	protected String[] args;
	protected int parentCount = 0;

	/**
	 * 
	 * @param index
	 * @return
	 */
	protected String argAsString(int index) {
		try {
			return this.args[index + this.parentCount];
		} catch (Exception e) {
			return null;
		}
	}

	/**
	 * 
	 * @param index
	 * @param defaultValue
	 * @return
	 */
	protected String argAsString(int index, String defaultValue) {
		try {
			return this.args[index + this.parentCount];
		} catch (Exception e) {
			return defaultValue;
		}
	}


	/**
	 * 
	 * @param index
	 * @return
	 */
	protected boolean argAsBoolean(int index) {
		return Boolean.parseBoolean(argAsString(index));
	}

	/**
	 * 
	 * @param index
	 * @param defaultValue
	 * @return
	 */
	protected boolean argAsBoolean(int index, boolean defaultValue) {
		try {
			return Boolean.parseBoolean(argAsString(index));
		} catch (Exception e) {
			return defaultValue;
		}
	}
	
	/**
	 * 
	 * @param index
	 * @return
	 */
	protected int argAsInteger(int index) {
		return Integer.parseInt(argAsString(index));
	}

	/**
	 * 
	 * @param index
	 * @param defaultValue
	 * @return
	 */
	protected int argAsInteger(int index, int defaultValue) {
		try {
			return Integer.parseInt(argAsString(index));
		} catch (Exception e) {
			return defaultValue;
		}
	}

	/**
	 * 
	 * @param index
	 * @return
	 */
	protected long argAsLong(int index) {
		return Long.parseLong(argAsString(index));
	}

	/**
	 * 
	 * @param index
	 * @param defaultValue
	 * @return
	 */
	protected long argAsLong(int index, long defaultValue) {
		try {
			return Long.parseLong(argAsString(index));
		} catch (Exception e) {
			return defaultValue;
		}
	}

	/**
	 * 
	 * @param index
	 * @param defaultValue
	 * @return
	 */
	protected double argAsDouble(int index, double defaultValue) {
		try {
			return Double.parseDouble(argAsString(index).replace(",", "."));
		} catch (Exception e) {
			return defaultValue;
		}
	}

	/**
	 * 
	 * @param index
	 * @return
	 */
	protected double argAsDouble(int index) {
		return Double.parseDouble(argAsString(index).replace(",", "."));
	}

	/**
	 * 
	 * @param index
	 * @return
	 */
	protected Player argAsPlayer(int index) {
		return Bukkit.getPlayer(argAsString(index));
	}

	/**
	 * 
	 * @param index
	 * @param defaultValue
	 * @return
	 */
	protected Player argAsPlayer(int index, Player defaultValue) {
		try {
			return Bukkit.getPlayer(argAsString(index));
		} catch (Exception e) {
			return defaultValue;
		}
	}

	/**
	 * 
	 * @param index
	 * @return
	 */
	protected OfflinePlayer argAsOfflinePlayer(int index) {
		return Bukkit.getOfflinePlayer(argAsString(index));
	}

	/**
	 * 
	 * @param index
	 * @param defaultValue
	 * @return
	 */
	protected OfflinePlayer argAsOfflinePlayer(int index, OfflinePlayer defaultValue) {
		try {
			return Bukkit.getOfflinePlayer(argAsString(index));
		} catch (Exception e) {
			return defaultValue;
		}
	}

	/**
	 * 
	 * @param index
	 * @return
	 */
	protected Location argAsLocation(int index) {
		return LocationUtils.changeStringLocationToLocationEye(argAsString(index));
	}

	/**
	 * 
	 * @param index
	 * @param defaultValue
	 * @return
	 */
	protected Location argAsLocation(int index, Location defaultValue) {
		try {
			return LocationUtils.changeStringLocationToLocationEye(argAsString(index));
		} catch (Exception e) {
			return defaultValue;
		}
	}

	/**
	 * 
	 * @param index
	 * @return
	 */
	protected EntityType argAsEntityType(int index) {
		return EntityType.valueOf(argAsString(index).toUpperCase());
	}

	/**
	 * 
	 * @param index
	 * @param defaultValue
	 * @return
	 */
	protected EntityType argAsEntityType(int index, EntityType defaultValue) {
		try {
			return EntityType.valueOf(argAsString(index).toUpperCase());
		} catch (Exception e) {
			return defaultValue;
		}
	}

	/**
	 * 
	 * @param index
	 * @return
	 */
	protected World argAsWorld(int index) {
		try {
			return Bukkit.getWorld(argAsString(index));
		} catch (Exception e) {
			return null;
		}
	}

	/**
	 * 
	 * @param index
	 * @return
	 */
	protected World argAsWorld(int index, World world) {
		try {
			return Bukkit.getWorld(argAsString(index));
		} catch (Exception e) {
			return world;
		}
	}

}

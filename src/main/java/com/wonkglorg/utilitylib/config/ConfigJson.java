package com.wonkglorg.utilitylib.config;

import com.wonkglorg.utilitylib.logger.Logger;
import org.bukkit.Color;
import org.bukkit.Location;
import org.bukkit.OfflinePlayer;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class ConfigJson implements Config
{
	/**
	 * The Main.
	 */
	protected final JavaPlugin main;
	/**
	 * The Name.
	 */
	protected String name;
	/**
	 * The Path.
	 */
	protected String path;
	/**
	 * The File.
	 */
	protected final File file;
	protected final JSONParser parser = new JSONParser();
	protected JSONObject data;
	
	/**
	 * Creates a new config object which represents a yml file
	 *
	 * @param main the java plugin
	 * @param name the file name
	 */
	public ConfigJson(JavaPlugin main, String name) throws IOException, ParseException
	{
		this.main = main;
		this.name = name + (name.endsWith(".json") ? "" : ".json");
		this.path = this.name;
		file = new File(main.getDataFolder(), name);
		
		this.data = (JSONObject) parser.parse(new FileReader(file));
		
	}
	
	/**
	 * Creates a new config object which represents a yml file
	 *
	 * @param main the java plugin
	 * @param name the file name
	 * @param paths the path to the file
	 */
	public ConfigJson(JavaPlugin main, String name, String... paths) throws IOException, ParseException
	{
		this.main = main;
		this.name = name + (name.endsWith(".json") ? "" : ".json");
		StringBuilder pathBuilder = new StringBuilder();
		for(String s : paths)
		{
			pathBuilder.append(s).append(File.separator);
		}
		this.path = pathBuilder + this.name;
		file = new File(main.getDataFolder(), this.path);
		
		this.data = (JSONObject) parser.parse(new FileReader(file));
		
	}
	
	public ConfigJson(JavaPlugin main, String name, String path) throws IOException, ParseException
	{
		this.main = main;
		this.name = name + (name.endsWith(".json") ? "" : ".json");
		path = path.startsWith(File.separator) ? path.replaceFirst(File.separator, "") : path;
		this.path = path.endsWith(File.separator) ? path + name : path + File.separator + name;
		file = new File(main.getDataFolder(), this.path);
		
		this.data = (JSONObject) parser.parse(new FileReader(file));
	}
	
	@Override
	public Set<String> getSection(@NotNull String path, boolean deep)
	{
		return null;
	}
	
	@Override
	public @Nullable String getString(@NotNull String path)
	{
		if(data.get(path) instanceof String)
		{
			return (String) data.get(path);
		}
		return null;
	}
	
	@Override
	public int getInt(@NotNull String path)
	{
		//does this work? how to implement this?
		if(data.get(path) instanceof Integer)
		{
			return (int) data.get(path);
		}
		return 0;
	}
	
	@Override
	public double getDouble(@NotNull String path)
	{
		if(data.get(path) instanceof Double)
		{
			return (double) data.get(path);
		}
		return 0;
	}
	
	@Override
	public long getLong(@NotNull String path)
	{
		return 0;
	}
	
	@Override
	public boolean getBoolean(@NotNull String path)
	{
		return false;
	}
	
	@Override
	public List<String> getStringList(@NotNull String path)
	{
		return null;
	}
	
	@Override
	public List<Integer> getIntegerList(@NotNull String path)
	{
		return null;
	}
	
	@Override
	public List<Double> getDoubleList(@NotNull String path)
	{
		return null;
	}
	
	@Override
	public List<Character> getCharacterList(@NotNull String path)
	{
		return null;
	}
	
	@Override
	public List<Long> getLongList(@NotNull String path)
	{
		return null;
	}
	
	@Override
	public List<Boolean> getBooleanList(@NotNull String path)
	{
		return null;
	}
	
	@Override
	public List<Map<?, ?>> getMapList(@NotNull String path)
	{
		return null;
	}
	
	
	@Override
	public <T> T getObject(@NotNull String path, @NotNull Class<T> clazz)
	{
		return null;
	}
	
	@Override
	public Location getLocation(@NotNull String path)
	{
		return null;
	}
	
	@Override
	public ItemStack getItemStack(@NotNull String path)
	{
		return null;
	}
	
	@Override
	public Color getColor(@NotNull String path)
	{
		return null;
	}
	
	@Override
	public OfflinePlayer getOfflinePlayer(@NotNull String path)
	{
		return null;
	}
	
	@Override
	public boolean contains(@NotNull String path)
	{
		return false;
	}
	
	@Override
	public void set(@NotNull String path, Object value)
	{
	
	}
	
	@Override
	public void updateFiles()
	{
	
	}
	
	@Override
	public void load()
	{
		try
		{
			this.data = (JSONObject) parser.parse(new FileReader(file));
			Logger.log("Loaded data from " + name + "!");
		} catch(IOException | ParseException e)
		{
			Logger.logWarn("Error loading data from " + name + "!");
			e.printStackTrace();
		}
	}
	
	@Override
	public void silentLoad()
	{
		try
		{
			this.data = (JSONObject) parser.parse(new FileReader(file));
		} catch(IOException | ParseException e)
		{
			Logger.logWarn("Error loading data from " + name + "!");
			e.printStackTrace();
		}
	}
	
	@Override
	public void save()
	{
		try(FileWriter fileWriter = new FileWriter(file))
		{
			fileWriter.write(data.toJSONString());
			Logger.log("Saved data to " + name + "!");
		} catch(IOException e)
		{
			Logger.logWarn("Error saving data to " + name + "!");
			e.printStackTrace();
		}
	}
	
	@Override
	public void silentSave()
	{
		try(FileWriter fileWriter = new FileWriter(file))
		{
			fileWriter.write(data.toJSONString());
		} catch(IOException e)
		{
			Logger.logWarn("Error saving data to " + name + "!");
			e.printStackTrace();
		}
	}
	
	@Override
	public String name()
	{
		return null;
	}
	
	@Override
	public String path()
	{
		return null;
	}
}
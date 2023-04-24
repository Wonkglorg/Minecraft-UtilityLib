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
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
/**
 * @author Wonkglorg
 */
public final class ConfigJson implements Config
{
	/**
	 * The Main.
	 */
	private final JavaPlugin main;
	/**
	 * The Name.
	 */
	private final String name;
	/**
	 * The Path.
	 */
	private final String path;
	/**
	 * The File.
	 */
	private final File file;
	private final JSONParser parser = new JSONParser();
	private JSONObject data;
	
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
		Object obj = data.get(path);
		if(obj instanceof JSONObject)
		{
			return ((JSONObject) obj).keySet();
		} else
		{
			return null;
		}
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
		Object obj = data.get(path);
		if(obj instanceof Number)
		{
			return ((Number) obj).intValue();
		} else
		{
			return 0;
		}
	}
	
	@Override
	public double getDouble(@NotNull String path)
	{
		Object obj = data.get(path);
		if(obj instanceof Number)
		{
			return ((Number) obj).doubleValue();
		} else
		{
			return 0;
		}
	}
	
	@Override
	public long getLong(@NotNull String path)
	{
		Object obj = data.get(path);
		if(obj instanceof Number)
		{
			return ((Number) obj).longValue();
		} else
		{
			return 0;
		}
	}
	
	@Override
	public boolean getBoolean(@NotNull String path)
	{
		Object obj = data.get(path);
		if(obj instanceof Boolean)
		{
			return (boolean) obj;
		} else
		{
			return false;
		}
	}
	
	@Override
	public List<String> getStringList(@NotNull String path)
	{
		Object obj = data.get(path);
		if(obj instanceof List<?> list)
		{
			List<String> result = new ArrayList<>();
			for(Object element : list)
			{
				if(element instanceof String)
				{
					result.add((String) element);
				}
			}
			return result;
		} else
		{
			return null;
		}
	}
	
	@Override
	public List<Integer> getIntegerList(@NotNull String path)
	{
		Object obj = data.get(path);
		if(obj instanceof List<?> list)
		{
			List<Integer> result = new ArrayList<>();
			for(Object element : list)
			{
				if(element instanceof Number)
				{
					result.add(((Number) element).intValue());
				}
			}
			return result;
		} else
		{
			return null;
		}
	}
	
	@Override
	public List<Double> getDoubleList(@NotNull String path)
	{
		Object obj = data.get(path);
		if(obj instanceof List<?> list)
		{
			List<Double> result = new ArrayList<>();
			for(Object element : list)
			{
				if(element instanceof Number)
				{
					result.add(((Number) element).doubleValue());
				}
			}
			return result;
		} else
		{
			return null;
		}
	}
	
	@Override
	public List<Character> getCharacterList(@NotNull String path)
	{
		Object obj = data.get(path);
		if(obj instanceof List<?> list)
		{
			List<Character> result = new ArrayList<>();
			for(Object element : list)
			{
				if(element instanceof String && ((String) element).length() == 1)
				{
					result.add(((String) element).charAt(0));
				}
			}
			return result;
		} else
		{
			return null;
		}
	}
	
	@Override
	public List<Long> getLongList(@NotNull String path)
	{
		Object obj = data.get(path);
		if(obj instanceof List<?> list)
		{
			List<Long> result = new ArrayList<>();
			for(Object element : list)
			{
				if(element instanceof Number)
				{
					result.add(((Number) element).longValue());
				}
			}
			return result;
		} else
		{
			return null;
		}
	}
	
	@Override
	public List<Boolean> getBooleanList(@NotNull String path)
	{
		Object obj = data.get(path);
		if(obj instanceof List<?> list)
		{
			List<Boolean> result = new ArrayList<>();
			for(Object element : list)
			{
				if(element instanceof Boolean)
				{
					result.add((boolean) element);
				}
			}
			return result;
		}
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
		checkFile();
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
		checkFile();
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
		checkFile();
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
		checkFile();
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
	
	private void checkFile()
	{
		if(!file.exists())
		{
			if(main.getResource(path) != null)
			{
				main.saveResource(path, false);
			} else
			{
				boolean ignored = file.getParentFile().mkdirs();
				try
				{
					ignored = file.getParentFile().mkdir();
					ignored = file.createNewFile();
				} catch(IOException e)
				{
					throw new RuntimeException(e);
				}
			}
			return;
		}
		updateFiles();
		
	}
}
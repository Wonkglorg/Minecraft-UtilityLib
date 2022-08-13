package com.wonkglorg.utilitylib.utils.serializer;

import org.bukkit.inventory.ItemStack;
import org.bukkit.util.io.BukkitObjectInputStream;
import org.bukkit.util.io.BukkitObjectOutputStream;
import org.yaml.snakeyaml.external.biz.base64Coder.Base64Coder;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

/**
 * Serialize Single or multiple Items into Base64
 */
@SuppressWarnings("unused")
public class SerializeItems
{
	/**
	 * Serializes an Array of {@link ItemStack} to Base64
	 *
	 * @param items Array of {@link ItemStack}.
	 * @return Base64 encoded String.
	 * @throws IllegalStateException Items can not be saved to Base64.
	 */
	public static String itemStackArrayToBase64(ItemStack[] items) throws IllegalStateException
	{
		try
		{
			ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
			BukkitObjectOutputStream dataOutput = new BukkitObjectOutputStream(outputStream);
			
			// Write the size of the inventory
			dataOutput.writeInt(items.length);
			
			// Save every element in the list
			for(ItemStack item : items)
			{
				dataOutput.writeObject(item);
			}
			
			// Serialize that array
			dataOutput.close();
			return Base64Coder.encodeLines(outputStream.toByteArray());
		} catch(Exception e)
		{
			throw new IllegalStateException("Unable to save item stacks.", e);
		}
	}
	/**
	 * Serializes an {@link ItemStack} to Base64
	 *
	 * @param item {@link ItemStack}.
	 * @return Base64 encoded String.
	 * @throws IllegalStateException Item can not be saved to Base64.
	 */
	public static String itemToBase64(ItemStack item) throws IllegalStateException
	{
		try
		{
			ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
			BukkitObjectOutputStream dataOutput = new BukkitObjectOutputStream(outputStream);
			
			// Save every element in the list
			dataOutput.writeObject(item);
			
			// Serialize that array
			dataOutput.close();
			return Base64Coder.encodeLines(outputStream.toByteArray());
		} catch(Exception e)
		{
			throw new IllegalStateException("Unable to save item stack.", e);
		}
	}
	
	/**
	 * Returns an Array of {@link ItemStack} from Base64.
	 *
	 * @param data Base64 String.
	 * @return Array of {@link ItemStack}.
	 * @throws IOException String can not be converted from Base64.
	 */
	public static ItemStack[] itemStackArrayFromBase64(String data) throws IOException
	{
		try
		{
			ByteArrayInputStream inputStream = new ByteArrayInputStream(Base64Coder.decodeLines(data));
			BukkitObjectInputStream dataInput = new BukkitObjectInputStream(inputStream);
			ItemStack[] items = new ItemStack[dataInput.readInt()];
			
			// Read the serialized inventory
			for(int i = 0; i < items.length; i++)
			{
				items[i] = (ItemStack) dataInput.readObject();
			}
			
			dataInput.close();
			return items;
		} catch(ClassNotFoundException e)
		{
			throw new IOException("Unable to decode class type.", e);
		}
		
	}
	/**
	 * Returns an {@link ItemStack} from Base64.
	 *
	 * @param data Base64 String.
	 * @return {@link ItemStack}.
	 * @throws IOException String can not be converted from Base64.
	 */
	public static ItemStack itemFromBase64(String data)
	{
		try
		{
			ByteArrayInputStream inputStream = new ByteArrayInputStream(Base64Coder.decodeLines(data));
			BukkitObjectInputStream dataInput = new BukkitObjectInputStream(inputStream);
			ItemStack item;
			
			// Read the serialized itemStack
			item = (ItemStack) dataInput.readObject();
			
			dataInput.close();
			return item;
		} catch(ClassNotFoundException e)
		{
			try
			{
				throw new IOException("Unable to decode class type.", e);
			} catch(IOException ex)
			{
				throw new RuntimeException(ex);
			}
		} catch(IOException e)
		{
			throw new RuntimeException(e);
		}
		
	}
}
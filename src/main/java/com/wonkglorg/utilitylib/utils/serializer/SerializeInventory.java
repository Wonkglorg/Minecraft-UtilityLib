package com.wonkglorg.utilitylib.utils.serializer;

import org.bukkit.inventory.ItemStack;
import org.bukkit.util.io.BukkitObjectInputStream;
import org.bukkit.util.io.BukkitObjectOutputStream;
import org.yaml.snakeyaml.external.biz.base64Coder.Base64Coder;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

@SuppressWarnings("unused")
public class SerializeInventory
{
	
	public static String toBase64(ItemStack[] contents)
	{
		boolean convert = false;
		
		if(contents != null)
		{
			for(ItemStack item : contents)
			{
				if(item != null)
				{
					convert = true;
					break;
				}
			}
		}
		
		if(convert)
		{
			try
			{
				ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
				BukkitObjectOutputStream dataOutput = new BukkitObjectOutputStream(outputStream);
				
				dataOutput.writeInt(contents.length);
				
				for(ItemStack stack : contents)
				{
					dataOutput.writeObject(stack);
				}
				dataOutput.close();
				byte[] byteArr = outputStream.toByteArray();
				return Base64Coder.encodeLines(byteArr);
			} catch(Exception e)
			{
				throw new IllegalStateException("Unable to save item stacks.", e);
			}
		}
		
		return null;
	}
	
	public static ItemStack[] stacksFromBase64(String data)
	{
		if(data == null)
		{
			return new ItemStack[]{};
		}
		
		ByteArrayInputStream inputStream;
		
		try
		{
			inputStream = new ByteArrayInputStream(Base64Coder.decodeLines(data));
		} catch(IllegalArgumentException e)
		{
			return new ItemStack[]{};
		}
		
		BukkitObjectInputStream dataInput = null;
		ItemStack[] stacks = null;
		
		try
		{
			dataInput = new BukkitObjectInputStream(inputStream);
			stacks = new ItemStack[dataInput.readInt()];
		} catch(IOException e1)
		{
			e1.printStackTrace();
		}
		
		if(stacks == null)
		{
			return new ItemStack[]{};
		}
		
		for(int i = 0; i < stacks.length; i++)
		{
			try
			{
				stacks[i] = (ItemStack) dataInput.readObject();
			} catch(IOException | ClassNotFoundException | NullPointerException e)
			{
				try
				{
					dataInput.close();
				} catch(IOException e1)
				{
					e1.printStackTrace();
				}
				return null;
			}
		}
		
		try
		{
			dataInput.close();
		} catch(IOException e1)
		{
			e1.printStackTrace();
		}
		
		return stacks;
	}
	
}
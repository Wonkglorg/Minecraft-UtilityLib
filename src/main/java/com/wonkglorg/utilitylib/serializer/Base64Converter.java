package com.wonkglorg.utilitylib.serializer;

import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.util.io.BukkitObjectInputStream;
import org.bukkit.util.io.BukkitObjectOutputStream;
import org.yaml.snakeyaml.external.biz.base64Coder.Base64Coder;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/**
 * @author Wonkglorg
 */
@SuppressWarnings("unused")
public class Base64Converter{
	
	public static String toBase64(final Object obj) throws IOException {
		
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		ObjectOutputStream oos = new ObjectOutputStream(baos);
		oos.writeObject(obj);
		oos.close();
		return java.util.Base64.getEncoder().encodeToString(baos.toByteArray());
	}
	
	public static Object fromBase64(final String str) throws IOException, ClassNotFoundException {
		byte[] data = java.util.Base64.getDecoder().decode(str);
		ObjectInputStream ois = new ObjectInputStream(new ByteArrayInputStream(data));
		Object obj = ois.readObject();
		ois.close();
		return obj;
	}
	
	/**
	 * Serializes an Array of {@link ItemStack} to Base64
	 *
	 * @param items Array of {@link ItemStack}.
	 * @return Base64 encoded String.
	 * @throws IllegalStateException Items can not be saved to Base64.
	 */
	public static String itemStackArrayToBase64(ItemStack[] items) throws IllegalStateException, IOException {
		
		ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
		BukkitObjectOutputStream dataOutput = new BukkitObjectOutputStream(outputStream);
		
		dataOutput.writeInt(items.length);
		
		for(ItemStack item : items){
			dataOutput.writeObject(item);
		}
		
		dataOutput.close();
		return Base64Coder.encodeLines(outputStream.toByteArray());
	}
	
	/**
	 * Serializes an {@link ItemStack} to Base64
	 *
	 * @param item {@link ItemStack}.
	 * @return Base64 encoded String.
	 * @throws IllegalStateException Item can not be saved to Base64.
	 */
	public static String itemToBase64(ItemStack item) throws IllegalStateException {
		try{
			ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
			BukkitObjectOutputStream dataOutput = new BukkitObjectOutputStream(outputStream);
			
			// Save every element in the list
			dataOutput.writeObject(item);
			
			// Serialize that array
			dataOutput.close();
			return Base64Coder.encodeLines(outputStream.toByteArray());
		} catch(Exception e){
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
	public static ItemStack[] itemStackArrayFromBase64(String data) throws IOException {
		try(ByteArrayInputStream inputStream = new ByteArrayInputStream(Base64Coder.decodeLines(data))){
			BukkitObjectInputStream dataInput = new BukkitObjectInputStream(inputStream);
			ItemStack[] items = new ItemStack[dataInput.readInt()];
			
			// Read the serialized inventory
			for(int i = 0; i < items.length; i++){
				items[i] = (ItemStack) dataInput.readObject();
			}
			
			dataInput.close();
			return items;
		} catch(ClassNotFoundException e){
			throw new IOException("Unable to decode class type.", e);
		}
		
	}
	
	/**
	 * Returns an {@link ItemStack} from Base64.
	 *
	 * @param data Base64 String.
	 * @return {@link ItemStack}.
	 */
	public static ItemStack itemFromBase64(String data) {
		try(ByteArrayInputStream inputStream = new ByteArrayInputStream(Base64Coder.decodeLines(data))){
			BukkitObjectInputStream dataInput = new BukkitObjectInputStream(inputStream);
			ItemStack item;
			
			// Read the serialized itemStack
			item = (ItemStack) dataInput.readObject();
			
			dataInput.close();
			return item;
		} catch(ClassNotFoundException e){
			try{
				throw new IOException("Unable to decode class type.", e);
			} catch(IOException ex){
				throw new RuntimeException(ex);
			}
		} catch(IOException e){
			throw new RuntimeException(e);
		}
		
	}
	
	public static String toBase64(ItemStack[] contents) {
		boolean convert = false;
		
		if(contents == null){
			return null;
		}
		
		for(ItemStack item : contents){
			if(item != null){
				convert = true;
				break;
			}
		}
		
		if(convert){
			try{
				ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
				BukkitObjectOutputStream dataOutput = new BukkitObjectOutputStream(outputStream);
				
				dataOutput.writeInt(contents.length);
				
				for(ItemStack stack : contents){
					dataOutput.writeObject(stack);
				}
				dataOutput.close();
				byte[] byteArr = outputStream.toByteArray();
				return Base64Coder.encodeLines(byteArr);
			} catch(Exception e){
				throw new IllegalStateException("Unable to save item stacks.", e);
			}
		}
		
		return null;
	}
	
	public static ItemStack[] stacksFromBase64(String data) {
		if(data == null){
			return new ItemStack[]{};
		}
		Player player = null;
		ByteArrayInputStream inputStream;
		
		try{
			inputStream = new ByteArrayInputStream(Base64Coder.decodeLines(data));
		} catch(IllegalArgumentException e){
			return new ItemStack[]{};
		}
		
		BukkitObjectInputStream dataInput = null;
		ItemStack[] stacks = null;
		
		try{
			dataInput = new BukkitObjectInputStream(inputStream);
			stacks = new ItemStack[dataInput.readInt()];
		} catch(IOException e1){
			e1.printStackTrace();
		}
		
		if(stacks == null){
			return new ItemStack[]{};
		}
		
		for(int i = 0; i < stacks.length; i++){
			try{
				stacks[i] = (ItemStack) dataInput.readObject();
			} catch(IOException | ClassNotFoundException | NullPointerException e){
				try{
					dataInput.close();
				} catch(IOException e1){
					e1.printStackTrace();
				}
				return null;
			}
		}
		
		try{
			dataInput.close();
		} catch(IOException e1){
			e1.printStackTrace();
		}
		
		return stacks;
	}
	
}
package com.wonkglorg.utilitylib.enums;

import org.bukkit.inventory.ItemStack;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Wonkglorg
 */
public enum MessageEnums
{
	TELEPORT_MOVE("Teleport cancelled, player moved!"),
	TELEPORT_MESSAGE("Teleporting in %second% seconds!"),
	TELEPORT_ERROR("Currently Teleporting"),
	TELEPORT_SUCCESS("Teleported!"),
	
	INVENTORY_NULL("Inventory not found"),
	INVENTORY_OPEN_ERROR("Error while opening inventory"),
	INVENTORY_BUTTON_PREVIOUS("Previous page"),
	INVENTORY_BUTTON_NEXT("Next page"),
	
	TIME_DAY("%02d %day% %02d %hour% %02d %minute% %02d %second%"),
	TIME_HOUR("%02d %hour% %02d minute(s) %02d %second%"),
	TIME_HOUR_SIMPLE("%02d:%02d:%02d"),
	TIME_MINUTE("%02d %minute% %02d %second%"),
	TIME_SECOND("%02d %second%"),
	
	COMMAND_SYNTAXE_ERROR("Execute command error: %syntax%"),
	COMMAND_NO_PERMISSION("You do not have permission to execute this command."),
	COMMAND_NO_CONSOLE("Only players can execute this command"),
	COMMAND_NO_ARG("No Command with this argument"),
	COMMAND_SYNTAXE_HELP("%syntax% %description%"),
	
	;
	
	private List<String> messages;
	private String message;
	private Map<String, Object> titles = new HashMap<>();
	private boolean use = true;
	private MessageType type = MessageType.TCHAT;
	
	private ItemStack itemStack;
	
	/**
	 * @param message
	 */
	MessageEnums(String message)
	{
		this.message = message;
	}
	
	/**
	 * @param title
	 * @param subTitle
	 * @param a
	 * @param b
	 * @param c
	 */
	MessageEnums(String title, String subTitle, int a, int b, int c)
	{
		this.titles.put("title", title);
		this.titles.put("subtitle", subTitle);
		this.titles.put("start", a);
		this.titles.put("time", b);
		this.titles.put("end", c);
		this.titles.put("isUse", true);
		this.type = MessageType.TITLE;
	}
	
	/**
	 * @param message
	 */
	MessageEnums(String... message)
	{
		this.messages = Arrays.asList(message);
	}
	
	/**
	 * @param message
	 */
	MessageEnums(MessageType type, String... message)
	{
		this.messages = Arrays.asList(message);
		this.type = type;
	}
	
	/**
	 * @param message
	 */
	MessageEnums(MessageType type, String message)
	{
		this.message = message;
		this.type = type;
	}
	
	/**
	 * @param message
	 * @param use
	 */
	MessageEnums(String message, boolean use)
	{
		this.message = message;
		this.use = use;
	}
	
	public String getMessage()
	{
		return message;
	}
	
	public String toMsg()
	{
		return message;
	}
	
	public String msg()
	{
		return message;
	}
	
	public boolean isUse()
	{
		return use;
	}
	
	public void setMessage(String message)
	{
		this.message = message;
	}
	
	public List<String> getMessages()
	{
		return messages == null ? Collections.singletonList(message) : messages;
	}
	
	public void setMessages(List<String> messages)
	{
		this.messages = messages;
	}
	
	public boolean isMessage()
	{
		return messages != null && messages.size() > 1;
	}
	
	public String getTitle()
	{
		return (String) titles.get("title");
	}
	
	public Map<String, Object> getTitles()
	{
		return titles;
	}
	
	public void setTitles(Map<String, Object> titles)
	{
		this.titles = titles;
		this.type = MessageType.TITLE;
	}
	
	public String getSubTitle()
	{
		return (String) titles.get("subtitle");
	}
	
	public boolean isTitle()
	{
		return titles.containsKey("title");
	}
	
	public int getStart()
	{
		return ((Number) titles.get("start")).intValue();
	}
	
	public int getEnd()
	{
		return ((Number) titles.get("end")).intValue();
	}
	
	public int getTime()
	{
		return ((Number) titles.get("time")).intValue();
	}
	
	public boolean isUseTitle()
	{
		return (boolean) titles.getOrDefault("isUse", "true");
	}
	
	public String replace(String a, String b)
	{
		return message.replace(a, b);
	}
	
	public ItemStack getItemStack()
	{
		return itemStack;
	}
	
	public void setType(MessageType type)
	{
		this.type = type;
	}
	
	public void setItemStack(ItemStack itemStack)
	{
		this.itemStack = itemStack;
	}
	
}


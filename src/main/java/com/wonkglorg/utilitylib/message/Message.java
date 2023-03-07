package com.wonkglorg.utilitylib.message;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.TextComponent;
import net.kyori.adventure.text.TextComponent.Builder;
import net.kyori.adventure.text.format.TextColor;
import net.kyori.adventure.text.format.TextDecoration;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@SuppressWarnings("unused")
public final class Message
{
	private static final String WITH_DELIMITER = "((?<=%1$s)|(?=%1$s))";
	
	/**
	 * Reformates dates to dd/MM/yyyy HH:mm:ss
	 *
	 * @return reformatted date
	 */
	public static String date(Date date)
	{
		SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss"); //HH:mm:ss
		
		return format.format(date);
	}
	
	public static Component toComponent(@NotNull String text)
	{
		return Component.text().append(Component.text(text)).build();
	}
	
	public static Component toComponent(@NotNull String... text)
	{
		Builder builder = Component.text();
		for(String s : text)
		{
			builder.append(Component.text(s));
		}
		return builder.build();
	}
	
	/**
	 * Allows for & to be converted to color can be used with
	 * default minecraft color codes or hex values
	 * usage: "& #xxxxxx".
	 *
	 * @param text Text to be colored.
	 * @return Color converted text.
	 */
	public static Component color(@NotNull String text)
	{
		String[] texts = text.split(String.format(WITH_DELIMITER, "&"));
		Builder component = Component.text();
		
		return convertToComponent(texts, component).build();
	}
	
	/**
	 * Allows for & to be converted to color can be used with
	 * default minecraft color codes or hex values
	 * usage: "& #xxxxxx".
	 *
	 * @param stringList Array of strings to be colored.
	 * @return Color converted text.
	 */
	public static List<Component> color(@NotNull List<String> stringList)
	{
		List<Component> result = new ArrayList<>();
		for(String s : stringList)
		{
			String[] texts = s.split(String.format(WITH_DELIMITER, "&"));
			Builder component = Component.text();
			result.add(convertToComponent(texts, component).build());
		}
		
		return result;
	}
	
	/**
	 * Strips a text from all its color components and returns
	 * the stripped text
	 *
	 * @param text Text to decoler.
	 * @return Decolored text.
	 */
	public static String decolor(@NotNull String text)
	{
		String[] texts = text.split(String.format(WITH_DELIMITER, "&"));
		return convertToDecolor(texts);
	}
	
	/**
	 * Strips a text array from all its color components and returns
	 * the stripped text array
	 *
	 * @param stringList String list to decoler.
	 * @return Decolored text.
	 */
	public static List<String> decolor(@NotNull List<String> stringList)
	{
		List<String> result = new ArrayList<>();
		for(String s : stringList)
		{
			String[] texts = s.split(String.format(WITH_DELIMITER, "&"));
			result.add(convertToDecolor(texts));
		}
		return result;
	}
	
	private static String convertToDecolor(String[] texts)
	{
		StringBuilder builder = new StringBuilder();
		for(int i = 0; i < texts.length; i++)
		{
			if(!texts[i].equalsIgnoreCase("&"))
			{
				builder.append(texts[i]);
				continue;
			}
			i++;
			if(texts[i].charAt(0) == '#')
			{
				builder.append(texts[i].substring(7));
				continue;
			}
			builder.append(texts[i].substring(1));
		}
		return builder.toString();
	}
	/*
	private static Builder convertToComponent(String[] texts, Builder component)
	{
		Map<TextDecoration, Boolean> decorationMap = new HashMap<>();
		TextColor textColor = null;
		for(int i = 0; i < texts.length; i++)
		{
			if(!texts[i].equalsIgnoreCase("&"))
			{
				component.append(Component.text(texts[i]));
				continue;
			}
			i++;
			if(texts[i].charAt(0) == '#')
			{
				textColor = TextColor.fromHexString(texts[i].substring(0, 7));
				TextComponent inputComponent = Component.text(texts[i].substring(7), textColor);
				Builder outputComponent = Component.text();
				outputComponent.append(inputComponent);
				for(TextDecoration decoration1 : decorationMap.keySet())
				{
					outputComponent.decoration(decoration1, decorationMap.get(decoration1));
				}
				
				component.append(outputComponent.build());
				continue;
			}
			char value = texts[i].charAt(0);
			if(value == 'r')
			{
				decorationMap.put(TextDecoration.ITALIC, false);
				textColor = null;
				decorationMap.replaceAll((d, v) -> false);
			} else
			{
				if(ChatColor.colorCharacters().contains(value))
				{
					textColor = TextColor.fromHexString(ChatColor.charToColor(value));
				}
				if(ChatColor.decorationCharacters().contains(value))
				{
					TextDecoration decoration = ChatColor.charToComponent(value);
					if(decoration != null)
					{
						decorationMap.put(decoration, !decorationMap.getOrDefault(decoration, false));
					}
				}
			}
			
			TextComponent inputComponent;
			
			inputComponent = textColor != null ? Component.text(texts[i].substring(1)).color(textColor) : Component.text(texts[i].substring(1));
			
			Builder outputComponent = Component.text();
			outputComponent.append(inputComponent);
			for(TextDecoration decoration1 : decorationMap.keySet())
			{
				outputComponent.decoration(decoration1, decorationMap.get(decoration1));
			}
			
			component.append(outputComponent.build());
		}
		return component;
	}
	
	 */
	//Test this refactored method
	private static Builder convertToComponent(String[] texts, Builder component) {
		Map<TextDecoration, Boolean> decorationMap = new HashMap<>();
		TextColor textColor = null;
		for(int i = 0; i < texts.length; i++) {
			if(!texts[i].equalsIgnoreCase("&")) {
				component.append(Component.text(texts[i]));
				continue;
			}
			i++;
			if(texts[i].charAt(0) == '#') {
				textColor = TextColor.fromHexString(texts[i].substring(0, 7));
				TextComponent inputComponent = Component.text(texts[i].substring(7), textColor);
				Builder outputComponent = Component.text();
				outputComponent.append(inputComponent);
				for(TextDecoration decoration1 : decorationMap.keySet()) {
					outputComponent.decoration(decoration1, decorationMap.get(decoration1));
				}
				component.append(outputComponent.build());
				continue;
			}
			if (texts[i].length() == 0) {
				continue;
			}
			char value = texts[i].charAt(0);
			if(value == 'r') {
				decorationMap.put(TextDecoration.ITALIC, false);
				textColor = null;
				decorationMap.replaceAll((d, v) -> false);
			} else {
				if(ChatColor.colorCharacters().contains(value)) {
					textColor = TextColor.fromHexString(ChatColor.charToColor(value));
				}
				if(ChatColor.decorationCharacters().contains(value)) {
					TextDecoration decoration = ChatColor.charToComponent(value);
					if(decoration != null) {
						decorationMap.put(decoration, !decorationMap.getOrDefault(decoration, false));
					}
				}
			}
			TextComponent inputComponent;
			inputComponent = textColor != null ? Component.text(texts[i].substring(1)).color(textColor) : Component.text(texts[i].substring(1));
			Builder outputComponent = Component.text();
			outputComponent.append(inputComponent);
			for(TextDecoration decoration1 : decorationMap.keySet()) {
				outputComponent.decoration(decoration1, decorationMap.get(decoration1));
			}
			component.append(outputComponent.build());
		}
		return component;

	}
	
	/**
	 * Sends a text to the player.
	 *
	 * @param player Player to message.
	 * @param text Text to send.
	 */
	public static void msgPlayer(Player player, @NotNull String... text)
	{
		if(player == null){
			//Logger.log(text);
			return;
		}
		for(String string : text)
		{
			player.sendMessage(Message.color(string));
		}
	}
	
}
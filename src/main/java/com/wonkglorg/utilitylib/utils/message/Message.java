package com.wonkglorg.utilitylib.utils.message;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.TextComponent;
import net.kyori.adventure.text.TextComponent.Builder;
import net.kyori.adventure.text.format.TextColor;
import net.kyori.adventure.text.format.TextDecoration;
import net.kyori.adventure.text.serializer.legacy.LegacyComponentSerializer;
import net.kyori.adventure.text.serializer.plain.PlainTextComponentSerializer;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Message
{
	private static final String WITH_DELIMITER = "((?<=%1$s)|(?=%1$s))";
	
	/**
	 * Strips a text from all its color components and returns
	 * the stripped text
	 *
	 * @param text Text to decoler.
	 * @return Decolored text.
	 */
	public static String decolor(@NotNull String text)
	{
		return PlainTextComponentSerializer.plainText().serialize(LegacyComponentSerializer.legacySection().deserialize(text));
	}
	
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
		Map<TextDecoration, Boolean> decorationMap = new HashMap<>();
		String[] texts = text.split(String.format(WITH_DELIMITER, "&"));
		Builder component = Component.text();
		
		return convertToComponent(texts, component).build();
	}
	
	/**
	 * Allows for & to be converted to color can be used with
	 * default minecraft color codes or hex values
	 * usage: "& #xxxxxx".
	 *
	 * @param stringArray Array of strings to be colored.
	 * @return Color converted text.
	 */
	public static List<Component> color(@NotNull List<String> stringArray)
	{
		List<Component> result = new ArrayList<>();
		for(String s : stringArray)
		{
			String[] texts = s.split(String.format(WITH_DELIMITER, "&"));
			Builder component = Component.text();
			result.add(convertToComponent(texts, component).build());
		}
		
		return result;
	}
	
	/**
	 * Logic to convert Text into component
	 * Converts Color and Style to components
	 *
	 * @param texts input text
	 * @param component builder to add to
	 * @return builder
	 */
	private static Builder convertToComponent(String[] texts, Builder component)
	{
		Map<TextDecoration, Boolean> decorationMap = new HashMap<>();
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
				component.append(Component.text(texts[i].substring(7), TextColor.fromHexString(texts[i].substring(0, 7))));
				continue;
			}
			
			TextDecoration decoration = ChatColor.StringToComponent(String.valueOf(texts[i].charAt(0)));
			
			if(decoration == null)
			{
				component.append(Component.text(texts[i].substring(1)));
			}
			
			TextComponent inputComponent = Component.text(texts[i].substring(1));
			TextComponent outputComponent;
			
			outputComponent = inputComponent;
			
			decorationMap.put(decoration, !decorationMap.getOrDefault(decoration, false));
			
			for(TextDecoration decoration1 : decorationMap.keySet())
			{
				outputComponent = inputComponent.decoration(decoration1, decorationMap.get(decoration1));
			}
			component.append(outputComponent);
			
		}
		return component;
	}
	
	/**
	 * Sends a text to the player.
	 *
	 * @param player Player to message.
	 * @param text Text to send.
	 */
	public static void msgPlayer(@NotNull Player player, @NotNull String... text)
	{
		for(String string : text)
		{
			player.sendMessage(Message.color(string));
		}
	}
	
}
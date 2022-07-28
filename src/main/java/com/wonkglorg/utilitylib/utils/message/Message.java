package com.wonkglorg.utilitylib.utils.message;


import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.TextComponent.Builder;
import net.kyori.adventure.text.format.TextColor;
import net.kyori.adventure.text.serializer.legacy.LegacyComponentSerializer;
import net.kyori.adventure.text.serializer.plain.PlainTextComponentSerializer;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

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
	 * Allows for & to be converted to color can be used with
	 * default minecraft color codes or hex values
	 * usage: "& #xxxxxx".
	 *
	 * @param text Text to be colored.
	 * @return Color converted text.
	 */
	public static Component color(@NotNull String text)
	{
		Builder component = Component.text();
		String[] texts = text.split(String.format(WITH_DELIMITER, "&"));
		
		for(int i = 0; i < texts.length; i++)
		{
			if(texts[i].equalsIgnoreCase("&"))
			{
				i++;
				if(texts[i].charAt(0) == '#')
				{
					component.append(Component.text(texts[i].substring(7),TextColor.fromHexString(texts[i].substring(0,7))));
				} else
				{
					component.append(LegacyComponentSerializer.legacy('&').deserialize(texts[i]));
				}
			} else
			{
				component.append(Component.text(texts[i]));
			}
		}
		
		return component.build();
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
			
			for(int i = 0; i < texts.length; i++)
			{
				if(texts[i].equalsIgnoreCase("&"))
				{
					i++;
					if(texts[i].charAt(0) == '#')
					{
						component.append(Component.text(texts[i].substring(7), TextColor.fromHexString(texts[i].substring(0, 7))));
					} else
					{
						component.append(LegacyComponentSerializer.legacy('&').deserialize(texts[i]));
					}
				} else
				{
					component.append(Component.text(texts[i]));
				}
			}
			result.add(component.build());
		}
		
		return result;
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
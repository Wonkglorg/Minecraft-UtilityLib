package com.wonkglorg.utilitylib.utils.message;

import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class Message
{
	
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
			player.sendMessage(Color.color(string));
		}
	}
}
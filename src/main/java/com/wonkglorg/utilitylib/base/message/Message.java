package com.wonkglorg.utilitylib.base.message;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.TextComponent;
import net.kyori.adventure.text.TextComponent.Builder;
import net.kyori.adventure.text.event.ClickEvent;
import net.kyori.adventure.text.format.Style;
import net.kyori.adventure.text.format.TextColor;
import net.kyori.adventure.text.format.TextDecoration;
import net.kyori.adventure.text.serializer.plain.PlainTextComponentSerializer;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Deque;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@SuppressWarnings("unused")
public final class Message{
	private static final String WITH_DELIMITER = "((?<=%1$s)|(?=%1$s))";
	
	/**
	 * Reformats dates to dd/MM/yyyy HH:mm:ss
	 *
	 * @return reformatted date
	 */
	public static String date(Date date) {
		SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss"); //HH:mm:ss
		
		return format.format(date);
	}
	
	public static Component toComponent(@NotNull String text) {
		return Component.text().append(Component.text(text)).build();
	}
	
	public static Component toComponent(@NotNull String... text) {
		Builder builder = Component.text();
		for(String s : text){
			builder.append(Component.text(s));
		}
		return builder.build();
	}
	
	/**
	 * Converts text components from Adventure api to a readable string for the color method.
	 *
	 * @param component
	 * @return
	 */
	public static String convertComponentToString(Component component) {
		StringBuilder result = new StringBuilder();
		Deque<Component> stack = new LinkedList<>();
		stack.push(component);
		
		while(!stack.isEmpty()){
			Component current = stack.pop();
			for(Component child : current.children()){
				Style style = child.style();
				TextColor color = style.color();
				
				if(color != null){
					result.append("&").append(color.asHexString());
				}
				
				if(style.hasDecoration(TextDecoration.BOLD)){
					result.append("&b");
				}
				if(style.hasDecoration(TextDecoration.ITALIC)){
					result.append("&i");
				}
				if(style.hasDecoration(TextDecoration.OBFUSCATED)){
					result.append("&o");
				}
				if(style.hasDecoration(TextDecoration.UNDERLINED)){
					result.append("&u");
				}
				if(style.hasDecoration(TextDecoration.STRIKETHROUGH)){
					result.append("&s");
				}
				
				result.append(PlainTextComponentSerializer.plainText().serialize(child));
				if(!child.children().isEmpty()){
					stack.push(child);
				}
			}
		}
		
		if(result.isEmpty()){
			result.append(PlainTextComponentSerializer.plainText().serialize(component));
		}
		
		return result + ChatColor.Reset;
	}
	
	/**
	 * Allows for & to be converted to color can be used with default minecraft color codes or hex values usage: "& #xxxxxx".
	 *
	 * @param text Text to be colored.
	 * @param allowExtras allows for use of special stylings like clickable links in chat, and the like
	 * @return Color converted text or an empty component if the string is empty or null
	 */
	public static Component color(String text, boolean allowExtras) {
		if(text == null){
			return null;
		}
		
		if(text.isBlank()){
			return Component.empty();
		} else {
			String[] texts = text.split(String.format(WITH_DELIMITER, ChatColor.preset));
			Builder component = Component.text();
			
			return convertToComponent(texts, allowExtras, component).build();
		}
	}
	
	/**
	 * Allows for & to be converted to color can be used with default minecraft color codes or hex values usage: "& #xxxxxx".
	 *
	 * @param text Text to be colored.
	 * @return Color converted text.
	 */
	public static Component color(String text) {
		return color(text, false);
	}
	
	/**
	 * Allows for & to be converted to color can be used with default minecraft color codes or hex values usage: "& #xxxxxx".
	 *
	 * @param stringList Array of strings to be colored.
	 * @return Color converted text.
	 */
	public static List<Component> color(@NotNull List<String> stringList) {
		List<Component> result = new ArrayList<>();
		for(String s : stringList){
			result.add(color(s));
		}
		
		return result;
	}
	
	/**
	 * Strips a text from all its color components and returns the stripped text
	 *
	 * @param text Text to decoler.
	 * @return Decolored text.
	 */
	public static String decolor(@NotNull String text) {
		String[] texts = text.split(String.format(WITH_DELIMITER, ChatColor.preset));
		return convertToDecolor(texts);
	}
	
	/**
	 * Strips a text array from all its color components and returns the stripped text array
	 *
	 * @param stringList String list to decoler.
	 * @return Decolored text.
	 */
	public static List<String> decolor(@NotNull List<String> stringList) {
		List<String> result = new ArrayList<>();
		for(String s : stringList){
			result.add(decolor(s));
		}
		return result;
	}
	
	private static String convertToDecolor(String[] texts) {
		StringBuilder builder = new StringBuilder();
		for(int i = 0; i < texts.length; i++){
			if(!texts[i].equalsIgnoreCase(ChatColor.preset)){
				builder.append(texts[i]);
				continue;
			}
			i++;
			if(texts[i].charAt(0) == '#'){
				builder.append(texts[i].substring(7));
				continue;
			}
			builder.append(texts[i].substring(1));
		}
		return builder.toString();
	}
	private static Builder convertToComponent(String[] texts, boolean allowExtras, Builder component) {

		//this is horribly quick written but I ain't touching it again it works.
		Map<TextDecoration, Boolean> decorationMap = new HashMap<>();
		TextColor textColor = null;
		for(int i = 0; i < texts.length; i += 1){
			if(!texts[i].equalsIgnoreCase(ChatColor.preset)){
				component.append(Component.text(texts[i]));
				continue;
			}
			i++;
			if(texts[i].charAt(0) == '#'){
				textColor = TextColor.fromHexString(texts[i].substring(0, 7));
				TextComponent inputComponent = Component.text(texts[i].substring(7), textColor);
				Builder outputComponent = Component.text();
				outputComponent.append(inputComponent);
				for(TextDecoration decoration1 : decorationMap.keySet()){
					outputComponent.decoration(decoration1, decorationMap.get(decoration1));
				}
				component.append(outputComponent.build());
				continue;
			}
			if(texts[i].length() == 0){
				continue;
			}
			char value = texts[i].charAt(0);
			if(value == 'r'){
				decorationMap.put(TextDecoration.ITALIC, false);
				textColor = null;
				decorationMap.replaceAll((d, v) -> false);
			} else {
				if(ChatColor.colorCharacters().contains(value)){
					textColor = TextColor.fromHexString(ChatColor.charToColor(value));
				}
				if(ChatColor.decorationCharacters().contains(value)){
					TextDecoration decoration = ChatColor.charToComponent(value);
					if(decoration != null){
						decorationMap.put(decoration, !decorationMap.getOrDefault(decoration, false));
					}
				}
			}
			TextComponent inputComponent;
			inputComponent = textColor != null ? Component.text(texts[i].substring(1)).color(textColor) : Component.text(texts[i].substring(1));
			Builder outputComponent = Component.text();
			outputComponent.append(inputComponent);
			for(TextDecoration decoration1 : decorationMap.keySet()){
				outputComponent.decoration(decoration1, decorationMap.get(decoration1));
			}
			component.append(outputComponent.build());

			if(allowExtras){
				if(texts[i].startsWith("@link{")){
					String linkText = texts[i].substring(6, texts[i].length() - 1);
					String[] linkParts = linkText.split(",", 2);
					if(linkParts.length == 2){
						String url = linkParts[0].trim();
						String message = linkParts[1].trim();
						component.append(Message.toComponent(message));
						Builder linkComponent = Component.text(message).clickEvent(ClickEvent.openUrl(url)).toBuilder();
						
						component.append(linkComponent.build());
					}
				}
			}
			
		}
		return component;
		
	}
	
	private final Pattern pattern = Pattern.compile("@link\\{(.*?)\\}");
	
	public Component convertLink(String input) {
		Builder builder = Component.text();
		Matcher matcher = pattern.matcher(input);
		
		int lastMatchEnd = 0;
		while(matcher.find()){
			int matchStart = matcher.start();
			int matchEnd = matcher.end();
			
			String beforeMatch = input.substring(lastMatchEnd, matchStart);
			builder.append(Component.text(beforeMatch));
			
			String match = matcher.group(1);
			String[] parts = match.split(",", 2);
			if(parts.length == 2){
				String url = parts[0].trim();
				String message = parts[1].trim();
				
				builder.append(Component.text(message).clickEvent(ClickEvent.openUrl(url)));
			}
			
			lastMatchEnd = matchEnd;
		}
		
		String restOfMessage = input.substring(lastMatchEnd);
		builder.append(Component.text(restOfMessage));
		
		return builder.build();
	}
	
	/**
	 * Sends a text to the player.
	 *
	 * @param player Player to message.
	 * @param text Text to send.
	 * @throws NullPointerException if the player or string is null
	 */
	public static void msgPlayer(Player player, @NotNull String... text) {
		if(player == null){
			throw new NullPointerException("Input Player cannot be null");
		}
		if(text == null){
			throw new NullPointerException("Input text cannot be null");
		}
		for(String string : text){
			player.sendMessage(Message.color(string));
		}
	}
	
}
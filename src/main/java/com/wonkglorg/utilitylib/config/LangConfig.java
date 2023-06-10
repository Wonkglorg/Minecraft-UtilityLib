package com.wonkglorg.utilitylib.config;

import com.wonkglorg.utilitylib.message.ChatColor;
import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.NotNull;

import java.nio.file.Path;
import java.util.List;

public class LangConfig extends ConfigYML{
	
	private String primaryColor = LangValue.PRIMARY.defaultValue;
	private String secondaryColor = LangValue.SECONDARY.defaultValue;
	private String prefix = LangValue.PREFIX.defaultValue;
	
	public LangConfig(@NotNull JavaPlugin plugin, @NotNull Path sourcePath, @NotNull Path destinationPath) {
		super(plugin, sourcePath, destinationPath);
		addDefaults();
	}
	
	public LangConfig(@NotNull JavaPlugin plugin, @NotNull String name) {
		super(plugin, name);
		addDefaults();
	}
	
	public LangConfig(@NotNull JavaPlugin plugin, @NotNull Path path) {
		super(plugin, path);
		addDefaults();
	}
	
	private void addDefaults() {
		for(LangValue langValue : LangValue.values()){
			if(!this.isSet(langValue.path)){
				this.set(langValue.path, langValue.getDefaultValue());
				this.setComments(langValue.path, langValue.getComment());
			}
		}
	}
	
	public @NotNull String getPrimaryColor() {
		String color = this.getString(LangValue.PRIMARY.path);
		
		return color != null ? color : primaryColor;
	}
	
	public @NotNull String getSecondaryColor() {
		String color = this.getString(LangValue.SECONDARY.path);
		
		return color != null ? color : secondaryColor;
	}
	
	public @NotNull String getPrefix() {
		String color = this.getString(LangValue.PREFIX.path);
		
		return color != null ? color : prefix;
	}
	
	public String getLangValue(LangValue langValue) {
		switch(langValue) {
			case PREFIX -> {
				return prefix;
			}
			case PRIMARY -> {
				return primaryColor;
			}
			case SECONDARY -> {
				return secondaryColor;
			}
		}
		return null;
	}
	
	private enum LangValue{
		PRIMARY("primary-color", ChatColor.GOLD, "This can be used in any lang text under the prefix %pri%"),
		SECONDARY("alt-color", ChatColor.YELLOW, "This can be used in any lang text under the prefix %alt%!"),
		PREFIX("prefix",
				ChatColor.GRAY + "[" + LangValue.PRIMARY + " Plugin " + ChatColor.GRAY + "]" + ChatColor.Reset,
				"This can be used in any lang text under the prefix %prefix%! primary and alternate colors can be used to define the prefix colors"),
		
		;
		private final String path;
		private final String defaultValue;
		private final String comment;
		
		LangValue(String path, String defaultValue, String comment) {
			this.path = path;
			this.defaultValue = defaultValue;
			this.comment = comment;
		}
		
		public List<String> getComment() {
			return List.of(comment);
		}
		
		public String getDefaultValue() {
			return defaultValue;
		}
		
		public String getPath() {
			return path;
		}
	}
	
}
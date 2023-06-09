package com.wonkglorg.utilitylib.config;

import com.wonkglorg.utilitylib.message.ChatColor;
import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.NotNull;

import java.nio.file.Path;

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
		PRIMARY("primary-color", ChatColor.GOLD),
		SECONDARY("secondary-color", ChatColor.YELLOW),
		PREFIX("prefix", ChatColor.GRAY + "[" + LangValue.PRIMARY + " Plugin " + ChatColor.GRAY + "]" + ChatColor.Reset),
		
		;
		private final String path;
		private final String defaultValue;
		
		LangValue(String path, String defaultValue) {
			this.path = path;
			this.defaultValue = defaultValue;
		}
		
		public String getDefaultValue() {
			return defaultValue;
		}
		
		public String getPath() {
			return path;
		}
	}
	
}
package com.wonkglorg.utilitylib.lang;

import com.wonkglorg.utilitylib.config.Config;
import net.kyori.adventure.text.Component;

import java.util.Locale;

/**
 * Implement this for a lang enum.
 */
@SuppressWarnings("unused")
public interface Lang
{
	
	/**
	 * Lang.
	 *
	 * @param language the language
	 */
	void lang(Locale language);
	
	/**
	 * Sets lang file.
	 *
	 * @param config the config
	 */
	void setLangFile(Config config);
	
	/**
	 * To component component.
	 *
	 * @return the component
	 */
	Component toComponent();
	
	/**
	 * Gets message.
	 *
	 * @return the message
	 */
	String getMessage();
	
	/**
	 * Gets path.
	 *
	 * @return the path
	 */
	String getPath();
	
	/**
	 * Gets lang.
	 *
	 * @return the lang
	 */
	Locale getLang();
	
	/**
	 * Gets lang file.
	 *
	 * @return the lang file
	 */
	Config getLangFile();
}
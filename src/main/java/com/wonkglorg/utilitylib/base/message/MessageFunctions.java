package com.wonkglorg.utilitylib.base.message;


import com.wonkglorg.utilitylib.base.message.color_components.Color;

import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Deprecated
public class MessageFunctions{
	
	String patternString = "\\$gradient\\(((?:#[0-9a-fA-F]{6}(?:\\s*,\\s*)?)+)\\)\\{([^}]*)\\}";
	Pattern pattern = Pattern.compile(patternString);
	
	@Deprecated
	public String findGradientSegment(String text) {
		Matcher matcher = pattern.matcher(text);
		
		if(matcher.find()){
			String gradients = matcher.group(1);
			String coloredText = matcher.group(2);
			
			Color[] colors = (Color[]) Arrays.stream(gradients.split(",")).map(Color::new).toArray();
			String outputText = Color.createGradient(coloredText, colors);
			// Process gradients and colored text here as needed
			// ...
			
			return outputText;
		}
		
		return text; // No gradient segment found
	}
	
}
package gameProcesses.themes;

import java.awt.Color;

public class ThemeHandler {

	private static Theme theme = new DarkTheme();
	
	public static Color getBackground() {
		return theme.BACKGROUND;
	}
	public static Color getMiddleground() {
		return theme.MIDDLEGROUND;
	}
	public static Color getForeground() {
		return theme.FOREGROUND;
	}
	public static Color getText() {
		return theme.TEXT;
	}
	public static Color getButton() {
		return theme.BUTTON;
	}

	public static void setTheme(Theme chosenTheme) {
		theme = chosenTheme;
	}
	
	public static Theme getTheme() {
		return theme;
	}
	
}

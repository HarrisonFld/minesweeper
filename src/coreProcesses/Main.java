package coreProcesses;

import javax.swing.SwingUtilities;

import gameProcesses.StartMenu;
import gameProcesses.themes.DarkTheme;
import gameProcesses.themes.ThemeHandler;

public class Main {

	public static void main(String[] args) {
		
		SwingUtilities.invokeLater(new StartMenu());
		
	}

}

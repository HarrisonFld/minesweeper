package coreProcesses;

import javax.swing.SwingUtilities;

import gameProcesses.StartMenu;

public class Main {

	public static void main(String[] args) {
		
		SwingUtilities.invokeLater(new StartMenu());
		
	}

}

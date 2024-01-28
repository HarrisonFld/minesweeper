package coreProcesses;

import javax.swing.SwingUtilities;

import gameProcesses.GenericStartMenu;

public class Main {

	public static void main(String[] args) {
		
		SwingUtilities.invokeLater(new GenericStartMenu());
		
	}

}

package coreProcesses;

import gameProcesses.graphicProcesses.GenericStartMenu;
import utils.PlotGrid;

public class Main {
	
	public static void main(String[] args) {
		
		GenericStartMenu.startGUI();
		
		PlotGrid<Integer> plots = new PlotGrid<Integer>(2 , 2);
		
	}
	
}

package gameProcesses;

import gameProcesses.graphicProcesses.game.GenericGame;
import gameProcesses.graphicProcesses.game.PlotButton;

public final class GameHandler {
	
	//Defines the individual length of row and column
	private static int plotsSqrt = 15;
	private static int seed;
	private static PlotButton[] plotsArray;
	
	public static void startGame() {
		
		GenericGame.startGUI();
		
	}
	
	public static int getSurroundingMines(int i) {
		
		int minesAmount = 0;
		
		return minesAmount;
		
	}
	
	/*
	 * This eventually needs to be based off the seed
	 * 
	 * Make there is less mines than there are non-mines
	 */
	public static boolean getRandomBoolean() {
		
		if (Math.random() > 0.7) {
			return true;
		} else {
			return false;
		}
		
	}
	
	public static int getPlotsSqrt() {
		return plotsSqrt;
	}
	
	public static void setPlotsArray(PlotButton[] plots) {
		
		plotsArray = plots;
		
	}
	
	public static PlotButton getPlot(int i) {
		
		return plotsArray[i];
		
	}
	
	public static void setPlotsSqrt(int plotsLength) {
		plotsSqrt = plotsLength;
	}
	
	private GameHandler() {
		
	}
	
}

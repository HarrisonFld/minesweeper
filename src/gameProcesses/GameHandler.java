package gameProcesses;

import java.util.ArrayList;

import javax.swing.SwingUtilities;

import gameProcesses.game.GenericGame;
import gameProcesses.game.PlotButton;
import utils.SpatialGrid;

public class GameHandler {
	
	//Defines the individual length of row and column
	private static int plotsSqrt;
	private static int seed;
	private static SpatialGrid<PlotButton> plots;
	private static ArrayList<Integer> mines = new ArrayList<Integer>();
	private static int squareCounter = 0;
	private static boolean gameOver = false;
	
	private static GenericGame game;
	
	public static void startGame() {
		
		GameHandler.setPlotsSqrt(15);
		SwingUtilities.invokeLater(game = new GenericGame());
		
	}
	
	public static boolean getGameState() {
		return gameOver;
	}
	
	public static void endGame() {
		
		gameOver = true;
		//game.endGame();
		
	}
	
	public static int getSurroundingMines(int i) {
		
		int mines = 0;
		
		for (Object plot : plots.relativeSurroundings(i)) {
			if (plot != null && ((PlotButton) plot).isMine()) {
				mines++;
			}
		}
		
		return mines;
		
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
	
	//When a zero is clicked check if there are any other zeros around
	public static void checkForZeros(int i) {
		
		
		
	}
	
	public static int getPlotsSqrt() {
		return plotsSqrt;
	}
	
	public static void setPlots(SpatialGrid<PlotButton> p) {
		
		plots = p;
		
	}
	
	public static SpatialGrid<PlotButton> getPlots() {
		
		return plots;
		
	}
	
	public static void setPlotsSqrt(int plotsLength) {
		
		if (plotsLength > 30) {
			plotsLength = 30;
		} else if (plotsLength < 4) {
			plotsLength = 4;
		}
		
		plotsSqrt = plotsLength;
	}
	
	public static void updateCounter() {
		
		squareCounter++;
		
		if (squareCounter == (plots.getFillAmount() - mines.size())) {
			System.out.println("Game Won");
			endGame();
		}
		
	}
	
	public static void addToMinesCounter(int i) {
		mines.add(i);
	}
	
	private GameHandler() {
		
	}
	
}

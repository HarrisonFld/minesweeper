package gameProcesses;

import gameProcesses.game.GenericGame;
import gameProcesses.game.PlotButton;
import utils.SpatialGrid;

public final class GameHandler {
	
	//Defines the individual length of row and column
	private static int plotsSqrt;
	private static int seed;
	private static SpatialGrid<PlotButton> plots;
	
	public static void startGame() {
		
		GameHandler.setPlotsSqrt(15);
		GenericGame.startGUI();
		setStartingSquares();
		
		
	}
	
	public static void endGame() {
		
		GenericGame.endGame();
		
	}
	
	private static void setStartingSquares() {
		
		int height =  (int) (Math.random() * plotsSqrt);
		int width = (int) (Math.random() * plotsSqrt);
		PlotButton plot = plots.get(height, width);
		
		System.out.println(height + " " + width);
		
		while (plot.isMine()) {
			
			height =  (int) (Math.random() * plotsSqrt);
			width = (int) (Math.random() * plotsSqrt);
			
			plot = plots.get(height, width);
			
		}
		
		PlotButton[] nonMinePlots = new PlotButton[8];
		
//		for (int i = 0; i < 8; i++) {
//			
//			plots.getElementRelativeTo(SpatialGrid.TOP ,plot.getIndex())
//			
//			for (int j = 0; j < 3; j++) {
//				
//				
//				
//			}
//			
//		}
		
		plot.onLeftLicked();
		
		
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
	
	public static int getPlotsSqrt() {
		return plotsSqrt;
	}
	
	public static void setPlots(SpatialGrid<PlotButton> p) {
		
		plots = p;
		
	}
	
	public static PlotButton getPlot(int i) {
		
		return plots.get(i);
		
	}
	
	public static void setPlotsSqrt(int plotsLength) {
		
		if (plotsLength > 30) {
			plotsLength = 30;
		} else if (plotsLength < 4) {
			plotsLength = 4;
		}
		
		plotsSqrt = plotsLength;
	}
	
	private GameHandler() {
		
	}
	
}

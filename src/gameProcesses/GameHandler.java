package gameProcesses;

import gameProcesses.game.Game;
import gameProcesses.game.PlotButton;
import gameProcesses.themes.Theme;
import gameProcesses.themes.ThemeHandler;
import utils.SpatialGrid;

public class GameHandler {

	//Defines the individual length of row and column
	private static Game game;

	public static void startGame(int plotsSqrt) {
		
		//Necessary to make sure multiple game instances don't appear
		if (game != null) {
			game.restartGame(plotsSqrt);
		} else {
			game = new Game(plotsSqrt);
		}
		
		game.setStartingSquares();
		
		
	}
	
	//TEMP
	public static void endGame() {
		game.endGame();
	}

	/*
	 * This eventually needs to be based off the seed
	 *
	 * Make there is less mines than there are non-mines
	 */
	public static boolean getRandomBoolean(int plotsSqrt) {

		if (Math.random() > 0.7) {
			return true;
		} else {
			return false;
		}

	}
	
	public static void changeTheme(Theme theme) {
		ThemeHandler.setTheme(theme);
		game.updateGraphics();
	}
	
	//Bunch of encapsulations that might want to be looked over and changed.
	
	public static SpatialGrid<PlotButton> getPlots() {
		return game.getPlots();
	}

	public static boolean getGameOverState() {
		return game.getGameOverState();
	}

	public static int getSurroundingMines(int index) {
		return game.getSurroundingMines(index);
	}
	
	public static void updateCounter() {
		game.checkWin();
	}
	
	public static void checkForZeros(int i) {
		game.checkForZeros(i);
	}

}

package gameProcesses.game;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Frame;
import java.awt.GridLayout;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SpringLayout;
import javax.swing.WindowConstants;

import gameProcesses.GameHandler;
import gameProcesses.GUIRunnableInterface;
import gameProcesses.themes.ThemeHandler;
import utils.SpatialGrid;
import utils.SpatialGrid.Directions;

public class Game implements GUIRunnableInterface, Runnable {

	JFrame frame;
	JPanel focusAreaContainer;
	JPanel playAreaContainer;
	GridLayout grid;
	JPanel GameInfoPanel;
	
	private int plotsSqrt;
	private int seed;
	private SpatialGrid<PlotButton> plots;
	private ArrayList<Integer> mines = new ArrayList<>(); //Where the mines are located
	private int dugSquaresCounter = 0; //How many non-mine squares have ben clicked
	private boolean gameOver = false;
	
	public Game(int plotsSqrt) {

		setPlotsSqrt(plotsSqrt);
		
		grid = new GridLayout(this.plotsSqrt, this.plotsSqrt, 4, 4);
		
		startGUI();

	}
	
	

	@Override
	public void startGUI() {

		frame = new JFrame();
		frame.setUndecorated(true);
		frame.setExtendedState(Frame.MAXIMIZED_BOTH);
		frame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		frame.setLayout(new BorderLayout());
		frame.setBackground(ThemeHandler.getBackground());
		frame.getRootPane().setBorder(BorderFactory.createEmptyBorder(50, 50, 50, 50));

		//Add To Frame
		addPanels();
		addPlotButtons();

	}
	
	@Override
	public void stopGUI() {
		frame.dispose();
	}
	
	public void restartGame() {
		
		seed = 0;
		plots = null;
		mines.clear();
		dugSquaresCounter = 0;
		gameOver = false;
		plotsSqrt =  4;
		
		resetPlayAreaContainers();
		
	}
	
	private void resetPlayAreaContainers() {
		
		playAreaContainer.removeAll();
		
		grid = new GridLayout(plotsSqrt, plotsSqrt, 4, 4);
		playAreaContainer.setLayout(grid);
		
		addPlotButtons();
		
		playAreaContainer.repaint();
		
	}

	private void addPanels() {

		focusAreaContainer = new JPanel();
		focusAreaContainer.setBackground(ThemeHandler.getBackground());

		SpringLayout layout = new SpringLayout();

		focusAreaContainer.setLayout(layout);
		focusAreaContainer.setOpaque(true);

		playAreaContainer = new JPanel();
		playAreaContainer.setBackground(Color.black);
		playAreaContainer.setPreferredSize(new Dimension(700, 700));
		playAreaContainer.setFocusable(true);
		playAreaContainer.setLayout(grid);
		playAreaContainer.setOpaque(true);

		GameInfoPanel = new GameInfoPanel();

		//Move both to vertical center
		layout.putConstraint(SpringLayout.VERTICAL_CENTER, playAreaContainer, 0, SpringLayout.VERTICAL_CENTER, focusAreaContainer);
		layout.putConstraint(SpringLayout.VERTICAL_CENTER, GameInfoPanel, 0, SpringLayout.VERTICAL_CENTER, focusAreaContainer);

		//Position playAreaContainer to the horizontal center
		layout.putConstraint(SpringLayout.HORIZONTAL_CENTER, playAreaContainer, 0, SpringLayout.HORIZONTAL_CENTER, focusAreaContainer);

		//Position GameInfoPanel to the right of playAreaContainer
		layout.putConstraint(SpringLayout.WEST, GameInfoPanel, 75, SpringLayout.EAST, playAreaContainer);

		focusAreaContainer.add(playAreaContainer);
		focusAreaContainer.add(GameInfoPanel);

		//Add To Parent
		frame.add(focusAreaContainer, BorderLayout.CENTER);
		
	}
	
	

	private void addPlotButtons() {

		plots = new SpatialGrid<>(plotsSqrt, plotsSqrt);

		for (int i = 0; i < plots.getArea(); i++) {

			PlotButton plot = new PlotButton(GameHandler.getRandomBoolean(), i);

			plots.add(plot);
			playAreaContainer.add(plots.get(i));

			if (plot.isMine()) {
				mines.add(plot.getIndex());
			}
			
		}
		
	}
	
	//TEMP
	public void endGame() {

		gameOver = true;
		
//		for (Component plot : playAreaContainer.getComponents()) {
//			((PlotButton) plot).setEnabled(false);
//		}
//		
	}

	/*
	 * Needs to be changed to work with the seed
	 */
	public void setStartingSquares() {

		int height =  (int) (Math.random() * plotsSqrt);
		int width = (int) (Math.random() * plotsSqrt);
		PlotButton initialPlot = plots.get(height, width);

		if (initialPlot.isMine()) {
			setStartingSquares();
			return;
		}

		ArrayList<PlotButton> nonMinePlots = new ArrayList<>();

		nonMinePlots.add(initialPlot);

		for (int i = 0; i < 8; i++) {

			PlotButton plot = plots.getRelativeTo(Directions.returnRandomDirection(), initialPlot.getIndex());
			nonMinePlots.add(plot);

			finder:
			for (int j = 0; j < 5; j++) {

				if (plot == null || plot.isMine()) {
					break finder;
				}

				plot = plots.getRelativeTo(Directions.returnRandomDirection(), plot.getIndex());
				nonMinePlots.add(plot);

				for (int k = 0; k < 2; k++) {

					if (plot == null || plot.isMine()) {
						break finder;
					}

					plot = plots.getRelativeTo(Directions.returnRandomDirection(), plot.getIndex());
					nonMinePlots.add(plot);

				}


			}

			frame.setVisible(true);

		}

		for (PlotButton plotCurrent : nonMinePlots) {

			if (plotCurrent != null && !plotCurrent.isMine()) {
				plotCurrent.onLeftClicked();
			}

		}

		//Change the first plot to be black
		initialPlot.setBackground(Color.black);


	}

	public boolean getGameOverState() {
		return gameOver;
	}
	
	public int getSurroundingMines(int i) {

		int mines = 0;

		for (Object plot : plots.relativeSurroundings(i)) {
			if (plot != null && ((PlotButton) plot).isMine()) {
				mines++;
			}
		}

		return mines;

	}
	 
	//When a zero is clicked check if there are any other zeros around
	private void checkForZeros(int i) {



	}

	public int getPlotsSqrt() {
		return plotsSqrt;
	}
	
	public SpatialGrid<PlotButton> getPlots() {

		return plots;

	}
	
	private void setPlotsSqrt(int plotsLength) {

		if (plotsLength > 30) {
			plotsLength = 30;
		} else if (plotsLength < 4) {
			plotsLength = 4;
		}

		plotsSqrt = plotsLength;
	}
	
	public void updateCounter() {
		
		dugSquaresCounter++;
		
		if (dugSquaresCounter == (plots.getArea() - mines.size())) {
			System.out.println("Game Won");
			endGame();
		}
		
	}
	
}

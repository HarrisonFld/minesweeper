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
import javax.swing.JLayeredPane;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SpringLayout;
import javax.swing.WindowConstants;

import gameProcesses.GUIRunnableInterface;
import gameProcesses.GameHandler;
import gameProcesses.Menu;
import gameProcesses.themes.ThemeHandler;
import utils.SpatialGrid;
import utils.SpatialGrid.Directions;

public class Game implements GUIRunnableInterface {

	private JFrame frame;
	private JLayeredPane focusAreaContainer; //Contains All Game Elements
	private JPanel playAreaContainer; //Contains All Gameplay Elements
	private GameInfoPanel gameInfoPanel; //Game Utilities
	private Menu menu; //The Game Menu
	
	private GridLayout grid;

	private int plotsSqrt;
	// int seed;
	private SpatialGrid<PlotButton> plots;
	private ArrayList<Integer> mines = new ArrayList<>(); //Where the mines are located
	private int dugSquaresCounter = 0; //How many non-mine squares have ben clicked
	private boolean gameOver = false;
	
	public Game(int plotsSqrt) {

		setPlotsSqrt(plotsSqrt);
		
		int borderWidth = Math.round(this.plotsSqrt / 5);
		grid = new GridLayout(this.plotsSqrt, this.plotsSqrt, borderWidth, borderWidth);
		
		startGUI();

	}
	
	@Override
	public void startGUI() {
		
		//Insantiate all members of the GUI
		frame = new JFrame();
		focusAreaContainer = new JLayeredPane();
		menu = new Menu();
		gameInfoPanel = new GameInfoPanel();
		playAreaContainer = new JPanel();
		
		frame.setUndecorated(true);
		frame.setExtendedState(Frame.MAXIMIZED_BOTH);
		frame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		frame.setLayout(new BorderLayout());

		//Add To Frame
		addPanels();
		addPlotButtons();
		
		updateColors();
		
	}
	
	@Override
	public void stopGUI() {
		frame.dispose();
	}
	
	public void restartGame(int plotsSqrt) {
		
		plots = null;
		mines.clear();
		dugSquaresCounter = 0;
		setPlotsSqrt(plotsSqrt);
		gameOver = false;
		
		resetPlayAreaContainers();
		
	}
	
	private void resetPlayAreaContainers() {
		
		playAreaContainer.removeAll();
		
		grid.setColumns(plotsSqrt);
		grid.setRows(plotsSqrt);
		
		addPlotButtons();
		
		playAreaContainer.repaint();
		
	}

	private void addPanels() {
		
		SpringLayout layout = new SpringLayout();

		focusAreaContainer.setBorder(BorderFactory.createEmptyBorder(50, 50, 50, 50));
		focusAreaContainer.setPreferredSize(focusAreaContainer.getMaximumSize());
		focusAreaContainer.setLayout(layout);
		focusAreaContainer.setOpaque(true);
		
		playAreaContainer.setPreferredSize(new Dimension(700, 700));
		playAreaContainer.setFocusable(true);
		playAreaContainer.setLayout(grid);
		playAreaContainer.setOpaque(true);

		//Move both to vertical center
		layout.putConstraint(SpringLayout.VERTICAL_CENTER, playAreaContainer, 0, SpringLayout.VERTICAL_CENTER, focusAreaContainer);
		layout.putConstraint(SpringLayout.VERTICAL_CENTER, gameInfoPanel, 0, SpringLayout.VERTICAL_CENTER, focusAreaContainer);

		//Position playAreaContainer to the horizontal center
		layout.putConstraint(SpringLayout.HORIZONTAL_CENTER, playAreaContainer, 0, SpringLayout.HORIZONTAL_CENTER, focusAreaContainer);

		//Position GameInfoPanel to the right of playAreaContainer
		layout.putConstraint(SpringLayout.WEST, gameInfoPanel, 75, SpringLayout.EAST, playAreaContainer);
		
		//Position the Menu to the center of focusAreaContainer.
		layout.putConstraint(SpringLayout.HORIZONTAL_CENTER, menu, 0, SpringLayout.HORIZONTAL_CENTER, focusAreaContainer);
		layout.putConstraint(SpringLayout.VERTICAL_CENTER, menu, 0, SpringLayout.VERTICAL_CENTER, focusAreaContainer);
		
		focusAreaContainer.add(playAreaContainer, JLayeredPane.DEFAULT_LAYER);
		focusAreaContainer.add(gameInfoPanel, JLayeredPane.DRAG_LAYER);
		focusAreaContainer.add(menu, JLayeredPane.MODAL_LAYER);
		
		//Add To Parent
		frame.add(focusAreaContainer);
		
	}
	
	

	private void addPlotButtons() {

		plots = new SpatialGrid<>(plotsSqrt, plotsSqrt);

		for (int i = 0; i < plots.getArea(); i++) {

			PlotButton plot = new PlotButton(GameHandler.getRandomBoolean(plotsSqrt), i);

			plots.add(plot);
			playAreaContainer.add(plots.get(i));

			if (plot.isMine()) {
				mines.add(plot.getIndex());
			}
			
		}
		
	}
	
	//TEMP
	public void endGame(boolean gameWon) {
		gameOver = true;
		
		if (gameWon) {
			JOptionPane.showMessageDialog(frame, "Game Won");
		} else {
			JOptionPane.showMessageDialog(frame, "Game Over");
		}
		
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

		for (Object plot : plots.getRelativeSurroundings(i)) {
			if (plot != null && ((PlotButton) plot).isMine()) {
				mines++;
			}
		}

		return mines;

	}
	 
	static int x = 0;
	
	//When a zero is clicked check if there are any other zeros around
	public void checkForZeros(int i) {
		
		//PlotButton plot;
		
		//Get all plots around it
		//	If plot in null or plot isn't enabled continue
		//call the plot's left clicked.
		
		
		for (Object plot : plots.getRelativeSurroundings(i)) {
				
			if (plot == null || ((Component) plot).isEnabled() == false) {
				continue;
			}
			
			((Component) plot).setBackground(Color.blue);
			
			((PlotButton) plot).onLeftClicked();
			
		}


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
	
	public void checkWin() {
		
		dugSquaresCounter++;
		
		if (dugSquaresCounter == (plots.getArea() - mines.size())) {
			endGame(true);
		}
		
	}
	
	public void updateGraphics() {
		updateColors();
		frame.repaint();
	}
	
	private void updateColors() {
		
		focusAreaContainer.setBackground(ThemeHandler.getBackground());
		frame.setBackground(ThemeHandler.getBackground());
		playAreaContainer.setBackground(Color.black);
		menu.setBackground(ThemeHandler.getBackground());
		gameInfoPanel.setBackground(ThemeHandler.getForeground());
		
	}
	
	public void toggleMenuVisible() {
		menu.setVisible(!menu.isVisible());
	}
	
}

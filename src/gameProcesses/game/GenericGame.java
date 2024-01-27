package gameProcesses.game;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JPanel;

import gameProcesses.GameHandler;
import gameProcesses.Generic;
import gameProcesses.themes.ThemeHandler;
import utils.SpatialGrid;
import utils.SpatialGrid.Directions;

public class GenericGame implements Generic {
	
	JFrame frame;
	JPanel focusAreaContainer;
	JPanel playAreaContainer;
	GridLayout grid = new GridLayout(GameHandler.getPlotsSqrt(), GameHandler.getPlotsSqrt(), 4, 4);
	
	@Override
	public void startGUI() {
		
		frame = new JFrame();
		frame.setUndecorated(true);
		frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.setLayout(new BorderLayout());
		frame.setBackground(ThemeHandler.getBackground());
		frame.getRootPane().setBorder(BorderFactory.createEmptyBorder(50, 50, 50, 50));
		
		//Add To Frame
		addPanels();
		addPlotButtons();
		
		frame.setVisible(true);
		
	}
	
	
	
	private void addPanels() {
		
		focusAreaContainer = new JPanel();
		focusAreaContainer.setBackground(ThemeHandler.getMiddleground());
		
		focusAreaContainer.setLayout(new GridBagLayout());
		focusAreaContainer.setOpaque(true);
		
		playAreaContainer = new JPanel();
		playAreaContainer.setBackground(Color.black);
		playAreaContainer.setPreferredSize(new Dimension(700, 700));
		playAreaContainer.setLayout(grid);
		playAreaContainer.setOpaque(true);
		
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.gridwidth = 3;
		gbc.gridheight = 1;
		
		focusAreaContainer.add(playAreaContainer, gbc);
		
		//Add To Parent
		frame.add(focusAreaContainer, BorderLayout.CENTER);
		
	}
	
	private void addPlotButtons() {
		
		SpatialGrid<PlotButton> plots = new SpatialGrid<PlotButton>(GameHandler.getPlotsSqrt(), GameHandler.getPlotsSqrt());
		
		for (int i = 0; i < plots.getArea(); i++) {
			
			PlotButton plot = new PlotButton(GameHandler.getRandomBoolean(), i);
			
			plots.add(plot);
			playAreaContainer.add(plots.get(i));
			
			if (plot.isMine()) {
				GameHandler.addToMinesCounter(plot.getIndex());
			}
			
		}
		
		GameHandler.setPlots(plots);

	}
	
	public void endGame() {
		
		for (Component plot : playAreaContainer.getComponents()) {
			((PlotButton) plot).setEnabled(false);
		}
		
	}
	
	/*
	 * Needs to be changed to work with the seed
	 */
	public void setStartingSquares() {
		
		SpatialGrid<PlotButton> plots = GameHandler.getPlots();
		
		int height =  (int) (Math.random() * GameHandler.getPlotsSqrt());
		int width = (int) (Math.random() * GameHandler.getPlotsSqrt());
		PlotButton initialPlot = plots.get(height, width);
		
		if (initialPlot.isMine()) {
			setStartingSquares();
			return;
		}
		
		ArrayList<PlotButton> nonMinePlots = new ArrayList<PlotButton>();
		
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
			
		}
		
		for (PlotButton plotCurrent : nonMinePlots) {
			
			if (plotCurrent != null && !plotCurrent.isMine()) {
				plotCurrent.onLeftClicked();
			}
			
		}
		
		initialPlot.setBackground(Color.black);
		
		
	}


	@Override
	public void stopGUI() {
		frame.dispose();
	}
	
}

package gameProcesses.game;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JPanel;

import gameProcesses.GameHandler;
import gameProcesses.Generic;
import utils.SpatialGrid;

public class GenericGame extends Generic {
	
	static JPanel focusAreaContainer;
	static JPanel playAreaContainer;
	static GridLayout grid = new GridLayout(GameHandler.getPlotsSqrt(), GameHandler.getPlotsSqrt(), 4, 4);
	
	public static void startGUI() {
		
		frame = new JFrame();
		//frame.setSize(new Dimension (1000, 1000));
		frame.setUndecorated(true);
		frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.setLayout(new BorderLayout());
		frame.getRootPane().setBorder(BorderFactory.createEmptyBorder(50, 50, 50, 50));
		
		//Add To Frame
		addPanels();
		addPlotButtons();
		
		frame.setVisible(true);
		
	}
	
	public static void endGame() {
		
		for (Component plot : playAreaContainer.getComponents()) {
			((PlotButton) plot).setEnabled(false);
		}
		
	}
	
	private static void addPanels() {
		
		focusAreaContainer = new JPanel();
		focusAreaContainer.setBackground(Color.blue);
		
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
	
	private static void addPlotButtons() {
		
		SpatialGrid<PlotButton> plots = new SpatialGrid<PlotButton>(GameHandler.getPlotsSqrt(), GameHandler.getPlotsSqrt());
		
		
		for (int i = 0; i < plots.getArea(); i++) {
			
			plots.add(new PlotButton(GameHandler.getRandomBoolean(), i));
			playAreaContainer.add(plots.get(i));
			
		}
		
		GameHandler.setPlots(plots);

	}

}

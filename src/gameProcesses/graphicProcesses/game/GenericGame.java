package gameProcesses.graphicProcesses.game;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JPanel;

import gameProcesses.GameHandler;
import gameProcesses.graphicProcesses.Generic;

public class GenericGame extends Generic {
	
	static JPanel focusAreaContainer;
	static JPanel playAreaContainer;
	static GridLayout grid = new GridLayout(GameHandler.getPlotsSqrt(), GameHandler.getPlotsSqrt(), 4, 4);

	public static void startGUI() {
		
		frame = new JFrame();
		frame.setUndecorated(true);
		frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
		frame.setLayout(new BorderLayout());
		frame.getRootPane().setBorder(BorderFactory.createEmptyBorder(50, 50, 50, 50));
		
		//Add To Frame
		addPanels();
		addPlotButtons();
		
		frame.setVisible(true);
		
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
		
		PlotButton[] plots = new PlotButton[GameHandler.getPlotsSqrt() * GameHandler.getPlotsSqrt()];
		
		for (int i = 0; i < plots.length; i++) {
			
			plots[i] = new PlotButton(GameHandler.getRandomBoolean(), i);
			playAreaContainer.add(plots[i]);
			
		}
		
		GameHandler.setPlotsArray(plots);
		
	}

}

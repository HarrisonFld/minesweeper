package gameProcesses.graphicProcesses;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import gameProcesses.GameHandler;

public class GenericStartMenu extends Generic implements ActionListener  {
	
	static JPanel containerPanel;
	static JPanel buttonPanel;
	static JLabel title;
	static JButton start;
	static JButton exit;
	private static GenericStartMenu thisClass = new GenericStartMenu();
	
	private GenericStartMenu() {
	}
	
	//Start the GUI and open the start menu
	public static void startGUI() {
		
		frame = new JFrame();
		frame.setSize(new Dimension(500, 300));
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setUndecorated(true);
		frame.setLayout(new BoxLayout(frame.getContentPane(), BoxLayout.Y_AXIS));
		frame.getRootPane().setBorder(BorderFactory.createEmptyBorder(2, 2, 2, 2));
		frame.getContentPane().setBackground(Color.GREEN);
		frame.setLocationRelativeTo(null);
		
		//Add Components
		frame.add(Box.createVerticalStrut(25));
		addPanels();
		frame.add(Box.createVerticalStrut(15));
		
		frame.setVisible(true);
		
	}
	
	private static void addPanels() {
		
		//Title
		title = new JLabel("MineSweeper", SwingConstants.CENTER);
		title.setBackground(Color.red);
		title.setVerticalTextPosition(Font.CENTER_BASELINE);
		title.setFont(new Font(null, Font.PLAIN, 60));
		title.setAlignmentX(JLabel.CENTER_ALIGNMENT);
		title.setForeground(Color.white);
		title.setOpaque(true);
		
		//Bottom Buttons Panels
		containerPanel = new JPanel();
		containerPanel.setBackground(Color.BLACK);
		containerPanel.setLayout(new BorderLayout(10, 10));
		containerPanel.setBorder(BorderFactory.createEmptyBorder(25, 25, 25, 25));
		containerPanel.setOpaque(true);

		buttonPanel = new JPanel();
		buttonPanel.setBackground(Color.blue);
		buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 0));
		buttonPanel.setOpaque(true);
		
		addButtons();
		
		containerPanel.add(buttonPanel, BorderLayout.CENTER);
		
		//Add To Parent
		frame.add(title);
		frame.add(Box.createVerticalStrut(25));
		frame.add(containerPanel);

	}
	
	private static void addButtons() {
		
		Dimension buttonSize = new Dimension(200, 100);
		
		start = new JButton("Start");
		start.addActionListener(thisClass);
		start.setFocusable(false);
		start.setPreferredSize(buttonSize);
		
		exit = new JButton("Quit");
		exit.addActionListener(thisClass);
		exit.setFocusable(false);
		exit.setPreferredSize(buttonSize);
		
		//Add To Parent
		buttonPanel.add(start);
		buttonPanel.add(exit);
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
		if (e.getSource() == start) {
			
			stopGUI();
			GameHandler.startGame();
			
		} else {
			System.exit(1);
		}
		
	}
}

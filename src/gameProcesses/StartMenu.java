package gameProcesses;

import java.awt.BorderLayout;
import java.awt.Component;
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
import javax.swing.WindowConstants;

import gameProcesses.themes.ThemeHandler;

public class StartMenu implements ActionListener, GUIRunnableInterface, Runnable {

	JFrame frame;
	JPanel containerPanel;
	JPanel buttonPanel;
	JLabel title;
	JButton start;
	JButton exit;

	
	@Override
	public void startGUI() {
		
		frame = new JFrame();
		frame.setSize(new Dimension(500, 300));
		frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		frame.setUndecorated(true);
		frame.setLayout(new BoxLayout(frame.getContentPane(), BoxLayout.Y_AXIS));
		frame.getContentPane().setBackground(ThemeHandler.getBackground());
		frame.setLocationRelativeTo(null);

		//Add Components
		frame.add(Box.createVerticalStrut(25));
		addPanels();
		frame.add(Box.createVerticalStrut(15));

		frame.setVisible(true);

	}

	private void addPanels() {

		//Title
		title = new JLabel("MineSweeper", SwingConstants.CENTER);
		title.setVerticalTextPosition(Font.CENTER_BASELINE);
		title.setFont(new Font(null, Font.PLAIN, 60));
		title.setAlignmentX(Component.CENTER_ALIGNMENT);
		title.setForeground(ThemeHandler.getText());
		title.setBackground(ThemeHandler.getForeground());
		title.setOpaque(true);
		
		//Bottom Buttons Panels
		containerPanel = new JPanel();
		containerPanel.setBackground(ThemeHandler.getBackground());
		containerPanel.setLayout(new BorderLayout(10, 10));
		containerPanel.setBorder(BorderFactory.createEmptyBorder(25, 25, 25, 25));
		containerPanel.setOpaque(true);

		buttonPanel = new JPanel();
		buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 0));
		buttonPanel.setOpaque(false);

		addButtons();

		containerPanel.add(buttonPanel, BorderLayout.CENTER);

		//Add To Parent
		frame.add(title);
		frame.add(Box.createVerticalStrut(25));
		frame.add(containerPanel);

	}

	private void addButtons() {

		Dimension buttonSize = new Dimension(200, 100);

		start = new JButton("Start");
		start.addActionListener(this);
		start.setFocusable(false);
		start.setPreferredSize(buttonSize);
		start.setBackground(ThemeHandler.getButton());

		exit = new JButton("Quit");
		exit.addActionListener(this);
		exit.setFocusable(false);
		exit.setPreferredSize(buttonSize);
		exit.setBackground(ThemeHandler.getButton());

		//Add To Parent
		buttonPanel.add(start);
		buttonPanel.add(exit);

	}

	@Override
	public void actionPerformed(ActionEvent e) {

		if (e.getSource() == start) {

			GameHandler.startGame(15);
			
			stopGUI();

		} else {
			System.exit(1);
		}

	}

	@Override
	public void stopGUI() {
		frame.dispose();
	}

	@Override
	public void run() {
		Thread.currentThread().setName("GUI Thread");
		startGUI();
	}

}

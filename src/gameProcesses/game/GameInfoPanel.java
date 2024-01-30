package gameProcesses.game;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;

import gameProcesses.GameHandler;
import gameProcesses.themes.ThemeHandler;

public class GameInfoPanel extends JPanel implements ActionListener {

	private static final long serialVersionUID = 1L;

	JButton exit;
	JButton reset;

	public GameInfoPanel() {
		
		exit = new JButton("exit");
		reset = new JButton("reset");
		
		this.setPreferredSize(new Dimension(250, 500));
		this.setBackground(ThemeHandler.getForeground());
		
		
		exit.addActionListener(this);
		exit.setPreferredSize(new Dimension(70, 50));
		
		reset.addActionListener(this);
		reset.setPreferredSize(new Dimension(70, 50));
		
		this.add(exit);
		this.add(reset);
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {

		if (e.getSource() instanceof JButton) {
			
			if (e.getSource() == exit) {
				System.exit(0);
			} else if (e.getSource() == reset) {
				GameHandler.startGame();
			}
		}
	}

}

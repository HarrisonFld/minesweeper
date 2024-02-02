package gameProcesses.game;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

import gameProcesses.GameHandler;
import gameProcesses.themes.ThemeHandler;
import utils.FileManager;

public class GameInfoPanel extends JPanel implements ActionListener {

	private static final long serialVersionUID = 1L;

	JButton exit;
	JButton reset;
	JTextField plotsSqrtTextField;
	JButton settings;
	
	ImageIcon alive = new ImageIcon(FileManager.findFile("/imgs/alive.png"));
	ImageIcon dead = new ImageIcon(FileManager.findFile("/imgs/dead.png"));;

	public GameInfoPanel() {
		
		settings = new JButton("Open Settings");
		
		exit = new JButton("exit");
		reset = new JButton("reset");
		
		plotsSqrtTextField = new JTextField("15");
		
		this.setPreferredSize(new Dimension((int)Game.width / 7, (int) (Game.height / 1.5)));
		this.setBackground(ThemeHandler.getForeground());
		
		exit.addActionListener(this);
		exit.setPreferredSize(new Dimension(70, 50));
		exit.setFocusable(false);
		this.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
		
		reset.addActionListener(this);
		reset.setPreferredSize(new Dimension(70, 50));
		reset.setFocusable(false);
		reset.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
		
		plotsSqrtTextField.setPreferredSize(new Dimension(100, 25));
		plotsSqrtTextField.setHorizontalAlignment(SwingUtilities.HORIZONTAL);
		plotsSqrtTextField.setFocusable(false);
		plotsSqrtTextField.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
		
		settings.addActionListener(this);
		settings.setFocusable(false);
		settings.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
		
		this.add(exit);
		this.add(reset);
		this.add(plotsSqrtTextField);
		this.add(settings);
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {

		if (e.getSource() instanceof JButton) {
			
			JButton button = (JButton) e.getSource();
			
			if (button == exit) {
				System.exit(0);
			} else if (button == reset) {
				
				int userPlotsSqrt;
				
				try {
					userPlotsSqrt = Integer.parseInt(plotsSqrtTextField.getText());
				} catch (NumberFormatException e2) {
					userPlotsSqrt = 15;
					plotsSqrtTextField.setText(String.valueOf(15));
				}
				
				GameHandler.startGame(userPlotsSqrt);
				
			} else if (button == settings) {
				
				GameHandler.openMenu();
				
			}
			
			
		}
	}

}

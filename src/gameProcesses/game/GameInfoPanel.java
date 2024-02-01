package gameProcesses.game;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

import gameProcesses.GameHandler;
import gameProcesses.themes.ThemeHandler;

public class GameInfoPanel extends JPanel implements ActionListener {

	private static final long serialVersionUID = 1L;

	JButton exit;
	JButton reset;
	JTextField plotsSqrtTextField;
	JButton temp;

	public GameInfoPanel() {
		
		temp = new JButton("Open Settings");
		
		exit = new JButton("exit");
		reset = new JButton("reset");
		plotsSqrtTextField = new JTextField("15");
		
		this.setPreferredSize(new Dimension(250, 500));
		this.setBackground(ThemeHandler.getForeground());
		
		exit.addActionListener(this);
		exit.setPreferredSize(new Dimension(70, 50));
		
		reset.addActionListener(this);
		reset.setPreferredSize(new Dimension(70, 50));
		
		plotsSqrtTextField.setPreferredSize(new Dimension(100, 25));
		plotsSqrtTextField.setHorizontalAlignment(SwingUtilities.HORIZONTAL);
		
		temp.addActionListener(this);
		temp.setFocusable(false);
		
		this.add(exit);
		this.add(reset);
		this.add(plotsSqrtTextField);
		this.add(temp);
		
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
				
			} else if (button == temp) {
				
				GameHandler.openMenu();
				
			}
			
			
		}
	}

}

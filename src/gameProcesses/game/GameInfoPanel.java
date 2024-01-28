package gameProcesses.game;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;

public class GameInfoPanel extends JPanel implements ActionListener {
	
	JButton exit = new JButton();
	
	public GameInfoPanel() {
		this.setPreferredSize(new Dimension(500, 500));
		exit.addActionListener(this);
		this.add(exit);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
		if (e.getSource() instanceof JButton) {
			System.out.println("hi");
			System.exit(0);
		}
	}
	
}

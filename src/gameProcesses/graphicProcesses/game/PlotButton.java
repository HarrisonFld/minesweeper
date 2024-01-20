package gameProcesses.graphicProcesses.game;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;

import gameProcesses.GameHandler;

public class PlotButton extends JButton implements ActionListener {

	private static final long serialVersionUID = -7667955504747988800L;
	
	protected boolean mine = false;
	private int index;
	
	PlotButton(boolean mine, int index) {
		
		this.mine = mine;
		this.index = index;
		
		this.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
		this.addActionListener(PlotButton.this);
		this.setBackground(Color.green);
		
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		
		disableThisButton();
		onDisabledSetStyle();
		
	}
	
	private void onDisabledSetStyle() {
		
		if (mine) {
			
			this.setBackground(Color.red);
			
		} else {
			
			this.setText(String.valueOf(GameHandler.getSurroundingMines(index)));
			this.setBackground(Color.darkGray);
			
		}
		
	}
	
	private void disableThisButton() {
		
		this.setEnabled(false);
		
	}
	
	public boolean getMine() {
		
		return mine;
		
	}

}

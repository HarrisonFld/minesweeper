package gameProcesses.game;

import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.SwingUtilities;

import gameProcesses.GameHandler;

public class PlotButton extends JButton {

	private static final long serialVersionUID = -7667955504747988800L;
	
	private boolean mine = false;
	private int index;
	
	public PlotButton(boolean mine, int index) {
		
		this.mine = mine;
		this.index = index;
		
		this.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
		this.setFocusable(false);
		mouseListener();
		
		this.setBackground(Color.green);
		
	}
	
	private void mouseListener() {
		
		this.addMouseListener(new MouseAdapter() {
			
			public void mouseClicked(MouseEvent e) {
				
				if (!isEnabled()) {
					return;
				}
				
				if (SwingUtilities.isRightMouseButton(e)) {
					
					e.getComponent().setBackground(Color.blue);
					
				} else if (SwingUtilities.isLeftMouseButton(e)) {
					
					onLeftLicked();
					
				}
				
			}
			
			
		});
		
	}
	
	public int getIndex() {
		
		return index;
		
	}
	
	public void onLeftLicked() {
		
		if (mine) {
			
			this.setBackground(Color.red);
			GameHandler.endGame();
			
		} else {
			
			this.setBackground(Color.darkGray);
			this.setText(String.valueOf(GameHandler.getSurroundingMines(index)));
			this.setEnabled(false);
			
		}
		
	}
	
	public boolean isMine() {
		
		return mine;
		
	}

}

package gameProcesses.game;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.SwingUtilities;

import gameProcesses.GameHandler;

public class PlotButton extends JButton {

	private static final long serialVersionUID = -7667955504747988800L;
	
	private boolean mine = false;
	private int index;
	private boolean flagged = false;
	
	ImageIcon icon = new ImageIcon("flag.png");
	
	public PlotButton(boolean mine, int index) {
		
		this.mine = mine;
		this.index = index;
		
		//this.setContentAreaFilled(false);
		this.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
		this.setFocusable(false);
		this.setDisabledIcon(icon);
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
					
					if (flagged == false) {
						
						flagged = true;
						((PlotButton)e.getComponent()).setIcon(icon);
						 
					} else {
						flagged = false;
						((PlotButton)e.getComponent()).setIcon(null);
					}
					
					
					
				} else if (SwingUtilities.isLeftMouseButton(e)) {
					
					onLeftClicked();
					
				}
				
			}
			
			
		});
		
	}
	
	public int getIndex() {
		
		return index;
		
	}
	
	public void onLeftClicked() {
		
		if (!this.isEnabled()) {
			return;
		}
		
		if (mine) {
			
			this.setIcon(null);
			this.setBackground(Color.red);
			GameHandler.endGame();
			
		} else {
			
			GameHandler.updateCounter();
			this.setBackground(Color.darkGray);
			
			int mines = GameHandler.getSurroundingMines(index);
			
			this.setText(String.valueOf(mines));
			this.setEnabled(false);
			
		}
		
	}
	
	public boolean isMine() {
		
		return mine;
		
	}
	
//	@Override
//	protected void paintComponent(Graphics grphcs) {
//		
//		Graphics2D g2 = (Graphics2D) grphcs;
//		g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
//		
//		g2.setColor(new Color(5, 5, 5));
//		g2.fillRoundRect(0, 0, this.getWidth(), this.getHeight(), 0, 0);
//		g2.setColor(getBackground());
//		
//		g2.fillRoundRect(2, 2, this.getWidth() -4, this.getHeight() - 4, 0, 0);
//		
//		super.paintComponent(grphcs);
//		
//	}

}

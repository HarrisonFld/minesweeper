package gameProcesses.game;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.SwingUtilities;

import gameProcesses.GameHandler;
import utils.FileManager;

/**
 *	Button for minesweeper plot.
 *
 *	Uses MouseListener to listen for left and right click. On left click the plot
 *	is "dug up" and if it's a mine it ends the game, if not it displays how many mines are around it.
 *	On right click it sets the icon to a flag.
 *
 *	<P>call onLeftClicked to skip the MouseListener and "dig up" the plot.
 *
 *	@param mine		Is this plot a mine.
 *	@param flagged	Is this plot flagged.
 *	@param index	The index of the plot in the grid
 *
 *	@see onLeftClicked
 */
public class PlotButton extends JButton implements MouseListener {

	private static final long serialVersionUID = -7667955504747988800L;

	private boolean mine = false;
	private int index;
	private boolean flagged = false;

	private static ImageIcon icon = new ImageIcon(FileManager.findFile("/imgs/flag.png"));

	/**
	 * @see PlotButton
	 *
	 * @param mine		Is this plot a mine.
	 * @param index		The plots index in the grid.
	 */
	public PlotButton(boolean mine, int index) {

		this.mine = mine;
		this.index = index;

		//this.setContentAreaFilled(false);
		this.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
		this.setFocusable(true);
		this.setFocusPainted(false);
		this.setDisabledIcon(icon);
		this.setFont(new Font("Copperplate", Font.BOLD, 25));
		mouseListener();

		this.setBackground(Color.green);

	}

	private void mouseListener() {
		this.addMouseListener(this);
	}

	public int getIndex() {
		return index;
	}

	/**
	 * "Dig up" the plot
	 *
	 * <P>
	 * The background turns grey and displays the number if not a mine
	 * The background turns red and ends the game if it is a mine.
	 */
	public void onLeftClicked() {

		if (!this.isEnabled() || GameHandler.getGameOverState()) {
			return;
		}

		this.setIcon(null);
		this.setEnabled(false); //This has to be before the checkForZeros Method!

		if (mine) {

			this.setBackground(Color.red);
			GameHandler.endGame();

		} else {

			this.setBackground(Color.darkGray);

			int mines = GameHandler.getSurroundingMines(index);

			if (mines == 0) {
				this.setForeground(Color.green);
				GameHandler.checkForZeros(index);
			} else if (mines == 1) {
				this.setForeground(Color.yellow);
			} else if (mines > 1 && mines < 4) {
				this.setForeground(Color.orange);
			} else {
				this.setForeground(Color.red);
			}

			this.setText(String.valueOf(mines));
			this.removeMouseListener(this);
			
			GameHandler.updateCounter();
			
		}

	}

	public boolean isMine() {
		return mine;
	}
	
	public void onRightClicked(JButton plot) {
		
		flagged = !flagged;
		
		if (flagged) {
			plot.setIcon(icon);
		} else {
			plot.setIcon(null);
		}
		
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		
		if (!isEnabled() || GameHandler.getGameOverState()) {
			return;
		}
		
		if (SwingUtilities.isRightMouseButton(e)) {

			onRightClicked((JButton) e.getComponent());

		} else if (SwingUtilities.isLeftMouseButton(e)) {

			onLeftClicked();

		}
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub

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

package gameProcesses.graphicProcesses;

import javax.swing.JFrame;

public abstract class Generic {
	
	protected static JFrame frame;
	
	public static void startGUI() {
		
		frame = new JFrame();
		
	}
	
	public static void stopGUI() {
		
		frame.dispose();
		
	}
	
}

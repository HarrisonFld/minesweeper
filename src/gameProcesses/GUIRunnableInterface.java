package gameProcesses;

public interface GUIRunnableInterface extends Runnable{

	public void startGUI();
	public void stopGUI();

	/**
	 * Implements the runnable interface
	 *
	 * <p> Override to set the thread name.
	 */
	@Override
	public default void run() {
		startGUI();
	}

}

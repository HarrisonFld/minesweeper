package gameProcesses;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.JComboBox;
import javax.swing.JPanel;

import gameProcesses.themes.DarkTheme;
import gameProcesses.themes.LightTheme;
import gameProcesses.themes.Theme;
import gameProcesses.themes.ThemeHandler;

public class Menu extends JPanel implements ItemListener {
	
	private static final long serialVersionUID = -8872473059626312879L;
	
	Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
	double width = screenSize.getWidth();
	double height = screenSize.getHeight();
	
	private JComboBox<Theme> themeMenu;

	public Menu() {
		
		themeMenu = new JComboBox<Theme>();
		themeMenu.addItem(new DarkTheme());
		themeMenu.addItem(new LightTheme());
		themeMenu.addItemListener(this);
		
		this.setBackground(ThemeHandler.getBackground());
		this.setPreferredSize(new Dimension((int)width, (int)height));
		
		this.add(themeMenu);
		
		this.setVisible(false);;
		
		
	}

	@Override
	public void itemStateChanged(ItemEvent e) {
		GameHandler.changeTheme((Theme) themeMenu.getSelectedItem());
	}

}

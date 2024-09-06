package org.retal.offgame.ui;

import java.awt.*;
import java.io.*;

import javax.swing.*;

public class MainFrame extends JFrame
{
	public MainFrame() throws IOException
	{
		setName("game_frame");
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		setSize(dim.width, dim.height);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setExtendedState(Frame.MAXIMIZED_BOTH);
		//setUndecorated(true);
		MainPanel main_panel = new MainPanel();
		main_panel.setBounds(new Rectangle(0, 0, dim.width, dim.height));
		add(main_panel);
		main_panel.setVisible(true);
		setVisible(true);
	}
	
	
}

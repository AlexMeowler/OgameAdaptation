package offgame;

import java.awt.*;
import java.awt.image.*;
import java.io.*;

import javax.imageio.ImageIO;
import javax.swing.*;

public class OffGameFrame extends JFrame
{
	public OffGameFrame() throws IOException
	{
		setName("game_frame");
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		setSize(dim.width, dim.height);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setExtendedState(Frame.MAXIMIZED_BOTH);
		//setUndecorated(true);
		OffGamePanel main_panel = new OffGamePanel();
		main_panel.setBounds(new Rectangle(0, 0, dim.width, dim.height));
		add(main_panel);
		main_panel.setVisible(true);
		setVisible(true);
	}
	
	
}

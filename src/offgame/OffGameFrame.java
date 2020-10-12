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
		setName("game frame");
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		setSize(dim.width, dim.height);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setExtendedState(Frame.MAXIMIZED_BOTH);
		//setUndecorated(true);
		OffGamePanel main_panel = new OffGamePanel();
		main_panel.setBounds(new Rectangle(0, 0, dim.width, dim.height));
		main_panel.setVisible(true);
		add(main_panel);
		setVisible(true);
	}
	
	
}

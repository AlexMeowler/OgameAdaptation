package offgame;

import java.awt.*;

import javax.swing.*;

public class InfoPanel extends JPanel 
{
	public InfoPanel(String name)
	{
		setName(name);
		setLayout(new GridBagLayout());
	}
	
	protected GridBagConstraints constraints = new GridBagConstraints();
}

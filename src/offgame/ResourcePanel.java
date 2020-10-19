package offgame;

import java.awt.*;
import java.awt.image.*;
import java.io.IOException;
import java.text.NumberFormat;
import java.util.Locale;

import javax.imageio.ImageIO;
import javax.swing.*;

public class ResourcePanel extends JPanel 
{
	public ResourcePanel(String path) throws IOException
	{
		label = new ResourceLabel(path);
		setLayout(new GridBagLayout());
		GridBagConstraints constraints = new GridBagConstraints();
		constraints.weightx = 0.5f;
		constraints.weighty = 1.0f;
		constraints.gridx = 0;
		constraints.gridy = 0;
		constraints.gridwidth = 1;
		constraints.gridheight = 1;
		constraints.insets.left = 10;
		constraints.anchor = GridBagConstraints.WEST;
		constraints.fill = GridBagConstraints.NONE;
		add(label, constraints);
	}
	public void paintComponent(Graphics g)
	{
		super.paintComponents(g);
		Graphics2D g2d = (Graphics2D) g;
		GradientPaint gp = new GradientPaint(0, 0, new Color(111, 153, 188), 0, getHeight(), new Color(46, 70, 101));
        g2d.setPaint(gp);
        g2d.fillRect(0, 0, getWidth(), getHeight());
        g2d.setColor(Color.BLACK);
        g2d.drawRect(0, 0, getWidth(), getHeight());
	}
	
	private String getHTMLWrappedString(String text)
	{
		String decoy = "";
		for(int i = 0; i < LABEL_CHAR_WIDTH; i++)
		{
			decoy += "0";
		}
		int width = getFontMetrics(getFont()).stringWidth(decoy);
		return "<html><body width='" + width + "'>" + text + "</body></html>";
	}
	
	protected void setTextAsNum(int num)
	{
		label.setText(getHTMLWrappedString(NumberFormat.getNumberInstance(Locale.US).format(num)));
		revalidate();
	}
	
	protected void setTextAsMail(int num)
	{
		label.setText(getHTMLWrappedString("[" + num + "]"));
		revalidate();
	}
	
	private ResourceLabel label;
	
	private class ResourceLabel extends JLabel
	{
		public ResourceLabel(String path) throws IOException
		{
			BufferedImage icon = ImageIO.read(this.getClass().getResourceAsStream("/" + path));
			setIcon(new ImageIcon(icon));
			setForeground(Color.WHITE);
			setVisible(true);
			
		}
	}
	private static final int LABEL_CHAR_WIDTH = ("" + Integer.MAX_VALUE).length();
}

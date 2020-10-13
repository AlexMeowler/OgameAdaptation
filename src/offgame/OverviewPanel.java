package offgame;

import java.awt.*;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;
import java.io.IOException;
import java.text.NumberFormat;
import java.util.Locale;

import javax.imageio.ImageIO;
import javax.swing.*;

public class OverviewPanel extends InfoPanel
{

	public OverviewPanel(String name, Planet planet) 
	{
		super(name);
		setOpaque(false);
		addMouseWheelListener(this);
		constraints.weightx = 1.0f;
		constraints.weighty = 0.0f;
		constraints.gridx = 0;
		constraints.gridy = 0;
		constraints.gridwidth = 3;
		constraints.gridheight = 1;
		constraints.insets.bottom = 3;
		constraints.fill = GridBagConstraints.BOTH;
		constraints.anchor = GridBagConstraints.NORTHWEST;
		add(new TextLabel("�������", false), constraints);
		constraints.gridx = 0;
		constraints.gridy = 1;
		constraints.gridwidth = 3;
		constraints.gridheight = 1;
		constraints.anchor = GridBagConstraints.NORTH;
		//constraints.ipady = 10;
		add(new TextLabel("�����", true), constraints);
		constraints.gridx = 0;
		constraints.gridy = 2;
		constraints.gridwidth = 3;
		constraints.gridheight = 1;
		add(Box.createVerticalStrut(STRUT_SIZE), constraints);
		constraints.gridx = 0;
		constraints.gridy = 3;
		constraints.gridwidth = 3;
		constraints.gridheight = 1;
		add(Box.createVerticalStrut(STRUT_SIZE), constraints);
		constraints.gridx = 0;
		constraints.gridy = 4;
		constraints.gridwidth = 1;
		constraints.gridheight = 1;
		constraints.insets.right = 5;
		add(new TextLabel("�������", true), constraints);
		constraints.insets.right = 0;
		constraints.gridx = 1;
		constraints.gridy = 4;
		constraints.gridwidth = 2;
		constraints.gridheight = 1;
		add(Box.createVerticalStrut(STRUT_SIZE), constraints);
		constraints.gridx = 0;
		constraints.gridy = 5;
		constraints.gridwidth = 1;
		constraints.gridheight = 1;
		constraints.insets.right = 5;
		add(new TextLabel("�������", true), constraints);
		constraints.insets.right = 0;
		constraints.gridx = 1;
		constraints.gridy = 5;
		constraints.gridwidth = 2;
		constraints.gridheight = 1;
		add(Box.createVerticalStrut(STRUT_SIZE), constraints);
		constraints.gridx = 0;
		constraints.gridy = 6;
		constraints.gridwidth = 1;
		constraints.gridheight = 1;
		constraints.insets.right = 5;
		constraints.fill = GridBagConstraints.BOTH;
		add(new TextLabel("", false), constraints);
		add(Box.createVerticalStrut(5 * STRUT_SIZE), constraints);
		constraints.gridx = 1;
		constraints.gridy = 6;
		constraints.gridwidth = 1;
		constraints.gridheight = 1;
		add(new PlanetImg(planet), constraints);
		add(Box.createVerticalStrut(new PlanetImg(planet).getIcon().getIconHeight() + 30), constraints);
		//add(Box.createHorizontalStrut(200), constraints);
		constraints.gridx = 2;
		constraints.gridy = 6;
		constraints.gridwidth = 1;
		constraints.gridheight = 1;
		add(new TextLabel("", false), constraints);
		add(Box.createHorizontalStrut(STRUT_SIZE), constraints);
		constraints.gridx = 0;
		constraints.gridy = 7;
		constraints.gridwidth = 1;
		constraints.gridheight = 1;
		add(new TextLabel("�������", true), constraints);
		constraints.gridx = 1;
		constraints.gridy = 7;
		constraints.gridwidth = 2;
		constraints.gridheight = 1;
		add(new TextLabel("" + NumberFormat.getNumberInstance(Locale.US).format(planet.getDiameter()) + " ��", true), constraints);
		constraints.gridx = 0;
		constraints.gridy = 8;
		constraints.gridwidth = 1;
		constraints.gridheight = 1;
		add(new TextLabel("��������� �����", true), constraints);
		constraints.gridx = 1;
		constraints.gridy = 8;
		constraints.gridwidth = 2;
		constraints.gridheight = 1;
		add(new TextLabel("", false), constraints);
		constraints.gridx = 0;
		constraints.gridy = 9;
		constraints.gridwidth = 1;
		constraints.gridheight = 1;
		add(new TextLabel("������������", true), constraints);
		constraints.gridx = 1;
		constraints.gridy = 9;
		constraints.gridwidth = 2;
		constraints.gridheight = 1;
		add(new TextLabel("", false), constraints);
		constraints.gridx = 0;
		constraints.gridy = 10;
		constraints.gridwidth = 1;
		constraints.gridheight = 1;
		add(new TextLabel("�����", true), constraints);
		constraints.gridx = 1;
		constraints.gridy = 10;
		constraints.gridwidth = 2;
		constraints.gridheight = 1;
		add(new TextLabel("", false), constraints);
		constraints.gridx = 0;
		constraints.gridy = 11;
		constraints.gridwidth = 1;
		constraints.gridheight = 1;
		add(new TextLabel("�����������", true), constraints);
		constraints.gridx = 1;
		constraints.gridy = 11;
		constraints.gridwidth = 2;
		constraints.gridheight = 1;
		add(new TextLabel("�� " + planet.getMinTemperature() + "�C �� " + planet.getMaxTemperature() + "�C", true), constraints);
		constraints.gridx = 0;
		constraints.gridy = 12;
		constraints.gridwidth = 1;
		constraints.gridheight = 1;
		add(new TextLabel("����������", true), constraints);
		constraints.gridx = 1;
		constraints.gridy = 12;
		constraints.gridwidth = 2;
		constraints.gridheight = 1;
		add(new TextLabel("[" + planet.getCoords()[0] + ":" + planet.getCoords()[1] + ":" + planet.getCoords()[2] + "]", true), constraints);
		constraints.gridx = 0;
		constraints.gridy = 13;
		constraints.gridwidth = 1;
		constraints.gridheight = 1;
		add(new TextLabel("���� ��������", true), constraints);
		constraints.gridx = 1;
		constraints.gridy = 13;
		constraints.gridwidth = 2;
		constraints.gridheight = 1;
		add(new TextLabel("reserved", true), constraints);
		constraints.gridx = 0;
		constraints.gridy = 14;
		constraints.gridwidth = 1;
		constraints.gridheight = 5;
		add(new TextLabel("�������������", true), constraints);
		//constraints.insets.right = 0;
		constraints.gridx = 1;
		constraints.gridy = 14;
		constraints.gridwidth = 1;
		constraints.gridheight = 1;
		add(new TextLabel("���������:", false), constraints);
		constraints.gridx = 2;
		add(new TextLabel("������", false), constraints);
		constraints.gridx = 1;
		constraints.gridy = 15;
		add(new TextLabel("������������:", false), constraints);
		constraints.gridx = 2;
		add(new TextLabel("������", false), constraints);
		constraints.gridx = 1;
		constraints.gridy = 16;
		add(new TextLabel("����:", false), constraints);
		constraints.gridx = 2;
		add(new TextLabel("������", false), constraints);
		constraints.gridx = 1;
		constraints.gridy = 17;
		add(new TextLabel("�������:", false), constraints);
		constraints.gridx = 2;
		add(new TextLabel("������", false), constraints);
		constraints.gridx = 1;
		constraints.gridy = 18;
		add(new TextLabel("�������������:", false), constraints);
		constraints.gridx = 2;
		add(new TextLabel("������", false), constraints);
		constraints.gridx = 0;
		constraints.gridy = 19;
		constraints.gridwidth = 1;
		constraints.gridheight = 3;
		add(new TextLabel("������ ����������", true), constraints);
		constraints.gridx = 1;
		constraints.gridy = 19;
		constraints.gridwidth = 1;
		constraints.gridheight = 1;
		add(new TextLabel("����� ����:", false), constraints);
		constraints.gridx = 2;
		add(new TextLabel("������", false), constraints);
		constraints.gridx = 1;
		constraints.gridy = 20;
		add(new TextLabel("�����:", false), constraints);
		constraints.gridx = 2;
		add(new TextLabel("������", false), constraints);
		constraints.gridx = 1;
		constraints.gridy = 21;
		add(new TextLabel("���������:", false), constraints);
		constraints.gridx = 2;
		add(new TextLabel("������", false), constraints);
		constraints.gridx = 0;
		constraints.gridy = 22;
		constraints.gridwidth = 3;
		constraints.gridheight = 1;
		constraints.weighty = 1.0f;
		add(Box.createVerticalStrut(STRUT_SIZE), constraints);
	}
	
	private class PlanetImg extends JLabel
	{
		public PlanetImg(Planet planet)
		{
			setIcon(new ImageIcon(planet.getImg()));
			setBackground(new Color(42, 69, 112));
			setHorizontalAlignment(SwingConstants.CENTER);
			setOpaque(true);
		}
	}
	
	public void mouseWheelMoved(MouseWheelEvent e) 
	{
		if(e.getSource() instanceof OverviewPanel)
		{
			JScrollBar bar = ((JScrollPane)SwingUtilities.getAncestorOfClass(JScrollPane.class, this)).getVerticalScrollBar();
			int x = e.getWheelRotation();
			bar.setValue(bar.getValue() + MOUSE_SPEED_MODIFIER * x);
		}
	}
	
	private class TextLabel extends JLabel
	{
		public TextLabel(String text, boolean centered)
		{
			setText("<html><font size=\"4\">" + text +"</font></html>");
			setForeground(Color.WHITE);
			setBackground(new Color(42, 69, 112));
			setOpaque(true);
			setVisible(true);
			if(centered)
			{
				setHorizontalAlignment(SwingConstants.CENTER);
			}
		}
		
	}
	
	private static final int STRUT_SIZE = 20;
	private static final int MOUSE_SPEED_MODIFIER = 20;
}

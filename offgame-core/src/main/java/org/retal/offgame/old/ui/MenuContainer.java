package org.retal.offgame.old.ui;

import java.awt.*;
import java.awt.event.*;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.*;

public class MenuContainer extends JPanel implements MouseListener, MouseMotionListener
{

	public MenuContainer()
	{
		addMouseListener(this);
		addMouseMotionListener(this);
		setName("menu");
		setLayout(new GridBagLayout());
		GridBagConstraints constraints = new GridBagConstraints();
		constraints.weightx = 1.0f;
		constraints.weighty = 1.0f;
		constraints.gridx = 0;
		constraints.gridy = 0;
		constraints.gridwidth = 1;
		constraints.gridheight = 1;
		constraints.fill = GridBagConstraints.HORIZONTAL;
		add(new MenuLabel("Технологии", TECH_TREE), constraints);
		constraints.gridx = 0;
		constraints.gridy = 1;
		add(new MenuLabel("Обзор", OVERVIEW), constraints);
		constraints.gridx = 0;
		constraints.gridy = 2;
		add(new MenuLabel("Ресурсы", RESOURCES), constraints);
		constraints.gridx = 0;
		constraints.gridy = 3;
		add(new MenuLabel("Постройки", BUILDINGS), constraints);
		constraints.gridx = 0;
		constraints.gridy = 4;
		add(new MenuLabel("Исследования", RESEARCH), constraints);
		constraints.gridx = 0;
		constraints.gridy = 5;
		add(new MenuLabel("Верфь", SPACE_YARD), constraints);
		constraints.gridx = 0;
		constraints.gridy = 6;
		add(new MenuLabel("Оборона", DEFENCE), constraints);
		constraints.gridx = 0;
		constraints.gridy = 7;
		add(new MenuLabel("Флот", FLEET), constraints);
		constraints.gridx = 0;
		constraints.gridy = 8;
		add(new MenuLabel("Галактика", GALAXY), constraints);
		constraints.gridx = 0;
		constraints.gridy = 9;
		add(new MenuLabel("Империя", EMPIRE), constraints);
		constraints.gridx = 0;
		constraints.gridy = 10;
		add(new MenuLabel("Симулятор боя", SIMULATOR), constraints);
		constraints.gridx = 0;
		constraints.gridy = 11;
		add(new MenuLabel("Сообщения", MESSAGES), constraints);
		constraints.gridx = 0;
		constraints.gridy = 12;
		add(new MenuLabel("Выход", EXIT_CODE), constraints);
		constraints.gridx = 0;
		constraints.gridy = 13;
		add(Box.createHorizontalStrut(120), constraints);
		
	}
	
	public void paintComponent(Graphics g)
	{
		super.paintComponents(g);
		Graphics2D g2d = (Graphics2D) g;
	}
	
	private class MenuLabel extends JLabel
	{
		public MenuLabel(String text, int code)
		{
			setText("<html><u><font size=\"4\">" + text +"</font></u></html>");
			setForeground(new Color(200, 190, 144));
			setBackground(new Color(42, 69, 112));
			setOpaque(true);
			setVisible(true);
			this.code = code;
			this.text = text;
		}
		
		public int getCode()
		{
			return code;
		}
		
		public void makeNoU()
		{
			setText("<html><font size=\"4\">" + text +"</font></html>");
			try 
			{
				setIcon(new ImageIcon(ImageIO.read(this.getClass().getResourceAsStream("/link.gif"))));
				revalidate();
			} 
			catch (IOException e) 
			{
				e.printStackTrace();
			}
		}
		
		public void makeU()
		{
			setText("<html><u><font size=\"4\">" + text +"</font></u></html>");
			setIcon(null);
			revalidate();
		}
		
		private int code;
		private String text;
	}

	public void mouseClicked(MouseEvent e) 
	{
		requestFocusInWindow();
		if(e.getSource() instanceof MenuContainer)
		{
			Component[] list = ((MenuContainer)e.getSource()).getComponents();
			for(int i = 0; i < list.length; i++)
			{
				if((list[i] instanceof MenuLabel) && (list[i].contains(new Point(e.getX() - list[i].getX(), e.getY() - list[i].getY()))))
				{
					switch(((MenuLabel)list[i]).getCode())
					{
						case EXIT_CODE:
							JFrame window = (JFrame)SwingUtilities.getAncestorNamed("game_frame", this);
							MainPanel panel = (MainPanel)SwingUtilities.getAncestorNamed("main_panel", this);
							panel.killClock();
							window.dispatchEvent(new WindowEvent(window, WindowEvent.WINDOW_CLOSING));
							break;
						default:
							((MainPanel)SwingUtilities.getAncestorNamed("main_panel", this)).setCurrentWindow(((MenuLabel)list[i]).getCode());
							break;
					}
				}
			}
		}
	}

	public void mousePressed(MouseEvent e) 
	{
		
	}

	public void mouseReleased(MouseEvent e) 
	{
		
	}

	public void mouseEntered(MouseEvent e) 
	{
		
	}

	public void mouseExited(MouseEvent e) 
	{
		if(e.getSource() instanceof MenuContainer)
		{
			Component[] list = ((MenuContainer)e.getSource()).getComponents();
			for(int i = 0; i < list.length; i++)
			{
				if((list[i] instanceof MenuLabel))
				{
					((MenuLabel)list[i]).makeU();
				}
			}
		}
	}
	
	public void mouseDragged(MouseEvent e) 
	{
		
	}

	public void mouseMoved(MouseEvent e)
	{
		if(e.getSource() instanceof MenuContainer)
		{
			Component[] list = ((MenuContainer)e.getSource()).getComponents();
			for(int i = 0; i < list.length; i++)
			{
				if((list[i] instanceof MenuLabel))
				{
					if ((list[i].contains(new Point(e.getX() - list[i].getX(), e.getY() - list[i].getY()))))
					{
						((MenuLabel)list[i]).makeNoU();
					}
					else
					{
						((MenuLabel)list[i]).makeU();
					}
				}
			}
		}
	}
	
	public static final int TECH_TREE = 0;
	public static final int OVERVIEW = 1;
	public static final int RESOURCES = 2;
	public static final int BUILDINGS = 3;
	public static final int RESEARCH = 4;
	public static final int SPACE_YARD = 5;
	public static final int DEFENCE = 6;
	public static final int FLEET = 7;
	public static final int GALAXY = 8;
	public static final int EMPIRE = 9;
	public static final int SIMULATOR = 10;
	public static final int MESSAGES = 11;
	public static final int EXIT_CODE = 12;
}

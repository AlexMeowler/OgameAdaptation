package offgame;

import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
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
		add(new MenuLabel("Технологии", constraints.gridy), constraints);
		constraints.gridx = 0;
		constraints.gridy = 1;
		add(new MenuLabel("Обзор", constraints.gridy), constraints);
		constraints.gridx = 0;
		constraints.gridy = 2;
		add(new MenuLabel("Ресурсы", constraints.gridy), constraints);
		constraints.gridx = 0;
		constraints.gridy = 3;
		add(new MenuLabel("Постройки", constraints.gridy), constraints);
		constraints.gridx = 0;
		constraints.gridy = 4;
		add(new MenuLabel("Исследования", constraints.gridy), constraints);
		constraints.gridx = 0;
		constraints.gridy = 5;
		add(new MenuLabel("Верфь", constraints.gridy), constraints);
		constraints.gridx = 0;
		constraints.gridy = 6;
		add(new MenuLabel("Оборона", constraints.gridy), constraints);
		constraints.gridx = 0;
		constraints.gridy = 7;
		add(new MenuLabel("Флот", constraints.gridy), constraints);
		constraints.gridx = 0;
		constraints.gridy = 8;
		add(new MenuLabel("Галактика", constraints.gridy), constraints);
		constraints.gridx = 0;
		constraints.gridy = 9;
		add(new MenuLabel("Империя", constraints.gridy), constraints);
		constraints.gridx = 0;
		constraints.gridy = 10;
		add(new MenuLabel("Симулятор боя", constraints.gridy), constraints);
		constraints.gridx = 0;
		constraints.gridy = 11;
		add(new MenuLabel("Сообщения", constraints.gridy), constraints);
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
							JFrame window = (JFrame)SwingUtilities.getAncestorNamed("game frame", this);
							if  (window != null)
							{
								window.dispatchEvent(new WindowEvent(window, WindowEvent.WINDOW_CLOSING));
							}
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
			//repaint();
		}
	}
	
	public static final int EXIT_CODE = 12;
}

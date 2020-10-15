package offgame;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

public class InfoPanel extends JPanel  implements MouseWheelListener, MouseListener, MouseMotionListener, Scrollable
{
	public InfoPanel(String name, Planet planet)
	{
		setName(name);
		setLayout(new GridBagLayout());
		current_planet = planet;
	}
	
	public void mouseWheelMoved(MouseWheelEvent e) 
	{
		if(e.getSource() instanceof InfoPanel)
		{
			JScrollBar bar = ((JScrollPane)SwingUtilities.getAncestorOfClass(JScrollPane.class, this)).getVerticalScrollBar();
			int x = e.getWheelRotation();
			bar.setValue(bar.getValue() + MOUSE_SPEED_MODIFIER * x);
		}
	}
	public void mouseClicked(MouseEvent e) 
	{
		
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
		
	}

	public void mouseDragged(MouseEvent e)
	{
		
	}

	public void mouseMoved(MouseEvent e) 
	{
		
	}

	public Dimension getPreferredScrollableViewportSize() 
	{
		return null;
	}

	public int getScrollableUnitIncrement(Rectangle visibleRect, int orientation, int direction) 
	{

		return 0;
	}

	public int getScrollableBlockIncrement(Rectangle visibleRect, int orientation, int direction) 
	{

		return 0;
	}

	public boolean getScrollableTracksViewportWidth() 
	{
		return true;
	}

	public boolean getScrollableTracksViewportHeight() 
	{
		return false;
	}
	
	protected GridBagConstraints constraints = new GridBagConstraints();
	private static final int MOUSE_SPEED_MODIFIER = 60;
	protected Planet current_planet;
}

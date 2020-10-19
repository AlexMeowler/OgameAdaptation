package offgame;

import java.awt.*;
import java.awt.event.*;
import java.util.Date;

import javax.swing.*;

public class InfoPanel extends JPanel  implements MouseWheelListener, MouseListener, MouseMotionListener, Scrollable
{
	public InfoPanel(String name, Player player)
	{
		setName(name);
		setLayout(new GridBagLayout());
		current_planet = player.getPlanet(player.getCurrentPlanetIndex());
		this.player = player;
	}
	
	public void updatePanelUI()
	{
		
	}
	
	protected int[] getBuildingTimeArray(int code)
	{
		return null;
	}
	
	protected String createTimeString(int code)
	{
		int[] time_digits = getBuildingTimeArray(code);
		return String.format("Время строительства: %02d дн. %02d ч. %02d мин. %02d с.", time_digits[0], time_digits[1], time_digits[2], time_digits[3]);
	}
	
	protected int[] getRemainingTime(int code, Date date)
	{
		return null;
	}
	
	protected String generateButtonText(int row)
	{
		return "";
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
	protected Player player;
	protected int y_offset;
	public static final Color BACKGROUND_COLOR = new Color(42, 69, 112);
}

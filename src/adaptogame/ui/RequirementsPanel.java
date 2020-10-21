package adaptogame.ui;

import java.awt.Component;
import java.awt.Image;
import java.io.IOException;

import javax.swing.*;

import adaptogame.core.Building;
import adaptogame.core.EntityCategory;
import adaptogame.core.Technology;

public class RequirementsPanel extends JPanel
{
	public RequirementsPanel(int[] buildings_required, int[] technologies_required, Building[] buildings, Technology[] techs) throws IOException
	{
		setOpaque(true);
		setBackground(InfoPanel.BACKGROUND_COLOR);
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		for(int i = 0; i < buildings_required.length; i++)
		{
			if(buildings_required[i] != 0)
			{
				add(new RequirementsImg(EntityCategory.BUILDING, i, "<font color='lime'>" + buildings[i].getLevel() + "</font>/<font color='red'>" + buildings_required[i] + "</font>"));
			}	
		}
		for(int i = 0; i < technologies_required.length; i++)
		{
			if(technologies_required[i] != 0)
			{
				add(new RequirementsImg(EntityCategory.RESEARCH, i, "<font color='lime'>" + techs[i].getLevel() + "</font>/<font color='red'>" + technologies_required[i] + "</font>"));
			}
		}
		
	}
	
	public void updatePanelUI(int[] buildings_required, int[] technologies_required, Building[] buildings, Technology[] techs)
	{
		Component[] list = getComponents();
		for(int i = 0; i < list.length; i++)
		{
			int code = ((RequirementsImg)list[i]).getCode();
			String color;
			switch(((RequirementsImg)list[i]).getType())
			{
				case BUILDING:
					if (buildings[code].getLevel() >= buildings_required[code])
					{
						color = "lime";
					}
					else
					{
						color = "red";
					}
					((RequirementsImg)list[i]).setText("<font color='lime'>" + buildings[code].getLevel() + "</font>/<font color='" + color + "'>" + buildings_required[code] + "</font>");
					break;
				case RESEARCH:
					if (techs[code].getLevel() >= technologies_required[code])
					{
						color = "lime";
					}
					else
					{
						color = "red";
					}
					((RequirementsImg)list[i]).setText("<font color='lime'>" + techs[code].getLevel() + "</font>/<font color='" + color + "'>" + technologies_required[code] + "</font>");
					break;
				default:
					break;
			}
		}
	}
	
	private class RequirementsImg extends JLabel
	{
		public RequirementsImg(EntityCategory type, int code, String text) throws IOException 
		{
			String folder = "/";
			this.type = type;
			this.code = code;
			switch(this.type)
			{
				case BUILDING:
					folder += "bl/";
					break;
				case RESEARCH:
					folder += "rs/";
					break;
				default:
					break;
			}
			ImageIcon img = new ImageIcon(getClass().getResource(folder + code + ".gif"));
			setIcon(new ImageIcon(img.getImage().getScaledInstance(32, 32, Image.SCALE_SMOOTH)));
			setText(text);
			real_text = text;
			setBackground(InfoPanel.BACKGROUND_COLOR);
			setHorizontalAlignment(SwingConstants.CENTER);
			setOpaque(true);
		}
		
		public void setText(String text)
		{
			real_text = text;
			super.setText("<html>" + "<font size=\"3\" color= \"white\">"+ text +"</font></html>");
		}
		
		public EntityCategory getType()
		{
			return type;
		}
		
		public int getCode()
		{
			return code;
		}
		
		private String real_text;
		private EntityCategory type;
		private int code;
	}
}

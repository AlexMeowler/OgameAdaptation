package offgame;

import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
import java.net.URL;
import java.text.NumberFormat;
import java.util.Locale;
import java.util.regex.Pattern;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.text.html.*;

public class BuildingPanel extends InfoPanel 
{

	public BuildingPanel(String name, Planet planet) throws IOException 
	{
		super(name, planet);
		setOpaque(false);
		constraints.weightx = 0.0f;
		constraints.weighty = 0.0f;
		constraints.gridx = 0;
		constraints.gridy = 0;
		constraints.gridwidth = 1;
		constraints.gridheight = 1;
		constraints.insets.bottom = 3;
		constraints.insets.right = 3;
		constraints.fill = GridBagConstraints.BOTH;
		constraints.anchor = GridBagConstraints.NORTH;
		add(new TextLabel("<div style='text-align: center'>Занятость полей</div>", "" + constraints.gridx + "." +constraints.gridy, false), constraints);
		constraints.weightx = 0.0f;
		constraints.gridx = 1;
		constraints.gridy = 0;
		constraints.gridwidth = 1;
		constraints.gridheight = 1;
		add(new TextLabel("<div style='text-align: center'>Резерв / " + current_planet.getFields() + " (осталось РЕЗЕРВ)</div>",  "" + constraints.gridx + "." +constraints.gridy, false), constraints);
		constraints.gridx = 2;
		constraints.gridy = 0;
		constraints.gridwidth = 1;
		constraints.gridheight = 1;
		constraints.weightx = 0.0f;
		constraints.insets.right = 0;
		add(new TextLabel("" + current_planet.getMinTemperature() + " — " + current_planet.getMaxTemperature() + "°C",  "" + constraints.gridx + "." +constraints.gridy, false), constraints);
		addRow(1, Building.POWER_STATION, "Солнечная электростанция " + getCurrentLevelStrig(Building.POWER_STATION) + " (<font color='lime'>+" + (int)current_planet.getBuildings()[Building.POWER_STATION].calcDifference(current_planet.getBuildings()[Building.POWER_STATION].getLevel(), current_planet.getBuildings()[Building.POWER_STATION].getLevel() + 1) + " Энергия</font>)<br>Производит энергию из солнечных лучей.Энергия требуется для работы всех строений, производящих ресурсы.<br>");
		addRow(2, Building.METAL_MINES, "Рудник по добыче металла " + getCurrentLevelStrig(Building.METAL_MINES) + " (<font color='red'>-" + (int)current_planet.getBuildings()[Building.METAL_MINES].calcDifference(current_planet.getBuildings()[Building.METAL_MINES].getLevel(), current_planet.getBuildings()[Building.METAL_MINES].getLevel() + 1) + " Энергия</font>)<br> Основной поставщик сырья для строительства несущих структур построек и кораблей.<br>");
		addRow(3, Building.CRYSTAL_MINES, "Рудник по добыче кристалла " + getCurrentLevelStrig(Building.CRYSTAL_MINES) + " (<font color='red'>-" + (int)current_planet.getBuildings()[Building.CRYSTAL_MINES].calcDifference(current_planet.getBuildings()[Building.CRYSTAL_MINES].getLevel(), current_planet.getBuildings()[Building.CRYSTAL_MINES].getLevel() + 1) + " Энергия</font>)<br> Основной поставщик сырья для электронных строительных элементов и сплавов.<br>");
		addRow(4, Building.DEITERIUM_MINES, "Рудник по добыче дейтерия " + getCurrentLevelStrig(Building.DEITERIUM_MINES) + " (<font color='red'>-" + (int)current_planet.getBuildings()[Building.DEITERIUM_MINES].calcDifference(current_planet.getBuildings()[Building.DEITERIUM_MINES].getLevel(), current_planet.getBuildings()[Building.DEITERIUM_MINES].getLevel() + 1) + " Энергия</font>)<br> Извлекает из воды на планете незначительную долю дейтерия.<br>");
		addRow(5, Building.ROBOT_FACTORY, "Фабрика роботов " + getCurrentLevelStrig(Building.ROBOT_FACTORY) + "<br>Предоставляет простую рабочую силу, которую можно применять при строительств планетарной инфраструктуры. Каждый уровень развития фабрики повышает скорость строительства зданий.<br>");
		addRow(6, Building.SPACE_YARD, "Верфь " + getCurrentLevelStrig(Building.SPACE_YARD) + "<br>В строительной верфи производятся все виды кораблей и оборонительных сооружений. Увеличение уровня повышает скорость постройки кораблей и обороны.<br>");
		addRow(7, Building.LABORATORY, "Исследовательская лаборатория " + getCurrentLevelStrig(Building.LABORATORY) + "<br>Необходима для исследования новых технологий. Увеличение уровня повышает скорость исследований.<br>");
		addRow(8, Building.METAL_STORAGE, "Хранилище металла " + getCurrentLevelStrig(Building.METAL_STORAGE) + "<br>Хранилище для необработанных руд металлов до их дальнейшей переработки.<br>");
		addRow(9, Building.CRYSTAL_STORAGE, "Хранилище кристалла " + getCurrentLevelStrig(Building.CRYSTAL_STORAGE) + "<br>Хранилище для необработанного кристалла до его дальнейшей переработки.<br>");
		addRow(10, Building.DEITERIUM_STORAGE, "Емкость для дейтерия " + getCurrentLevelStrig(Building.DEITERIUM_STORAGE) + "<br>Огромные ёмкости для сберегания добытого дейтерия.<br>");
		addRow(11, Building.NUCLEAR_STATION, "Термоядерная электростанция " + getCurrentLevelStrig(Building.NUCLEAR_STATION) + " (<font color='lime'>+" + (int)current_planet.getBuildings()[Building.NUCLEAR_STATION].calcDifference(current_planet.getBuildings()[Building.NUCLEAR_STATION].getLevel(), current_planet.getBuildings()[Building.NUCLEAR_STATION].getLevel() + 1) + " Энергия</font>)<br>Добывает энергию из процесса образования атома гелия двумя атомами тяжёлого водорода.<br>Потребляет дейтерий.<br>");
		addRow(12, Building.NANITE_FACTORY, "Фабрика нанитов " + getCurrentLevelStrig(Building.NANITE_FACTORY) + "<br>Фабрика нанитов производит специальных эволюционированных роботов - нанитов. Наниты - это роботы, которые путём объединения в состоянии выполнять экстраординарные задания. Каждый уровень в 2 раза ускоряет постройку зданий, кораблей и обороны.<br>");
		addRow(13, Building.TERRAFORMER, "Терраформер " + getCurrentLevelStrig(Building.TERRAFORMER) + "<br>Терраформер может преобразовывать огромные территории делая их пригодными для застройки. Увеличивает количество полей на планете.<br>");
		addRow(14, Building.ROCKET_SHAFT, "Ракетная шахта " + getCurrentLevelStrig(Building.ROCKET_SHAFT) + "<br>Служит для хранения ракет. Увеличение уровня позволяет хранить больше ракет.<br>");
		
	}
	
	private void addRow(int row, int code, String description) throws IOException
	{
		// добавить остаток ресурсов
		constraints.anchor = GridBagConstraints.NORTH;
		constraints.gridx = 0;
		constraints.gridy = row;
		constraints.gridwidth = 1;
		constraints.gridheight = 1;
		constraints.insets.right = 3;
		add(new BuildingImg(code), constraints);
		add(Box.createRigidArea(new Dimension(new BuildingImg(code).getIcon().getIconHeight() + 6, new BuildingImg(code).getIcon().getIconHeight() + 6)), constraints);
		constraints.gridx = 1;
		constraints.gridwidth = 1;
		constraints.gridheight = 1;
		constraints.weightx = 1.0f;
		add(new TextLabel(description + createCostString(code) + createTimeString(code), "" + constraints.gridx + "." +constraints.gridy, true), constraints);
		constraints.gridx = 2;
		constraints.gridwidth = 1;
		constraints.gridheight = 1;
		constraints.weightx = 0.0f;
		constraints.insets.right = 0;
		constraints.anchor = GridBagConstraints.CENTER;
		add(new TextLabel("<div style='text-align: center'><font color='lime'><u>Построить<br>следующий<br>уровень " + (current_planet.getBuildings()[code].getLevel() + 1) + "</u></font></div>",  "" + constraints.gridx + "." +constraints.gridy, false), constraints);
		add(Box.createHorizontalStrut(80), constraints);
	}
	
	private String createCostString(int code)
	{
		double[] building_cost = current_planet.getBuildings()[code].calcBuildingCost();
		String met = "";
		String cryst = "";
		String deit = "";
		if (building_cost[0] != 0) // добавить цвет цифрам
		{
			met = "Металл: " + NumberFormat.getNumberInstance(Locale.US).format((int)building_cost[0]);
		}
		if (building_cost[1] != 0) // добавить цвет цифрам
		{
			cryst = "Кристалл: " + NumberFormat.getNumberInstance(Locale.US).format((int)building_cost[1]);
		}
		if (building_cost[2] != 0) // добавить цвет цифрам
		{
			deit = "Дейтерий: " + NumberFormat.getNumberInstance(Locale.US).format((int)building_cost[2]);
		}
		return "Необходимые ресурсы: " + met + " " + cryst + " " + deit + "<br>";
	}
	
	private String getCurrentLevelStrig(int code)
	{
		String s = "";
		int lv = current_planet.getBuildings()[code].getLevel();
		if(lv != 0)
		{
			s = "(Уровень " + lv + ")";
		}
		return s;
	}
	
	private String createTimeString(int code)
	{
		long total = current_planet.getBuildings()[code].calcBuildingTime(current_planet.getBuildings()[Building.ROBOT_FACTORY].getLevel(), current_planet.getBuildings()[Building.NANITE_FACTORY].getLevel());
		int day = (int)(total / 86400);
		int hours = (int)((total % 86400) / 3600);
		int minutes = (int)((total % 3600) / 60);
		int seconds = (int)(total % 60);
		return String.format("Время строительства: %02d дн. %02d ч. %02d мин. %02d с.", day, hours, minutes, seconds);
	}
	private class BuildingImg extends JLabel
	{
		public BuildingImg(int img_num) throws IOException
		{
			//setIcon(new ImageIcon(ImageIO.read(this.getClass().getResourceAsStream("/bl/" + img_num + ".gif"))));
			setIcon(new ImageIcon(getClass().getResource("/bl/" + img_num + ".gif")));
			setBackground(new Color(42, 69, 112));
			setHorizontalAlignment(SwingConstants.CENTER);
			setOpaque(true);
		}
	}
	
	private class TextLabel extends JLabel implements ComponentListener
	{
		public TextLabel(String text, String name, boolean VerticallyAtTop)
		{	
			setName(name);
			addComponentListener(this);
			real_text = text;
			setText("<html><body width='" + getWidth() + "'><font size=\"4\" color= \"white\">"+ text +"</font></body></html>");
			setForeground(Color.WHITE);
			setBackground(new Color(42, 69, 112));
			setOpaque(true);
			setVisible(true);
			if(VerticallyAtTop)
			{
				setVerticalAlignment(SwingConstants.NORTH);
			}
		}
		
		public void componentResized(ComponentEvent e) 
		{
			setText("<html><body width='" + getWidth() + "'><font size=\"3\" color= \"white\">"+ real_text +"</font></body></html>");
		}

		public void componentMoved(ComponentEvent e) 
		{
			
		}

		public void componentShown(ComponentEvent e) 
		{
			
		}

		public void componentHidden(ComponentEvent e) 
		{
			
		}
		
		private String real_text;
	}
}

package org.retal.offgame;

import java.io.IOException;

import org.retal.offgame.old.ui.MainFrame;

public class GameLauncher 
{
	public static void main(String[] args) throws IOException 
	{
		MainFrame frame = new MainFrame();
        frame.setTitle("Game");
	}
	
	public static volatile Object[] arguments = null;
}

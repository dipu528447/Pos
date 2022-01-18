package com.ShareClass;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JWindow;

public class Progress_bar extends JWindow{

	JPanel main_panel=new JPanel();
	JPanel center_panel=new JPanel();
	JPanel south_panel=new JPanel();
	JLabel label=new JLabel(new ImageIcon("images/p.png"));
	JProgressBar bar=new JProgressBar(0,2000);
	int c;
	Dimension screen =Toolkit.getDefaultToolkit().getScreenSize();
	
	public Progress_bar()
	{
		Set_frame();
		Component();
	}
	public void Set_frame()
	{
		setSize((screen.width-360)/2,(screen.height+32)/2);
		setVisible(true);
		setLocation(screen.width/2-180,screen.height/2-180);
	}
	public void Component()
	{
		add(main_panel);
		main_panel.setLayout(new BorderLayout());
		main_panel.add(label,new BorderLayout().CENTER);
		main_panel.add(bar,new BorderLayout().SOUTH);
		bar.setStringPainted(true);
		bar.setBackground(Color.MAGENTA);
		bar.setForeground(Color.pink);
		
		try
		{
			for(c=0;c<2000;c++)
			{
				int value =bar.getValue();
				if(value<2000)
				{
					bar.setValue(++value);
				}
				Thread.sleep(1);
			}
			dispose();
		}
		catch(Exception s)
		{
			
		}
	} 
}

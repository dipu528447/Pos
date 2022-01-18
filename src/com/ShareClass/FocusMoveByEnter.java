package com.ShareClass;

import java.awt.Component;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class FocusMoveByEnter {
	
	public FocusMoveByEnter(final Component oparent[])
	{
		for(int a=0;a<oparent.length-1;a++){
			final int i=a;
			oparent[a].addKeyListener(new KeyListener() {
				
				@Override
				public void keyTyped(KeyEvent e) {
					// TODO Auto-generated method stub
				}
				
				@Override
				public void keyReleased(KeyEvent e) {
					// TODO Auto-generated method stub

				}
				
				@Override
				public void keyPressed(KeyEvent e) {
					// TODO Auto-generated method stub
					if(e.getKeyChar()==KeyEvent.VK_ENTER){
						oparent[i+1].requestFocusInWindow();
					}
				}
			});
		}
	}
	
}

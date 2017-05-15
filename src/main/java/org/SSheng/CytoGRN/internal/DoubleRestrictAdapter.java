package org.SSheng.CytoGRN.internal;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class DoubleRestrictAdapter implements KeyListener{
	public void keyTyped(KeyEvent e){
		int keyChar = e.getKeyChar();
		if((keyChar >= KeyEvent.VK_0 && keyChar <= KeyEvent.VK_9)||keyChar==46){
		}else{
			e.consume();
		}
	}

	public void keyPressed(KeyEvent e) {
		
	}

	public void keyReleased(KeyEvent e) {
		
	}
}
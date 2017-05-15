package org.SSheng.CytoGRN.internal;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class IntRestrictAdapter implements KeyListener{

	public void keyTyped(KeyEvent e){
		int keyChar = e.getKeyChar();
		if(keyChar >= KeyEvent.VK_0 && keyChar <= KeyEvent.VK_9){
		}else{
			e.consume();
		}
	}

	@Override
	public void keyPressed(KeyEvent e) {
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		
	}
}

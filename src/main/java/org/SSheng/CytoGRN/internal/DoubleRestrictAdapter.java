package org.SSheng.CytoGRN.internal;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class DoubleRestrictAdapter extends KeyAdapter{
	public void keyTyped(KeyEvent e){
		int keyChar = e.getKeyChar();
		if(keyChar >= KeyEvent.VK_0 && keyChar <= KeyEvent.VK_9){
		}else if(keyChar==KeyEvent.VK_PERIOD){
		}else{
			e.consume();
		}
	}
}
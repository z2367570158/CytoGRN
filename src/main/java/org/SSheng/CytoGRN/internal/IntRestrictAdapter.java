package org.SSheng.CytoGRN.internal;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class IntRestrictAdapter extends KeyAdapter{
	public void keyTyped(KeyEvent e){
		int keyChar = e.getKeyChar();
		if(keyChar >= KeyEvent.VK_0 && keyChar <= KeyEvent.VK_9){
		}else{
			e.consume();
		}
	}
}

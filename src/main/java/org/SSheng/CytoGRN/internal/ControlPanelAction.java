package org.SSheng.CytoGRN.internal;

import java.awt.event.ActionEvent;

import org.cytoscape.application.swing.AbstractCyAction;
import org.cytoscape.application.swing.CySwingApplication;
import org.cytoscape.application.swing.CytoPanel;
import org.cytoscape.application.swing.CytoPanelName;
import org.cytoscape.application.swing.CytoPanelState;

public class ControlPanelAction extends AbstractCyAction {

	private CySwingApplication desktopApp;
	private final CytoPanel cytoPanelWest;
	private MyControlPanel myControlPanel;

	public ControlPanelAction(CySwingApplication desktopApp, MyControlPanel myCytoPanel) {
		super("CytoGRN");
		setPreferredMenu("Apps");
		this.desktopApp = desktopApp;
		this.cytoPanelWest = this.desktopApp.getCytoPanel(CytoPanelName.WEST);
		this.myControlPanel = myCytoPanel;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// 如果隐藏则显示
		if (cytoPanelWest.getState() == CytoPanelState.HIDE) {
			cytoPanelWest.setState(CytoPanelState.DOCK);
		}
		// 选中app的panel
		int index = cytoPanelWest.indexOfComponent(myControlPanel);
		if (index == -1) {
			return;
		}
		cytoPanelWest.setSelectedIndex(index);
	}

}

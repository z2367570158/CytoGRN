package org.SSheng.CytoGRN.internal;

import java.util.Properties;

import org.cytoscape.application.swing.CyAction;
import org.cytoscape.application.swing.CySwingApplication;
import org.cytoscape.application.swing.CytoPanelComponent;
import org.cytoscape.model.CyNetworkFactory;
import org.cytoscape.model.CyNetworkManager;
import org.cytoscape.service.util.AbstractCyActivator;
import org.cytoscape.view.model.CyNetworkViewFactory;
import org.cytoscape.view.model.CyNetworkViewManager;
import org.osgi.framework.BundleContext;

public class CyActivator extends AbstractCyActivator {

	@Override
	public void start(BundleContext bc) throws Exception {

		CyNetworkFactory networkFactory = getService(bc, CyNetworkFactory.class);
		CyNetworkManager networkManager = getService(bc, CyNetworkManager.class);
		CyNetworkViewFactory networkViewFactory = getService(bc, CyNetworkViewFactory.class);
		CyNetworkViewManager networkViewManager = getService(bc, CyNetworkViewManager.class);
		CySwingApplication cytoscapeDesktopService = getService(bc, CySwingApplication.class);
		
		
		MyControlPanel myControlPanel = new MyControlPanel(networkFactory,networkManager,networkViewFactory,networkViewManager);
		ControlPanelAction controlPanelAction = new ControlPanelAction(cytoscapeDesktopService, myControlPanel);

//		Properties props = new Properties();
//		props.setProperty("preferredMenu","Apps");
//		props.setProperty("title","CytoGRN");
//		
		registerService(bc, myControlPanel, CytoPanelComponent.class, new Properties());
		registerService(bc, controlPanelAction, CyAction.class, new Properties());
	}

}

package org.SSheng.CytoGRN.internal.tool.mergenetworks;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import javax.swing.BorderFactory;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import org.SSheng.CytoGRN.internal.MyControlPanel;
import org.cytoscape.model.CyNetwork;
import org.cytoscape.model.CyNetworkFactory;
import org.cytoscape.model.CyNetworkManager;
import org.cytoscape.view.model.CyNetworkViewFactory;
import org.cytoscape.view.model.CyNetworkViewManager;

public class MergeNetworksConfigPanel extends JPanel {

	HashMap<String, Long> cynetworkMap = new HashMap<String, Long>();

	MyControlPanel mycontrolpanel;

	CyNetworkFactory networkFactory;
	CyNetworkManager networkManager;
	CyNetworkViewFactory networkViewFactory;
	CyNetworkViewManager networkViewManager;

	Set<CyNetwork> networkSet;
	
	JScrollPane sorceNetworkScrollPane;
	DefaultListModel<String> sorceNetworklistModel;
	JList sorceNetworkList;

	JScrollPane targetNetworkscrollPane;
	DefaultListModel<String> targetNetworklistModel;
	JList targetNetworklist;
	
	JButton btnApply;

	public MergeNetworksConfigPanel(MyControlPanel mycontrolpanel, CyNetworkFactory networkFactory,
			CyNetworkManager networkManager, CyNetworkViewFactory networkViewFactory,
			CyNetworkViewManager networkViewManager) {

		this.mycontrolpanel = mycontrolpanel;

		this.networkFactory = networkFactory;
		this.networkManager = networkManager;
		this.networkViewFactory = networkViewFactory;
		this.networkViewManager = networkViewManager;

		this.setBorder(BorderFactory.createTitledBorder(null, "Configuration",
				javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION,
				javax.swing.border.TitledBorder.DEFAULT_POSITION));
		this.setLayout(null);

		networkSet = networkManager.getNetworkSet();
		Iterator<CyNetwork> iterator = networkSet.iterator();
		while (iterator.hasNext()) {
			CyNetwork cn = (CyNetwork) iterator.next();
			String name = cn.getRow(cn).get(CyNetwork.NAME, String.class);
			long ID = cn.getSUID();
			cynetworkMap.put(name, ID);
		}

		sorceNetworkScrollPane = new JScrollPane();
		sorceNetworklistModel = new DefaultListModel<String>();

		Set<String> networksName = cynetworkMap.keySet();
		Iterator<String> nameIterator = networksName.iterator();
		while(nameIterator.hasNext()){
			String name = (String) nameIterator.next();
			sorceNetworklistModel.addElement(name);
		}
		
		sorceNetworkScrollPane.setSize(new Dimension(150, 150));
		sorceNetworkScrollPane.setLocation(10, 20);
		sorceNetworkList = new JList(sorceNetworklistModel);
		sorceNetworkScrollPane.setViewportView(sorceNetworkList);

		targetNetworkscrollPane = new JScrollPane();
		targetNetworklistModel = new DefaultListModel<String>();

		targetNetworkscrollPane.setSize(new Dimension(150, 150));
		targetNetworkscrollPane.setLocation(230, 20);
		targetNetworklist = new JList(targetNetworklistModel);
		targetNetworkscrollPane.setViewportView(targetNetworklist);

		JButton btnadd = new JButton("¡ú");
		btnadd.setSize(60, 40);
		btnadd.setLocation(165, 50);
		btnadd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				List indices = sorceNetworkList.getSelectedValuesList();
				for (int i = 0; i < indices.size(); i++) {
					String o = (String) indices.get(i);
					targetNetworklistModel.addElement(o);
					sorceNetworklistModel.removeElement(o);
				}
			}
		});

		JButton btnremove = new JButton("¡û");
		btnremove.setSize(60, 40);
		btnremove.setLocation(165, 100);
		btnremove.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				List indices = targetNetworklist.getSelectedValuesList();
				for (int i = 0; i < indices.size(); i++) {
					String o = (String) indices.get(i);
					sorceNetworklistModel.addElement(o);
					targetNetworklistModel.removeElement(o);
				}
			}
		});

		this.add(btnadd);
		this.add(btnremove);
		this.add(sorceNetworkScrollPane);
		this.add(targetNetworkscrollPane);

		if (mycontrolpanel.applyPanel != null) {
			mycontrolpanel.remove(mycontrolpanel.applyPanel);
		}

		btnApply = new JButton("Apply");
		btnApply.setPreferredSize(new Dimension(0, 40));

		mycontrolpanel.applyPanel = new JPanel();
		mycontrolpanel.applyPanel.setBorder(
				BorderFactory.createTitledBorder(null, "Apply", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION,
						javax.swing.border.TitledBorder.DEFAULT_POSITION));
		mycontrolpanel.applyPanel.setLayout(new BorderLayout());
		mycontrolpanel.applyPanel.add(btnApply);

		mycontrolpanel.add(mycontrolpanel.applyPanel, BorderLayout.SOUTH);

	}
}

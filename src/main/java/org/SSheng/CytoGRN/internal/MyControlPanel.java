package org.SSheng.CytoGRN.internal;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.Icon;
import javax.swing.JComboBox;
import javax.swing.JPanel;

import org.SSheng.CytoGRN.internal.algorithm.CN.CNConfigPanel;
import org.SSheng.CytoGRN.internal.algorithm.GENIE3.Genie3ConfigPanel;
import org.SSheng.CytoGRN.internal.algorithm.Multigranger.MultigrangerConfigPanel;
import org.SSheng.CytoGRN.internal.algorithm.pca_cmi.Pca_cmiConfigPanel;
import org.SSheng.CytoGRN.internal.algorithm.pca_pmi.Pca_pmiConfigPanel;
import org.SSheng.CytoGRN.internal.tool.mergenetworks.MergeNetworksConfigPanel;
import org.cytoscape.application.swing.CytoPanelComponent;
import org.cytoscape.application.swing.CytoPanelName;
import org.cytoscape.model.CyNetworkFactory;
import org.cytoscape.model.CyNetworkManager;
import org.cytoscape.view.model.CyNetworkViewFactory;
import org.cytoscape.view.model.CyNetworkViewManager;

public class MyControlPanel extends JPanel implements CytoPanelComponent {

	public JPanel algorithmPanel, configPanel, applyPanel;
	JComboBox box;
	String[] algorithmList = { "=======================Algorithms========================", "GENIE3", "PCA-CMI", "PCA-PMI", "CN", "Multigranger",
			"=========================Tools===========================", "merge networks" };

	public MyControlPanel(CyNetworkFactory networkFactory, CyNetworkManager networkManager,
			CyNetworkViewFactory networkViewFactory, CyNetworkViewManager networkViewManager) {
		this.setLayout(new BorderLayout());
		this.setPreferredSize(new Dimension(400, 0));

		algorithmPanel = new JPanel();
		algorithmPanel.setBorder(BorderFactory.createTitledBorder(null, "Algorithm",
				javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION,
				javax.swing.border.TitledBorder.DEFAULT_POSITION));
		algorithmPanel.setLayout(new BorderLayout());

		configPanel = new JPanel();

		// 实例化一个下拉菜单栏对象
		box = new JComboBox(algorithmList);
		ActionListener listener = new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JComboBox com = (JComboBox) e.getSource();
				String selectedAlgorithm = (String) com.getSelectedItem();
				if (selectedAlgorithm.equals("GENIE3")) {
					if (configPanel.getClass() != Genie3ConfigPanel.class) {
						MyControlPanel.this.remove(configPanel);
						configPanel = new Genie3ConfigPanel(MyControlPanel.this, networkFactory, networkManager,
								networkViewFactory, networkViewManager);
						MyControlPanel.this.add(configPanel, BorderLayout.CENTER);
						MyControlPanel.this.validate();
					}
				} else if (selectedAlgorithm.equals("PCA-CMI")) {
					if (configPanel.getClass() != Pca_cmiConfigPanel.class) {
						MyControlPanel.this.remove(configPanel);
						configPanel = new Pca_cmiConfigPanel(MyControlPanel.this, networkFactory, networkManager,
								networkViewFactory, networkViewManager);
						MyControlPanel.this.add(configPanel, BorderLayout.CENTER);
						MyControlPanel.this.validate();
					}
				} else if (selectedAlgorithm.equals("PCA-PMI")) {
					if (configPanel.getClass() != Pca_pmiConfigPanel.class) {
						MyControlPanel.this.remove(configPanel);
						configPanel = new Pca_pmiConfigPanel(MyControlPanel.this, networkFactory, networkManager,
								networkViewFactory, networkViewManager);
						MyControlPanel.this.add(configPanel, BorderLayout.CENTER);
						MyControlPanel.this.validate();
					}
				} else if (selectedAlgorithm.equals("CN")) {
					if (configPanel.getClass() != CNConfigPanel.class) {
						MyControlPanel.this.remove(configPanel);
						configPanel = new CNConfigPanel(MyControlPanel.this, networkFactory, networkManager,
								networkViewFactory, networkViewManager);
						MyControlPanel.this.add(configPanel, BorderLayout.CENTER);
						MyControlPanel.this.validate();
					}
				} else if (selectedAlgorithm.equals("Multigranger")) {
					if (configPanel.getClass() != MultigrangerConfigPanel.class) {
						MyControlPanel.this.remove(configPanel);
						configPanel = new MultigrangerConfigPanel(MyControlPanel.this, networkFactory, networkManager,
								networkViewFactory, networkViewManager);
						MyControlPanel.this.add(configPanel, BorderLayout.CENTER);
						MyControlPanel.this.validate();
					}
				}else if (selectedAlgorithm.equals("merge networks")) {
					if (configPanel.getClass() != MergeNetworksConfigPanel.class) {
						MyControlPanel.this.remove(configPanel);
						configPanel = new MergeNetworksConfigPanel(MyControlPanel.this, networkFactory, networkManager,
								networkViewFactory, networkViewManager);
						MyControlPanel.this.add(configPanel, BorderLayout.CENTER);
						MyControlPanel.this.validate();
					}
				}
			}
		};
		box.addActionListener(listener);

		algorithmPanel.add(box);

		this.add(algorithmPanel, BorderLayout.NORTH);
		this.add(configPanel, BorderLayout.CENTER);
		this.setVisible(true);
	}

	@Override
	public Component getComponent() {
		return this;
	}

	@Override
	public CytoPanelName getCytoPanelName() {
		return CytoPanelName.WEST;
	}

	@Override
	public Icon getIcon() {
		return null;
	}

	@Override
	public String getTitle() {
		return "CyGRN Panel";
	}

}

package org.SSheng.CytoGRN.internal.GENIE3;

import java.awt.BorderLayout;
import java.awt.Checkbox;
import java.awt.CheckboxGroup;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.File;
import java.util.HashMap;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import org.SSheng.CytoGRN.internal.Common;
import org.SSheng.CytoGRN.internal.DoubleRestrictAdapter;
import org.SSheng.CytoGRN.internal.IntRestrictAdapter;
import org.SSheng.CytoGRN.internal.MyControlPanel;
import org.SSheng.CytoGRN.internal.ProcessingFrame;
import org.cytoscape.model.CyEdge;
import org.cytoscape.model.CyNetwork;
import org.cytoscape.model.CyNetworkFactory;
import org.cytoscape.model.CyNetworkManager;
import org.cytoscape.model.CyNode;
import org.cytoscape.model.CyRow;
import org.cytoscape.view.model.CyNetworkViewFactory;
import org.cytoscape.view.model.CyNetworkViewManager;

import com.mathworks.toolbox.javabuilder.MWArray;
import com.mathworks.toolbox.javabuilder.MWClassID;
import com.mathworks.toolbox.javabuilder.MWException;
import com.mathworks.toolbox.javabuilder.MWNumericArray;

import GENIE3.ClassGENIE3;

public class Genie3ConfigPanel extends JPanel {

	double threshold;
	int amount;

	MyControlPanel mycontrolpanel;

	CyNetworkFactory networkFactory;
	CyNetworkManager networkManager;
	CyNetworkViewFactory networkViewFactory;
	CyNetworkViewManager networkViewManager;

	JLabel labelSelectDataFile;
	JTextField tfSelectDataFile;
	JButton btnFile;
	CheckboxGroup dataTransposeTypeGroup;
	Checkbox cbCG, cbRG;
	JLabel labelSelectNameFile;
	JTextField tfSelectNameFile;
	JButton btnName;
	JLabel labelNonNameNote;

	CheckboxGroup edgefilterTypeGroup;
	Checkbox cbThreshold;
	JTextField tfThreshold;
	Checkbox cbAmount;
	JTextField tfAmount;

	JButton btnApply;

	public Genie3ConfigPanel(MyControlPanel mycontrolpanel, CyNetworkFactory networkFactory,
			CyNetworkManager networkManager, CyNetworkViewFactory networkViewFactory,
			CyNetworkViewManager networkViewManager) {

		mycontrolpanel = mycontrolpanel;

		this.networkFactory = networkFactory;
		this.networkManager = networkManager;
		this.networkViewFactory = this.networkViewFactory;
		this.networkViewManager = this.networkViewManager;

		this.setBorder(BorderFactory.createTitledBorder(null, "Configuration",
				javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION,
				javax.swing.border.TitledBorder.DEFAULT_POSITION));
		this.setLayout(null);

		labelSelectDataFile = new JLabel("please input your data file:");
		labelSelectDataFile.setSize(new Dimension(250, 20));
		labelSelectDataFile.setLocation(10, 20);

		tfSelectDataFile = new JTextField();
		tfSelectDataFile.setSize(new Dimension(250, 30));
		tfSelectDataFile.setLocation(10, 45);

		btnFile = new JButton("Browse");
		btnFile.setSize(new Dimension(80, 30));
		btnFile.setLocation(270, 45);
		btnFile.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent arg0) {
				JFileChooser jf = new JFileChooser(Common.filePath);
				int flag = jf.showOpenDialog(null);
				if (flag == JFileChooser.APPROVE_OPTION) {
					File f = jf.getSelectedFile();
					tfSelectDataFile.setText(f.getPath());
				}
			}
		});

		dataTransposeTypeGroup = new CheckboxGroup();
		cbCG = new Checkbox("column is gene", dataTransposeTypeGroup, true);
		cbRG = new Checkbox("row is gene", dataTransposeTypeGroup, false);
		cbCG.setLocation(10, 75);
		cbCG.setSize(new Dimension(120, 20));
		cbRG.setLocation(140, 75);
		cbRG.setSize(new Dimension(120, 20));

		labelSelectNameFile = new JLabel("please input your gene name file:");
		labelSelectNameFile.setSize(new Dimension(250, 20));
		labelSelectNameFile.setLocation(10, 95);

		tfSelectNameFile = new JTextField();
		tfSelectNameFile.setSize(new Dimension(250, 30));
		tfSelectNameFile.setLocation(10, 115);

		btnName = new JButton("Browse");
		btnName.setSize(new Dimension(80, 30));
		btnName.setLocation(270, 115);
		btnName.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent arg0) {
				JFileChooser jf = new JFileChooser(Common.filePath);
				int flag = jf.showOpenDialog(null);
				if (flag == JFileChooser.APPROVE_OPTION) {
					File f = jf.getSelectedFile();
					tfSelectNameFile.setText(f.getPath());
				}
			}
		});

		labelNonNameNote = new JLabel(
				"<html>" + "Note: You may not input the gene name file, then the node name of the network will be marked as G1,G2,G3..."
						+ "</html>");
		labelNonNameNote.setSize(new Dimension(360, 50));
		labelNonNameNote.setLocation(10, 145);

		edgefilterTypeGroup = new CheckboxGroup();

		cbThreshold = new Checkbox("edge threshold:", edgefilterTypeGroup, false);
		cbThreshold.setSize(new Dimension(130, 30));
		cbThreshold.setLocation(10, 200);

		tfThreshold = new JTextField();
		tfThreshold.setSize(new Dimension(40, 20));
		tfThreshold.setLocation(150, 205);
		tfThreshold.setHorizontalAlignment(JTextField.CENTER);
		tfThreshold.setEditable(false);
		tfThreshold.addKeyListener(new DoubleRestrictAdapter());

		cbAmount = new Checkbox("edges amount:", edgefilterTypeGroup, true);
		cbAmount.setSize(new Dimension(130, 30));
		cbAmount.setLocation(10, 240);

		tfAmount = new JTextField();
		tfAmount.setSize(new Dimension(40, 20));
		tfAmount.setLocation(150, 245);
		tfAmount.setHorizontalAlignment(JTextField.CENTER);
		tfAmount.setText("10");
		tfAmount.setEditable(true);
		tfThreshold.addKeyListener(new IntRestrictAdapter());

		cbThreshold.addItemListener(new ItemListener() {

			public void itemStateChanged(ItemEvent ie) {
				if (ie.getStateChange() == ItemEvent.SELECTED) {
					cbThreshold.setForeground(Color.BLACK);
					cbAmount.setForeground(Color.gray);
					tfThreshold.setEditable(true);
					tfThreshold.setText("0.09");
					tfAmount.setEditable(false);
					tfAmount.setText("");
				}
			}
		});

		cbAmount.addItemListener(new ItemListener() {

			public void itemStateChanged(ItemEvent ie) {
				if (ie.getStateChange() == ItemEvent.SELECTED) {
					cbAmount.setForeground(Color.BLACK);
					cbThreshold.setForeground(Color.gray);
					tfAmount.setEditable(true);
					tfAmount.setText("10");
					tfThreshold.setEditable(false);
					tfThreshold.setText("");
				}
			}
		});

		this.add(labelSelectDataFile);
		this.add(tfSelectDataFile);
		this.add(btnFile);
		this.add(cbCG);
		this.add(cbRG);
		this.add(labelSelectNameFile);
		this.add(tfSelectNameFile);
		this.add(btnName);
		this.add(labelNonNameNote);
		this.add(cbThreshold);
		this.add(tfThreshold);
		this.add(cbAmount);
		this.add(tfAmount);

		if (mycontrolpanel.applyPanel != null) {
			mycontrolpanel.remove(mycontrolpanel.applyPanel);
		}

		btnApply = new JButton("Apply");
		btnApply.setPreferredSize(new Dimension(0, 40));
		btnApply.addActionListener(new Genie3Listner());

		mycontrolpanel.applyPanel = new JPanel();
		mycontrolpanel.applyPanel.setBorder(
				BorderFactory.createTitledBorder(null, "Apply", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION,
						javax.swing.border.TitledBorder.DEFAULT_POSITION));
		mycontrolpanel.applyPanel.setLayout(new BorderLayout());
		mycontrolpanel.applyPanel.add(btnApply);

		mycontrolpanel.add(mycontrolpanel.applyPanel, BorderLayout.SOUTH);
	}

	class Genie3Listner implements ActionListener {

		public void actionPerformed(ActionEvent arg0) {
			Thread processThread = new Thread(new Runnable() {
				public void run() {
					try {
						ProcessingFrame pf = new ProcessingFrame();
						pf.setString(Common.schedule0);
						double[][] dataArray = Common.readDataFile(new File(tfSelectDataFile.getText()));
						String[] nameArray = Common.readNameFile(new File(tfSelectNameFile.getText()));

						if (cbRG.getState() == true && cbCG.getState() == false) {
							pf.setString(Common.schedule1);
							double[][] temp = Common.getTranspose(dataArray);
							dataArray = temp;
						}

						pf.setString(Common.schedule2);
						ClassGENIE3 algorithmGenie3 = new ClassGENIE3();
						MWNumericArray n = new MWNumericArray(dataArray, MWClassID.DOUBLE);

						pf.setString(Common.schedule3);
						Object[] out = algorithmGenie3.GENIE3(1, n);
						MWNumericArray result = (MWNumericArray) out[0];
						double[][] vim = (double[][]) result.toDoubleArray();

						String[][] list = getList(vim, nameArray);
						Common.sort(list);

						pf.setString(Common.schedule4);
						CyNetwork myNet = networkFactory.createNetwork();
						myNet.getRow(myNet).set(CyNetwork.NAME, "Genie3 network");

						HashMap<String, CyNode> nodeNameMap = new HashMap<String, CyNode>();

						if (Genie3ConfigPanel.this.cbAmount.getState()
								&& (!Genie3ConfigPanel.this.cbThreshold.getState())) {
							amount = Integer.parseInt(tfAmount.getText());
							int temp;
							if (amount > list.length) {
								temp = list.length;
							} else {
								temp = amount;
							}
							for (int i = 0; i < temp; i++) {
								String source = list[i][0].trim();
								String target = list[i][1].trim();
								String edgeData = list[i][2].trim();

								CyNode nodeSource = null;
								CyNode nodeTarget = null;

								// for node1
								if (nodeNameMap.containsKey(source)) {
									nodeSource = nodeNameMap.get(source);
								} else {
									nodeSource = myNet.addNode();
									CyRow attributes = myNet.getRow(nodeSource);
									attributes.set("name", source);
									nodeNameMap.put(source, nodeSource);
								}

								if (nodeNameMap.containsKey(target)) {
									nodeTarget = nodeNameMap.get(target);
								} else {
									nodeTarget = myNet.addNode();
									CyRow attributes = myNet.getRow(nodeTarget);
									attributes.set("name", target);
									nodeNameMap.put(target, nodeTarget);
								}

								CyEdge edge = myNet.addEdge(nodeSource, nodeTarget, true);
								CyRow edgeAttributes = myNet.getRow(edge);
								edgeAttributes.set("name", source + " - " + target);
								edgeAttributes.set("interaction", edgeData);
								pf.setString(Common.schedule4 + ":  " + source + "  " + target);
							}
						} else if (Genie3ConfigPanel.this.cbThreshold.getState()
								&& (!Genie3ConfigPanel.this.cbAmount.getState())) {
							threshold = new Double(tfThreshold.getText());
							for (int i = 0; i < list.length; i++) {
								if (new Double(list[i][2]) > threshold) {
									String source = list[i][0].trim();
									String target = list[i][1].trim();
									String edgeData = list[i][2].trim();

									CyNode nodeSource = null;
									CyNode nodeTarget = null;

									// for node1
									if (nodeNameMap.containsKey(source)) {
										nodeSource = nodeNameMap.get(source);
									} else {
										nodeSource = myNet.addNode();
										CyRow attributes = myNet.getRow(nodeSource);
										attributes.set("name", source);
										nodeNameMap.put(source, nodeSource);
									}

									if (nodeNameMap.containsKey(target)) {
										nodeTarget = nodeNameMap.get(target);
									} else {
										nodeTarget = myNet.addNode();
										CyRow attributes = myNet.getRow(nodeTarget);
										attributes.set("name", target);
										nodeNameMap.put(target, nodeTarget);
									}

									CyEdge edge = myNet.addEdge(nodeSource, nodeTarget, true);
									CyRow edgeAttributes = myNet.getRow(edge);
									edgeAttributes.set("name", source + " - " + target);
									edgeAttributes.set("interaction", edgeData);
									pf.setString(Common.schedule4 + ":  " + source + "  " + target);
								}
							}
						}

						networkManager.addNetwork(myNet);

						pf.setString(Common.schedule5);
						MWArray.disposeArray(n);
						MWArray.disposeArray(result);

						pf.dispose();
					} catch (MWException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			});
			processThread.start();
		}

	}

	public String[][] getList(double[][] vim, String[] nameArray) {

		String[][] result = new String[vim.length * (vim[0].length - 1)][3];

		int index = 0;
		for (int i = 0; i < vim.length; i++) {
			for (int j = 0; j < vim[i].length; j++) {

				if (i == j)
					continue;
				if (nameArray == null) {
					result[index][0] = "G" + (i + 1);
					result[index][1] = "G" + (j + 1);
					result[index][2] = vim[i][j] + "";
				} else {
					result[index][0] = nameArray[i];
					result[index][1] = nameArray[j];
					result[index][2] = vim[i][j] + "";
				}

				index++;
			}
		}
		return result;
	}

}

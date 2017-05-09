package org.SSheng.CytoGRN.internal.pca_pmi;

import java.awt.BorderLayout;
import java.awt.Checkbox;
import java.awt.CheckboxGroup;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.HashMap;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import org.SSheng.CytoGRN.internal.Common;
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

import pca_cmi.ClassPCA_CMI;
import pca_pmi.ClassPCA_PMI;

public class Pca_pmiConfigPanel extends JPanel {

	double lamda;
	double order0;
	double threshold;
	
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
	JLabel labellamda;
	JTextField tflamda;
	JLabel labelOrder0;
	JTextField tfOrder0;
	JLabel labelThreshold;
	JTextField tfThreshold;

	JButton btnApply;

	public Pca_pmiConfigPanel(MyControlPanel mycontrolpanel, CyNetworkFactory networkFactory,
			CyNetworkManager networkManager, CyNetworkViewFactory networkViewFactory,
			CyNetworkViewManager networkViewManager) {

		mycontrolpanel = mycontrolpanel;

		this.networkFactory = networkFactory;
		this.networkManager = networkManager;
		this.networkViewFactory = this.networkViewFactory;
		this.networkViewManager = this.networkViewManager;

		this.setBorder(BorderFactory.createTitledBorder(null, "Configuration",
				javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION));
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

		labellamda = new JLabel("lamda:");
		labellamda.setSize(new Dimension(70, 30));
		labellamda.setLocation(10, 200);

		tflamda = new JTextField();
		tflamda.setSize(new Dimension(35, 20));
		tflamda.setLocation(80, 205);
		tflamda.setHorizontalAlignment(JTextField.CENTER);
		tflamda.setText("0.3");
		
		labelOrder0 = new JLabel("order0:");
		labelOrder0.setSize(new Dimension(70, 30));
		labelOrder0.setLocation(10, 230);

		tfOrder0 = new JTextField();
		tfOrder0.setSize(new Dimension(35, 20));
		tfOrder0.setLocation(80, 235);
		tfOrder0.setHorizontalAlignment(JTextField.CENTER);
		tfOrder0.setText("2");
		
		labelThreshold = new JLabel("edge threshold:");
		labelThreshold.setSize(new Dimension(100, 30));
		labelThreshold.setLocation(10, 260);

		tfThreshold = new JTextField();
		tfThreshold.setSize(new Dimension(35, 20));
		tfThreshold.setLocation(110, 265);
		tfThreshold.setHorizontalAlignment(JTextField.CENTER);
		tfThreshold.setText("0.1");

		this.add(labelSelectDataFile);
		this.add(tfSelectDataFile);
		this.add(btnFile);
		this.add(cbCG);
		this.add(cbRG);
		this.add(labelSelectNameFile);
		this.add(tfSelectNameFile);
		this.add(btnName);
		this.add(labelNonNameNote);
		this.add(labellamda);
		this.add(tflamda);
		this.add(labelOrder0);
		this.add(tfOrder0);
		this.add(labelThreshold);
		this.add(tfThreshold);
		

		if (mycontrolpanel.applyPanel != null) {
			mycontrolpanel.remove(mycontrolpanel.applyPanel);
		}

		btnApply = new JButton("Apply");
		btnApply.setPreferredSize(new Dimension(0, 40));
		btnApply.addActionListener(new PCA_PMIListner());

		mycontrolpanel.applyPanel = new JPanel();
		mycontrolpanel.applyPanel.setBorder(
				BorderFactory.createTitledBorder(null, "Apply", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION,
						javax.swing.border.TitledBorder.DEFAULT_POSITION));
		mycontrolpanel.applyPanel.setLayout(new BorderLayout());
		mycontrolpanel.applyPanel.add(btnApply);

		mycontrolpanel.add(mycontrolpanel.applyPanel, BorderLayout.SOUTH);
	}

	class PCA_PMIListner implements ActionListener {

		public void actionPerformed(ActionEvent arg0) {
			Thread processThread = new Thread(new Runnable() {
				public void run() {
					try {
						ProcessingFrame pf = new ProcessingFrame();
						pf.setString(Common.schedule0);
						double[][] dataArray = Common.readDataFile(new File(tfSelectDataFile.getText()));
						String[] nameArray = Common.readNameFile(new File(tfSelectNameFile.getText()));
						lamda = new Double(tflamda.getText());
						order0 = new Double(tfOrder0.getText());
						threshold = new Double(tfThreshold.getText());
						
						if(cbCG.getState()==true && cbRG.getState()==false){
							pf.setString(Common.schedule1);
							double[][] temp = Common.getTranspose(dataArray);
							dataArray = temp;
						}

						pf.setString(Common.schedule2);
						ClassPCA_PMI algorithmPCA_PMI = new ClassPCA_PMI();
						MWNumericArray n = new MWNumericArray(dataArray, MWClassID.DOUBLE);

						pf.setString(Common.schedule3);
						Object[] parameters = {n,lamda,order0};
						Object[] out = algorithmPCA_PMI.pca_pmi(3, parameters);
						MWNumericArray result0 = (MWNumericArray) out[0];
						MWNumericArray result1 = (MWNumericArray) out[1];
						MWNumericArray result2 = (MWNumericArray) out[2];
						double[][] G = (double[][]) result0.toDoubleArray();
						double[][] Gvalue = (double[][]) result1.toDoubleArray();
						String[][] list = getList(G, nameArray, Gvalue);
						Common.sort(list);

						pf.setString(Common.schedule4);
						CyNetwork myNet = networkFactory.createNetwork();
						myNet.getRow(myNet).set(CyNetwork.NAME, "PCA-PMI network");

						HashMap<String, CyNode> nodeNameMap = new HashMap<String, CyNode>();

						for (int i = 0; i < list.length; i++) {
							if (new Double(list[i][2]) > threshold) {
								String source = list[i][0].trim();
								String target = list[i][1].trim();
								String edgeData = list[i][2].trim();
								CyNode nodeSource = null;
								CyNode nodeTarget = null;

								// for node1
								if (nodeNameMap.containsKey(source)) {
									// Node already existed, get a reference to
									// it
									nodeSource = nodeNameMap.get(source);
								} else {
									// Node does not exist, create new one
									nodeSource = myNet.addNode();
									CyRow attributes = myNet.getRow(nodeSource);
									attributes.set("name", source);
									nodeNameMap.put(source, nodeSource);
								}

								if (nodeNameMap.containsKey(target)) {
									// Node already existed, get a reference to
									// it
									nodeTarget = nodeNameMap.get(target);
								} else {
									// Node does not exist, create new one
									nodeTarget = myNet.addNode();
									CyRow nodeAttributes = myNet.getRow(nodeTarget);
									nodeAttributes.set("name", target);
									nodeNameMap.put(target, nodeTarget);
								}

								CyEdge edge = myNet.addEdge(nodeSource, nodeTarget, false);
								CyRow edgeAttributes = myNet.getRow(edge);
								edgeAttributes.set("name", source+" - "+target);
								edgeAttributes.set("interaction", edgeData );
								pf.setString(Common.schedule4+":  "+source+"  "+target);
								
							}
						}
						networkManager.addNetwork(myNet);

						pf.setString(Common.schedule5);
						MWArray.disposeArray(n);
						MWArray.disposeArray(result0);
						MWArray.disposeArray(result1);
						MWArray.disposeArray(result2);

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
	
	public static String[][] getList(double[][] G, String[] nameArray, double[][] Gvalue) {

		String[][] result = new String[G.length * (G[0].length - 1)][3];

		int index = 0;
		for (int i = 0; i < G.length; i++) {
			for (int j = 0; j < G[i].length; j++) {

				if (i == j)
					continue;
				if (nameArray == null) {
					result[index][0] = "G" + (i + 1);
					result[index][1] = "G" + (j + 1);
					result[index][2] = Gvalue[i][j] + "";
				} else {
					result[index][0] = nameArray[i];
					result[index][1] = nameArray[j];
					result[index][2] = Gvalue[i][j] + "";
				}

				index++;
			}
		}
		return result;
	}

}

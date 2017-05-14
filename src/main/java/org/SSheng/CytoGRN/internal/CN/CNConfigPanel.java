package org.SSheng.CytoGRN.internal.CN;

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

import cn.ClassCN;
import pca_cmi.ClassPCA_CMI;

public class CNConfigPanel extends JPanel {

	double[] lamda;
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
	JLabel labelAlgorithmThreshold;
	JTextField tfAlgorithmThreshold;

	JButton btnApply;

	public CNConfigPanel(MyControlPanel mycontrolpanel, CyNetworkFactory networkFactory,
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

		labellamda = new JLabel("lamda:");
		labellamda.setSize(new Dimension(70, 30));
		labellamda.setLocation(10, 200);

		tflamda = new JTextField();
		tflamda.setSize(new Dimension(35, 20));
		tflamda.setLocation(80, 205);
		tflamda.setHorizontalAlignment(JTextField.CENTER);
		tflamda.setText("0.02, 0.01, 0.05");

		labelOrder0 = new JLabel("order0:");
		labelOrder0.setSize(new Dimension(70, 30));
		labelOrder0.setLocation(10, 230);

		tfOrder0 = new JTextField();
		tfOrder0.setSize(new Dimension(35, 20));
		tfOrder0.setLocation(80, 235);
		tfOrder0.setHorizontalAlignment(JTextField.CENTER);
		tfOrder0.setText("2");

		labelAlgorithmThreshold = new JLabel("threshold:");
		labelAlgorithmThreshold.setSize(new Dimension(100, 30));
		labelAlgorithmThreshold.setLocation(10, 260);

		tfAlgorithmThreshold = new JTextField();
		tfAlgorithmThreshold.setSize(new Dimension(35, 20));
		tfAlgorithmThreshold.setLocation(110, 265);
		tfAlgorithmThreshold.setHorizontalAlignment(JTextField.CENTER);
		tfAlgorithmThreshold.setText("0.5485");


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
		this.add(labelAlgorithmThreshold);
		this.add(tfAlgorithmThreshold);

		if (mycontrolpanel.applyPanel != null) {
			mycontrolpanel.remove(mycontrolpanel.applyPanel);
		}

		btnApply = new JButton("Apply");
		btnApply.setPreferredSize(new Dimension(0, 40));
		btnApply.addActionListener(new CNListner());

		mycontrolpanel.applyPanel = new JPanel();
		mycontrolpanel.applyPanel.setBorder(
				BorderFactory.createTitledBorder(null, "Apply", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION,
						javax.swing.border.TitledBorder.DEFAULT_POSITION));
		mycontrolpanel.applyPanel.setLayout(new BorderLayout());
		mycontrolpanel.applyPanel.add(btnApply);

		mycontrolpanel.add(mycontrolpanel.applyPanel, BorderLayout.SOUTH);
	}

	class CNListner implements ActionListener {

		public void actionPerformed(ActionEvent arg0) {
			Thread processThread = new Thread(new Runnable() {
				public void run() {
					try {
						ProcessingFrame pf = new ProcessingFrame();
						pf.setString(Common.schedule0);
						double[][] dataArray = Common.readDataFile(new File(tfSelectDataFile.getText()));
						String[] nameArray = Common.readNameFile(new File(tfSelectNameFile.getText()));
						String[] tempLamda = tflamda.getText().split(",");
						lamda = new double[tempLamda.length];
						for (int i = 0; i < tempLamda.length; i++) {
							lamda[i] = Double.parseDouble(tempLamda[i]);
						}
						order0 = new Double(tfOrder0.getText());
						threshold = new Double(tfAlgorithmThreshold.getText());

						if (cbCG.getState() == true && cbRG.getState() == false) {
							pf.setString(Common.schedule1);
							double[][] temp = Common.getTranspose(dataArray);
							dataArray = temp;
						}

						pf.setString(Common.schedule2);
						ClassCN algorithmCN = new ClassCN();
						MWNumericArray data = new MWNumericArray(dataArray, MWClassID.DOUBLE);
						MWNumericArray lamdaa = new MWNumericArray(lamda, MWClassID.DOUBLE);
						Object[] parameters = {data, lamdaa, order0, threshold };
						pf.setString(Common.schedule3);
						Object[] out = algorithmCN.cn(1, parameters);
						MWNumericArray result0 = (MWNumericArray) out[0];
						double[][] GGTT = (double[][]) result0.toDoubleArray();
						String[][] list = getList(GGTT, nameArray);

						pf.setString(Common.schedule4);
						CyNetwork myNet = networkFactory.createNetwork();
						myNet.getRow(myNet).set(CyNetwork.NAME, "CN network");

						HashMap<String, CyNode> nodeNameMap = new HashMap<String, CyNode>();

						for (int i = 0; i < list.length; i++) {
							String source = list[i][0].trim();
							String target = list[i][1].trim();
							CyNode nodeSource = null;
							CyNode nodeTarget = null;
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
								CyRow nodeAttributes = myNet.getRow(nodeTarget);
								nodeAttributes.set("name", target);
								nodeNameMap.put(target, nodeTarget);
							}
							CyEdge edge = myNet.addEdge(nodeSource, nodeTarget, false);
							CyRow edgeAttributes = myNet.getRow(edge);
							edgeAttributes.set("name", source + " - " + target);
							pf.setString(Common.schedule4 + ":  " + source + "  " + target);
						}
						networkManager.addNetwork(myNet);

						pf.setString(Common.schedule5);
						MWArray.disposeArray(data);
						MWArray.disposeArray(lamdaa);
						MWArray.disposeArray(result0);

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

	public static String[][] getList(double[][] G, String[] nameArray) {

		String[][] temp = new String[(G.length * (G.length - 1))/2][3];

		int index = 0;
		for (int i = 0; i < G.length; i++) {
			for (int j = 0; j <= i; j++) {

				if (i == j)
					continue;
				if (nameArray == null) {
					temp[index][0] = "G" + (i + 1);
					temp[index][1] = "G" + (j + 1);
					temp[index][2] = ""+G[i][j];
				} else {
					temp[index][0] = nameArray[i];
					temp[index][1] = nameArray[j];
					temp[index][2] = ""+G[i][j];
				}

				index++;
			}
		}
		
		int length = 0;
		for (int i = 0; i < temp.length; i++) {
			if (Double.parseDouble(temp[i][2]) == 1) {
				length++;
			}
		}
		String[][] result = new String[length][2];
		index = 0;
		for (int i = 0; i < temp.length; i++) {
			if (Double.parseDouble(temp[i][2]) == 1) {
				result[index][0] = temp[i][0];
				result[index][1] = temp[i][1];
				index++;
			}
		}
		return result;
	}

}

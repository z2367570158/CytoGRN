package org.SSheng.CytoGRN.internal;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

public class ProcessingFrame extends JFrame {

	JLabel labelProcessing;
	JLabel labelSchedule;

//	public static void main(String[] args) throws InterruptedException {
//		ProcessingFrame p = new ProcessingFrame();
//	
//	}

	public ProcessingFrame() {
		this.setSize(250, 70);
		this.setLocationRelativeTo(null);
		this.setLayout(new BorderLayout());
		this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		this.setResizable(false);

		labelProcessing = new JLabel("processing...please wait");
		labelProcessing.setHorizontalAlignment(SwingConstants.CENTER);

		labelSchedule = new JLabel("start");
		labelSchedule.setHorizontalAlignment(SwingConstants.CENTER);

		this.add(labelProcessing, BorderLayout.NORTH);
		this.add(labelSchedule, BorderLayout.CENTER);
		this.setVisible(true);
	}

	public void setString(String s) {
		labelSchedule.setText(s);
	}

}

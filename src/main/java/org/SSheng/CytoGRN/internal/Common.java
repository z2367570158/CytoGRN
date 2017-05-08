package org.SSheng.CytoGRN.internal;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.StringWriter;

import javax.swing.JOptionPane;

public class Common {

	private static final int BUFFER_SIZE = 16384;
	public static String filePath = "C:/Users/SSheng/Desktop/data";
	
	
	public static String schedule0 = "reading data";
	public static String schedule1 = "transposing";
	public static String schedule2 = "starting matlab environment";
	public static String schedule3 = "calculating";
	public static String schedule4 = "creating network";
	public static String schedule5 = "disposing data";

	public static String readString(InputStream source) throws IOException {
		StringWriter writer = new StringWriter();
		BufferedReader reader = new BufferedReader(new InputStreamReader(source));
		try {
			char[] buffer = new char[BUFFER_SIZE];
			int charactersRead = reader.read(buffer, 0, buffer.length);
			while (charactersRead != -1) {
				writer.write(buffer, 0, charactersRead);
				charactersRead = reader.read(buffer, 0, buffer.length);
			}
		} finally {
			reader.close();
		}
		return writer.toString();
	}

	public static double[][] readDataFile(File f) {
		double[][] result = null;
		try {
			FileInputStream fin = new FileInputStream(f);
			String text = readString(fin);

			String[] lines = text.split("\n");
			if (lines.length == 0) {
				return null;
			}
			String line = lines[0];
			String[] items = line.split("\t");

			result = new double[lines.length][items.length];

			for (int i = 0; i < lines.length; i++) {
				for (int j = 0; j < items.length; j++) {
					line = lines[i];
					items = line.split("\t");
					result[i][j] = new Double(items[j]);
				}
			}

		} catch (FileNotFoundException e) {
			JOptionPane.showMessageDialog(null, "data file not found");
		} catch (IOException e) {

		}

		return result;
	}

	public static String[] readNameFile(File f) {
		String[] result = null;
		try {
			FileInputStream fin = new FileInputStream(f);
			String text = readString(fin);

			String[] lines = text.split("\n");
			if (lines.length == 0) {
				return null;
			}

			result = lines;

		} catch (FileNotFoundException e) {
		} catch (IOException e) {
		}
		return result;
	}

	

	public static void sort(String[][] list) {
		String[][] temp = new String[1][3];
		for (int i = list.length - 1; i > 0; i--) {
			for (int j = 0; j < i; j++) {
				double d1 = new Double(list[j][2]);
				double d2 = new Double(list[j + 1][2]);
				if (d2 > d1) {
					temp[0][0] = list[j][0];
					temp[0][1] = list[j][1];
					temp[0][2] = list[j][2];

					list[j][0] = list[j + 1][0];
					list[j][1] = list[j + 1][1];
					list[j][2] = list[j + 1][2];

					list[j + 1][0] = temp[0][0];
					list[j + 1][1] = temp[0][1];
					list[j + 1][2] = temp[0][2];
				}
			}
		}
	}

	public static double[][] getTranspose(double[][] data) {

		double[][] result = new double[data[0].length][data.length];
		for (int i = 0; i < data.length; i++) {
			for (int j = 0; j < data[0].length; j++) {
				result[j][i] = data[i][j];
			}
		}
		return result;
	}

}

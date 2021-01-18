package crud;

import java.io.File;
import java.io.FileNotFoundException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Scanner;

import javax.swing.JOptionPane;

import ij.IJ;


import ij.ImagePlus;
import ij.Prefs;
import ij.measure.ResultsTable;
import ij.plugin.frame.RoiManager;
import ij.plugin.frame.ThresholdAdjuster;
import sampleQueries.DB.mysqlconnect;
import ij.WindowManager;
import ij.gui.OvalRoi;


public class Algorithme {
	
	private int idAlgorithme;
	private String nom, description;
	
	public Algorithme() {
		
	}
	
	
	public Algorithme(String nom) {
		super();
		this.nom = nom;
	}


	public Algorithme(int idAlgorithme, String nom) {
		super();
		this.idAlgorithme = idAlgorithme;
		this.nom = nom;
	}


	public int getIdAlgorithme() {
		return idAlgorithme;
	}

	public void setIdAlgorithme(int idAlgorithme) {
		this.idAlgorithme = idAlgorithme;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Override
	public String toString() {
		return "algorithme [idAlgorithme=" + idAlgorithme + ", nom=" + nom + ", description=" + description + "]";
	}

	
	public void ExecuteAlgorithm(int idAlgo, ImagePlus imp, int idEssai) {
		Connection conn = mysqlconnect.ConnectDb();
		
		String sqlGetDate = "SELECT date FROM essai WHERE idEssai = ?";
		String sqlGetNom = "SELECT nom FROM algorithme WHERE idAlgorithme = ?";
		String date = "";
		String nom = "";
		try {
			PreparedStatement pst = conn.prepareStatement(sqlGetDate);
			pst.setInt(1, idEssai);
			ResultSet rs = pst.executeQuery();
			while (rs.next()) {
				date = rs.getString("date");
			}
			
			pst = conn.prepareStatement(sqlGetNom);
			pst.setInt(1, idAlgo);
			rs = pst.executeQuery();
			while (rs.next()) {
				nom = rs.getString("nom");
			}
		} catch (Exception e) {
			return;
		}
		
		
		String trueDate = date.replace(":", "-");
		Path currentRelativePath = Paths.get("");
		String s = currentRelativePath.toAbsolutePath().toString();
		String configFile = s + "\\Configuration\\configAlgos.txt";
		String folderPath = s + "\\imageProcessing\\Résultats\\"+trueDate;
		
		
		
		ResultsTable RT = ResultsTable.getResultsTable();
		File config = new File(configFile);
		
		int tMin = 25;
		int tMax = 255;
		String size = "4-Infinity";
		
		
		switch (idAlgo) {
		case 1:
			try {
				
				Scanner reader = new Scanner(config);

				while (reader.hasNextLine()) {
					String line = reader.nextLine();
					if (line.equals(nom)) {
						line = reader.next();
						tMin = Integer.parseInt(line.substring(line.indexOf('[')+1, line.lastIndexOf(']')));
						line = reader.next();
						tMax = Integer.parseInt(line.substring(line.indexOf('[')+1, line.lastIndexOf(']')));
						line = reader.next();
						size = line.substring(line.indexOf('[')+1, line.lastIndexOf(']'));
					}
				}

				
				reader.close();
			} catch (FileNotFoundException e) {
				JOptionPane.showMessageDialog(null, "Fichier de configuration des algorithmes non trouvé.");
				return;
			}
			
			
			
			imp.show();
			String main1 = imp.getTitle();
			String Nom1 = main1.substring(0, main1.lastIndexOf('.'));
			ImagePlus impCopy = imp.duplicate();
			IJ.selectWindow(main1);
			
			IJ.run(imp, "Subtract Background...", "rolling=12");
			IJ.run(imp, "8-bit", "");
			IJ.setAutoThreshold(imp, "Default dark");
			//IJ.run(imp, "Threshold...", "");
			IJ.setThreshold(imp, tMin, tMax);
			IJ.run(imp, "Convert to Mask", "");
			IJ.run(imp, "Fill Holes", "");
			IJ.run(imp, "Convert to Mask", "");
			IJ.run(imp, "Watershed", "");
			IJ.run(imp, "Set Measurements...", "area centroid redirect=None decimal=3");
			IJ.run(imp, "Analyze Particles...", "size="+size+" show=Outlines display exclude clear add");
			
			RoiManager ROI = RoiManager.getInstance();
			
			impCopy.show();
			IJ.selectWindow("DUP_"+main1);
			if (ROI != null) {
				ROI.runCommand("Show All without labels");
				IJ.run(impCopy, "From ROI Manager", "");
			}
			IJ.saveAs("PNG", folderPath +"\\"+Nom1+ "RESULTS.png");
			WindowManager.closeAllWindows();
			
		break;
		
		
		case 2:
			
			try {
				
				Scanner reader = new Scanner(config);

				while (reader.hasNextLine()) {
					String line = reader.nextLine();
					if (line.equals(nom)) {
						line = reader.next();
						tMin = Integer.parseInt(line.substring(line.indexOf('[')+1, line.lastIndexOf(']')));
						line = reader.next();
						tMax = Integer.parseInt(line.substring(line.indexOf('[')+1, line.lastIndexOf(']')));
						line = reader.next();
						size = line.substring(line.indexOf('[')+1, line.lastIndexOf(']'));
					}
				}

				
				reader.close();
			} catch (FileNotFoundException e) {
				JOptionPane.showMessageDialog(null, "Fichier de configuration des algorithmes non trouvé.");
				return;
			}
			
			
			
			imp.show();
			String main2 = imp.getTitle();
			String Nom2 = main2.substring(0, main2.lastIndexOf('.'));
			ImagePlus impCopy2 = imp.duplicate();
			IJ.selectWindow(main2);
			
			IJ.run(imp, "Subtract Background...", "rolling=12");
			IJ.run(imp, "8-bit", "");
			IJ.setAutoThreshold(imp, "Default dark");
			//IJ.run(imp, "Threshold...", "");
			IJ.setThreshold(imp, tMin, tMax);
			IJ.run(imp, "Convert to Mask", "");
			IJ.run(imp, "Fill Holes", "");
			IJ.run(imp, "Convert to Mask", "");
			IJ.run(imp, "Set Measurements...", "area centroid redirect=None decimal=3");
			IJ.run(imp, "Analyze Particles...", "size="+size+" show=Outlines display exclude clear add");
			
			RoiManager ROI2 = RoiManager.getInstance();
			
			impCopy2.show();
			IJ.selectWindow("DUP_"+main2);
			if (ROI2 != null) {
				ROI2.runCommand("Show All without labels");
				IJ.run(impCopy2, "From ROI Manager", "");
			}
			IJ.saveAs("PNG", folderPath +"\\"+Nom2+ "RESULTS.png");
			WindowManager.closeAllWindows();
		break;
		
		case 3:
			
			try {
				
				Scanner reader = new Scanner(config);

				while (reader.hasNextLine()) {
					String line = reader.nextLine();
					if (line.equals(nom)) {
						line = reader.next();
						tMin = Integer.parseInt(line.substring(line.indexOf('[')+1, line.lastIndexOf(']')));
						line = reader.next();
						tMax = Integer.parseInt(line.substring(line.indexOf('[')+1, line.lastIndexOf(']')));
						line = reader.next();
						size = line.substring(line.indexOf('[')+1, line.lastIndexOf(']'));
					}
				}

				
				reader.close();
			} catch (FileNotFoundException e) {
				JOptionPane.showMessageDialog(null, "Fichier de configuration des algorithmes non trouvé.");
				return;
			}
			
			
			
			imp.show();
			String main3 = imp.getTitle();
			String Nom3 = main3.substring(0, main3.lastIndexOf('.'));
			ImagePlus impCopy3 = imp.duplicate();
			IJ.selectWindow(main3);
			int width = imp.getWidth();
			int height = imp.getHeight();
			
			imp.setRoi(new OvalRoi(width*0.01,height*0.01,width*0.99,height*0.99));
			IJ.setBackgroundColor(0, 0, 0);
			IJ.run(imp, "Clear Outside", "");
			IJ.run(imp, "8-bit", "");
			IJ.setThreshold(imp, tMin, tMax, "Red");
			Prefs.blackBackground = false;
			IJ.run(imp, "Convert to Mask", "");
			IJ.run(imp, "Watershed", "");
			IJ.run(imp, "Set Measurements...", "area centroid redirect=None decimal=3");
			IJ.run(imp, "Analyze Particles...", "size="+size+" show=Outlines display exclude clear add");
			
			RoiManager ROI3 = RoiManager.getInstance();
			
			impCopy3.show();
			IJ.selectWindow("DUP_"+main3);
			if (ROI3 != null) {
				ROI3.runCommand("Show All without labels");
				IJ.run(impCopy3, "From ROI Manager", "");
			}

			IJ.saveAs("PNG", folderPath +"\\"+Nom3+ "RESULTS.png");
			WindowManager.closeAllWindows();
		break;
		}
	}
	//test git 2

}

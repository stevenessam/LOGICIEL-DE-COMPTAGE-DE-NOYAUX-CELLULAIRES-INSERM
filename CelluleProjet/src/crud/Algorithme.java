package crud;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

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
		String date = "";
		try {
			PreparedStatement pst = conn.prepareStatement(sqlGetDate);
			pst.setInt(1, idEssai);
			ResultSet rs = pst.executeQuery();
			while (rs.next()) {
				date = rs.getString("date");
			}
		} catch (Exception e) {
			return;
		}
		
		
		String trueDate = date.replace(":", "-");
		Path currentRelativePath = Paths.get("");
		String s = currentRelativePath.toAbsolutePath().toString();
		String folderPath = s + "\\imageProcessing\\Résultats\\"+trueDate;
		
		switch (idAlgo) {
		case 1:
			
			ResultsTable RT1 = ResultsTable.getResultsTable();
			imp.show();
			String main1 = imp.getTitle();
			String Nom1 = main1.substring(0, main1.lastIndexOf('.'));
			ImagePlus impCopy = imp.duplicate();
			IJ.selectWindow(main1);
			
			IJ.run(imp, "Subtract Background...", "rolling=12");
			IJ.run(imp, "8-bit", "");
			IJ.setAutoThreshold(imp, "Default dark");
			//IJ.run(imp, "Threshold...", "");
			IJ.setThreshold(imp, 25, 255);
			IJ.run(imp, "Convert to Mask", "");
			IJ.run(imp, "Fill Holes", "");
			IJ.run(imp, "Convert to Mask", "");
			IJ.run(imp, "Watershed", "");
			IJ.run(imp, "Set Measurements...", "area centroid redirect=None decimal=3");
			IJ.run(imp, "Analyze Particles...", "size=4-Infinity show=Outlines display exclude clear add");
			
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
			ResultsTable RT2 = ResultsTable.getResultsTable();
			imp.show();
			String main2 = imp.getTitle();
			String Nom2 = main2.substring(0, main2.lastIndexOf('.'));
			ImagePlus impCopy2 = imp.duplicate();
			IJ.selectWindow(main2);
			
			IJ.run(imp, "Subtract Background...", "rolling=12");
			IJ.run(imp, "8-bit", "");
			IJ.setAutoThreshold(imp, "Default dark");
			//IJ.run(imp, "Threshold...", "");
			IJ.setThreshold(imp, 25, 255);
			IJ.run(imp, "Convert to Mask", "");
			IJ.run(imp, "Fill Holes", "");
			IJ.run(imp, "Convert to Mask", "");
			IJ.run(imp, "Set Measurements...", "area centroid redirect=None decimal=3");
			IJ.run(imp, "Analyze Particles...", "size=4-Infinity show=Outlines display exclude clear add");
			
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
			ResultsTable RT3 = ResultsTable.getResultsTable();
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
			IJ.setThreshold(imp, 140, 208, "Red");
			Prefs.blackBackground = false;
			IJ.run(imp, "Convert to Mask", "");
			IJ.run(imp, "Watershed", "");
			IJ.run(imp, "Set Measurements...", "area centroid redirect=None decimal=3");
			IJ.run(imp, "Analyze Particles...", "size=2-75 show=Outlines display exclude clear add");
			
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

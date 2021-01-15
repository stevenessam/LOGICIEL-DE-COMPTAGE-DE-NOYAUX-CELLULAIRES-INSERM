package crud;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.swing.JOptionPane;

import ij.IJ;


import ij.ImagePlus;
import ij.measure.ResultsTable;
import ij.plugin.frame.RoiManager;
import sampleQueries.DB.mysqlconnect;
import ij.WindowManager;


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
			String main = imp.getTitle();
			String Nom = main.substring(0, main.lastIndexOf('.'));
			ImagePlus impCopy = imp.duplicate();
			IJ.selectWindow(main);
			
			IJ.run(imp, "Subtract Background...", "rolling=12");
			IJ.run(imp, "8-bit", "");
			IJ.setAutoThreshold(imp, "Default dark");
			IJ.run(imp, "Threshold...", "");
			IJ.setThreshold(imp, 25, 255);
			IJ.run(imp, "Convert to Mask", "");
			IJ.run(imp, "Fill Holes", "");
			IJ.run(imp, "Convert to Mask", "");
			IJ.run(imp, "Close", "");
			IJ.run(imp, "Watershed", "");
			IJ.run(imp, "Set Measurements...", "area mean min centroid redirect=None decimal=3");
			IJ.run(imp, "Analyze Particles...", "size=4-Infinity show=Outlines display exclude clear add");
			
			RoiManager ROI = RoiManager.getInstance();
			
			impCopy.show();
			IJ.selectWindow("DUP_"+main);
			ROI.runCommand("Show All without labels");
			IJ.run(impCopy, "From ROI Manager", "");
			IJ.saveAs("PNG", folderPath +"\\"+Nom+ "RESULTS.png");
			WindowManager.closeAllWindows();
			
		break;
		}
	}
	//test git 2

}

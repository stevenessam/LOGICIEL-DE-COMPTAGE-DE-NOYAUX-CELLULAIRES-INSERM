package crud;

import ij.IJ;

import ij.ImagePlus;
import ij.measure.ResultsTable;
import ij.plugin.frame.RoiManager;


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

	
	public void ExecuteAlgorithm(int idAlgo, ImagePlus imp) {
		switch (idAlgo) {
		case 1:
			
			ResultsTable RT1 = ResultsTable.getResultsTable();
			String main = imp.getTitle();
			String Nom = main.substring(0, main.lastIndexOf('.'));
			IJ.run(imp, "Duplicate...", "title="+Nom);
			IJ.selectWindow(main);
			

			IJ.run(imp, "Subtract Background...", "rolling=12");
			IJ.run(imp, "8-bit", "");
			IJ.setAutoThreshold(imp, "Default dark");
			IJ.run(imp, "Threshold...", "");
			IJ.setThreshold(imp, 25, 255);
			IJ.run(imp, "Convert to Mask", "");
			IJ.run(imp, "Fill Holes", "");
			IJ.run(imp, "Convert to Mask", "");
			IJ.run(imp, "Watershed", "");
			IJ.run(imp, "Set Measurements...", "area mean min centroid redirect=None decimal=3");
			IJ.run(imp, "Analyze Particles...", "size=4-Infinity show=Outlines display exclude clear add");
			
			
			RoiManager ROI = RoiManager.getInstance();
			IJ.selectWindow(Nom);
			ROI.runCommand("Show All without labels");
			IJ.run(imp, "From ROI Manager", "");
			IJ.saveAs("PNG", "C:/Users/Cinna/Desktop/" +Nom+ " RESULTS.png");
		break;
		}
	}
	//test git 2

}

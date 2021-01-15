package imageProcessing;

import java.awt.List;
import java.io.File;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

import crud.Algorithme;
import crud.Amas;
import ij.IJ;
import ij.ImagePlus;
import ij.io.Opener;
import ij.measure.ResultsTable;


public class testImageJ {

	public static void main(String[] args) {
		
		Path currentRelativePath = Paths.get("");
		String s = currentRelativePath.toAbsolutePath().toString();
		
		Opener opener = new Opener();  
		String imageFilePath = s + "\\Images\\Capture.png";
		ImagePlus imp = IJ.openImage(imageFilePath);
		
		
		//Macro_Runner test_runner = new Macro_Runner();
		//test_runner.run(path);
		
		Algorithme algo = new Algorithme();
		//algo.ExecuteAlgorithm(1, imp);
		
		ResultsTable RT1 = ResultsTable.getResultsTable();
		int rowNbr = RT1.getCounter();

		
		//IJ.saveAs(imp, "PNG", s+"\\Images\\Result");
	}
}

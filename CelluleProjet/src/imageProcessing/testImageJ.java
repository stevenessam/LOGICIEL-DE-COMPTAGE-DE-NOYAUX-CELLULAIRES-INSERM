package imageProcessing;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;

import crud.Algorithme;
import ij.IJ;
import ij.ImagePlus;
import ij.io.Opener;
import ij.macro.Interpreter;
import ij.plugin.Macro_Runner;


public class testImageJ {

	public static void main(String[] args) {
		
		Path currentRelativePath = Paths.get("");
		String s = currentRelativePath.toAbsolutePath().toString();
		String filename = "MacroWorkWell1";
		String path = s + "\\macros\\"+filename;
		
		Opener opener = new Opener();  
		String imageFilePath = s + "\\Images\\Capture.png";
		ImagePlus imp = opener.openImage(imageFilePath);
		
		//Macro_Runner test_runner = new Macro_Runner();
		//test_runner.run(path);
		
		Algorithme algo = new Algorithme();
		algo.algorithme1(imp);
		IJ.saveAs(imp, "PNG", s+"\\Images\\Result");
	}
}

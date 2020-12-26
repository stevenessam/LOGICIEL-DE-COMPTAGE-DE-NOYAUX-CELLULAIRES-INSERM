package imageProcessing;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;


import ij.macro.Interpreter;
import ij.plugin.Macro_Runner;


public class testImageJ {

	public static void main(String[] args) {
		
		Path currentRelativePath = Paths.get("");
		String s = currentRelativePath.toAbsolutePath().toString();
		String filename = "Hello_World.ijm";
		String filename2 = "MacroWorkWell1";
		
		String path = s + "\\macros\\"+filename;

		Macro_Runner test_runner = new Macro_Runner();
		for (int i = 0; i < 2; i++) {
			if (i % 2 == 0) {
				path = s + "\\macros\\"+filename;
			} else {
				path = s + "\\macros\\"+filename2;
			}
			
			test_runner.run(path);
			System.out.println(i+"\n\n");
		}
	}

}

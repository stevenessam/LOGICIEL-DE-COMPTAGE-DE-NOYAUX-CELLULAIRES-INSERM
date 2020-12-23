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
		String path = s + "\\Macros\\Hellow_World.ijm";
		

		Macro_Runner test_runner = new Macro_Runner();
		test_runner.runMacroFile("Hello_World.ijm", "");
	}

}

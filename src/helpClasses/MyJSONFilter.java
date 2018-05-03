package helpClasses;

import java.io.File;

import javax.swing.filechooser.FileFilter;

public class MyJSONFilter extends FileFilter{

	@Override
	public boolean accept(File f) {
		String fileName = f.getName();
		return fileName.endsWith(".json");
	}

	@Override
	public String getDescription() {
		return "*.json";
	}

}

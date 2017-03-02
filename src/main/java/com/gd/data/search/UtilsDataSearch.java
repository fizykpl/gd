package com.gd.data.search;

import java.io.File;
import java.io.FilenameFilter;
import java.util.HashMap;

public class UtilsDataSearch {

	public static HashMap<String, File> getInputFiles(final String pattern) {
		File hackDir = new File("Hack").getAbsoluteFile().getParentFile();

		FilenameFilter fileNameFilter = new FilenameFilter() {
			public boolean accept(File dir, String name) {
				if (name.contains(pattern)) {
					return true;
				} else {
					return false;
				}
			}
		};
		
		File[] listFiles = hackDir.listFiles(fileNameFilter);
		HashMap<String, File> out = new HashMap<String, File>();
		for (File file : listFiles) {
			String name = file.getName();
			String key = name.replace(pattern, "");
			out.put(key, file);
		}	

		return out;

	}

}

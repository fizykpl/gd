package com.gd.data.transform;

import java.io.File;
import java.io.FilenameFilter;

public class UtilsDataTransform {
	
	


	public static File[] getFilePattern(String pattern){
		File hackDir = new File("Hack").getAbsoluteFile().getParentFile();
		return getFilePattern(hackDir, pattern);
	}
	
	public static File[] getFilePattern(File dir, final String pattern){
		 FilenameFilter fileNameFilter = new FilenameFilter() {
			   public boolean accept(File dir, String name) {
	            	if(name.contains(pattern)){
	            		return true;
	            	}else{
	            		return false;
	            	}
	            }
	         };
		return dir.listFiles(fileNameFilter);
	}

}

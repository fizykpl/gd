package com.gd.data.search;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.logging.Logger;

public class DataSearch {
	public static Logger LOGGER = Logger.getLogger(DataSearch.class.getName());
	private int limitCahceIds = 10;
	private HashMap<String,File> inputFiles;
	private LinkedHashMap<String, List<String>> cacheIds;
	
	

	public DataSearch(HashMap<String,File> inputFiles) {
		this.inputFiles = inputFiles;
		this.cacheIds = new LinkedHashMap<String, List<String>>();
	}
	
	public List<String> getIDs(String attribute){
		long time = System.nanoTime();
		if(cacheIds.containsKey(attribute)){
			time = System.nanoTime()-time;
			LOGGER.info("attribute " + attribute +" getIDs (cache) time = " + (time*1.0)*10e-10 + " [s]");			
			return cacheIds.get(attribute);
			 
		}else{
			if(cacheIds.size() > limitCahceIds){
				deleteLast();
			}
			List<String> newID = readIDs(attribute);
			cacheIds.put(attribute, newID);
			time = System.nanoTime()-time;
			LOGGER.info("attribute " + attribute +" getIDs (file) time = " + (time*1.0)*10e-10 + " [s]");		
			return newID;
		}
		
		
	}

	private List<String> readIDs(String attribute)  {
		List<String> out = new ArrayList<String>();
		File file = inputFiles.get(attribute);
		if(!file.exists()){
			return new ArrayList<String>();
		}
		
		try {
			FileInputStream fstream = new FileInputStream(file);
			BufferedReader br = new BufferedReader(new InputStreamReader(fstream));
			String line;
			while ((line = br.readLine()) != null) {
				out.add(line);
			}
			br.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

		
		return out;
	}

	private void deleteLast() {
		String oldSql = cacheIds.keySet().iterator().next();
		cacheIds.remove(oldSql);		
		System.out.println("Delete form cache " + oldSql);
	}
	
	
	
	


}

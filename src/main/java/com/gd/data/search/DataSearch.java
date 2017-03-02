package com.gd.data.search;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Set;

import com.gd.data.model.InputModel;

public class DataSearch {
	
	private int limitCahceIds = 10;
	private HashMap<String,File> inputFiles;
	private LinkedHashMap<String, List<String>> cacheIds;
	
	

	public DataSearch(HashMap<String,File> inputFiles) {
		this.inputFiles = inputFiles;
		this.cacheIds = new LinkedHashMap<String, List<String>>();
		
//		cacheIds.r
	}
	
	public List<String> getIDs(String attribute){
		long time = System.nanoTime();
		if(cacheIds.containsKey(attribute)){
			System.out.println("attribute " + attribute +" getIDs (cache) time = " + (System.nanoTime() -time) + "[ns]");
			return cacheIds.get(attribute);
			 
		}else{
			if(cacheIds.size() > limitCahceIds){
				deleteLast();
			}
			List<String> newID = readIDs(attribute);
			cacheIds.put(attribute, newID);
			System.out.println("attribute " + attribute +" getIDs (file) time = " + (System.nanoTime() -time) + "[ns]");
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

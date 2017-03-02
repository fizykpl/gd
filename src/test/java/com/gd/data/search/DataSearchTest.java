package com.gd.data.search;

import static com.gd.data.search.UtilsDataSearch.*;
import static org.junit.Assert.*;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Set;

import org.junit.Test;

import com.gd.data.transform.DataTransform;
import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import com.google.common.collect.Sets.SetView;

public class DataSearchTest {

	@Test
	public void cacheTest() {
		int limit = 10;
		LinkedHashMap<String, List<String>> cacheIds = new LinkedHashMap<String, List<String>>();
		cacheIds.put("1", null);
		cacheIds.put("2", null);
		assertTrue(cacheIds.size() ==2 );
		assertTrue(cacheIds.keySet().iterator().next().equals("1"));
		
		String oldSql = cacheIds.keySet().iterator().next();
		cacheIds.remove(oldSql);
		assertTrue(cacheIds.size() == 1 );
		assertTrue(cacheIds.keySet().iterator().next().equals("2"));
		
		cacheIds.put("3", null);
		assertTrue(cacheIds.size() == 2 );
		assertTrue(cacheIds.keySet().iterator().next().equals("2"));
		
		oldSql = cacheIds.keySet().iterator().next();
		cacheIds.remove(oldSql);
		
		assertTrue(cacheIds.size() == 1 );
		assertTrue(cacheIds.keySet().iterator().next().equals("3"));
		
		cacheIds.put("4", null);
		
		assertTrue(cacheIds.size() == 2 );
		assertTrue(cacheIds.keySet().iterator().next().equals("3"));
		

		


	}

	@Test
	public void searchTest() {
		HashMap<String,File> filePattern = getInputFiles("1488404818130_1488376600246_1000000_500_0,25_0,1_");
		 DataSearch ds = new DataSearch(filePattern);
		 
		 List<String> iDs0 = ds.getIDs("0");
		 List<String> iDs1 = ds.getIDs("1");
//		 List<String> iDs2 = ds.getIDs("2");
//		 List<String> iDs3 = ds.getIDs("3");
//		 List<String> iDs4 = ds.getIDs("4");
//		 List<String> iDs5 = ds.getIDs("5");
//		 List<String> iDs6 = ds.getIDs("6");
//		 List<String> iDs7 = ds.getIDs("7");
//		 List<String> iDs8 = ds.getIDs("8");
//		 List<String> iDs9 = ds.getIDs("9");
//		 List<String> iDs10 = ds.getIDs("10");
//		 List<String> iDs11 = ds.getIDs("11");
//		 List<String> iDs11a = ds.getIDs("11");
//		 List<String> iDs11b = ds.getIDs("11");
		 
		 //And
		 long time = System.nanoTime();
		 Set<String> intersection = new HashSet<String>(iDs0);
		 intersection.removeAll(iDs1);	 
		 
		 System.out.println(System.nanoTime() -time + " [ns]");
		 List<String> view = new ArrayList<String>(intersection);
		 Collections.sort(view);
		 System.out.println("End");
		 



	}

}

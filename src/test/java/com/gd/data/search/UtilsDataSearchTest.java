package com.gd.data.search;

import static org.junit.Assert.*;

import java.io.File;
import java.util.HashMap;

import static com.gd.data.search.UtilsDataSearch.*;
import org.junit.Test;

public class UtilsDataSearchTest {
	
	@Test
	public void dataSearchTest(){
		String pattern = "1488404818130_1488376600246_1000000_500_0,25_0,1_";
		HashMap<String, File> inputFiles = getInputFiles(pattern);
		
		DataSearch ds = new DataSearch(inputFiles);
		
		
	}

	@Test
	public void getInputFilesTest() {
		String pattern = "1488404818130_1488376600246_1000000_500_0,25_0,1_";
		HashMap<String, File> inputFiles = getInputFiles(pattern);
		
		assertTrue(inputFiles.size() == 500);
	}

}

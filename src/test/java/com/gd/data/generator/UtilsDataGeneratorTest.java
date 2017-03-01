package com.gd.data.generator;

import static org.junit.Assert.*;
import static com.gd.data.generator.UtilsDataGenerator.*;
import java.io.File;


import org.junit.Test;

public class UtilsDataGeneratorTest {
	
	long line = 1000000;
	int attribute = 500;
	double mi = 0.25;
	double sigma = 0.1;
	
	
	@Test 
	public void generateDataTest(){
			File[] generateData = generateData(line, attribute, mi, sigma);
			for (File file : generateData) {
				file.delete();
				System.out.println("delete file " + file.toString());
			}

		
	}
	
	@Test 
	public void getNewFileTest(){

		
		File newFile = getNewFile(line,attribute, mi, sigma);
		String expected = line+"_"+attribute+"_"+mi+"_"+sigma;
		expected = expected.replace(".", ",");
		assertTrue(newFile.toString().contains(expected));
		
	}

	@Test
	public void createFileTest() {

		File f = getNewFile(line,attribute, mi, sigma);
		
		assertTrue("File should be created", createFile(f));
		assertFalse("File shouldn't be overwrite", createFile(f));
		
	}

}

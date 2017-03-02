package com.gd.data.transform;
import static com.gd.data.transform.UtilsDataTransform.*;

import java.io.File;
import java.io.IOException;

import static org.junit.Assert.*;
import org.junit.Test;

public class DataTransformTest {
	
	@Test
	public void transformTest(){
		File[] filePattern = getFilePattern("1488488964794");
		DataTransform df = new DataTransform(filePattern);
		try {
			df.execute();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	@Test
	public void getFilePatternTest(){
		File[] filePattern = getFilePattern("1488488964794");
		assertTrue(filePattern.length==4);		
	}
	
	

}

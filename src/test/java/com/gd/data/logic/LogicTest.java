package com.gd.data.logic;

import static com.gd.data.search.UtilsDataSearch.getInputFiles;
import static org.junit.Assert.*;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.junit.Test;

import com.gd.data.search.DataSearch;

public class LogicTest {

	@Test
	public void logicTest() {
		
		List<String> a1 = new ArrayList<String>();
		a1.add("1");
		a1.add("2");
		a1.add("3");
		a1.add("4");
		List<String> a2 = new ArrayList<String>();
		a2.add("1");
		a2.add("2");
		a2.add("3");
		a2.add("5");
		
		Logic l = new Logic();
		l.add(a1);
		l.add(a2);
		
		// Logical and
		List<String> and = l.and();
		//Logical or
		List<String> or = l.or();
		
		assertTrue(and.size()==3);
		assertTrue(and.get(0).equals("1"));
		assertTrue(and.get(1).equals("2"));
		assertTrue(and.get(2).equals("3"));
		
		assertTrue(or.size()==5);
		assertTrue(or.get(0).equals("1"));
		assertTrue(or.get(1).equals("2"));
		assertTrue(or.get(2).equals("3"));
		assertTrue(or.get(3).equals("4"));
		assertTrue(or.get(4).equals("5"));
		
 	}
	
	@Test
	public void logicTest2() {
		HashMap<String,File> filePattern = getInputFiles("1488404818130_1488376600246_1000000_500_0,25_0,1_");
		DataSearch ds = new DataSearch(filePattern);
		 
		List<String> iDs0 = ds.getIDs("0");
		List<String> iDs1 = ds.getIDs("1");
		
		Logic l = new Logic();
		l.add(iDs0);
		l.add(iDs1);
		
		List<String> and = l.and();
		List<String> or = l.or();
		System.out.println("end");
		
		
	}

}

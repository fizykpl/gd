package com.gd.data.generator;

import static org.junit.Assert.*;

import org.junit.Test;

import com.google.common.primitives.Booleans;

public class GenaratorTest {


	
	@Test
	public void getNextTest() {
		int n = 100;
		Generator g = new Generator(500, 0.25, 0.1);
		boolean[] next = g.getNext(100);
		
		assertTrue(Booleans.countTrue(next)==n);
	}

}

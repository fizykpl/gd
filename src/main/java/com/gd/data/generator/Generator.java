package com.gd.data.generator;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import org.apache.commons.math3.random.RandomDataGenerator;

import com.google.common.primitives.Booleans;;

public class Generator {
	private static RandomDataGenerator rdg;
	private static Random r;
	public final int nAttributes;
	public final double mi;
	public final double sigma;
	private int maxLine = 1000000;


	public Generator(int nAttributes, double mi, double sigma) {
		this.nAttributes = nAttributes;
		this.mi = mi;
		this.sigma = sigma;
		this.rdg = new RandomDataGenerator();
		this.r = new Random();
	}

	
	public boolean[] getNext() {
		return getNext(getNTrue());
	}
	
	
	public boolean[] getNext(int n) {
		if(n < 0 || n > nAttributes){
			n = (int) (sigma * nAttributes);
		}
		boolean[] attributes = new boolean[nAttributes];
		Arrays.fill(attributes, 0, n, true);		
		List<Boolean> asList = Booleans.asList(attributes);
		Collections.shuffle(asList);
		return Booleans.toArray(asList);
	}

	private int getNTrue() {
		double nextGaussian = rdg.nextGaussian(mi, sigma);		
		int nTrue = (int) Math.round(nAttributes*nextGaussian);
		return nTrue;
	}

}

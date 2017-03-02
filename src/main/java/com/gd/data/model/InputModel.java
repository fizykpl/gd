package com.gd.data.model;

import java.util.ArrayList;
import java.util.List;
import com.google.common.primitives.Ints;

public class InputModel {

	public int id;
	public String[] attributes;

	public InputModel(String id, String... attributes) {
		this.id = Integer.parseInt(id);
		this.attributes = prepate(attributes);

	}

	private String[] prepate(String[] attributes) {
		List<String> ret =  new ArrayList<String>(); 
		for(int i = 0; i < attributes.length; i++){
			if(!attributes[i].isEmpty()){
				ret.add(Integer.toString(i));
			}
		}
		
		return (String[]) ret.toArray(new String[ret.size()]);
	}

}

package com.gd.data.logic;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

public class Logic {
	
//	Map<String, List<String>> attributes;
	Map<String, Integer> counts; 
	int amountList;
	
	
	public Logic(){
		this.counts = new HashMap<String, Integer>();
	}
	
	public void add(List<String> ids){
		long time = System.nanoTime();
		this.amountList++;
		for (String id : ids) {
			if(counts.containsKey(id)){
				int count = counts.get(id);
				count +=1;
				counts.put(id, count);
			}else{
				counts.put(id, 1);
			}
		}
		time =System.nanoTime() - time;
		System.out.println("Add time: count= " + ids.size() + " time= " + time+ "[ns]");
			
	}
	
	public void remove(List<String> ids){
		long time = System.nanoTime();
		this.amountList--;
		for (String id : ids) {
			if(!counts.containsKey(id)){
				continue;
			}
			
			if(counts.get(id)>0){
				int count = counts.get(id);
				count -= 1;
				counts.put(id, count);
			}else{
				counts.remove(id);
			}
		}
		time =System.nanoTime() - time;
		System.out.println("Remove time: count= " + ids.size() + " time= " + time+ "[ns]");
	}
	

	
	public List<String> and(){
		long time = System.nanoTime();
		List<String> out = new ArrayList<String>();		
		for (Entry<String, Integer> e : counts.entrySet()) {
			if(e.getValue()==amountList){
				out.add(e.getKey());
			}					
		}
		
		time =System.nanoTime() - time;
		System.out.println("And time: count= " + out.size() + " time= " + time+ "[ns]");
		return out;		
	}
	
	public List<String> or(){
		long time = System.nanoTime();
		ArrayList<String> out = new ArrayList<String>(counts.keySet());
		
		time =System.nanoTime() - time;
		System.out.println("Or time: count= " + out.size() + " time= " + time + "[ns]");
		return out;		
	}

}

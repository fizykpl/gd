package com.gd.data.generator;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Logger;

import com.google.common.primitives.Booleans;



public class UtilsDataGenerator {
	
	public static Logger LOGGER = Logger.getLogger(UtilsDataGenerator.class.getName());
	
	public static int maxLine = 250000;
	
	public static File[] generateData(long nline,int nAttributes, double mi, double sigma){
		int countFile = 1;
		List<File> files = new ArrayList<File>();
		File file = getNewFile(nline, nAttributes, mi, sigma);
		files.add(file);
		if(createFile(file)){
			try  {
				BufferedWriter bw = new BufferedWriter(new FileWriter(file),4096);
				Generator g = new Generator(nAttributes, mi, sigma);
				
				for(int i = 0; i < nline ; i ++){
					if(i%maxLine == 0 && i!=0){
						bw.close();
						
						File newFile = new File(file.getParentFile(), file.getName()+"_part_"+ ++countFile);
						files.add(newFile);
						System.out.println("new file " +  newFile.toString());
						bw = new BufferedWriter(new FileWriter(newFile));
					}
					String attributes = prepareAttributes(g.getNext());
					

//					System.out.println(string);
					//write to file
					bw.write(Integer.toString(i));
					bw.write(",");
					bw.write(attributes);
					bw.write("\n");
//					System.out.println(i);
				}

				

				bw.close();
				

			} catch (IOException e) {
				LOGGER.warning(e.getMessage());
				e.printStackTrace();

			}
		}else{
			LOGGER.info("The application don't  generate data");
		}
		return (File[]) files.toArray(new File[files.size()]);
	}
	
	
	private static String prepareAttributes(boolean[] next) {
		String string = Arrays.toString(next);
		string = string.replace(" ", "");
		string = string.substring(1, string.length()-1);
		string = string.replace("true", "1");
		string = string.replace("false", "");
		return string;
	}


	public static File getNewFile(long nline,int nAttribute, double mi, double sigma){
		long timeStamp = System.currentTimeMillis();
		StringBuilder sb = new StringBuilder();
		sb.append(timeStamp).append("_");
		sb.append(nline).append("_").append(nAttribute).append("_").append(mi).append("_").append(sigma);
		
		return new File(sb.toString().replace(".", ","));
		
	}
	public static boolean createFile(File outPut){
		
		if(outPut.exists()){
			LOGGER.info("Application does not support overwrite file.");
			return false;
		}
		
		try {
			return outPut.createNewFile();
		} catch (IOException e) {
			LOGGER.warning(e.getMessage());
			e.printStackTrace();
			return false;
		}
		
	}
	
	

}

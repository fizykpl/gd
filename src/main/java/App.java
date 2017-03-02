import static com.gd.data.generator.UtilsDataGenerator.generateData;

import java.io.File;
import java.io.IOException;
import java.util.logging.Logger;

import com.gd.data.generator.UtilsDataGenerator;
import com.gd.data.transform.DataTransform;
import com.gd.data.transform.UtilsDataTransform;


public class App {
	public static Logger LOGGER = Logger.getLogger(App.class.getName());
//	public static Logger LOGGER = Logger.getLogger(.class.getName());
	public static void main(String[] args) throws InterruptedException {
		// Generate Data		
		long line = 1000000;
		int attribute = 500;
		double mi = 0.25;
		double sigma = 0.1;
		LOGGER.info("Start generate data");
		long time = System.nanoTime();		
		File[] filesData = UtilsDataGenerator.generateData(line, attribute, mi, sigma);
		time = System.nanoTime()-time;
		LOGGER.info("Time generate data: " + (time*1.0)*10e-10 + " [s]");
		
		
		//Transform Data

//		String pattern = filesData[0].getName();
//		File[] filesTransform = UtilsDataTransform.getFilePattern(pattern);
		
		DataTransform df = new DataTransform(filesData);
		try {
			df.execute();
		} catch (IOException e) {
			LOGGER.warning("IOException: "+ e.getMessage());
			e.printStackTrace();
		}

		
		
		//Remove Files
		for (File file : filesData) {
			file.delete();
			System.out.println("delete file " + file.toString());
		}
		

		
		

	}

}

import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.logging.Logger;

import com.gd.data.generator.UtilsDataGenerator;
import com.gd.data.logic.Logic;
import com.gd.data.search.DataSearch;
import com.gd.data.search.UtilsDataSearch;
import com.gd.data.transform.DataTransform;

public class App {
	public static Logger LOGGER = Logger.getLogger(App.class.getName());

	// public static Logger LOGGER = Logger.getLogger(.class.getName());
	public static void main(String[] args) throws InterruptedException {
		// Generate Data
		// long line = 1000000;
		// int attribute = 500;
		// double mi = 0.25;
		// double sigma = 0.1;
		// LOGGER.info("Start generate data");
		// long time = System.nanoTime();
		// File[] filesData = UtilsDataGenerator.generateData(line, attribute,
		// mi, sigma);
		// time = System.nanoTime()-time;
		// LOGGER.info("Time generate data: " + (time*1.0)*10e-10 + " [s]");
		//
		//
		// //Transform Data
		// DataTransform df = new DataTransform(filesData);
		// try {
		// df.execute();
		// } catch (IOException e) {
		// LOGGER.warning("IOException: "+ e.getMessage());
		// e.printStackTrace();
		// }

		// Search data
		HashMap<String, File> inputFiles = UtilsDataSearch
				.getInputFiles("1488489052844_1488488964794_1000000_500_0,25_0,1_");
		DataSearch ds = new DataSearch(inputFiles);
		// read from file
		List<String> iDs = ds.getIDs("1");
		List<String> iDs2 = ds.getIDs("100");
		List<String> iDs3 = ds.getIDs("300");
		// read from cache
		List<String> iDs4 = ds.getIDs("300");

		// Logic

		Logic l1 = new Logic();
		l1.add(iDs);
		l1.add(iDs2);
		List<String> and = l1.and();
		List<String> or = l1.or();

		// Lexicographical order to better human eye compare

		MyComparator c = new MyComparator();
		Collections.sort(and, c);
		Collections.sort(or, c);

		System.out.println("A" + iDs.toString());
		System.out.println("B" + iDs2.toString());
		System.out.println("A & B" + and.toString());
		System.out.println("A | B" + or.toString());

		Logic l2 = new Logic();
		l2.add(iDs3);
		l2.add(iDs2);
		l2.add(iDs);
		List<String> and2 = l2.and();
		List<String> or2 = l2.or();
		
		Collections.sort(and2, c);
		Collections.sort(or2, c);

		System.out.println("A" + iDs3.toString());
		System.out.println("B" + iDs2.toString());
		System.out.println("C" + iDs.toString());
		System.out.println("A & B & C" + and2.toString());
//		System.out.println("A | B | C" + or2.toString());

		LOGGER.info("Finish");
	}

}

class MyComparator implements Comparator<String> {

	public int compare(String o1, String o2) {
		return new Integer(o1).compareTo(new Integer(o2));
	}
}

package com.gd.data.transform;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;
import java.util.logging.Logger;

import com.gd.data.model.InputModel;

public class DataTransform {
	public static Logger LOGGER = Logger.getLogger(DataTransform.class.getName());
	private File[] inputFile;
	private String outputFileNamePattern;
	private HashMap<String, File> outputFiles;
	private HashMap<File, List<String>> outputIDs;

	public DataTransform(File[] inputFile) {
		this.inputFile = inputFile;
		this.outputFiles = new HashMap<String, File>();
		this.outputIDs = new HashMap<File, List<String>>();
	}

	public void execute() throws IOException {
		this.outputFileNamePattern = prepareOutputFileNamePattern();
		for (File file : inputFile) {

			FileInputStream fstream = new FileInputStream(file);
			BufferedReader br = new BufferedReader(new InputStreamReader(fstream));
			String line;

			while ((line = br.readLine()) != null) {
				InputModel model = getModel(line);
				transform(model);
			}
			save();
			br.close();
		}
		save();

	}

	private String prepareOutputFileNamePattern() {
		String timestamp = Long.toString(System.currentTimeMillis());
		StringBuilder sb = new StringBuilder();
		String sufix = inputFile[0].getName().split("_part")[0];
		sb.append(timestamp).append("_").append(sufix);
		sb.append("_%s");
		return sb.toString();
	}

	private void transform(InputModel model) {
		LOGGER.info("Transform id: " + model.id);
		int size = model.attributes.length;
		for (int i = 0; i < size; i++) {
			checkFileAttribute(model, i);
		}
		writeId(model);

	}

	private void writeId(InputModel model) {
		int size = model.attributes.length;
		for (int i = 0; i < size; i++) {
			File file = outputFiles.get(model.attributes[i]);
			List<String> list = outputIDs.get(file);
			list.add(Integer.toString(model.id));
			if (list.size() > 50000) {
				save();
			}
			// try {
			// BufferedWriter bw = new BufferedWriter(new FileWriter(file,
			// true), 4096);
			// bw.write(Integer.toString(model.id));
			// bw.write("\n");
			// bw.close();
			// } catch (IOException e) {
			// e.printStackTrace();
			// }
		}

	}

	private void save() {
		System.out.println("Save");
		for (Entry<File, List<String>> entry : outputIDs.entrySet()) {
			File key = entry.getKey();
			List<String> value = entry.getValue();
			try {
				BufferedWriter bw = new BufferedWriter(new FileWriter(key, true), 4096);
				for (String string : value) {
					bw.write(string);
					bw.write("\n");
				}
				bw.close();
				outputIDs.put(key, new ArrayList<String>());
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

	}

	private void checkFileAttribute(InputModel model, int i) {
		if (!outputFiles.containsKey(model.attributes[i])) {
			String id = model.attributes[i];
			String format = String.format(outputFileNamePattern, model.attributes[i]);
			File file = new File(format);
			outputFiles.put(id, file);
			outputIDs.put(file, new ArrayList<String>());
		}
	}

	private InputModel getModel(String line) {
		String[] split = line.split(",", -1);
		String[] attributes = Arrays.copyOfRange(split, 1, split.length);

		return new InputModel(split[0], attributes);

	}

}

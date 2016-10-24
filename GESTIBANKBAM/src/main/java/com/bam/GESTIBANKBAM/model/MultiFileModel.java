package com.bam.GESTIBANKBAM.model;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class MultiFileModel {
	private static final int DEFAULT_CAPACITY = 3;
	public static final int CAPACITY;
	private static final Properties config;
	private static final String CONFIG_FILE_NAME = "/bambank/upload.properties";

	static {
		config = new Properties();
		try {
			config.load(MultiFileModel.class
					.getResourceAsStream(CONFIG_FILE_NAME));
			config.list(System.out);
			CAPACITY = Integer.parseInt(
					config.getProperty("upload.files.count", 
							DEFAULT_CAPACITY + ""));
		} catch (IOException e) {
			e.printStackTrace(System.err);
			throw new ExceptionInInitializerError(e);
		}
	};

	List<FileModel> files = new ArrayList<FileModel>();

	public MultiFileModel() {
		for (int i = 0; i < CAPACITY; i++) {
			files.add(new FileModel());
		}
	}

	public List<FileModel> getFiles() {
		return files;
	}

	public void setFiles(List<FileModel> files) {
		this.files = files;
	}
}

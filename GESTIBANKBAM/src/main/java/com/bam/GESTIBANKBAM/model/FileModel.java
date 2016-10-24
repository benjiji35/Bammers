package com.bam.GESTIBANKBAM.model;

import org.springframework.web.multipart.MultipartFile;

public class FileModel {
	MultipartFile file;

	public MultipartFile getFile() {
		return file;
	}

	public void setFile(MultipartFile file) {
		this.file = file;
	}

}

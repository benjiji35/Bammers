package com.bam.GESTIBANKBAM.util;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.bam.GESTIBANKBAM.model.FileModel;
import com.bam.GESTIBANKBAM.model.MultiFileModel;

@Component
public class MultiFileValidator implements Validator {

	public boolean supports(Class<?> clazz) {
		return MultiFileModel.class.isAssignableFrom(clazz);
	}

	public void validate(Object obj, Errors errors) {
		MultiFileModel multiModel = (MultiFileModel) obj;

		int index = 0;

		for (FileModel file : multiModel.getFiles()) {
			if (file.getFile() != null) {
				if (file.getFile().getSize() == 0) {
					errors.rejectValue("files[" + index + "].file", "missing.file");
				}
			}
			index++;
		}

	}
}

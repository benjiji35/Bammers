package com.bam.GESTIBANKBAM.util;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.bam.GESTIBANKBAM.model.FileModel;

@Component
public class FileValidator implements Validator {
	public boolean supports(Class<?> clazz) {
        return FileModel.class.isAssignableFrom(clazz);
    }
 
    public void validate(Object obj, Errors errors) {
    	FileModel file = (FileModel) obj;
         
        if(file.getFile()!=null){
            if (file.getFile().getSize() == 0) {
                errors.rejectValue("file", "missing.file");
            }
        }
    }
}


package com.bam.GESTIBANKBAM.dao;

import java.util.List;

import com.bam.GESTIBANKBAM.model.BamFileModel;

public interface BamMetaDAO {
	   
	List<BamFileModel> getBySid(String sid);
	BamFileModel findFileModel(String sid, String dir, String key);

	boolean addFilePath(String sid, String dir, String key);

	void update(String sid, String dir, String key);
	void remove(String sid);
	
}

package com.bam.GESTIBANKBAM.service;

import java.util.List;

import com.bam.GESTIBANKBAM.model.BamFileModel;

public interface BamMetaService {

	List<BamFileModel>	getBySid(String sid);

	boolean addFilePath(String sid, String dir, String key);

	void remove(String sid);
}

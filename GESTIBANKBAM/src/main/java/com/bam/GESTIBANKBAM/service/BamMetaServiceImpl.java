package com.bam.GESTIBANKBAM.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bam.GESTIBANKBAM.dao.BamMetaDAO;
import com.bam.GESTIBANKBAM.model.BamFileModel;

@Service("bamMetaService")
@Transactional
public class BamMetaServiceImpl implements BamMetaService {

	@Autowired
	private static BamMetaDAO bamMetaDAO; 

//	static {
//		// init connection with DB
//		ApplicationContext context = 
//	             new ClassPathXmlApplicationContext("classpath:/bambank/bam-file-model-beans.xml");
//
//		bamMetaDAOImpl = (BamMetaDAOImpl)context.getBean("bamMetaDAOImpl");
//	};
//


	@Override
	public List<BamFileModel> getBySid(String sid) {
		return bamMetaDAO.getBySid(sid);
	}

	@Override
	public boolean addFilePath(String sid, String dir, String key) {
		return bamMetaDAO.addFilePath(sid, dir, key);
	}

	@Override
	public void remove(String sid) {
		bamMetaDAO.remove(sid);
	}
}

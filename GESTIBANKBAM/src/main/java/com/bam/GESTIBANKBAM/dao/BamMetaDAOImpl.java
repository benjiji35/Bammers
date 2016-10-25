package com.bam.GESTIBANKBAM.dao;

import java.util.List;

import javax.persistence.NoResultException;

import org.springframework.stereotype.Repository;

import com.bam.GESTIBANKBAM.model.BamFileModel;

@Repository ("bamMetaDAO")
public class BamMetaDAOImpl extends AbstractDAO<Long, BamFileModel> implements BamMetaDAO {

	@SuppressWarnings("unchecked")
	@Override
	public List<BamFileModel> getBySid(String sid) {
		try {
			return (List<BamFileModel>)getEntityManager()
				.createQuery("SELECT b FROM BamFileModel b WHERE b.sid = :sid ")
				.setParameter("sid", sid)
				.getResultList();
		} catch (NoResultException nre) {
			nre.printStackTrace(System.err);
			return null;
		}
	}

	@Override
	public BamFileModel findFileModel(String sid, String dir, String key) {
		try {
			return (BamFileModel)getEntityManager()
				.createQuery("SELECT b FROM BamFileModel b WHERE b.sid = :sid AND " +
						"b.dir = :dir AND " +
						"b.key = :key ")
				.setParameter("sid", sid)
				.setParameter("dir", dir)
				.setParameter("key", key)
				.getSingleResult();
		} catch (NoResultException nre) {
			nre.printStackTrace(System.err);
			return null;
		}
	}

	@Override
	public boolean addFilePath(String sid, String dir, String key) {
		boolean status = true;

		try {
			BamFileModel b = new BamFileModel();
			b.setSid(sid);
			b.setPathBase(dir);
			b.setKey(key);
			persist(b);
		} catch (Exception e) {
			e.printStackTrace(System.err);
			status = false;
		}
		return status;
	}

	@Override
	public void update(String sid, String dir, String key) {
		BamFileModel b = new BamFileModel();
		b.setSid(sid);
		b.setPathBase(dir);
		b.setKey(key);
		persist(b);		
	}

	@Override
	public void remove(String sid) {
		for (BamFileModel b : getBySid(sid)) {
			delete(b);
		}
	}

//	private DataSource dataSource;
//	private JdbcTemplate jdbcTemplate;
//
//	@SuppressWarnings({ "rawtypes", "unchecked" })
//	@Override
//	public List<Map> getBySid(String sid) {
//		String query = "select * from paths_cfg where sid = ?";
//		List<Map> bfm = jdbcTemplate.query(query, new Object[] { sid },
//				new BeanPropertyRowMapper(BamFileModelMapper.class));
//		return bfm;
//	}
//
//	@Override
//	public BamFileModel findFileModel(String sid, String dir, String key) {
//		String query = "select * from paths_cfg where sid = ? AND " +
//				"path_base = ? AND " +
//				"key = ? AND " +
//				"date = ? ";
//		BamFileModel bfm = jdbcTemplate.queryForObject(query, 
//				BamFileModel.class, 
//				new Object[]{ sid, dir, key});
//		return bfm;
//	}
//
//	@Override
//	public boolean addFilePath(String sid, String dir, String key) {
//		String query = "insert into paths_cfg (sid, path_base, key, date) values (?, ?, ?, ?)";
//
//		jdbcTemplate.update(query, sid, dir, key, new java.sql.Date(new Date().getTime()));
//		return true;
//	}
//
//	@Override
//	public void remove(String sid) {
//		String query = "delete from paths_cfg where sid = ?";
//		jdbcTemplate.update(query, sid);
//	}
//
//	@Override
//	@Autowired
//	public void setDataSource(DataSource ds) {
//		dataSource = ds;
//		jdbcTemplate = new JdbcTemplate(dataSource);
//	}
//
//	@Override
//	public void update(String sid, String dir, String key) {
//		// TODO Auto-generated method stub
//		
//	}
}

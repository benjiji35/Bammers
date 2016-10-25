package com.bam.GESTIBANKBAM.dao.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

import org.springframework.jdbc.core.RowMapper;

import com.bam.GESTIBANKBAM.model.BamFileModel;

public class BamFileModelMapper implements RowMapper<BamFileModel> {

	@Override
	public BamFileModel mapRow(ResultSet rs, int rowNum) throws SQLException {
		BamFileModel bfm = new BamFileModel();
		bfm.setId(rs.getLong("id"));
		bfm.setSid(rs.getString("sid"));
		bfm.setPathBase(rs.getString("path_base"));
		bfm.setKey(rs.getString("key"));
		bfm.setDate(new Date(rs.getDate("date").getTime()));
		return bfm;
	}
}

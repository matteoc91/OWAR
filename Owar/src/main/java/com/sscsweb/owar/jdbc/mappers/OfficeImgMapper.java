package com.sscsweb.owar.jdbc.mappers;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.sscsweb.owar.entities.OfficeImg;

public class OfficeImgMapper implements RowMapper<OfficeImg> {

	public OfficeImg mapRow(ResultSet arg0, int arg1) throws SQLException {
		OfficeImg officeImg = new OfficeImg();
		officeImg.setId(arg0.getInt("id"));
		officeImg.setImg(arg0.getBytes("img"));
		officeImg.setOffice_id(arg0.getInt("office_id"));
		return officeImg;
	}

}

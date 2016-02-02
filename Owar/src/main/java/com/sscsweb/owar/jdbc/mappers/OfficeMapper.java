package com.sscsweb.owar.jdbc.mappers;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.sscsweb.owar.entities.Office;

public class OfficeMapper implements RowMapper<Office> {

	public Office mapRow(ResultSet arg0, int arg1) throws SQLException {
		Office office = new Office();
		office.setAddress(arg0.getString("address"));
		office.setDaily_price(arg0.getDouble("daily_price"));
		office.setDate_begin(arg0.getTimestamp("date_begin"));
		office.setDate_end(arg0.getTimestamp("date_end"));
		office.setDescription(arg0.getString("description"));
		office.setHome_number(arg0.getInt("home_number"));
		office.setId(arg0.getInt("id"));
		office.setLessor_id(arg0.getInt("lessor_id"));
		office.setTbComuni_id(arg0.getInt("tbComuni_id"));
		office.setReserved(arg0.getInt("reserved"));
		return office;
	}

}

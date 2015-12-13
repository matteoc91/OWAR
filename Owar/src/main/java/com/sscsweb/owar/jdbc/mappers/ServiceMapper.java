package com.sscsweb.owar.jdbc.mappers;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.sscsweb.owar.entities.Service;

public class ServiceMapper implements RowMapper<Service> {

	public Service mapRow(ResultSet arg0, int arg1) throws SQLException {
		Service service = new Service();
		service.setDescription(arg0.getString("description"));
		service.setId(arg0.getInt("id"));
		service.setType(arg0.getString("type"));
		return service;
	}

}

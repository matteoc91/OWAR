package com.sscsweb.owar.jdbc.mappers;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.sscsweb.owar.entities.Administrator;

public class AdministratorMapper implements RowMapper<Administrator> {

	public Administrator mapRow(ResultSet arg0, int arg1) throws SQLException {
		Administrator administrator = new Administrator();
		administrator.setId(arg0.getInt("id"));
		administrator.setUser_id(arg0.getInt("user_id"));
		return administrator;
	}

}

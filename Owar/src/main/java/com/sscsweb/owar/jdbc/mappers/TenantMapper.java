package com.sscsweb.owar.jdbc.mappers;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.sscsweb.owar.entities.Tenant;

public class TenantMapper implements RowMapper<Tenant> {

	public Tenant mapRow(ResultSet arg0, int arg1) throws SQLException {
		Tenant tenant = new Tenant();
		tenant.setFeedback_tenant(arg0.getDouble("feedback_tenant"));
		tenant.setId(arg0.getInt("id"));
		tenant.setUser_id(arg0.getInt("user_id"));
		return tenant;
	}

}

package com.sscsweb.owar.jdbc.mappers;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.sscsweb.owar.entities.OfficeHasTenant;

public class OfficeHasTenantMapper implements RowMapper<OfficeHasTenant> {

	public OfficeHasTenant mapRow(ResultSet arg0, int arg1) throws SQLException {
		OfficeHasTenant officeHasTenant = new OfficeHasTenant();
		officeHasTenant.setDate_begin(arg0.getTimestamp("date_begin"));
		officeHasTenant.setDate_end(arg0.getTimestamp("date_end"));
		officeHasTenant.setOffice_id(arg0.getInt("office_id"));
		officeHasTenant.setTenant_id(arg0.getInt("tenant_id"));
		return officeHasTenant;
	}
	
}

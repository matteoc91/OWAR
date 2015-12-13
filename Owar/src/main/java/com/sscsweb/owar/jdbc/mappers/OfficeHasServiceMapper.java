package com.sscsweb.owar.jdbc.mappers;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.sscsweb.owar.entities.OfficeHasService;

public class OfficeHasServiceMapper implements RowMapper<OfficeHasService> {

	public OfficeHasService mapRow(ResultSet arg0, int arg1) throws SQLException {
		OfficeHasService officeHasService = new OfficeHasService();
		officeHasService.setNum_service(arg0.getInt("num_service"));
		officeHasService.setOffice_id(arg0.getInt("office_id"));
		officeHasService.setService_id(arg0.getInt("service_id"));
		return officeHasService;
	}

}

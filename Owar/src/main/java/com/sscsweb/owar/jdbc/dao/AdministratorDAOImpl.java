package com.sscsweb.owar.jdbc.dao;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;

import com.sscsweb.owar.entities.Administrator;
import com.sscsweb.owar.jdbc.mappers.AdministratorMapper;
import com.sscsweb.owar.utilities.ResponseCode;
import com.sscsweb.owar.utilities.ResponseMessage;
import com.sscsweb.owar.utilities.ResponseStatus;

public class AdministratorDAOImpl implements AdministratorDAO {

	private DataSource dataSource;
	private JdbcTemplate jdbcTemplateObject;
	
	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
		this.jdbcTemplateObject = new JdbcTemplate(dataSource);
	}
	
	public ResponseMessage getAdministrator(Integer userId) {
		String query = "SELECT * FROM administrator WHERE user_id=?";
		try {
			Administrator administrator = this.jdbcTemplateObject.queryForObject(query, new Object[] { userId }, new AdministratorMapper());
			if(administrator != null) {
				return new ResponseMessage(ResponseCode.SUCCESS, ResponseStatus.STATUS_MESSAGE.get(ResponseCode.SUCCESS), administrator);
			}
			return new ResponseMessage(ResponseCode.NOT_FOUND, ResponseStatus.STATUS_MESSAGE.get(ResponseCode.NOT_FOUND), null);
		} catch(Exception e) {
			return new ResponseMessage(ResponseCode.NOT_FOUND, ResponseStatus.STATUS_MESSAGE.get(ResponseCode.NOT_FOUND), null);
		}
	}
	
}

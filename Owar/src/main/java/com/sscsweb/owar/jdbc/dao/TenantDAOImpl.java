package com.sscsweb.owar.jdbc.dao;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;

import com.sscsweb.owar.entities.Lessor;
import com.sscsweb.owar.entities.Tenant;
import com.sscsweb.owar.jdbc.mappers.LessorMapper;
import com.sscsweb.owar.jdbc.mappers.TenantMapper;
import com.sscsweb.owar.utilities.ResponseCode;
import com.sscsweb.owar.utilities.ResponseMessage;
import com.sscsweb.owar.utilities.ResponseStatus;

public class TenantDAOImpl implements TenantDAO {

	private DataSource dataSource;
	private JdbcTemplate jdbcTemplateObject;
	
	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
		this.jdbcTemplateObject = new JdbcTemplate(dataSource);
	}
	
	public ResponseMessage createTenant(Tenant tenant) {
		String query = "INSERT INTO tenant(feedback_tenant, user_id) VALUES(?,?)";
		try {
			int result = this.jdbcTemplateObject.update(query, tenant.getFeedback_tenant(), tenant.getUser_id());
			return new ResponseMessage(result, ResponseStatus.STATUS_MESSAGE.get(result), null);
		} catch(Exception e) {
			return new ResponseMessage(ResponseCode.DB_ACCESS_ERROR, ResponseStatus.STATUS_MESSAGE.get(ResponseCode.DB_ACCESS_ERROR), null);
		}
	}
	
	public ResponseMessage getTenant(Integer userId) {
		String query = "SELECT * FROM tenant WHERE user_id=?";
		try {
			Tenant tenant = this.jdbcTemplateObject.queryForObject(query, new Object[] { userId }, new TenantMapper());
			if(tenant != null) {
				return new ResponseMessage(ResponseCode.SUCCESS, ResponseStatus.STATUS_MESSAGE.get(ResponseCode.SUCCESS), tenant);
			}
			return new ResponseMessage(ResponseCode.IS_EMPTY, ResponseStatus.STATUS_MESSAGE.get(ResponseCode.IS_EMPTY), null);
		} catch(Exception e) {
			return new ResponseMessage(ResponseCode.DB_ACCESS_ERROR, ResponseStatus.STATUS_MESSAGE.get(ResponseCode.DB_ACCESS_ERROR), null);
		}
	}
	
	public ResponseMessage getTenantFromId(Integer tenantId) {
		String query = "SELECT * FROM tenant WHERE id=?";
		try {
			Tenant tenant = this.jdbcTemplateObject.queryForObject(query, new Object[] { tenantId }, new TenantMapper());
			if(tenant != null) {
				return new ResponseMessage(ResponseCode.SUCCESS, ResponseStatus.STATUS_MESSAGE.get(ResponseCode.SUCCESS), tenant);
			}
			return new ResponseMessage(ResponseCode.NOT_VALID, ResponseStatus.STATUS_MESSAGE.get(ResponseCode.NOT_VALID), null);
		} catch(Exception e) {
			return new ResponseMessage(ResponseCode.DB_ACCESS_ERROR, ResponseStatus.STATUS_MESSAGE.get(ResponseCode.DB_ACCESS_ERROR), null);
		}
	}
	
	public ResponseMessage updateFeedback(double feedback, int feedback_number, Integer tenantId) {
		String query = "UPDATE lessor SET feedback_tenant=?, feedback_number=? WHERE id=?";
		try {
			int result = this.jdbcTemplateObject.update(query, feedback, feedback_number, tenantId);
			return new ResponseMessage(result, ResponseStatus.STATUS_MESSAGE.get(result), null);
		} catch(Exception e) {
			return new ResponseMessage(ResponseCode.DB_ACCESS_ERROR, ResponseStatus.STATUS_MESSAGE.get(ResponseCode.DB_ACCESS_ERROR), null);
		}
	}
	
}

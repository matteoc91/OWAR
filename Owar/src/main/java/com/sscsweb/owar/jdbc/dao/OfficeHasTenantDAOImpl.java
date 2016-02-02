package com.sscsweb.owar.jdbc.dao;

import java.util.List;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;

import com.sscsweb.owar.entities.OfficeHasTenant;
import com.sscsweb.owar.jdbc.mappers.OfficeHasTenantMapper;
import com.sscsweb.owar.utilities.ResponseCode;
import com.sscsweb.owar.utilities.ResponseMessage;
import com.sscsweb.owar.utilities.ResponseStatus;

public class OfficeHasTenantDAOImpl implements OfficeHasTenantDAO {

	private DataSource dataSource;
	private JdbcTemplate jdbcTemplateObject;
	
	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
		this.jdbcTemplateObject = new JdbcTemplate(dataSource);
	}
	
	public ResponseMessage addMap(OfficeHasTenant officeHasTenant) {
		String query = "INSERT INTO office_has_tenant(office_id,date_begin,date_end,tenant_id) VALUES(?,?,?,?)";
		try {
			int result = this.jdbcTemplateObject.update(query, officeHasTenant.getOffice_id(), officeHasTenant.getDate_begin(), 
					officeHasTenant.getDate_end(), officeHasTenant.getTenant_id());
			return new ResponseMessage(result, ResponseStatus.STATUS_MESSAGE.get(result), null);
		} catch(Exception e) {
			return new ResponseMessage(ResponseCode.DB_ACCESS_ERROR, ResponseStatus.STATUS_MESSAGE.get(ResponseCode.DB_ACCESS_ERROR), null);
		}
	}
	
	public ResponseMessage getOffices(Integer tenantId) {
		String query = "SELECT * FROM office_has_tenant WHERE tenant_id="+tenantId;
		try {
			List<OfficeHasTenant> officeHasTenantList = this.jdbcTemplateObject.query(query, new OfficeHasTenantMapper());
			if(officeHasTenantList != null && !officeHasTenantList.isEmpty()) {
				return new ResponseMessage(ResponseCode.SUCCESS, ResponseStatus.STATUS_MESSAGE.get(ResponseCode.SUCCESS), officeHasTenantList);
			}
			return new ResponseMessage(ResponseCode.IS_EMPTY, ResponseStatus.STATUS_MESSAGE.get(ResponseCode.IS_EMPTY), null);
		} catch(Exception e) {
			return new ResponseMessage(ResponseCode.DB_ACCESS_ERROR, ResponseStatus.STATUS_MESSAGE.get(ResponseCode.DB_ACCESS_ERROR), null);
		}
	}
	
	public ResponseMessage getTenantId(Integer officeId) {
		String query = "SELECT DISTINCT tenant_id FROM office_has_tenant WHERE office_id="+officeId;
		try {
			List<Integer> tenantList = this.jdbcTemplateObject.queryForList(query, Integer.class);
			if(tenantList != null && !tenantList.isEmpty()) {
				return new ResponseMessage(ResponseCode.SUCCESS, ResponseStatus.STATUS_MESSAGE.get(ResponseCode.SUCCESS), tenantList);
			}
			return new ResponseMessage(ResponseCode.IS_EMPTY, ResponseStatus.STATUS_MESSAGE.get(ResponseCode.IS_EMPTY), null);
		} catch(Exception e) {
			return new ResponseMessage(ResponseCode.DB_ACCESS_ERROR, ResponseStatus.STATUS_MESSAGE.get(ResponseCode.DB_ACCESS_ERROR), null);
		}
	}
	
	public ResponseMessage removeItem(Integer officeId, Integer tenantId) {
		String query = "DELETE FROM office_has_tenant WHERE office_id=? AND tenant_id=?";
		try {
			int result = this.jdbcTemplateObject.update(query, officeId, tenantId);
			return new ResponseMessage(result, ResponseStatus.STATUS_MESSAGE.get(result), null);
		} catch(Exception e) {
			return new ResponseMessage(ResponseCode.DB_ACCESS_ERROR, ResponseStatus.STATUS_MESSAGE.get(ResponseCode.DB_ACCESS_ERROR), null);
		}
	}
	
}

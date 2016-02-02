package com.sscsweb.owar.jdbc.dao;

import java.util.LinkedList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;

import com.sscsweb.owar.entities.OfficeHasService;
import com.sscsweb.owar.jdbc.mappers.OfficeHasServiceMapper;
import com.sscsweb.owar.jdbc.mappers.OfficeMapper;
import com.sscsweb.owar.utilities.ResponseCode;
import com.sscsweb.owar.utilities.ResponseMessage;
import com.sscsweb.owar.utilities.ResponseStatus;

public class OfficeHasServiceDAOImpl implements OfficeHasServiceDAO {

	private DataSource dataSource;
	private JdbcTemplate jdbcTemplateObject;
	
	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
		this.jdbcTemplateObject = new JdbcTemplate(dataSource);
	}
	
	public ResponseMessage getServicesId(Integer officeId) {
		String query = "SELECT * FROM office_has_service WHERE office_id=?";
		try {
			List<OfficeHasService> officeHasServiceList = this.jdbcTemplateObject.query(query, new OfficeHasServiceMapper(), officeId);
			if(officeHasServiceList == null || officeHasServiceList.isEmpty()) {
				return new ResponseMessage(ResponseCode.IS_EMPTY, ResponseStatus.STATUS_MESSAGE.get(ResponseCode.IS_EMPTY), null);
			}
			return new ResponseMessage(ResponseCode.SUCCESS, ResponseStatus.STATUS_MESSAGE.get(ResponseCode.SUCCESS), officeHasServiceList);
		} catch(Exception e) {
			return new ResponseMessage(ResponseCode.DB_ACCESS_ERROR, ResponseStatus.STATUS_MESSAGE.get(ResponseCode.DB_ACCESS_ERROR), null);
		}
	}
	
	public ResponseMessage addMap(OfficeHasService officeHasService) {
		String query = "INSERT INTO office_has_service(office_id, service_id, num_service) VALUES(?, ?, ?)";
		try {
			int result = this.jdbcTemplateObject.update(query, officeHasService.getOffice_id(),
					officeHasService.getService_id(), officeHasService.getNum_service());
			return new ResponseMessage(result, ResponseStatus.STATUS_MESSAGE.get(result), null);
		} catch(Exception e) {
			return new ResponseMessage(ResponseCode.DB_ACCESS_ERROR, ResponseStatus.STATUS_MESSAGE.get(ResponseCode.DB_ACCESS_ERROR), null);
		}
	}
	
	public ResponseMessage getOfficesId(List<OfficeHasService> officeHasServiceList) {
		try {
			List<Integer> officeIdList = new LinkedList<Integer>();
			String query = "SELECT DISTINCT office_id FROM office_has_service";
			if(officeHasServiceList != null && !officeHasServiceList.isEmpty()) {
				query += " WHERE";
				for(OfficeHasService officeHasService: officeHasServiceList) {
					query += " (service_id=" + officeHasService.getService_id() + " AND num_service>=" + officeHasService.getNum_service() + ") OR";
				}
				query = query.substring(0, query.lastIndexOf(" "));
				officeIdList = this.jdbcTemplateObject.queryForList(query, Integer.class);
			}
			if(officeIdList != null && !officeIdList.isEmpty()) {
				return new ResponseMessage(ResponseCode.SUCCESS, ResponseStatus.STATUS_MESSAGE.get(ResponseCode.SUCCESS), officeIdList);
			}
			return new ResponseMessage(ResponseCode.IS_EMPTY, ResponseStatus.STATUS_MESSAGE.get(ResponseCode.IS_EMPTY), null);
		} catch(Exception e) {
			return new ResponseMessage(ResponseCode.DB_ACCESS_ERROR, ResponseStatus.STATUS_MESSAGE.get(ResponseCode.DB_ACCESS_ERROR), null);
		}
	}
	
}

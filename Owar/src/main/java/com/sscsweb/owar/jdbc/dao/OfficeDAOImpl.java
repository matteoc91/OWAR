package com.sscsweb.owar.jdbc.dao;

import java.util.HashMap;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;

import com.sscsweb.owar.entities.Office;
import com.sscsweb.owar.jdbc.mappers.OfficeMapper;
import com.sscsweb.owar.jdbc.mappers.UserMapper;
import com.sscsweb.owar.utilities.ResponseCode;
import com.sscsweb.owar.utilities.ResponseMessage;
import com.sscsweb.owar.utilities.ResponseStatus;

public class OfficeDAOImpl implements OfficeDAO {

	private DataSource dataSource;
	private JdbcTemplate jdbcTemplateObject;
	
	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
		this.jdbcTemplateObject = new JdbcTemplate(dataSource);
	}
	
	public ResponseMessage getCount() {
		String query = "SELECT count(*) FROM office WHERE (reserved is NULL OR reserved <> 1)";
		try {
			int count = this.jdbcTemplateObject.queryForObject(query, Integer.class);
			if(count > 0) {
				return new ResponseMessage(ResponseCode.SUCCESS, ResponseStatus.STATUS_MESSAGE.get(ResponseCode.SUCCESS), count);
			}
			return new ResponseMessage(ResponseCode.IS_EMPTY, ResponseStatus.STATUS_MESSAGE.get(ResponseCode.IS_EMPTY), null);
		} catch(Exception e) {
			return new ResponseMessage(ResponseCode.DB_ACCESS_ERROR, ResponseStatus.STATUS_MESSAGE.get(ResponseCode.DB_ACCESS_ERROR), null);
		}
	}
	
	public ResponseMessage getOffices(Integer offset, Integer limit, List<Integer> districtList, List<Integer> officeIdList) {
		String query = "SELECT * FROM office WHERE (reserved is NULL OR reserved <> 1)";
		String numOf = "SELECT count(*) FROM office WHERE (reserved is NULL OR reserved <> 1)";
		if(districtList != null && !districtList.isEmpty()) {
			query += " AND (";
			numOf += " AND (";
			for(Integer districtId: districtList) {
				query += " tbComuni_id=" + districtId + " OR";
				numOf += " tbComuni_id=" + districtId + " OR";
			}
			query = query.substring(0, query.lastIndexOf(" "));
			numOf = numOf.substring(0,numOf.lastIndexOf(" "));
			query += ")";
			numOf += ")";
		}
		if(officeIdList != null && !officeIdList.isEmpty()) {
			query += " AND (";
			numOf += " AND (";
			for(Integer officeId: officeIdList) {
				query += " id=" + officeId + " OR";
				numOf += " id=" + officeId + " OR";
			}
			query = query.substring(0, query.lastIndexOf(" "));
			numOf = numOf.substring(0, numOf.lastIndexOf(" "));
			query += ")";
			numOf += ")";
		}
		if(limit != null) {
			query += " LIMIT " + limit;
		}
		if(offset != null) {
			query += " OFFSET " + limit*(offset-1);
		}
		HashMap<String, Object> result = new HashMap<String, Object>();
		try {
			int count = this.jdbcTemplateObject.queryForObject(numOf, Integer.class);
			result.put("numOfOffices", count);
			if(count > 0) {
				List<Office> offices = this.jdbcTemplateObject.query(query, new OfficeMapper());
				result.put("offices", offices);
				return new ResponseMessage(ResponseCode.SUCCESS, ResponseStatus.STATUS_MESSAGE.get(ResponseCode.SUCCESS), result);
			} else {
				result.put("offices", null);
				return new ResponseMessage(ResponseCode.IS_EMPTY, ResponseStatus.STATUS_MESSAGE.get(ResponseCode.IS_EMPTY), result);
			}
		} catch(Exception e) {
			result.put("numOfOffices", 0);
			result.put("offices", null);
			return new ResponseMessage(ResponseCode.DB_ACCESS_ERROR, ResponseStatus.STATUS_MESSAGE.get(ResponseCode.DB_ACCESS_ERROR), result);
		}
	}
	
	public ResponseMessage addOffice(Office office) {
		String query = "INSERT INTO office(address, home_number, date_begin, date_end, daily_price, "
				+ "description, lessor_id, tbComuni_id) VALUES(?, ?, ?, ?, ?, ?, ?, ?)";
		try {
			int result = this.jdbcTemplateObject.update(query, office.getAddress(), office.getHome_number(), office.getDate_begin(),
					office.getDate_end(), office.getDaily_price(), office.getDescription(), office.getLessor_id(), office.getTbComuni_id());
			if(result == ResponseCode.SUCCESS) {
				String getId = "SELECT id FROM office WHERE lessor_id";
				if(office.getLessor_id() != null) {
					getId += "=" + office.getLessor_id();
				} else {
					getId += " is null";
				}
				getId += " ORDER BY id DESC LIMIT 1";
				int officeId = this.jdbcTemplateObject.queryForObject(getId, Integer.class);
				return new ResponseMessage(result, ResponseStatus.STATUS_MESSAGE.get(result), officeId);
			}
			return new ResponseMessage(result, ResponseStatus.STATUS_MESSAGE.get(result), null);
		} catch(Exception e) {
			return new ResponseMessage(ResponseCode.DB_ACCESS_ERROR, ResponseStatus.STATUS_MESSAGE.get(ResponseCode.DB_ACCESS_ERROR), null);
		}
	}
	
	public ResponseMessage getOffice(Integer officeId) {
		String query = "SELECT * FROM office WHERE id = ?";
		try {
			Office office = this.jdbcTemplateObject.queryForObject(
					query, new Object[] { officeId }, new OfficeMapper());
			return new ResponseMessage(ResponseCode.SUCCESS, ResponseStatus.STATUS_MESSAGE.get(ResponseCode.SUCCESS), office);
		} catch(Exception e) {
			return new ResponseMessage(ResponseCode.NOT_FOUND, ResponseStatus.STATUS_MESSAGE.get(ResponseCode.NOT_FOUND), null);
		}
	}
	
	public ResponseMessage reserveOffice(Integer officeId, Integer reserveStatus) {
		String query = "UPDATE office SET reserved=? WHERE id=?";
		try {
			int result = this.jdbcTemplateObject.update(query, reserveStatus, officeId);
			return new ResponseMessage(result, ResponseStatus.STATUS_MESSAGE.get(result), null);
		} catch(Exception e) {
			return new ResponseMessage(ResponseCode.DB_ACCESS_ERROR, ResponseStatus.STATUS_MESSAGE.get(ResponseCode.DB_ACCESS_ERROR), null);
		}
	}
	
	public ResponseMessage getOfficeFromLessorId(Integer lessorId) {
		String query = "SELECT id FROM office WHERE lessor_id="+lessorId;
		try {
			List<Integer> officeList = this.jdbcTemplateObject.queryForList(query, Integer.class);
			if(officeList != null && !officeList.isEmpty()) {
				return new ResponseMessage(ResponseCode.SUCCESS, ResponseStatus.STATUS_MESSAGE.get(ResponseCode.SUCCESS), officeList);
			} else {
				return new ResponseMessage(ResponseCode.IS_EMPTY, ResponseStatus.STATUS_MESSAGE.get(ResponseCode.IS_EMPTY), null);
			}
		} catch(Exception e) {
			return new ResponseMessage(ResponseCode.DB_ACCESS_ERROR, ResponseStatus.STATUS_MESSAGE.get(ResponseCode.DB_ACCESS_ERROR), null);
		}
	}
	
}

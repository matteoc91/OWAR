package com.sscsweb.owar.jdbc.dao;

import java.util.List;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;

import com.sscsweb.owar.entities.TbComuni;
import com.sscsweb.owar.entities.TbRegioni;
import com.sscsweb.owar.jdbc.mappers.TbComuniMapper;
import com.sscsweb.owar.jdbc.mappers.TbRegioniMapper;
import com.sscsweb.owar.utilities.ResponseCode;
import com.sscsweb.owar.utilities.ResponseMessage;
import com.sscsweb.owar.utilities.ResponseStatus;

public class TbRegioniDAOImpl implements TbRegioniDAO {

	private DataSource dataSource;
	private JdbcTemplate jdbcTemplateObject;
	
	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
		this.jdbcTemplateObject = new JdbcTemplate(dataSource);
	}
	
	public ResponseMessage getAllRegion() {
		try {
			String query = "SELECT * FROM tbregioni";
			List<TbRegioni> regionList = this.jdbcTemplateObject.query(query, new TbRegioniMapper());
			if(regionList != null && !regionList.isEmpty()) {
				return new ResponseMessage(ResponseCode.SUCCESS, ResponseStatus.STATUS_MESSAGE.get(ResponseCode.SUCCESS), regionList);
			}
			return new ResponseMessage(ResponseCode.IS_EMPTY, ResponseStatus.STATUS_MESSAGE.get(ResponseCode.IS_EMPTY), null);
		} catch(Exception e) {
			return new ResponseMessage(ResponseCode.DB_ACCESS_ERROR, ResponseStatus.STATUS_MESSAGE.get(ResponseCode.DB_ACCESS_ERROR), null);
		}
	}
	
}

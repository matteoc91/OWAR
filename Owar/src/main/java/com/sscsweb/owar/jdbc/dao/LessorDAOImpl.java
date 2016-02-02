package com.sscsweb.owar.jdbc.dao;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;

import com.sscsweb.owar.entities.Lessor;
import com.sscsweb.owar.jdbc.mappers.LessorMapper;
import com.sscsweb.owar.utilities.ResponseCode;
import com.sscsweb.owar.utilities.ResponseMessage;
import com.sscsweb.owar.utilities.ResponseStatus;

public class LessorDAOImpl implements LessorDAO {

	private DataSource dataSource;
	private JdbcTemplate jdbcTemplateObject;
	
	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
		this.jdbcTemplateObject = new JdbcTemplate(dataSource);
	}
	
	public ResponseMessage createLessor(Lessor lessor) {
		String query = "INSERT INTO lessor(feedback_lessor,piva,user_id) VALUES(?,?,?)";
		try {
			int result = this.jdbcTemplateObject.update(query, lessor.getFeedback_lessor(), lessor.getPiva(), lessor.getUser_id());
			return new ResponseMessage(result, ResponseStatus.STATUS_MESSAGE.get(result), null);
		} catch(Exception e) {
			return new ResponseMessage(ResponseCode.DB_ACCESS_ERROR, ResponseStatus.STATUS_MESSAGE.get(ResponseCode.DB_ACCESS_ERROR), null);
		}
	}
	
	public ResponseMessage getLessor(Integer userId) {
		String query = "SELECT * FROM lessor WHERE user_id=?";
		try {
			Lessor lessor = this.jdbcTemplateObject.queryForObject(query, new Object[] { userId }, new LessorMapper());
			if(lessor != null) {
				return new ResponseMessage(ResponseCode.SUCCESS, ResponseStatus.STATUS_MESSAGE.get(ResponseCode.SUCCESS), lessor);
			}
			return new ResponseMessage(ResponseCode.NOT_VALID, ResponseStatus.STATUS_MESSAGE.get(ResponseCode.NOT_VALID), null);
		} catch(Exception e) {
			return new ResponseMessage(ResponseCode.DB_ACCESS_ERROR, ResponseStatus.STATUS_MESSAGE.get(ResponseCode.DB_ACCESS_ERROR), null);
		}
	}
	
	public ResponseMessage getLessorFromId(Integer lessorId) {
		String query = "SELECT * FROM lessor WHERE id=?";
		try {
			Lessor lessor = this.jdbcTemplateObject.queryForObject(query, new Object[] { lessorId }, new LessorMapper());
			if(lessor != null) {
				return new ResponseMessage(ResponseCode.SUCCESS, ResponseStatus.STATUS_MESSAGE.get(ResponseCode.SUCCESS), lessor);
			}
			return new ResponseMessage(ResponseCode.NOT_VALID, ResponseStatus.STATUS_MESSAGE.get(ResponseCode.NOT_VALID), null);
		} catch(Exception e) {
			return new ResponseMessage(ResponseCode.DB_ACCESS_ERROR, ResponseStatus.STATUS_MESSAGE.get(ResponseCode.DB_ACCESS_ERROR), null);
		}
	}
	
	public ResponseMessage updateFeedback(double feedback, int feedback_number, Integer lessorId) {
		String query = "UPDATE lessor SET feedback_lessor=?, feedback_number=? WHERE id=?";
		try {
			int result = this.jdbcTemplateObject.update(query, feedback, feedback_number, lessorId);
			return new ResponseMessage(result, ResponseStatus.STATUS_MESSAGE.get(result), null);
		} catch(Exception e) {
			return new ResponseMessage(ResponseCode.DB_ACCESS_ERROR, ResponseStatus.STATUS_MESSAGE.get(ResponseCode.DB_ACCESS_ERROR), null);
		}
	}
	
}

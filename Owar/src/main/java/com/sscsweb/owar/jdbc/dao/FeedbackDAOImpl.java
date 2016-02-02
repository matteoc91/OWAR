package com.sscsweb.owar.jdbc.dao;

import java.util.List;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;

import com.sscsweb.owar.entities.Feedback;
import com.sscsweb.owar.jdbc.mappers.FeedbackMapper;
import com.sscsweb.owar.utilities.ResponseCode;
import com.sscsweb.owar.utilities.ResponseMessage;
import com.sscsweb.owar.utilities.ResponseStatus;

public class FeedbackDAOImpl implements FeedbackDAO {

	private DataSource dataSource;
	private JdbcTemplate jdbcTemplateObject;
	
	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
		this.jdbcTemplateObject = new JdbcTemplate(dataSource);
	}
	
	public ResponseMessage addFeedback(Feedback feedback) {
		String query = "INSERT INTO feedback(office_valuation,comment,office_id) VALUES(?,?,?)";
		try {
			int result = this.jdbcTemplateObject.update(query, feedback.getOffice_valuation(), feedback.getComment(), feedback.getOffice_id());
			return new ResponseMessage(result, ResponseStatus.STATUS_MESSAGE.get(result), null);
		} catch(Exception e) {
			return new ResponseMessage(ResponseCode.DB_ACCESS_ERROR, ResponseStatus.STATUS_MESSAGE.get(ResponseCode.DB_ACCESS_ERROR), null);
		}
	}
	
	public ResponseMessage getFeedback(Integer officeId, Integer limit) {
		String query = "SELECT * FROM feedback WHERE office_id="+officeId;
		if(limit != null) {
			query += " LIMIT "+limit;
		}
		query += " ORDER BY id DESC";
		try {
			List<Feedback> feedbackList = this.jdbcTemplateObject.query(query, new FeedbackMapper());
			if(feedbackList != null && !feedbackList.isEmpty()) {
				return new ResponseMessage(ResponseCode.SUCCESS, ResponseStatus.STATUS_MESSAGE.get(ResponseCode.SUCCESS), feedbackList);
			}
			return new ResponseMessage(ResponseCode.IS_EMPTY, ResponseStatus.STATUS_MESSAGE.get(ResponseCode.IS_EMPTY), null);
		} catch(Exception e) {
			return new ResponseMessage(ResponseCode.DB_ACCESS_ERROR, ResponseStatus.STATUS_MESSAGE.get(ResponseCode.DB_ACCESS_ERROR), null);
		}
	}
	
}

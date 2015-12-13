package com.sscsweb.owar.jdbc.mappers;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.sscsweb.owar.entities.Feedback;

public class FeedbackMapper implements RowMapper<Feedback> {

	public Feedback mapRow(ResultSet arg0, int arg1) throws SQLException {
		Feedback feedback = new Feedback();
		feedback.setComment(arg0.getString("comment"));
		feedback.setId(arg0.getInt("id"));
		feedback.setOffice_id(arg0.getInt("office_id"));
		feedback.setOffice_valuation(arg0.getInt("office_valuation"));
		return feedback;
	}

}

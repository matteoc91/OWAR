package com.sscsweb.owar.jdbc.mappers;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.sscsweb.owar.entities.Lessor;

public class LessorMapper implements RowMapper<Lessor> {

	public Lessor mapRow(ResultSet arg0, int arg1) throws SQLException {
		Lessor lessor = new Lessor();
		lessor.setFeedback_lessor(arg0.getDouble("feedback_lessor"));
		lessor.setId(arg0.getInt("id"));
		lessor.setPiva(arg0.getString("piva"));
		lessor.setUser_id(arg0.getInt("user_id"));
		lessor.setFeedback_number(arg0.getInt("feedback_number"));
		return lessor;
	}

}

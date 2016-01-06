package com.sscsweb.owar.jdbc.mappers;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.sscsweb.owar.entities.User;

import org.springframework.jdbc.core.RowMapper;

public class UserMapper implements RowMapper<User> {

	public User mapRow(ResultSet arg0, int arg1) throws SQLException {
		User user = new User();
		user.setAddress(arg0.getString("address"));
		user.setBirth_date(arg0.getDate("birth_date"));
		user.setComune_id(arg0.getInt("comune_id"));
		user.setHouse_number(arg0.getInt("house_number"));
		user.setId(arg0.getInt("id"));
		user.setMail(arg0.getString("mail"));
		user.setName(arg0.getString("name"));
		user.setPassword(arg0.getString("password"));
		user.setPhone_number(arg0.getString("phone_number"));
		user.setSurname(arg0.getString("surname"));
		user.setTax_code(arg0.getString("tax_code"));
		user.setTwitter_id(arg0.getString("twitter_id"));
		user.setValid(arg0.getInt("valid"));
		return user;
	}

}

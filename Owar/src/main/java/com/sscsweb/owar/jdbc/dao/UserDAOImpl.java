package com.sscsweb.owar.jdbc.dao;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;

import com.sscsweb.owar.entities.User;

public class UserDAOImpl implements UserDAO {

	private DataSource dataSource;
	private JdbcTemplate jdbcTemplateObject;
	
	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
		this.jdbcTemplateObject = new JdbcTemplate(dataSource);
	}

	public int registration(User user) {
		String query = "INSERT INTO "
				+ "user(mail, password, comune_id, name, surname, tax_code, address, house_number, phone_number, birth_date) "
				+ "VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
		int result = -1;
		try {
			result = this.jdbcTemplateObject.update(query, user.getMail(), user.getPassword(), user.getComune_id(), user.getName(), 
					user.getSurname(), user.getTax_code(), user.getAddress(), user.getHouse_number(), user.getPhone_number(), user.getBirth_date());
		} catch(Exception e) {
			result = -1;
		}
		
		return result;
	}
	
}

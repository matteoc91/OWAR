package com.sscsweb.owar.jdbc.dao;

import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

import com.sscsweb.owar.entities.User;
import com.sscsweb.owar.jdbc.mappers.UserMapper;
import com.sscsweb.owar.sessionBean.UserBean;
import com.sscsweb.owar.utilities.Chiper;

public class UserDAOImpl implements UserDAO {

	private DataSource dataSource;
	private JdbcTemplate jdbcTemplateObject;
	
	@Autowired
	private UserBean userBean;
	
	public UserBean getUserBean() {
		return userBean;
	}

	public void setUserBean(UserBean userBean) {
		this.userBean = userBean;
	}
	
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
	
	public int login(User user) {
		String query = "SELECT * FROM user WHERE mail = ?";
		int result = -1;
		try {
			User dbUser = this.jdbcTemplateObject.queryForObject(
					query, new Object[] { user.getMail() }, new UserMapper());
			if(dbUser != null) {
				if(Chiper.checkPassword(user.getPassword(), dbUser.getPassword())) {
					/*
					 * TODO - implement ejb
					 * */
					userBean.setUser(dbUser);
					result = 1;
				}
			}
		} catch(Exception e) {
			result = -1;
		}
		
		return result;
	}
	
}

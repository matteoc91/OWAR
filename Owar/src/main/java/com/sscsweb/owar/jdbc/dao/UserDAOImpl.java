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
		if(getUserFromEmail(user.getMail()) != null) {
			return -1;
		}
		
		String query = "INSERT INTO "
				+ "user(mail, password, comune_id, name, surname, tax_code, address, house_number, phone_number, birth_date, twitter_id, valid) "
				+ "VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
		int result = -1;
		try {
			if(user.getPassword() != null) {
				user.setPassword(Chiper.encryptPassword(user.getPassword()));
			}
			result = this.jdbcTemplateObject.update(query, user.getMail(), user.getPassword(), user.getComune_id(), user.getName(), 
					user.getSurname(), user.getTax_code(), user.getAddress(), user.getHouse_number(), user.getPhone_number(), user.getBirth_date(), 
					user.getTwitter_id(), user.getValid());
		} catch(Exception e) {
			result = -1;
		}
		
		return result;
	}
	
	public int login(User user) {
		String query = "SELECT * FROM user WHERE mail = ?";
		String param = user.getMail();
		if(user.getTwitter_id() != null) {
			query = "SELECT * FROM user WHERE twitter_id = ?";
			param = user.getTwitter_id();
		}
		int result = -1;
		try {
			User dbUser = this.jdbcTemplateObject.queryForObject(
					query, new Object[] { param }, new UserMapper());
			if(dbUser != null && dbUser.getValid() != null && dbUser.getValid() == 1) {
				if(dbUser.getTwitter_id() != null) {
					result = 1;
				}
				else if(Chiper.checkPassword(user.getPassword(), dbUser.getPassword())) {
					userBean.setUser(dbUser);
					result = 1;
				}
			}
		} catch(Exception e) {
			result = -1;
		}
		
		return result;
	}
	
	public User getUserFromEmail(String email) {
		String query = "SELECT * FROM user WHERE mail = ?";
		try {
			User dbUser = this.jdbcTemplateObject.queryForObject(
					query, new Object[] { email }, new UserMapper());
			return dbUser;
		} catch(Exception e) {
			return null;
		}
	}
	
	public int validate(String email) {
		String query = "UPDATE user SET valid = 1 WHERE mail = ?";
		try {
			return this.jdbcTemplateObject.update(query, email);
		} catch(Exception e) {
			return -1;
		}
	}
	
	public int socialLogin(User user) {
		String query = "SELECT * FROM user WHERE twitter_id = ?";
		try {
			User dbUser = this.jdbcTemplateObject.queryForObject(
					query, new Object[] { user.getTwitter_id() }, new UserMapper());
			userBean.setUser(dbUser);
			return 1;
		} catch(Exception e) {
			try {
				if(registration(user) == 1) {
					User dbUser = this.jdbcTemplateObject.queryForObject(
							query, new Object[] { user.getTwitter_id() }, new UserMapper());
					userBean.setUser(dbUser);
					return 1;
				}
			} catch(Exception internalException) {
				
			}
			return -1;
		}
	}
	
}

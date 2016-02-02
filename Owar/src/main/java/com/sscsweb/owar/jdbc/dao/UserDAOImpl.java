package com.sscsweb.owar.jdbc.dao;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;

import com.sscsweb.owar.entities.User;
import com.sscsweb.owar.jdbc.mappers.UserMapper;
import com.sscsweb.owar.utilities.Chiper;
import com.sscsweb.owar.utilities.ResponseCode;
import com.sscsweb.owar.utilities.ResponseMessage;
import com.sscsweb.owar.utilities.ResponseStatus;

public class UserDAOImpl implements UserDAO {

	private DataSource dataSource;
	private JdbcTemplate jdbcTemplateObject;

	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
		this.jdbcTemplateObject = new JdbcTemplate(dataSource);
	}

	public ResponseMessage registration(User user) {
		if(getUserFromEmail(user.getMail()).getResponseCode() == ResponseCode.SUCCESS) {
			return new ResponseMessage(ResponseCode.ALREADY_EXIST, ResponseStatus.STATUS_MESSAGE.get(ResponseCode.ALREADY_EXIST), null);
		}
		
		String query = "INSERT INTO "
				+ "user(mail, password, comune_id, name, surname, tax_code, address, house_number, phone_number, birth_date, linkedin_id, valid) "
				+ "VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
		try {
			if(user.getPassword() != null) {
				user.setPassword(Chiper.encryptPassword(user.getPassword()));
			}
			int result = this.jdbcTemplateObject.update(query, user.getMail(), user.getPassword(), user.getComune_id(), user.getName(), 
					user.getSurname(), user.getTax_code(), user.getAddress(), user.getHouse_number(), user.getPhone_number(), user.getBirth_date(), 
					user.getLinkedin_id(), user.getValid());
			return new ResponseMessage(result, ResponseStatus.STATUS_MESSAGE.get(result), null);
		} catch(Exception e) {
			return new ResponseMessage(ResponseCode.DB_ACCESS_ERROR, ResponseStatus.STATUS_MESSAGE.get(ResponseCode.DB_ACCESS_ERROR), null);
		}
	}
	
	public ResponseMessage login(User user) {
		String query = "SELECT * FROM user WHERE mail = ?";
		String param = user.getMail();
		if(user.getLinkedin_id() != null) {
			query = "SELECT * FROM user WHERE linkedin_id = ?";
			param = user.getLinkedin_id();
		}
		try {
			User dbUser = this.jdbcTemplateObject.queryForObject(
					query, new Object[] { param }, new UserMapper());
			if(dbUser != null && dbUser.getValid() != null && dbUser.getValid() == 1) {
				if(dbUser.getLinkedin_id() != null) {
					return new ResponseMessage(ResponseCode.SUCCESS, ResponseStatus.STATUS_MESSAGE.get(ResponseCode.SUCCESS), dbUser);
				}
				else if(Chiper.checkPassword(user.getPassword(), dbUser.getPassword())) {
					return new ResponseMessage(ResponseCode.SUCCESS, ResponseStatus.STATUS_MESSAGE.get(ResponseCode.SUCCESS), dbUser);
				} else {
					return new ResponseMessage(ResponseCode.WRONG_CREDENTIALS, ResponseStatus.STATUS_MESSAGE.get(ResponseCode.WRONG_CREDENTIALS), null);
				}
			} else {
				return new ResponseMessage(ResponseCode.NOT_VALID, ResponseStatus.STATUS_MESSAGE.get(ResponseCode.NOT_VALID), null);
			}
		} catch(Exception e) {
			return new ResponseMessage(ResponseCode.NOT_FOUND, ResponseStatus.STATUS_MESSAGE.get(ResponseCode.NOT_FOUND), null);
		}
	}
	
	public ResponseMessage getUserFromEmail(String email) {
		String query = "SELECT * FROM user WHERE mail = ?";
		try {
			User dbUser = this.jdbcTemplateObject.queryForObject(
					query, new Object[] { email }, new UserMapper());
			return new ResponseMessage(ResponseCode.SUCCESS, ResponseStatus.STATUS_MESSAGE.get(ResponseCode.SUCCESS), dbUser);
		} catch(Exception e) {
			return new ResponseMessage(ResponseCode.NOT_FOUND, ResponseStatus.STATUS_MESSAGE.get(ResponseCode.NOT_FOUND), null);
		}
	}
	
	public ResponseMessage validate(String email) {
		String query = "UPDATE user SET valid = 1 WHERE mail = ?";
		try {
			int result = this.jdbcTemplateObject.update(query, email);
			return new ResponseMessage(result, ResponseStatus.STATUS_MESSAGE.get(result), null);
		} catch(Exception e) {
			return new ResponseMessage(ResponseCode.NOT_FOUND, ResponseStatus.STATUS_MESSAGE.get(ResponseCode.NOT_FOUND), null);
		}
	}
	
	public ResponseMessage socialLogin(User user) {
		String query = "SELECT * FROM user WHERE linkedin_id = ?";
		try {
			User dbUser = this.jdbcTemplateObject.queryForObject(
					query, new Object[] { user.getLinkedin_id() }, new UserMapper());
			return new ResponseMessage(ResponseCode.SUCCESS, ResponseStatus.STATUS_MESSAGE.get(ResponseCode.SUCCESS), dbUser);
		} catch(Exception e) {
			try {
				ResponseMessage response = registration(user);
				if(response.getResponseCode() == ResponseCode.SUCCESS) {
					User dbUser = this.jdbcTemplateObject.queryForObject(
							query, new Object[] { user.getLinkedin_id() }, new UserMapper());
					return new ResponseMessage(ResponseCode.SUCCESS, ResponseStatus.STATUS_MESSAGE.get(ResponseCode.SUCCESS), dbUser);
				} else {
					return new ResponseMessage(response.getResponseCode(), ResponseStatus.STATUS_MESSAGE.get(response.getResponseCode()), null);
				}
			} catch(Exception internalException) {
				return new ResponseMessage(ResponseCode.DB_ACCESS_ERROR, ResponseStatus.STATUS_MESSAGE.get(ResponseCode.DB_ACCESS_ERROR), null);
			}
		}
	}
	
	public ResponseMessage update(User user) {
		String query = "UPDATE user SET comune_id=?, name=?, surname=?, tax_code=?, address=?, house_number=?, phone_number=?, birth_date=? "
				+ "WHERE mail=?";
		try {
			int result = this.jdbcTemplateObject.update(query, user.getComune_id(), user.getName(), user.getSurname(), user.getTax_code(), 
					user.getAddress(), user.getHouse_number(), user.getPhone_number(), user.getBirth_date(), user.getMail());
			return new ResponseMessage(result, ResponseStatus.STATUS_MESSAGE.get(result), null);
		} catch(Exception e) {
			return new ResponseMessage(ResponseCode.DB_ACCESS_ERROR, ResponseStatus.STATUS_MESSAGE.get(ResponseCode.DB_ACCESS_ERROR), null);
		}
	}
	
	public ResponseMessage getUserFromId(Integer userId) {
		String query = "SELECT * FROM user WHERE id = ?";
		try {
			User dbUser = this.jdbcTemplateObject.queryForObject(
					query, new Object[] { userId }, new UserMapper());
			return new ResponseMessage(ResponseCode.SUCCESS, ResponseStatus.STATUS_MESSAGE.get(ResponseCode.SUCCESS), dbUser);
		} catch(Exception e) {
			return new ResponseMessage(ResponseCode.NOT_FOUND, ResponseStatus.STATUS_MESSAGE.get(ResponseCode.NOT_FOUND), null);
		}
	}
	
}

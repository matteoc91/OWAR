package com.sscsweb.owar.jdbc.dao;

import com.sscsweb.owar.entities.User;
import com.sscsweb.owar.utilities.ResponseMessage;

public interface UserDAO {

	public int registration(User user);
	public ResponseMessage login(User user);
	public ResponseMessage getUserFromEmail(String email);
	public int validate(String email);
	public ResponseMessage socialLogin(User user);
	
}

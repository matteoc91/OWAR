package com.sscsweb.owar.jdbc.dao;

import com.sscsweb.owar.entities.User;
import com.sscsweb.owar.utilities.ResponseMessage;

public interface UserDAO {

	public ResponseMessage registration(User user);
	public ResponseMessage login(User user);
	public ResponseMessage getUserFromEmail(String email);
	public ResponseMessage validate(String email);
	public ResponseMessage socialLogin(User user);
	
}

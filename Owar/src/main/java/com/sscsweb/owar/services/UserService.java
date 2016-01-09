package com.sscsweb.owar.services;

import com.sscsweb.owar.entities.User;
import com.sscsweb.owar.utilities.ResponseMessage;

public interface UserService {

	public ResponseMessage login(User user);
	public int register(User user);
	public int logout();
	public int validate(String email);
	public ResponseMessage socialLogin(User user);
	
}

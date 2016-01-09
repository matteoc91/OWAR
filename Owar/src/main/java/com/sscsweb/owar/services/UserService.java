package com.sscsweb.owar.services;

import com.sscsweb.owar.entities.User;
import com.sscsweb.owar.utilities.ResponseMessage;

public interface UserService {

	public ResponseMessage login(User user);
	public ResponseMessage register(User user);
	public ResponseMessage logout();
	public ResponseMessage validate(String email);
	public ResponseMessage socialLogin(User user);
	
}

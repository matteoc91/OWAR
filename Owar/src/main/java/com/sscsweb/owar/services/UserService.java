package com.sscsweb.owar.services;

import com.sscsweb.owar.entities.User;

public interface UserService {

	public boolean login(User user);
	public boolean register(User user);
	public boolean logout();
	
}

package com.sscsweb.owar.jdbc.dao;

import com.sscsweb.owar.entities.User;

public interface UserDAO {

	public int registration(User user);
	public int login(User user);
	public User getUserFromEmail(String email);
	public int validate(String email);
	public int socialLogin(User user);
	
}

package com.sscsweb.owar.sessionBean;

import javax.ejb.Local;

import com.sscsweb.owar.entities.User;

@Local
public interface UserBean {
	
	public void setUser(User user);
	public User getUser();

}

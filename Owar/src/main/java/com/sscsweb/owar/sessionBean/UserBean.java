package com.sscsweb.owar.sessionBean;

import javax.ejb.Local;

import org.springframework.context.annotation.Scope;

import com.sscsweb.owar.entities.User;

@Local
@Scope("session")
public interface UserBean {
	
	public void setUser(User user);
	public User getUser();

}

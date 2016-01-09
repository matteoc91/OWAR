package com.sscsweb.owar.sessionBean;

import java.rmi.RemoteException;

import javax.ejb.EJB;
import javax.ejb.EJBException;
import javax.ejb.SessionBean;
import javax.ejb.SessionContext;
import javax.ejb.Stateful;
import javax.enterprise.context.SessionScoped;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.sscsweb.owar.entities.User;

@Component
@Scope("session")
@EJB(name="java:module/UserBeanImpl", beanInterface=UserBean.class)
@Stateful
public class UserBeanImpl implements UserBean, SessionBean {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6318485110993411177L;
	
	private User user;

	public void ejbActivate() throws EJBException, RemoteException {
		// TODO Auto-generated method stub
		
	}

	public void ejbPassivate() throws EJBException, RemoteException {
		// TODO Auto-generated method stub
		
	}

	public void ejbRemove() throws EJBException, RemoteException {
		// TODO Auto-generated method stub
		
	}

	public void setSessionContext(SessionContext arg0) throws EJBException,
			RemoteException {
		// TODO Auto-generated method stub
		
	}
	
	public void setUser(User user) {
		this.user = user;
	}
	
	public User getUser() {
		return user;
	}

}

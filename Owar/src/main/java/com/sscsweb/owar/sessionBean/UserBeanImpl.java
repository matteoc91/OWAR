package com.sscsweb.owar.sessionBean;

import java.rmi.RemoteException;

import javax.ejb.EJB;
import javax.ejb.EJBException;
import javax.ejb.SessionBean;
import javax.ejb.SessionContext;
import javax.ejb.Stateful;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.sscsweb.owar.entities.Administrator;
import com.sscsweb.owar.entities.Lessor;
import com.sscsweb.owar.entities.Tenant;
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
	private Administrator administrator;
	private Tenant tenant;
	private Lessor lessor;
	private boolean isAdminMode = false;
	private boolean isLessorMode = false;
	private boolean isTenantMode = false;
	private Integer tmpOfficeId;

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
	
	public void setAdministrator(Administrator administrator) {
		this.administrator = administrator;
	}

	public Administrator getAdministrator() {
		return this.administrator;
	}

	public Tenant getTenant() {
		return tenant;
	}

	public void setTenant(Tenant tenant) {
		this.tenant = tenant;
	}

	public Lessor getLessor() {
		return lessor;
	}

	public void setLessor(Lessor lessor) {
		this.lessor = lessor;
	}

	public boolean isEditMode() {
		return isAdminMode || lessor != null;
	}

	public boolean isAdminMode() {
		return isAdminMode;
	}

	public void setAdminMode(boolean isAdminMode) {
		this.isAdminMode = isAdminMode;
	}

	public boolean isLessorMode() {
		return isLessorMode;
	}

	public void setLessorMode(boolean isLessorMode) {
		this.isLessorMode = isLessorMode;
	}

	public boolean isTenantMode() {
		return isTenantMode;
	}

	public void setTenantMode(boolean isTenantMode) {
		this.isTenantMode = isTenantMode;
	}

	public Integer getTmpOfficeId() {
		return tmpOfficeId;
	}

	public void setTmpOfficeId(Integer tmpOfficeId) {
		this.tmpOfficeId = tmpOfficeId;
	}
	
	public boolean checkTenant() {
		return this.user != null && this.user.getAddress() != null && this.user.getBirth_date() != null && this.user.getComune_id() != null && 
				this.user.getHouse_number() != null && this.user.getMail() != null && this.user.getName() != null && 
				this.user.getPhone_number() != null && this.user.getSurname() != null && this.user.getTax_code() != null && 
				this.user.getValid() != null;
	}

}

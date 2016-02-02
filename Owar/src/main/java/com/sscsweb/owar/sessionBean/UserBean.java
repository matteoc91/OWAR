package com.sscsweb.owar.sessionBean;

import javax.ejb.Local;

import org.springframework.context.annotation.Scope;

import com.sscsweb.owar.entities.Administrator;
import com.sscsweb.owar.entities.Lessor;
import com.sscsweb.owar.entities.Tenant;
import com.sscsweb.owar.entities.User;

@Local
@Scope("session")
public interface UserBean {
	
	public void setUser(User user);
	public User getUser();
	public void setAdministrator(Administrator administrator);
	public Administrator getAdministrator();
	public Tenant getTenant();
	public void setTenant(Tenant tenant);
	public Lessor getLessor();
	public void setLessor(Lessor lessor);
	public boolean isEditMode();
	public boolean isAdminMode();
	public void setAdminMode(boolean isAdminMode);
	public boolean isLessorMode();
	public void setLessorMode(boolean isLessorMode);
	public boolean isTenantMode();
	public void setTenantMode(boolean isTenantMode);
	public Integer getTmpOfficeId();
	public void setTmpOfficeId(Integer tmpOfficeId);
	public boolean checkTenant();

}

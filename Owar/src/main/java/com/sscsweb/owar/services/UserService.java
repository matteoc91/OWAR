package com.sscsweb.owar.services;

import com.sscsweb.owar.entities.Lessor;
import com.sscsweb.owar.entities.Tenant;
import com.sscsweb.owar.entities.User;
import com.sscsweb.owar.utilities.ResponseMessage;

public interface UserService {

	public ResponseMessage login(User user);
	public ResponseMessage register(User user);
	public ResponseMessage logout();
	public ResponseMessage validate(String email);
	public ResponseMessage socialLogin(User user);
	public ResponseMessage getAdminPrivilege();
	public ResponseMessage getTenant(Integer userId);
	public ResponseMessage createTenant(Tenant tenant);
	public ResponseMessage updateUser(User user);
	public ResponseMessage createLessor(Lessor lessor);
	public ResponseMessage updateFeedbackTenant(double feedback, Integer tenantId);
	public ResponseMessage updateFeedbackLessor(double feedback, Integer lessorId);
	
}

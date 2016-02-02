package com.sscsweb.owar.jdbc.dao;

import com.sscsweb.owar.entities.Tenant;
import com.sscsweb.owar.utilities.ResponseMessage;

public interface TenantDAO {
	
	public ResponseMessage createTenant(Tenant tenant);
	public ResponseMessage getTenant(Integer userId);
	public ResponseMessage getTenantFromId(Integer tenantId);
	public ResponseMessage updateFeedback(double feedback, int feedback_number, Integer tenantId);

}

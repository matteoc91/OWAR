package com.sscsweb.owar.jdbc.dao;

import com.sscsweb.owar.entities.OfficeHasTenant;
import com.sscsweb.owar.utilities.ResponseMessage;

public interface OfficeHasTenantDAO {

	public ResponseMessage addMap(OfficeHasTenant officeHasTenant);
	public ResponseMessage getOffices(Integer tenantId);
	public ResponseMessage getTenantId(Integer officeId);
	public ResponseMessage removeItem(Integer officeId, Integer tenantId);
	
}

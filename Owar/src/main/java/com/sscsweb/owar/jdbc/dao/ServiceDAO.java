package com.sscsweb.owar.jdbc.dao;

import com.sscsweb.owar.entities.Service;
import com.sscsweb.owar.utilities.ResponseMessage;

public interface ServiceDAO {

	public ResponseMessage getServices(Integer officeId);
	public ResponseMessage createService(Service service);
	public ResponseMessage getAllService();
	
}

package com.sscsweb.owar.jdbc.dao;

import java.util.List;

import com.sscsweb.owar.entities.OfficeHasService;
import com.sscsweb.owar.utilities.ResponseMessage;

public interface OfficeHasServiceDAO {
	
	public ResponseMessage getServicesId(Integer officeId);
	public ResponseMessage addMap(OfficeHasService officeHasService);
	public ResponseMessage getOfficesId(List<OfficeHasService> officeHasServiceList);
	public ResponseMessage getMap(Integer officeId, Integer serviceId);

}

package com.sscsweb.owar.utilities;

import com.sscsweb.owar.entities.OfficeHasService;
import com.sscsweb.owar.entities.Service;

public class ServiceInOffice {
	
	private Service service;
	private OfficeHasService officeHasService;
	
	public Service getService() {
		return service;
	}
	
	public void setService(Service service) {
		this.service = service;
	}

	public OfficeHasService getOfficeHasService() {
		return officeHasService;
	}

	public void setOfficeHasService(OfficeHasService officeHasService) {
		this.officeHasService = officeHasService;
	}
	
}

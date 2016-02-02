package com.sscsweb.owar.services;

import com.sscsweb.owar.utilities.ResponseMessage;

public interface DistrictService {

	public ResponseMessage getAllDistrict();
	public ResponseMessage getAllTown();
	public ResponseMessage getAllRegion();
	public ResponseMessage getLocationString(int id);
	
}

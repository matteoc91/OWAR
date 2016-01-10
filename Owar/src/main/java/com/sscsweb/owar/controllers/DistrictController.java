package com.sscsweb.owar.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sscsweb.owar.services.DistrictService;
import com.sscsweb.owar.utilities.ResponseMessage;

@RestController
@RequestMapping(value = "/district")
public class DistrictController {

	@Autowired
	private DistrictService districtService;

	public DistrictService getDistrictService() {
		return districtService;
	}

	public void setDistrictService(DistrictService districtService) {
		this.districtService = districtService;
	}
	
	@RequestMapping(value = "/all")
	public ResponseMessage getAllDistrict() {
		return this.districtService.getAllDistrict();
	}
	
}

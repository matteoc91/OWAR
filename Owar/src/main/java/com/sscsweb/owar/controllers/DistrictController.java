package com.sscsweb.owar.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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
	
	@RequestMapping(value = "/allDistrict")
	public ResponseMessage getAllDistrict() {
		return this.districtService.getAllDistrict();
	}
	
	@RequestMapping(value = "/allTown")
	public ResponseMessage getAllTown() {
		return this.districtService.getAllTown();
	}
	
	@RequestMapping(value = "/allRegion")
	public ResponseMessage getAllRegion() {
		return this.districtService.getAllRegion();
	}
	
	@RequestMapping(value = "/location")
	public ResponseMessage getLocationString(@RequestParam("id") int id) {
		return this.districtService.getLocationString(id);
	}
	
}

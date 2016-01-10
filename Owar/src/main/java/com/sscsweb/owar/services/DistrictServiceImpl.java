package com.sscsweb.owar.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sscsweb.owar.jdbc.dao.TbComuniDAO;
import com.sscsweb.owar.utilities.ResponseMessage;

@Service
public class DistrictServiceImpl implements DistrictService {

	@Autowired
	private TbComuniDAO tbComuniDAO;
	
	public TbComuniDAO getTbComuniDAO() {
		return tbComuniDAO;
	}

	public void setTbComuniDAO(TbComuniDAO tbComuniDAO) {
		this.tbComuniDAO = tbComuniDAO;
	}

	public ResponseMessage getAllDistrict() {
		return this.tbComuniDAO.getAllDistrict();
	}

}

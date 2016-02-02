package com.sscsweb.owar.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sscsweb.owar.entities.TbComuni;
import com.sscsweb.owar.entities.TbProvince;
import com.sscsweb.owar.entities.TbRegioni;
import com.sscsweb.owar.sessionBean.LocationBean;
import com.sscsweb.owar.utilities.ResponseCode;
import com.sscsweb.owar.utilities.ResponseMessage;
import com.sscsweb.owar.utilities.ResponseStatus;

@Service
public class DistrictServiceImpl implements DistrictService {

	@Autowired
	private LocationBean locationBean;

	public LocationBean getLocationBean() {
		return locationBean;
	}

	public void setLocationBean(LocationBean locationBean) {
		this.locationBean = locationBean;
	}

	public ResponseMessage getAllDistrict() {
		List<TbComuni> districList = this.locationBean.getAllDistrict();
		if(districList != null && !districList.isEmpty()) {
			return new ResponseMessage(ResponseCode.SUCCESS, ResponseStatus.STATUS_MESSAGE.get(ResponseCode.SUCCESS), districList);
		}
		return new ResponseMessage(ResponseCode.DB_ACCESS_ERROR, ResponseStatus.STATUS_MESSAGE.get(ResponseCode.DB_ACCESS_ERROR), null);
	}
	
	public ResponseMessage getAllTown() {
		List<TbProvince> townList = this.locationBean.getAllTown();
		if(townList != null && !townList.isEmpty()) {
			return new ResponseMessage(ResponseCode.SUCCESS, ResponseStatus.STATUS_MESSAGE.get(ResponseCode.SUCCESS), townList);
		}
		return new ResponseMessage(ResponseCode.DB_ACCESS_ERROR, ResponseStatus.STATUS_MESSAGE.get(ResponseCode.DB_ACCESS_ERROR), null);
	}
	
	public ResponseMessage getAllRegion() {
		List<TbRegioni> regionList = this.locationBean.getAllRegion();
		if(regionList != null && !regionList.isEmpty()) {
			return new ResponseMessage(ResponseCode.SUCCESS, ResponseStatus.STATUS_MESSAGE.get(ResponseCode.SUCCESS), regionList);
		}
		return new ResponseMessage(ResponseCode.DB_ACCESS_ERROR, ResponseStatus.STATUS_MESSAGE.get(ResponseCode.DB_ACCESS_ERROR), null);
	}
	
	public ResponseMessage getLocationString(int id) {
		String location = this.locationBean.getLocation(id);
		return new ResponseMessage(ResponseCode.SUCCESS, ResponseStatus.STATUS_MESSAGE.get(ResponseCode.SUCCESS), location);
	}

}

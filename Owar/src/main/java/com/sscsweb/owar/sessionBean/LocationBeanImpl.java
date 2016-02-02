package com.sscsweb.owar.sessionBean;

import java.rmi.RemoteException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map.Entry;

import javax.ejb.EJB;
import javax.ejb.EJBException;
import javax.ejb.SessionBean;
import javax.ejb.SessionContext;
import javax.ejb.Stateless;
import javax.persistence.PostLoad;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.sscsweb.owar.entities.TbComuni;
import com.sscsweb.owar.entities.TbProvince;
import com.sscsweb.owar.entities.TbRegioni;
import com.sscsweb.owar.jdbc.dao.TbComuniDAO;
import com.sscsweb.owar.jdbc.dao.TbProvinceDAO;
import com.sscsweb.owar.jdbc.dao.TbRegioniDAO;
import com.sscsweb.owar.utilities.ResponseCode;
import com.sscsweb.owar.utilities.ResponseMessage;

@Component
@EJB(name="java:module/LocationBeanImpl", beanInterface=LocationBean.class)
@Stateless
public class LocationBeanImpl implements LocationBean, SessionBean {

	/**
	 * 
	 */
	private static final long serialVersionUID = 134978586613758748L;

	@Autowired
	private TbComuniDAO tbComuniDAO;
	@Autowired
	private TbProvinceDAO tbProvinceDAO;
	@Autowired
	private TbRegioniDAO tbRegioniDAO;
	
	private HashMap<Integer, Object> districtMap = new HashMap<Integer, Object>();
	private HashMap<String, Object> regionMap = new HashMap<String, Object>();
	private HashMap<String, Object> townMap = new HashMap<String, Object>();
	
	public TbComuniDAO getTbComuniDAO() {
		return tbComuniDAO;
	}

	public void setTbComuniDAO(TbComuniDAO tbComuniDAO) {
		this.tbComuniDAO = tbComuniDAO;
	}

	public TbProvinceDAO getTbProvinceDAO() {
		return tbProvinceDAO;
	}

	public void setTbProvinceDAO(TbProvinceDAO tbProvinceDAO) {
		this.tbProvinceDAO = tbProvinceDAO;
	}

	public TbRegioniDAO getTbRegioniDAO() {
		return tbRegioniDAO;
	}

	public void setTbRegioniDAO(TbRegioniDAO tbRegioniDAO) {
		this.tbRegioniDAO = tbRegioniDAO;
	}

	public List<TbComuni> getAllDistrict() {
		checkInit();
		List<TbComuni> districtList = new LinkedList<TbComuni>();
		for(final Entry<Integer, Object> district: this.districtMap.entrySet()) {
			districtList.add((TbComuni) district.getValue());
		}
		return districtList;
	}
	
	public List<TbProvince> getAllTown() {
		checkInit();
		List<TbProvince> townList = new LinkedList<TbProvince>();
		for(final Entry<String, Object> town: this.townMap.entrySet()) {
			townList.add((TbProvince) town.getValue());
		}
		return townList;
	}
	
	public List<TbRegioni> getAllRegion() {
		checkInit();
		List<TbRegioni> regionList = new LinkedList<TbRegioni>();
		for(final Entry<String, Object> region: this.regionMap.entrySet()) {
			regionList.add((TbRegioni) region.getValue());
		}
		return regionList;
	}
	
	public TbProvince getTown(String districtCode) {
		checkInit();
		return (TbProvince) this.townMap.get(districtCode);
	}
	
	public TbRegioni getRegion(String regionCode) {
		checkInit();
		return (TbRegioni) this.regionMap.get(regionCode);
	}
	
	public void checkInit() {
		if(this.districtMap  == null || this.districtMap.isEmpty()) {
			initialize();
		}
	}
	
	public void initialize() {
		ResponseMessage responseMessage = this.tbComuniDAO.getAllDistrict();
		if(responseMessage.getResponseCode() == ResponseCode.SUCCESS) {
			List<TbComuni> responseObject = (List<TbComuni>) responseMessage.getResponseObject();
			for(final TbComuni district: responseObject) {
				districtMap.put(district.getId(), district);
			}
		}
		
		responseMessage = this.tbProvinceDAO.getAllTown();
		if(responseMessage.getResponseCode() == ResponseCode.SUCCESS) {
			List<TbProvince> responseObject = (List<TbProvince>) responseMessage.getResponseObject();
			for(final TbProvince town: responseObject) {
				townMap.put(town.getSigla_provincia(), town);
			}
		}
		
		responseMessage = this.tbRegioniDAO.getAllRegion();
		if(responseMessage.getResponseCode() == ResponseCode.SUCCESS) {
			List<TbRegioni> responseObject = (List<TbRegioni>) responseMessage.getResponseObject();
			for(final TbRegioni region: responseObject) {
				regionMap.put(region.getCodiceistat_regione(), region);
			}
		}
	}
	
	public String getLocation(int id) {
		checkInit();
		TbComuni tbComuni = (TbComuni) this.districtMap.get(id);
		TbProvince tbProvince = (TbProvince) this.townMap.get(tbComuni.getSiglaprovincia_comune());
		TbRegioni tbRegioni = (TbRegioni) this.regionMap.get(tbProvince.getCodiceistatregione_provincia());
		return tbComuni.getNome_comune() + " (" + tbProvince.getSigla_provincia() + ") " + tbRegioni.getNome_regione();
	}
	
	public List<Integer> getDistrictFromTown(String sigla_provincia) {
		checkInit();
		List<Integer> districtList = new LinkedList<Integer>();
		for(Entry<Integer, Object> entry: districtMap.entrySet()) {
			TbComuni district = (TbComuni) entry.getValue();
			if(district.getSiglaprovincia_comune().equalsIgnoreCase(sigla_provincia)) {
				districtList.add(district.getId());
			}
		}
		return districtList;
	}
	
	public List<Integer> getDistrictFromRegion(String codice_istat_regione) {
		checkInit();
		List<Integer> districtList = new LinkedList<Integer>();
		List<TbProvince> townList = new LinkedList<TbProvince>();
		for(Entry<String, Object> entry: townMap.entrySet()) {
			TbProvince town = (TbProvince) entry.getValue();
			if(town.getCodiceistatregione_provincia().equalsIgnoreCase(codice_istat_regione)) {
				townList.add(town);
			}
		}
		for(TbProvince town: townList) {
			for(Entry<Integer, Object> entry: districtMap.entrySet()) {
				TbComuni district = (TbComuni) entry.getValue();
				if(district.getSiglaprovincia_comune().equalsIgnoreCase(town.getSigla_provincia())) {
					districtList.add(district.getId());
				}
			}
		}
		return districtList;
	}

	public void setSessionContext(SessionContext ctx) throws EJBException,
			RemoteException {
		// TODO Auto-generated method stub
		
	}

	public void ejbRemove() throws EJBException, RemoteException {
		// TODO Auto-generated method stub
		
	}
	
	public void ejbActivate() throws EJBException, RemoteException {
		// TODO Auto-generated method stub
		
	}

	public void ejbPassivate() throws EJBException, RemoteException {
		// TODO Auto-generated method stub
		
	}

}

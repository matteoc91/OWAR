package com.sscsweb.owar.sessionBean;

import java.util.List;

import javax.ejb.Local;

import com.sscsweb.owar.entities.TbComuni;
import com.sscsweb.owar.entities.TbProvince;
import com.sscsweb.owar.entities.TbRegioni;

@Local
public interface LocationBean {

	public List<TbComuni> getAllDistrict();
	public List<TbProvince> getAllTown();
	public List<TbRegioni> getAllRegion();
	public TbProvince getTown(String districtCode);
	public TbRegioni getRegion(String regionCode);
	public String getLocation(int id);
	public List<Integer> getDistrictFromTown(String sigla_provincia);
	public List<Integer> getDistrictFromRegion(String codice_istat_regione);
	
}

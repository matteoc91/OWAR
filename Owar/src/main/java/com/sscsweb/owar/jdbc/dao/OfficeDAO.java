package com.sscsweb.owar.jdbc.dao;

import java.util.List;

import com.sscsweb.owar.entities.Office;
import com.sscsweb.owar.utilities.ResponseMessage;

public interface OfficeDAO {
	
	public ResponseMessage getCount();
	public ResponseMessage getOffices(Integer offset, Integer limit, List<Integer> districtList, List<Integer> officeIdList);
	public ResponseMessage addOffice(Office office);
	public ResponseMessage getOffice(Integer officeId);
	public ResponseMessage reserveOffice(Integer officeId, Integer reserveStatus);
	public ResponseMessage getOfficeFromLessorId(Integer lessorId);

}

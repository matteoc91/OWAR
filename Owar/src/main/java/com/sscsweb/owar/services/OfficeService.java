package com.sscsweb.owar.services;

import java.util.List;

import com.sscsweb.owar.entities.Feedback;
import com.sscsweb.owar.entities.Office;
import com.sscsweb.owar.entities.OfficeHasService;
import com.sscsweb.owar.entities.OfficeHasTenant;
import com.sscsweb.owar.entities.OfficeImg;
import com.sscsweb.owar.entities.Service;
import com.sscsweb.owar.utilities.ResponseMessage;

public interface OfficeService {

	public ResponseMessage getCount();
	public ResponseMessage getOffices(Integer offset, Integer limit, Integer tbComuni_id, String sigla_provincia, 
			String codice_istat_regione, List<OfficeHasService> officeHasServiceList);
	public ResponseMessage addOffice(Office office);
	public ResponseMessage getOffice(Integer officeId);
	public ResponseMessage getServices(Integer officeId);
	public ResponseMessage createService(Service service);
	public ResponseMessage addMapOfficeService(OfficeHasService officeHasService);
	public ResponseMessage getAllService();
	public ResponseMessage addImage(OfficeImg officeImg);
	public ResponseMessage getImages(Integer officeId);
	public ResponseMessage getImage(Integer imageId);
	public ResponseMessage getQRCode(Integer officeId, Integer tenantId, String dateBegin, String dateEnd);
	public ResponseMessage addFeedback(Feedback feedback);
	public ResponseMessage getFeedback(Integer officeId, Integer limit);
	public ResponseMessage rentOffice(OfficeHasTenant officeHasTenant);
	public ResponseMessage getTenantOffices(Integer tenantId);
	public ResponseMessage reserveOffice(Integer officeId, Integer reserveStatus);
	public ResponseMessage sendConfirmationMail(Integer officeId);
	public ResponseMessage getOfficeFromLessorId(Integer lessorId);
	public ResponseMessage getTenantFromOfficeRenting();
	public ResponseMessage getTenantObjectList(List<Integer> officeIdList);
	
}
